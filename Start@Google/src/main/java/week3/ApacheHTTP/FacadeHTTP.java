package week3.ApacheHTTP;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class FacadeHTTP {

    ApacheHTTP apache = new ApacheHTTP();

    public Response fetch(String url, Methods method, Map<String, String> body) throws IOException {

        HttpResponse response = apache.fetch(url, method, body);
        int code = response.getStatusLine().getStatusCode();
        if (code < 205 && code >= 200) return Response.createSuccessResponse(code, method);

        String message = response.getStatusLine().getReasonPhrase();
        return Response.createErrorResponse(code, message, method);
    }
}
