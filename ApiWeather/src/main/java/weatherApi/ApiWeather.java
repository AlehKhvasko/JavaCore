package weatherApi;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ApiWeather {
    //http://dataservice.accuweather.com/currentconditions/v1/topcities/
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("topcities")
                .addPathSegment("50")
                .addQueryParameter("apikey", "MTmCyiM2DsHIkr5YYqWU5028EEa9r4hI")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

    }


}
