package br.com.conversormoedas.main;

import br.com.conversormoedas.http.ExchangeRate;
import br.com.conversormoedas.records.ConversionRates;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import static com.google.gson.JsonParser.parseString;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExchangeRate exchangeRate = new ExchangeRate();
        var result = exchangeRate.search("USD");

        JsonObject object = JsonParser.parseString(result).getAsJsonObject();
        var conversionRates = object.get("conversion_rates");

        Gson gson = new Gson();

        ConversionRates conversionRatesJson = gson.fromJson(conversionRates, ConversionRates.class);

        System.out.println(conversionRatesJson);
    }
}
