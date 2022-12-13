
package utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JourneyDto;
import okhttp3.*;
import org.codehaus.plexus.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class HttpUtils {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static HashMap<String, String> getEmission(JourneyDto.TripDto tripDto) {


        HashMap<String, String> values = new HashMap<>();
        values.put("distance", tripDto.getDistance().toString());
        values.put("vehicle", tripDto.getTransportation().getName());


        return values;
    }

    public static JourneyDto.TripDto sendPost(JourneyDto.TripDto tripDto) throws Exception {

        String distance = tripDto.getDistance().toString();
        String vehicle = tripDto.getTransportation().getName();

        RequestBody formBody = new FormBody.Builder()
                .add("distance", distance)
                .add("vehicle", vehicle)
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()

                .url("https://app.trycarbonapi.com/api/carTravel")
                .method("POST", body)
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg")
                .addHeader("Cookie", "Cookie_1=value")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());



        }

        return tripDto;
    }

    public static String fetchAPIData(String _url, String apiKey) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_url))
                .GET()
                .header("Accept", "application/json")
                .header("User-Agent", "server")
                .header(apiKey != null ? "app-id" : "null", apiKey != null ? apiKey : "null")
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }

    }

    public static void main(String[] args) throws Exception {

        HttpUtils instance = new HttpUtils();
        JourneyDto.TripDto.TransportationDto transportationDto = new JourneyDto.TripDto.TransportationDto(1, "SmallDieselCar");
        JourneyDto.TripDto tripDto=  new JourneyDto.TripDto(1000f,transportationDto);

        System.out.println("Testing 1 - Send Http POST request");
        instance.sendPost(tripDto);

    }
}
