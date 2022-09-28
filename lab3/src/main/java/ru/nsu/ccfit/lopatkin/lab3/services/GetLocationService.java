package ru.nsu.ccfit.lopatkin.lab3.services;

import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class GetLocationService implements Runnable{
    private final VBox resultsVBox;

    private final String apiKey = "373223bc-1d18-4473-95f2-959326698116";

    public GetLocationService(VBox resultsVBox) {
        this.resultsVBox = resultsVBox;
    }

    public void getLocation(String location) {



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/geocode?q=" + location + "&key=" + apiKey )
                .get()
                .build();


        try {

            Response response = client.newCall(request).execute();
            //System.out.println(response.body().string());
            JsonObject jobj = new Gson().fromJson(response.body().string(), JsonObject.class);
            //String fieldValue = jobj.get(fieldName).getAsString();
            System.out.println(jobj.toString());
            JsonArray hits = jobj.getAsJsonArray("hits");
            System.out.println(hits.toString());
            //resultsVBox.getChildren().clear();
            for (JsonElement tmp : hits) {
                System.out.println(tmp.toString());
                JsonObject object = (JsonObject) tmp;
                System.out.println(object.getAsJsonObject("point").toString());
                resultsVBox.getChildren().add(new Button(object.get("name").toString()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addResultsOnScreen() {}

    @Override
    public void run() {

    }
}
