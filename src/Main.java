//import controller.GameConsoleController;
//import controller.GameController;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import model.Model;
import model.ModelImpl;
import utils.RandomGeneratorImpl;

/**
 * The driver function which instantiates the model, controller
 * and hands over the execution of the game to the controller.
 */
public class Main {
  /**
   * The main function which runs when the game begins.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("File name not provided!");
    } else {
      try {
        String configFilePath = args[0];
        int turnsPerGame = Integer.parseInt(args[1]);
        Readable configFile = new FileReader(configFilePath);
        Readable in = new InputStreamReader(System.in);
        RandomGeneratorImpl rand = new RandomGeneratorImpl();
        Model model = new ModelImpl(configFile, rand, turnsPerGame);

        File outputfile = new File("image.jpg");
        ImageIO.write(model.createGraphicalRepresentation(), "jpg", outputfile);
//        GameController controller = new GameConsoleController(in, System.out);
//        controller.start(model);
      } catch (IOException ioe) {
        throw new IllegalArgumentException("File not found!");
      }
    }
  }
}
