package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarTravelDTO;
import dtos.EmissionDto;
import dtos.JourneyDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class HttpUtils {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

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

    public static JourneyDto.TripDto getEmission(JourneyDto.TripDto tripDto) throws IOException {

        Random random = new Random();

        /*String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg";

        URL url = new URL("https://app.trycarbonapi.com/api/carTravel");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization",apiKey);
        con.setDoOutput(true);

        CarTravelDTO carTravelDTO = new CarTravelDTO(tripDto.getDistance(), tripDto.getTransportation().getName());
        String jsonobj = GSON.toJson(carTravelDTO);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonobj.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            return GSON.fromJson(response.toString(), EmissionDto.class);
        }*/

            tripDto.setEmission(random.nextFloat());


        return tripDto;
    }
}
