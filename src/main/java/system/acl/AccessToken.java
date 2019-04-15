package system.acl;

import kinds.User;
import system.Data;
import system.fields.HasId;
import utils.Commons;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class AccessToken {
    public static final String KNOWTREE_SALT_ENV = "knowtree_salt";
    Long userId;
    Date expireDate;
    String hash;
    public AccessToken(Long userId, Date expireDate) throws NoSuchAlgorithmException {
        this.userId = userId;
        this.expireDate = expireDate;
        hash = calculateHash();
    }

    public AccessToken(String msg) {
        String[] args = msg.split("-");
        if (args.length == 3) {
            this.userId = Long.valueOf(args[0]);
            this.expireDate = new Date(Long.valueOf(args[1]));
            this.hash = args[2];
        }
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        if (hash != null) {
            String expectHash = calculateHash();
            return expectHash.equals(hash);

        } else {
            return false;
        }
    }

    public Data loadUser() throws Exception {
        User user = new User();
        user.set(HasId.id, userId);
        user.retrieve(null);
        return user;
    }

    private String calculateHash() throws NoSuchAlgorithmException {
        String salt = System.getenv(KNOWTREE_SALT_ENV);
        String msg = String.format("%s-%s-%s", userId, expireDate.getTime(), salt);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(msg.getBytes(StandardCharsets.UTF_8));

        return Commons.byteToHex(hashInBytes);
    }
}
