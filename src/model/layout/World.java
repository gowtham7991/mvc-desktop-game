package model.layout;

import java.awt.image.WritableRenderedImage;
import java.util.List;
import java.util.Set;

/**
 * A world is one of the possible layout of a game. The world comprises a number of spaces.
 * Every world has a target.
 */
public interface World {

  /**
   * Returns the neighbours of a given space in the world,
   * or null if the space doesn't have any neighbours.
   *
   * @param name the name of the space
   * @return the list of spaces which can be viewed from a given space
   * @throws IllegalArgumentException - if space is not in the world.
   */
  String getNeighboursOf(String name);

  /**
   * Returns the information of specific space in the world like, the name of the space,
   * the items in the space and the neighbours of the space.
   *
   * @param name the name of the space
   * @return the details of the space.
   * @throws IllegalArgumentException - if space is not in the world.
   */
  String getInfoOfSpace(String name);

  /**
   * Creates the graphical representation of the world.
   * @return the buffered image of the world which has spaces.
   */
  WritableRenderedImage getBufferedImage();

  /**
   * Moves the target from the current position.
   */
  void moveTarget();

  /**
   * Returns the current position of the target.
   *
   * @return the current position (space index) of target
   */
  int getTargetPosition();

  /**
   * Returns the total no of spaces in the world.
   * @return the count of spaces
   */
  int getTotalNumberOfSpaces();

  /**
   * Returns the total no of items in the world.
   * @return the count of items
   */
  int getTotalNumberOfItems();

  /**
   * Returns the name of the world.
   * @return the name
   */
  String getName();

  /**
   * Adds a manually controlled player to the game to the specified space.
   * @param name the name of the player
   * @param space the starting position of the player in space
   * @throws IllegalArgumentException if space is not present in the world
   */
  String addPlayer(String name, String space);


  /**
   * Add a computer player with the default name of Computer(id of player).
   * The player is added to the first space by default.
   */
  String addComputerPlayer();

  /**
   * Moves a player to the chosen neighbouring space from the current space.
   * @param space the space to move into
   */
  String move(String space);

  /**
   * Returns the name of the player currently in turn
   * @return the name of the player
   */
  String getTurn();

  /**
   * Return the details of the current space and neighbouring spaces.
   * @return
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
   * Picks up an item for the player in turn from the current space the player is in.
   * @param item the name of the item
   */
  String pickUpItem(String item);

  /**
   * Returns the neighbours of a space the player currently in space.
   * @return the neighbours of current space
   */
  String getNeighboursOfPlayerCurrentSpace();

  /**
   * Returns the items in the current space of a player currently is in.
   * @return the items in a space
   */
  String getItemsInCurrentSpace();

  String getPlayers();

  int getTotalNumberOfPlayers();
}
