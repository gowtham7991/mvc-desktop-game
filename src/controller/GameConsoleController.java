package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

import controller.commands.CreateLayout;
import controller.commands.DisplayPlayerDescription;
import controller.commands.GetInfoOfSpace;
import controller.commands.LookAround;
import controller.commands.Move;
import controller.commands.PickUpItem;
import model.game.Model;
import model.game.ModelImpl;

public class GameConsoleController implements GameController{
  private final Scanner scan;
  private final Appendable out;
  private final int turns;
  private int noOfTurns;
  private Map<String, BiFunction<Scanner, Appendable, Command>> availableCommands = new HashMap<>();

  public GameConsoleController(Readable in, Appendable out, int turnsPerGame) {
    this.scan = new Scanner(in);
    this.out = out;
    this.turns = turnsPerGame;
    this.noOfTurns = 0;
  }

  @Override
  public void start(Model m) {
    if (!(m instanceof ModelImpl)) {
      throw new IllegalArgumentException("Model cannot be null object");
    }

    try {
      // Add commands in the available commands
      availableCommands.put("lookaround", (s, out) -> new LookAround(s, out));
      availableCommands.put("layout", (s, out) -> new CreateLayout(s, out));
      availableCommands.put("getinfo", (s, out) -> new GetInfoOfSpace(s, out));
      availableCommands.put("playerdesc", (s, out) -> new DisplayPlayerDescription(s, out));
      availableCommands.put("move", (s, out) -> new Move(s, out));
      availableCommands.put("pickup", (s, out) -> new PickUpItem(s, out));

      boolean isGameStarted = false;
      out.append("\n");
      out.append("### Welcome to ").append(m.getName()).append(" ###").append("\n\n");
      out.append("### Add Players ###\n");

      while (!isGameStarted) {
        out.append("Add more players (Y/N)?\n");
        String in1 = scan.nextLine().trim();
        if ("n".equalsIgnoreCase(in1)) {
          isGameStarted = true;
        }
        else {
          out.append("Is this a computer player (Y/N)?\n");
          String isComputerPlayer = scan.nextLine().trim();

          if ("y".equalsIgnoreCase(isComputerPlayer)) {
            String cmdResponse;
            boolean validExec = false;
            while (!validExec) {
              try {
                cmdResponse = m.addComputerPlayer();
                out.append(cmdResponse).append("\n");
                validExec = true;
              }
              catch (IllegalArgumentException e) {
                out.append("Could not add a player! Retry.\n");
              }
            }
          }
          else {
            String name;
            String spaceName;
            String cmdResponse;
            boolean validExec = false;
            while (!validExec) {
              try {
                out.append("Enter your name: \n");
                name = scan.nextLine();
                out.append("Enter the Space you wish to enter: \n");
                spaceName = scan.nextLine();
                cmdResponse = m.addPlayer(name, spaceName);
                out.append(cmdResponse).append("\n");
                validExec = true;
              }
              catch (IllegalArgumentException e) {
                out.append("Could not add a player! Retry.\n");
              }
            }
          }
        }
      }

      out.append("### Game has started ###\n\n");
      out.append("Players : \n");
      out.append(m.getPlayers());
      out.append("Available Commands - \n");
      out.append("layout - generate a layout of the game\n");
      out.append("playerdesc - Displays the description of a player\n");
      out.append("getinfo - Displays information about a space\n");
      out.append("lookaround - Displays the details of a specific space the player currently is in\n");
      out.append("move - Move to the neighbouring space\n");
      out.append("pickup - Pickup an item from the current space\n\n");

      while (noOfTurns < turns) {
        out.append(m.getTurn());
        Command c;
        String in = scan.nextLine();

        BiFunction<Scanner, Appendable, Command> cmd = availableCommands.getOrDefault(in, null);
        if (cmd == null) {
          out.append("Invalid command. Retry!\n");
        } else {
          c = cmd.apply(scan, out);
          c.execute(m);
          if ("move".equals(in) || "lookaround".equals(in) || "pickup".equals(in)) {
            noOfTurns += 1;
          }
        }
      }
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Append failed!");
    }
  }
}
