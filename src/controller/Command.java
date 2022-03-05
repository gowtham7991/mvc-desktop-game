package controller;

import model.Model;

/**
 * Command class which the controller executes based on the input from the user.
 */
public interface Command {
  /**
   * Executes the given command on the model passed.
   *
   * @param m the Model object
   */
  void execute(Model m);
}
