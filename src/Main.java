//import controller.GameConsoleController;
//import controller.GameController;
import controller.Controller;
import controller.ControllerImpl;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import model.Model;
import model.ModelImpl;
import utils.RandomGeneratorImpl;
import view.View;
import view.ViewImpl;
import view.screens.GameScreenImpl;
import view.screens.Screen;
import view.screens.SplashScreen;

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
        Controller controller = new ControllerImpl(model);
        View view = new ViewImpl(model);
        controller.setView(view);
//        model.addPlayer("Player1", "Billiard Room", 5);
//        model.addPlayer("Player2", "Billiard Room", 5);
//        model.addPlayer("Player3", "Billiard Room", 5);
//        model.addPlayer("Player4", "Billiard Room", 5);
//        model.addPlayer("Player5", "Billiard Room", 5);
//        model.addPlayer("Player6", "Billiard Room", 5);
//        model.addPlayer("Player7", "Billiard Room", 5);
//        model.addPlayer("Player8", "Billiard Room", 5);
//        Screen screen = new GameScreenImpl(model);
//        File outputfile = new File("image.jpg");
//        ImageIO.write(model.createGraphicalRepresentation(), "jpg", outputfile);
//        new SplashScreen();
//        GameController controller = new GameConsoleController(in, System.out);
//        controller.start(model);
      } catch (IOException ioe) {
        throw new IllegalArgumentException("File not found!");
      }
    }
  }
}
