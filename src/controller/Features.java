package controller;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */
public interface Features {
  /**
   * Start the game by moving to the player addition screen.
   */
  void startGame();

  /**
   * Exit the game.
   */
  void exit();

  /**
   * Begin the game after all the players are added to the game and boots the game board.
   */
  void begin();

  /**
   * Restarts the game by the displaying the start screen.
   */
  void restart();

  /**
   * Pickup an item from the list of items in the current space.
   */
  void pickUpItem();

  /**
   * Add a computer player to the game.
   */
  void addComputerPlayer();

  /**
   * Add a manual player to the game.
   */
  void addPlayer();

  /**
   * Move the pet to the specified space.
   */
  void movePet();

  /**
   * Attack the target using an item currently in player's bag.
   */
  void attack();

  /**
   * Look around the current space occupied by the player.
   */
  void lookAround();

  /**
   * Handles the upload of new game config file.
   */
  void handleGameFileUpload();

  /**
   * Handles the moue click of the game board.
   * @param x the x-coordinate of the game board
   * @param y the y-coordinate of the game board
   */
  void handleMouseClick(int x, int y);
}
