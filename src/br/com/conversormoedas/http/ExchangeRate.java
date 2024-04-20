package br.com.conversormoedas.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRate {

    public String search(String currencyCode) throws IOException, InterruptedException {
        var uri = "https://v6.exchangerate-api.com/v6/90531406d25c1ea1ff77263b/latest/"  + currencyCode;
        URI url = URI.create(uri);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //TODO manipular exceptions

        return response.body();
    }
}
