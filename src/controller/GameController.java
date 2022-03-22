package controller;

import model.Model;

/**
 * This is the game controller of the model.
 * The controller takes the input from the user and interacts with the model to make the game play
 * possible and display the relevant information to the user.
 */
public interface GameController {

  /**
   * Initiates the connection with the model when the model is passed as a parameter
   * to the controller.
   * @param model
   */
  void start(Model model);
}
