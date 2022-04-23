package model;

import java.awt.image.WritableRenderedImage;
import java.io.IOException;
import java.util.List;
import model.layout.GameStatus;

/**
 * An interface representing one of the possibilities of a World for the Game.
 */
public interface Model extends ReadOnlyModel {
  /**
   * Returns if a computer player is in turn.
   * @return true if computer player else false.
   */
  boolean isComputerInTurn();

  /**
   * Returns the information of specific space in the world like, the name of the space,
   * the items in the space and the neighbours of the space.
   *
   * @param name the name of the space
   * @return the details of the space.
   */
  String getInfoOfSpace(String name);

  /**
   * Returns the neighbours of a given space in the world,
   * or null if the space doesn't have any neighbours.
   *
   * @param name the name of the space
   * @return the list of spaces which can be viewed from a given space
   * @throws IllegalArgumentException - if space is not in the world
   */
  String getNeighboursOf(String name);

  /**
   * Adds a manually controlled player to the game to the specified space.
   *
   * @param name  the name of the player
   * @param space the starting position of the player in space
   * @param limit the max items the player can carry
   * @return the details of the addition of the player
   * @throws IllegalArgumentException if space is not present in the world
   */
  String addPlayer(String name, String space, int limit);

  /**
   * Add a computer player with the default name of Computer(id of player).
   * The player is added to the first space by default.
   * @return the details of the addition of the computer player
   */
  String addComputerPlayer();

  /**
   * Moves a player to the chosen neighbouring space from the current space.
   *
   * @param space the space to move into
   * @return the details of the move of a player to a space
   */
  String move(String space);

  /**
   * Moves a player to the chosen neighbouring space from the current space.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the details of the move of a player to the space.
   */
  String move(int x, int y);

  /**
   * Moves a pet to the chosen space from the current space.
   *
   * @param space the space to move into
   * @return the details of operation
   */
  String movePet(String space);



  /**
   * Return the details of the current space and neighbouring spaces.
   *
   * @return the details of the current space
   */
  String lookAround();

  /**
   * Return the details of a given player in the game including the items one is carrying.
   *
   * @param name the name of the player
   * @return the details of the player
   */
  String displayPlayerDescription(String name);

  /**
   * Return the details of the player currently in turn including the items one is carrying.
   *
   * @return the details of the player
   */
  String displayPlayerDescription();

  /**
   * Removes an item from the current space the player is in and adds it to the players item
   * inventory.
   *
   * @param item the name of the item
   * @return the details of the pickup
   */
  String pickUpItem(String item);

  /**
   * Returns the neighbours of the current players space.
   * @return the list of neighbours
   */
  String getNeighboursOfPlayerCurrentSpace();

  /**
   * Returns the list of items in the current player's space as a string.
   * @return the list of items
   */
  List<String> getItemsInCurrentSpace();

  /**
   * Returns the list if items in the current player's bag.
   * @return the list of items
   */
  List<String> getItemsOfPlayerInTurn();

  /**
   * Returns all the spaces in the world.
   * @return the list of spaces
   */
  List<String> getAllSpaces();

  /**
   * Returns the number of normal players added to the game.
   * @return the count of normal players
   */
  int getTotalNumberOfHumanPlayers();

  /**
   * Returns a boolean of whether the game has been completed.
   * @return true if max turns are completed or target has been killed else false
   */
  boolean isGameOver();

  /**
   * Return the winner of the game if a player has killed the target.
   * Game is tied if there is no winner.
   * @return the winner
   */
  String getWinner();

  /**
   * Attacks the target by using the item chosen.
   * @param itemName the item
   * @return the details of the attack
   */
  String attack(String itemName);

  /**
   * Attacks the target by poking in the eye.
   * @return the details of the attack
   */
  String attack();

  /**
   * Re-initializes the world data using the provided configuration file.
   * @param r the readable of the config file.
   */
  void reInitializeGame(Readable r);

  /**
   * Re-initializes the world data using default game layout file.
   */
  void reInitializeGame();

  /**
   * Return if the game is in progress.
   * @return true if game is inprogress else false
   */
  boolean isGameInProgress();
}
