import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GerarStickers {
  /**
   * @throws IOException
   * 
   */
  public void creating(InputStream inputStream, String nameFile) throws Exception {

    // Leitura da imagem
    // InputStream inputStream = new
    // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_3.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // criar nova imagem em memória com transparencia
    int lagerImage = imagemOriginal.getWidth();
    int heightImage = imagemOriginal.getHeight();
    int newHeight = heightImage + 200;
    BufferedImage newImageCreated = new BufferedImage(lagerImage, newHeight, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) newImageCreated.getGraphics();
    graphics.drawImage(imagemOriginal, null, 0, 0);

    // Configurar Fonte
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(fonte);
    // escrever texto
    double calcLarge = lagerImage - (lagerImage / 2);
    int valorLarge = (int) calcLarge;
    graphics.drawString("INSANO!", valorLarge / 2, newHeight - 100);

    // escrever a nova imagem em um arquivo

    ImageIO.write(newImageCreated, "png", new File("saida/" + nameFile));

  }

  // public static void main(String[] args) throws Exception {
  // var generetedStickers = new GerarStickers();
  // generetedStickers.creating();
  // }
}
