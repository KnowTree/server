package servlets;

import com.google.appengine.api.urlfetch.URLFetchServicePb;
import com.google.appengine.api.urlfetch.dev.LocalURLFetchService;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.mock.MockServer;
import org.mockserver.server.EmbeddedJettyRunner;
import org.mockserver.server.MockServerServlet;

import static org.mockserver.server.MockServerServlet.*;

import static org.junit.Assert.*;

public class AuthFilterTest {
    private final LocalServiceTestHelper helper
            = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


    @Before
    public void setUp() throws Exception {
        helper.setUp();

    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }
    @Test
    public void testSignUp() {

    }
}