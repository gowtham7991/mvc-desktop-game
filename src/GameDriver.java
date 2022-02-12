import game.Game;
import game.GameImpl;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 * Driver class which acts as intermediate controller to access the model.
 */
public class GameDriver {
  /**
   * The main class which controls the model.
   * @param args the config file path value from command line
   * @throws IOException - if the file is unreadable
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      throw new IllegalArgumentException("File name not provided!");
    } else {
      String configFilePath = args[0];
      Game game = new GameImpl(configFilePath);
      game.moveTarget();
      game.moveTarget();
      System.out.println(game.getTargetPosition());

      String str = game.getInfoOf("Lilac Room");
      System.out.println("The details the space: \n" + str);

      Set<String> neighbours = game.getNeighboursOf("Foyer");
      System.out.println("The neighbours: \n" + neighbours.toString());

      WritableRenderedImage img = game.createGraphicalRepresentation();
      File file = new File("World.png");
      ImageIO.write(img, "png", file);
    }
  }
}
