package controller;

import model.game.Model;

public interface GameController {

  void start(Model model);

  interface Command {

    /**
     * Executes the given command on the model passed.
     * @param m the Model object
     * @return the response of the command
     */
    void execute(Model m);
  }
}
