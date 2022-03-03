package model.game;

import java.awt.image.WritableRenderedImage;
import java.util.Set;

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
   * Moves the target character to the next possible space based on the index.
   * Returns to the 0 index if the target is at last possible space.
   */
  void moveTarget();

  /**
   * Return the current position of the target.
   *
   * @return the position of the target
   */
  int getTargetPosition();

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

  String getNeighboursOfPlayerCurrentSpace();

  String getItemsInCurrentSpace();

  String getPlayers();

  String getName();

  int getTotalNumberOfPlayers();
}
