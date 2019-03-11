package acl;

import neo4j.Node;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class AccessToken {
    String userKey, expire_date, hash;
    final String DELEMITER = "-";
    final String GUEST = "Guest";
    public AccessToken(String token) {
        String[] eles = token.split(DELEMITER);
        if (eles.length != 3) {
            userKey = GUEST;
        } else {
            userKey = eles[0];
            expire_date = eles[1];
            hash = eles[2];
        }
    }

    public AccessToken withUser(Node user) {
        userKey = user.getKey();
        return this;
    }

    public AccessToken withExpireDate(Date date) {
        expire_date = String.valueOf(date.getTime());
        return this;
    }

    public Date getExpireDate() {
        return new Date(Long.valueOf(expire_date));
    }

    public Node getUser() {
        Node user = new Node(User.KIND);
        user.setKey(userKey);
        return user;
    }

    public String getAccessTokenString() throws NoSuchAlgorithmException {
        String raw = groupValueUp();
        return raw + DELEMITER + getHashFromString(raw);
    }

    private String getHashFromString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(s.getBytes());
        byte[] digest = messageDigest.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    private String groupValueUp() {
        return userKey +  DELEMITER + expire_date;
    }

    public boolean isGuest() {
        return userKey.equals(GUEST);
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        if (isGuest()) {
            return true;
        } else {
            String raw = groupValueUp();
            return raw.equals(getHashFromString(raw)) && getExpireDate().after(new Date());
        }
    }

}
