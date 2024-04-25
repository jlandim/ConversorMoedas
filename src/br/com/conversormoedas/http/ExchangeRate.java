package br.com.conversormoedas.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRate {

    private String key = "90531406d25c1ea1ff77263b";
    public String search(String currencyCode) throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/"+ this.key +"/latest/"  + currencyCode;
        URI url = URI.create(uri);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
