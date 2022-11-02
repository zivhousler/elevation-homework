package week3.ApacheHTTP;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ApacheHTTP {

//    ApacheHTTP apache = new ApacheHTTP();

    public HttpResponse fetch(String url, Methods method, Map<String, String> body) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse httpresponse = null;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (body != null) {
            for (Map.Entry entry : body.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
            }
        }

        switch (method) {
            case POST:
//                System.out.println("You are creating a POST request");
                HttpPost httppost = new HttpPost(url);
                httppost.setEntity(new UrlEncodedFormEntity(params));
                httpresponse = client.execute(httppost);
                break;

            case PUT:
//                System.out.println("You are creating a PUT request");
                HttpPost httpput = new HttpPost(url);
                httpput.setEntity(new UrlEncodedFormEntity(params));
                httpresponse = client.execute(httpput);
                break;

            case PATCH:
//                System.out.println("You are creating a PATCH request");
                HttpPost httppatch = new HttpPost(url);
                httppatch.setEntity(new UrlEncodedFormEntity(params));
                httpresponse = client.execute(httppatch);
                break;

            case DELETE:
//                System.out.println("You are creating a DELETE request");
                HttpDelete httpdelete = new HttpDelete(url);
                httpresponse = client.execute(httpdelete);
                break;

            case GET:
//                System.out.println("You are creating a GET request");
                HttpGet httpget = new HttpGet(url);
                httpresponse = client.execute(httpget);
                break;

            default:
                throw new IllegalArgumentException("Such action was not defiled: " + method);

        }

        client.close();
        return httpresponse;
    }
}
