
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    private static final String chave = "d2c1214c64cf182843766e59";

    static double converteMoeda(String de, String para, double valor) {

        try {
            String endereco = "https://v6.exchangerate-api.com/v6/" + chave + "/latest/" + de;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            double converter = jsonObject.getAsJsonObject("conversion_rates").get(para).getAsDouble();
            return valor * converter;


        } catch (Exception e) {
            System.out.println("Falha ao obter taxa de câmbio: " + e.getMessage());
            return 0;
        }
    }
}
