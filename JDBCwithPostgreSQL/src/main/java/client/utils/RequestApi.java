package client.utils;
import com.squareup.okhttp.*;
import java.io.IOException;

public class RequestApi {
    public final String protocolVersion = "http";
    public final String version = "v1";
    public final String host = "dataservice.accuweather.com";

    public final String firstParameter;
    public final String secondParameter;
    public final String thirdParameter;
    public final Response response;
    private final String api = "w2EKSVBMOpVbMyHkQC30jv0PN9IalA5l";
    public String fourthParameter;

    public RequestApi(String firstParameter, String secondParameter,
                      String thirdParameter) throws IOException {
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.thirdParameter = thirdParameter;

        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(protocolVersion)
                .host(host)
                .addPathSegment(firstParameter)
                .addPathSegment(version)
                .addPathSegment(secondParameter)
                .addPathSegment(thirdParameter)
                .addQueryParameter("apikey", api)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        response = client.newCall(request).execute();
    }

    public RequestApi(String firstParameter, String secondParameter,
                      String thirdParameter, String fourthParameter) throws IOException {
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.thirdParameter = thirdParameter;
        this.fourthParameter = fourthParameter;

        OkHttpClient client = new OkHttpClient();
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(protocolVersion)
                .host(host)
                .addPathSegment(firstParameter)
                .addPathSegment(version)
                .addPathSegment(secondParameter)
                .addPathSegment(thirdParameter)
                .addPathSegment(fourthParameter)
                .addQueryParameter("apikey", api)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        response = client.newCall(request).execute();
    }

    public String getResponse() throws IOException {
        return response.body().string();
    }
}


