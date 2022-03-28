package controller;

import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controller.commands.Attack;
import controller.commands.CreateLayout;
import controller.commands.DisplayPlayerDescription;
import controller.commands.GetInfoOfSpace;
import controller.commands.LookAround;
import controller.commands.Move;
import controller.commands.MovePet;
import controller.commands.PickUpItem;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import model.Model;

/**
 * Concrete class of the game controller which creates the controller for the model.
 */
public class GameConsoleController implements GameController {
  private final Scanner scan;
  private final Appendable out;
  private final int turns;
  private final Map<String, BiFunction<Scanner, Appendable, Command>> gameConfigCommands;
  private final Map<String, BiFunction<Scanner, Appendable, Command>> gameExecutionCommands;
  private int noOfTurns;

  /**
   * Constructs the controller by taking a readable object,
   * appendable to write the data and max turns.
   * @param in the readable to read data
   * @param out the appendable to write the data
   * @param turnsPerGame the max turns of the game
   */
  public GameConsoleController(Readable in, Appendable out, int turnsPerGame) {

    if (in == null || out == null) {
      throw new IllegalArgumentException("Invalid values passed!");
    }

    this.scan = new Scanner(in);
    this.out = out;
    this.turns = turnsPerGame;
    this.noOfTurns = 0;
    this.gameConfigCommands = new HashMap<>();
    this.gameExecutionCommands = new HashMap<>();
  }

  @Override
  public void start(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Model cannot be null object");
    }

    try {
      // Add game config commands
      gameConfigCommands.put("addplayer", (s, out) -> new AddPlayer(s, out));
      gameConfigCommands.put("addcomputerplayer", (s, out) -> new AddComputerPlayer(s, out));

      // Add game commands in the game execution commands
      gameExecutionCommands.put("lookaround", (s, out) -> new LookAround(s, out));
      gameExecutionCommands.put("layout", (s, out) -> new CreateLayout(s, out));
      gameExecutionCommands.put("getinfo", (s, out) -> new GetInfoOfSpace(s, out));
      gameExecutionCommands.put("playerdesc", (s, out) -> new DisplayPlayerDescription(s, out));
      gameExecutionCommands.put("move", (s, out) -> new Move(s, out));
      gameExecutionCommands.put("pickup", (s, out) -> new PickUpItem(s, out));
      gameExecutionCommands.put("attack", (s, out) -> new Attack(s, out));
      gameExecutionCommands.put("movepet", (s, out) -> new MovePet(s, out));

      boolean isGameStarted = false;
      out.append("\n");
      out.append("### Welcome to ").append(m.getName()).append(" ###").append("\n\n");
      out.append("### Add Players ###\n\n");
      out.append("addplayer - to add a new human player\n");
      out.append("addcomputerplayer - to add a new computer player\n");
      out.append("start - to start the game\n");

      while (scan.hasNextLine()) {
        String in = scan.nextLine().trim();
        if ("start".equalsIgnoreCase(in)) {
          if (m.getTotalNumberOfHumanPlayers() > 0) {
            break;
          }
          out.append("Cannot start the game. Minimum no of players not added!\n");
        } else {
          Command c;
          BiFunction<Scanner, Appendable, Command> cmd = gameConfigCommands.getOrDefault(in, null);
          if (cmd == null) {
            out.append("Invalid command. Retry!\n");
          } else {
            c = cmd.apply(scan, out);
            c.execute(m);
          }
        }
      }

      out.append("### Game has started ###\n\n");
      out.append("Players : \n");
      out.append(m.getPlayers());
      out.append("--- Available Commands ---\n\n");
      out.append("layout - generate a layout of the game\n");
      out.append("playerdesc - Displays the description of a player\n");
      out.append("getinfo - Displays information about a space\n");
      out.append("lookaround - Displays the details of a ");
      out.append("specific space the player currently is in\n");
      out.append("move - Move to the neighbouring space\n");
      out.append("pickup - Pickup an item from the current space\n");
      out.append("attack - Attack the target using an item in possession\n");
      out.append("movepet - Move the pet to a space in the world\n");
      out.append("quit - quit the game\n\n");

      while (!m.isGameOver()) {
        out.append(m.getTurn());
        out.append(m.getCluesForTurn());
        out.append(">> Enter a command").append("\n");

        Command c;
        String in = scan.nextLine().trim();
        if ("quit".equals(in)) {
          break;
        }

        BiFunction<Scanner, Appendable, Command> cmd = gameExecutionCommands.getOrDefault(in, null);
        if (cmd == null) {
          out.append("Invalid command. Retry!\n");
        } else {
          c = cmd.apply(scan, out);
          c.execute(m);
        }
      }

      if (m.isGameOver()) {
        out.append(m.getWinner());
      } else {
        out.append("### User quit the game ###\n");
        out.append("GAME HAS ENDED!\n");
      }

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed!\n");
    }
  }
}
