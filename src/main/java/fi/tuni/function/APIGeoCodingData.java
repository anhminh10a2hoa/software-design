package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fi.tuni.forecast.GeoCodingModel;

public class APIGeoCodingData {
    public static List<GeoCodingModel> fetchGeoCodingData(HttpResponse<String> geoCodingRequest) {
        List<GeoCodingModel> geoCodingData = new ArrayList<>();
        String responseBody = geoCodingRequest.body();
        JSONArray json = new JSONArray(responseBody);

        // Based on the comment above, extract cityName, country, latitude, longitude
        for (int i = 0; i < json.length(); i++) {
            JSONObject dataToJson = json.getJSONObject(i);
            String cityName = dataToJson.getString("name");
            String countryCode = dataToJson.getString("country");
            double latitude = dataToJson.getDouble("lat");
            double longitude = dataToJson.getDouble("lon");

            GeoCodingModel model = new GeoCodingModel(cityName, countryCode, latitude, longitude);
            geoCodingData.add(model);
        }
        return geoCodingData;
    }
}
