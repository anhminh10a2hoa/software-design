package fi.tuni.function;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import fi.tuni.airpollution.AirPollutionModel;

public class APIAirPollutionData {
    public static List<AirPollutionModel> fetchAirPollutionData(HttpResponse<String> response) {
        List<AirPollutionModel> airPollutionData = new ArrayList<AirPollutionModel>();
        String responseBody = response.body();
        JSONObject json = new JSONObject(responseBody);
        JSONArray listAirPollutionDataReturn = json.getJSONArray("list");
        for (int i = 0; i < listAirPollutionDataReturn.length(); i++) {
            AirPollutionModel airPollutionModel = new AirPollutionModel();
            JSONObject dataToJson = listAirPollutionDataReturn.getJSONObject(i);
            double coValue = dataToJson.getJSONObject("components").getDouble("co");
            double noValue = dataToJson.getJSONObject("components").getDouble("no");
            double no2Value = dataToJson.getJSONObject("components").getDouble("no2");
            double o3Value = dataToJson.getJSONObject("components").getDouble("o3");
            double so2Value = dataToJson.getJSONObject("components").getDouble("so2");
            double pm25Value = dataToJson.getJSONObject("components").getDouble("pm2_5");
            double pm10Value = dataToJson.getJSONObject("components").getDouble("pm10");
            double nh3Value = dataToJson.getJSONObject("components").getDouble("nh3");
            int dateTime = dataToJson.getInt("dt");
            airPollutionModel = new AirPollutionModel(coValue, noValue, no2Value, o3Value, so2Value, pm25Value, pm10Value, nh3Value);
            airPollutionModel.setDateTime(dateTime);
            airPollutionData.add(airPollutionModel);
        }
        return airPollutionData;
    }
}
