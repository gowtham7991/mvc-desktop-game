package controller.commands;

import model.Model;
import view.View;

/**
 * This is the interface for all the commands.
 *
 */
public interface Command {

  /**
   * This allows the command to be executed.
   * @param m the valid model
   * @param v the valid model
   */
  void execute(Model m, View v);
}
