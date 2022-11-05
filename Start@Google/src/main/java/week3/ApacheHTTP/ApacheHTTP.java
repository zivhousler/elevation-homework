package week3.ApacheHTTP;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApacheHTTP {

    public HttpResponse fetch(String url, Methods method, Map<String, String> body) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse;
        List<NameValuePair> params = new ArrayList<>();

        if (body != null) {
            for (Map.Entry entry : body.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
            }
        }

        try {
            switch (method) {
                case POST:
                    HttpPost httppost = new HttpPost(url);
                    httppost.setEntity(new UrlEncodedFormEntity(params));
                    httpResponse = client.execute(httppost);
                    break;

                case PUT:
                    HttpPut httpput = new HttpPut(url);
                    httpput.setEntity(new UrlEncodedFormEntity(params));
                    httpResponse = client.execute(httpput);
                    break;

                case PATCH:
                    HttpPatch httppatch = new HttpPatch(url);
                    httppatch.setEntity(new UrlEncodedFormEntity(params));
                    httpResponse = client.execute(httppatch);
                    break;

                case DELETE:
                    HttpDelete httpdelete = new HttpDelete(url);
                    httpResponse = client.execute(httpdelete);
                    break;

                case GET:
                    HttpGet httpget = new HttpGet(url);
                    httpResponse = client.execute(httpget);
                    break;

                default:
                    throw new IllegalArgumentException("Such action was not defiled: " + method);
            }

        } catch (UnknownHostException e) {
            client.close();
            throw new UnknownHostException(e.toString());
        }

        return httpResponse;
    }
}
