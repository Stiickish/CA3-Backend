package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.CarTravelDTO;
import dtos.JourneyDto;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

import java.net.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiMjUzMGM4ZmIyMTdlYmJiYjg3ZjgwMDdjNDZjYTc5ODMwZjQxNzgzZDVhZTExNTUwMTA4ODdjMzY1NGRlMWNiNDI4YTc2ZGNmMjM3YWFlMGUiLCJpYXQiOjE2NjkzNzA5OTYsIm5iZiI6MTY2OTM3MDk5NiwiZXhwIjoxNzAwOTA2OTk2LCJzdWIiOiIyMzI0Iiwic2NvcGVzIjpbXX0.Ot63eEC6iCdCaea2TKX7DlMgvCpKGM8CfBuMSGivsTOUVerSUyQGUR-SA5e2-5ffN0ATmMavvFtK0f6SgCfETg";

        String distance = tripDto.getDistance().toString();
        String vehicle = tripDto.getTransportation().getName();
        HashMap<String, String> carbonMaster2000;


        performPostCall("https://app.trycarbonapi.com/api/carTravel", );

        }

       //CarTravelDTO carTravelDTO = new CarTravelDTO(tripDto.getDistance(), tripDto.getTransportation().getName());

        /*JsonObject responseJson = new JsonObject();
        responseJson.addProperty("distance",tripDto.getDistance());
        responseJson.addProperty("vehicle", tripDto.getTransportation().getName());*/





        return tripDto;
    }

    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams) throws MalformedURLException {

        URL url;
        String response = "";
        try {
            url=  new URL("https://app.trycarbonapi.com/api/carTravel");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String getPostDataString(HashMap<String, String> postDataParameters) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : postDataParameters.entrySet()) {
            if(first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}

