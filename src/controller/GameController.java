package controller;

import model.Model;

/**
 * The game console controller of the world.
 * The controller takes the input from the user and communicates with model
 * and display the relevant information to the user.
 */
public interface GameController {

  void start(Model model);
}
