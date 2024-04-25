package br.com.conversormoedas.main;

import br.com.conversormoedas.http.ExchangeRate;
import br.com.conversormoedas.records.ConversionRates;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.tools.jconsole.JConsoleContext;

import java.io.IOException;
import java.util.Scanner;

import static com.google.gson.JsonParser.parseString;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option = null;
        String currecyCode = null;
        double convertCode;

        while (true) {
            try {
                mainMenu();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Escolha uma opção válida: ");
                option = scanner.nextLine();

                if (option.equals("13")) {
                    System.out.println("Finalizando o programa!!!!!");
                    break;
                }

                convertCode = switch (option) {
                    case "1" -> getConversionRates("USD").ARS();
                    case "2" -> getConversionRates("ARS").USD();
                    case "3" -> getConversionRates("USD").BRL();
                    case "4" -> getConversionRates("BRL").USD();
                    case "5" -> getConversionRates("USD").COP();
                    case "6" -> getConversionRates("COP").USD();
                    case "7" -> getConversionRates("USD").CLP();
                    case "8" -> getConversionRates("CLP").USD();
                    case "9" -> getConversionRates("USD").EUR();
                    case "10" -> getConversionRates("EUR").USD();
                    case "11" -> getConversionRates("USD").JPY();
                    case "12" -> getConversionRates("JPY").USD();
                    default ->
                            throw new IllegalArgumentException("Opção invãlida para conversão. " +
                                    "Favor informar um opção do menu!!!!!!!");
                };

                System.out.println("Digite o valor que deseja converter: ");
                var moneyValue = Integer.parseInt(scanner.nextLine());

                var convertedValue = moneyValue * convertCode;

                System.out.println("Valor: " + moneyValue + " corresponde ao valor de: " + convertedValue);


            } catch (IllegalArgumentException | IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

        public static ConversionRates getConversionRates(String currecyCode) throws IOException, InterruptedException {
        ExchangeRate exchangeRate = new ExchangeRate();
        var result = exchangeRate.search(currecyCode);

        JsonObject object = parseString(result).getAsJsonObject();
        var conversionRates = object.get("conversion_rates");

        Gson gson = new Gson();

        return gson.fromJson(conversionRates, ConversionRates.class);
    }

    public static void mainMenu() {
        System.out.println("************************************************************************");
        System.out.println("Seja bem vindo(a) ao Conversor de moedas \n");

        var menu = "1) Dólar ==> Peso Argentino        2) Peso Argentino ==> Dólar \n" +
                "3) Dólar ==> Real Brasileiro      4) Real Brasileiro ==> Dólar \n" +
                "5) Dólar ==> Peso Colombiano      6) Peso Colombiano ==> Dólar \n" +
                "7) Dólar ==> Peso Chileno         8) Peso Chileno ==> Dólar \n" +
                "9) Dólar ==> Euro                 10) Euro ==> Dólar \n" +
                "11) Dólar ==> Yen Japones         12) Yen Japones ==> Dólar \n" +
                "13) Sair "
                ;

        System.out.println(menu);
    }


}
