package week3.ApacheHTTP;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.*;

public class Client {
    public static void main(String[] args) {

        FacadeHTTP facade = new FacadeHTTP();
        Response response;

        try {
            // create a get code
            response = facade.fetch("https://reqres.in/api/users/2", Methods.GET, null);
            System.out.println(response);

            // create a post code
            Map<String, String> bodyPost = new HashMap<>();
            bodyPost.put("name", "ziv");
            bodyPost.put("job", "none");
            response = facade.fetch("https://reqres.in/api/users", Methods.POST, bodyPost);
            System.out.println(response);

            // create a put code
            Map<String, String> bodyPut = new HashMap<>();
            bodyPut.put("name", "morpheus");
            bodyPut.put("job", "zion resident");
            response = facade.fetch("https://reqres.in/api/users/2", Methods.PUT, bodyPut);
            System.out.println(response);

            // create a delete code
            response = facade.fetch("https://reqres.in/api/users", Methods.DELETE, null);
            System.out.println(response);

            // create a patch code
            Map<String, String> bodyPatch = new HashMap<>();
            bodyPatch.put("string", "hey");
            response = facade.fetch("https://reqres.in/api/users/1", Methods.PATCH, bodyPatch);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
