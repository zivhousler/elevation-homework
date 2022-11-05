package week3.ApacheHTTP;

import org.apache.http.HttpResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class FacadeHTTP {

    ApacheHTTP apache = new ApacheHTTP();

    public Response fetch(String url, Methods method, Map<String, String> body) throws IOException {

        int code;
        try {
            HttpResponse response = apache.fetch(url, method, body);
            code = response.getStatusLine().getStatusCode();

            if (code >= 200 && code <= 205) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                String responseString = null;

                if (response.getEntity() != null) {
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                }

                out.close();
                return Response.createSuccessResponse(code, method, responseString);
            }
            String message = response.getStatusLine().getReasonPhrase();
            return Response.createErrorResponse(code, message, method);

        } catch (UnknownHostException e) {
            return Response.createErrorResponse(400, e.getMessage(), method);
        }
    }
}
