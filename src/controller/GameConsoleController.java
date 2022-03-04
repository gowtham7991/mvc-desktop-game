package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controller.commands.CreateLayout;
import controller.commands.DisplayPlayerDescription;
import controller.commands.GetInfoOfSpace;
import controller.commands.LookAround;
import controller.commands.Move;
import controller.commands.PickUpItem;
import controllertest.mocks.MockModel;
import model.Model;
import model.ModelImpl;

public class GameConsoleController implements GameController{
  private final Scanner scan;
  private final Appendable out;
  private final int turns;
  private int noOfTurns;
  private Map<String, BiFunction<Scanner, Appendable, Command>> gameConfigCommands = new HashMap<>();
  private Map<String, BiFunction<Scanner, Appendable, Command>> gameExecutionCommands = new HashMap<>();

  public GameConsoleController(Readable in, Appendable out, int turnsPerGame) {
    this.scan = new Scanner(in);
    this.out = out;
    this.turns = turnsPerGame;
    this.noOfTurns = 0;
  }

  @Override
  public void start(Model m) {
    if (!(m instanceof ModelImpl || m instanceof MockModel)) {
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

      boolean isGameStarted = false;
      out.append("\n");
      out.append("### Welcome to ").append(m.getName()).append(" ###").append("\n\n");
      out.append("### Add Players ###\n\n");
      out.append("addplayer - to add a new human player\n" +
              "addcomputerplayer - to add a new computer player\n" +
              "start - to start the game\n");
      while (scan.hasNextLine()) {
        String in = scan.nextLine().trim();
        if ("start".equalsIgnoreCase(in)) {
          if (m.getTotalNumberOfHumanPlayers() > 0) {
            break;
          }
          out.append("Cannot start the game. Minimum no of players not added!\n");
        }
        else {
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
      out.append("lookaround - Displays the details of a specific space the player currently is in\n");
      out.append("move - Move to the neighbouring space\n");
      out.append("pickup - Pickup an item from the current space\n");
      out.append("quit - quit the game\n\n");

      out.append(m.getTurn());
      while (scan.hasNextLine()) {
        out.append(m.getTurn());
        Command c;
        String in = scan.nextLine().trim();

        if (noOfTurns == turns) {
          break;
        }
        if ("quit".equals(in)) {
          break;
        }

        BiFunction<Scanner, Appendable, Command> cmd = gameExecutionCommands.getOrDefault(in, null);
        if (cmd == null) {
          out.append("Invalid command. Retry!\n");
        } else {
          c = cmd.apply(scan, out);
          c.execute(m);
          if ("move".equals(in) || "lookaround".equals(in) || "pickup".equals(in)) {
            noOfTurns += 1;
          }
        }

        out.append(m.getTurn());
      }

      if (noOfTurns == turns) {
        out.append("### Max turns in the reached ###\n");
        out.append("GAME HAS ENDED!\n");
      }
      else {
        out.append("### User quit the game ###\n");
        out.append("GAME HAS ENDED!\n");
      }
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Append failed!\n");
    }
  }
}
