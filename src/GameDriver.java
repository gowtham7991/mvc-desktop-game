import model.Model;
import model.ModelImpl;
import utils.RandomGeneratorImpl;

import java.io.FileReader;
import java.io.IOException;

/**
 * Driver class which acts as intermediate controller to access the model.
 */
public class GameDriver {
  /**
   * The main class which controls the model.
   * @param args the config file path value from command line
   */
  public static void main(String[] args) {
      if (! (args.length == 0)) {
        throw new IllegalArgumentException("File name not provided!");
      } else {
        try {
//          String configFilePath = args[0];
//          int turnsPerGame = Integer.parseInt(args[1]);
          Readable configFile = new FileReader("res/mansion.txt");
          Model game = new ModelImpl(configFile, new RandomGeneratorImpl());
          game.addPlayer("Player1", "Drawing Room", 4);
          game.pickUpItem("Letter Opener");
          System.out.println(game.displayPlayerDescription("Player1"));
//          game.addPlayer("player1", "Drawing Room");
//          game.addPlayer("player2", "Drawing Room");
//          game.pickUpItem("Letter Opener");
//          game.move("Dining Hall");
//          game.move("Dining Hall");
//          System.out.println(game.getTurn());
//          System.out.println(game.getInfoOfSpace("Drawing Room"));
//          System.out.println(game.lookAround());
//          System.out.println(game.getTurn());
//
//          System.out.println(game.displayPlayerDescription("player1"));
//          game.moveTarget();
//          game.moveTarget();
//          System.out.println("Target Position: \n" + game.getTargetPosition());
//
//          String space1 = "Avengers Mansion";
//          String str = game.getInfoOfSpace(space1);
//          System.out.println("The details the space: \n" + str);
//
//          String space2 = "Titan";
//          String neighbours = game.getNeighboursOf(space2);
//          System.out.println("The neighbours of" + space2 + "\n" + neighbours.toString());
//
//          WritableRenderedImage img = game.createGraphicalRepresentation();
//          File file = new File("WorldRepresentation.png");
//          ImageIO.write(img, "png", file);
        }
        catch (IOException ioe) {
          throw new IllegalArgumentException("Invalid file provided!");
        }
    }
  }
}