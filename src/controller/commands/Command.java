package controller.commands;

import model.Model;
import view.View;

/**
 * This is the interface for all the commands.
 *
 */
public interface Command {

  void execute(Model m, View v);
}
