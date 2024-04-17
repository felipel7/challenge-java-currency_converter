package br.com.currencyconverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class CurrencyApiService {
    private final String baseURL = "https://v6.exchangerate-api.com/v6/0e686b33995e4c04e99ff43c/latest/";
    private final Gson gson = new Gson();

    public JsonObject getAllData(String baseCurrency) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseURL + baseCurrency)).build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofLines());
        String responseBody = response.body().collect(Collectors.joining());
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        client.close();

        return jsonObject.getAsJsonObject("conversion_rates");
    }

    public JsonElement getExchangeRate() throws IOException, InterruptedException {
        JsonObject jsonObject = this.getAllData(CurrencyOptionHandler.getBaseCurrency());
        return jsonObject.get(CurrencyOptionHandler.getTargetCurrency());
    }
}