import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class MyClient {
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8801";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String resp = Objects.requireNonNull(response.body()).string();
            System.out.println(resp);
        }
    }
}

