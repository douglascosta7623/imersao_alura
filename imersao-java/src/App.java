import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BACK_TITLE = "\u001B[46m";

    public static final String ANSI_TITLE_FONT = "\u001B[33m";
    public static final String ANSI_TITLE_FONT_RESET = "\u001B[0m";

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
        System.out.println("TOP 10 - Melhores Filmes");
        for (Map<String, String> movie : listFilms) {
            System.out.println(
                    ANSI_BACK_TITLE + movie.get("rank") + " ° " + "TÍTULO: " + movie.get("title") + ANSI_RESET);
            System.out.print("NOTA: " + movie.get("imDbRating") + " ");
            Double rating = Double.parseDouble(movie.get("imDbRating"));
            for (int count = 0; count <= rating; count++) {
                System.out.print(ANSI_TITLE_FONT + "🌟" + ANSI_TITLE_FONT_RESET);
            }
            System.out.println("");
            System.out.println("IMAGEM: " + movie.get("image"));
            String urlImage = movie.get("image");
            InputStream inputStream = new URL(urlImage).openStream();
            String nameFile = movie.get("title").trim().replaceAll("\\:+", "") + ".png";
            var generated = new GerarStickers();
            generated.creating(inputStream, nameFile);
        }

    }
}
