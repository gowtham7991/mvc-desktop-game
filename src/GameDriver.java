import jdk.swing.interop.SwingInterOpUtils;
import model.game.Model;
import model.game.ModelImpl;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.FileReader;
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
   */
  public static void main(String[] args) {
      if (args.length == 0) {
        throw new IllegalArgumentException("File name not provided!");
      } else {
        try {
          String configFilePath = args[0];
          Readable configFile = new FileReader(configFilePath);
          Model game = new ModelImpl(configFile);
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
        catch (IOException ioe) {
          System.out.println("Error reading the file!");
        }
    }
  }
}