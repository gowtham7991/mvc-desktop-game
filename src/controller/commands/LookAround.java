package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.GameController;
import model.game.Model;

public class LookAround implements GameController.Command {

  private final Scanner scan;
  private final Appendable out;


  public LookAround(Scanner scan, Appendable out) {
    if ( scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {

    try {
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          cmdResponse = m.lookAround();
          out.append(cmdResponse);
          validExec = true;
        }
        catch (IllegalArgumentException e) {
          out.append("Could not add a player! Retry.\n");
        }
      }

    }
    catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}
