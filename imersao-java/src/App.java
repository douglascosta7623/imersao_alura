import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // fazer conexão http e buscar os tops 250 filmes

        String url = "https://api.mocki.io/v2/549a5d8b";
        URI adrees = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adrees).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        System.out.println(body);

        // pegar só os dados que interessam (título, poster classificação);

        var parser = new JsonParser();

        List<Map<String, String>> listFilms = parser.parse(body);

        // exibir e manipular os dados

        // for(int i = 0; i < listFilms.size(); i++ ){
        // System.out.println(listFilms.get(key:));
        // }

        for (Map<String, String> movie : listFilms) {
            System.out.println(movie.get("title"));
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));

        }

    }
}
