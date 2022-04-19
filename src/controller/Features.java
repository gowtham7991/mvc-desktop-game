package controller;

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
   * Handles the moue click of the game board.
   * @param xCoordinate the x-coordinate of the game board
   * @param yCoordinate the y-coordinate of the game board
   */
  void handleMouseClick(int xCoordinate, int yCoordinate);
}
