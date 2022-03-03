import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.GameConsoleController;
import controller.GameController;
import model.game.Model;
import model.game.ModelImpl;
import model.layout.RandomGeneratorImpl;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("File name not provided!");
    }
    else {
      try {
        String configFilePath = args[0];
        int turnsPerGame = Integer.parseInt(args[1]);
        Readable configFile = new FileReader(configFilePath);
        Readable in = new InputStreamReader(System.in);
        Model model = new ModelImpl(configFile, new RandomGeneratorImpl());
        GameController controller = new GameConsoleController(in, System.out, turnsPerGame);
        controller.start(model);
      }
      catch (IOException ioe) {
        throw new IllegalArgumentException("File not found!");
      }
    }
  }
}
