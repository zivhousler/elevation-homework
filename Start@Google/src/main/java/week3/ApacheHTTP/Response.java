package week3.ApacheHTTP;

import utils.Converts;

import java.time.LocalDateTime;
import java.util.Date;

public class Response {

    private final int code;
    private final LocalDateTime date;
    private final Methods method;
    private String message;
    private String data;

    private Response(int code, LocalDateTime date, Methods method) {
        this.code = code;
        this.date = date;
        this.method = method;
    }

    public static Response createSuccessResponse(int code, Methods method, String data) {
        Response response = new Response(code, LocalDateTime.now(), method);
        response.setData(data);
        return response;
    }


    public static Response createErrorResponse(int code, String message, Methods method) {
        Response response = new Response(code, LocalDateTime.now(), method);
        response.setMessage(message);
        return response;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code= " + code +
                ", date= " + date +
                ", method= " + method +
                ", data= " + data +
                ", message= " + message + '\'' +
                '}';
    }
}
