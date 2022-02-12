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
      System.out.println("Target Position: \n" + game.getTargetPosition());

      String space1 = "Avengers Mansion";
      String str = game.getInfoOf(space1);
      System.out.println("The details the space: \n" + str);

      String space2 = "Titan";
      Set<String> neighbours = game.getNeighboursOf(space2);
      System.out.println("The neighbours of" + space2 + "\n" + neighbours.toString());

      WritableRenderedImage img = game.createGraphicalRepresentation();
      File file = new File("WorldRepresentation.png");
      ImageIO.write(img, "png", file);
    }
  }
}