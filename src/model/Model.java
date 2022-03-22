package model;

import java.awt.image.WritableRenderedImage;

/**
 * An interface representing one of the possibilities of a World for the Game.
 */
public interface Model {
  /**
   * Returns the graphical representation of the world with spaces.
   *
   * @return the buffered image of the world
   */
  WritableRenderedImage createGraphicalRepresentation();

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
   * Returns the name of the player currently in turn.
   *
   * @return the name of the current player in turn
   */
  String getTurn();

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
  String getItemsInCurrentSpace();

  /**
   * Returns all the players in the game as a string.
   * @return the list of players
   */
  String getPlayers();

  /**
   * Returns the name of the game.
   * @return the name
   */
  String getName();

  /**
   * Returns the number of normal players added to the game.
   * @return the count of normal players
   */
  int getTotalNumberOfHumanPlayers();

  /**
   * Returns all the spaces in the world.
   * @return the list of spaces
   */
  String getAllSpaces();
}
