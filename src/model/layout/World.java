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
   * Returns the name of the player currently in turn.
   * @return the name
   */
  String playerInTurn();

  /**
   * Returns if a computer player is in turn.
   * @return true if computer player else false.
   */
  boolean isComputerInTurn();

  /**
   * Returns the neighbours of a given space in the world,
   * or null if the space doesn't have any neighbours.
   *
   * @param name the name of the space
   * @return the list of spaces which can be viewed from a given space
   * @throws IllegalArgumentException - if space is not in the world.
   */
  Set<String> getNeighboursOf(String name);

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
   *
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
  String getTargetPosition();

  /**
   * Returns the total no of spaces in the world.
   *
   * @return the count of spaces
   */
  int getTotalNumberOfSpaces();

  /**
   * Returns the total no of items in the world.
   *
   * @return the count of items
   */
  int getTotalNumberOfItems();

  /**
   * Returns the name of the world.
   *
   * @return the name
   */
  String getName();

  /**
   * Adds a manually controlled player to the game to the specified space.
   *
   * @param name  the name of the player
   * @param space the starting position of the player in space
   * @param limit the max items the player can carry
   * @return the response of the addition of player
   */
  String addPlayer(String name, String space, int limit);

  /**
   * Add a computer player with the default name of Computer(id of player).
   * The player is added to the first space by default.
   *
   * @return the response of the addition of computer player
   */
  String addComputerPlayer();

  /**
   * Moves a player to the chosen neighbouring space from the current space.
   *
   * @param space the space to move into
   * @return the response of the move of player
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
   * Return the details of the current space and neighbouring spaces.
   *
   * @return the details of the current space and neighbouring spaces of a player
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
   * Return the details of the player in turn including the items one is carrying.
   *
   * @return the details of the player
   */
  String displayPlayerDescription();

  /**
   * Picks up an item for the player in turn from the current space the player is in.
   *
   * @param item the name of the item
   * @return the details of the pick up item.
   */
  String pickUpItem(String item);

  /**
   * Returns the neighbours of a space the player currently in space.
   *
   * @return the neighbours of current space
   */
  String getNeighboursOfPlayerCurrentSpace();

  /**
   * Returns the list of items in the current space occupied by the player currently in turn.
   *
   * @return the items in a space as a string
   */
  List<String> getItemsInCurrentSpace();

  /**
   * Returns the list of items in the bag of player in turn.
   *
   * @return the list of items as a string
   */
  List<String> getItemsOfPlayerInTurn();

  /**
   * Returns all the players in the game as a string.
   *
   * @return the list of players
   */
  List<String> getPlayers();

  /**
   * Returns the number of normal players added to the game.
   *
   * @return the count of normal players
   */
  int getTotalNumberOfHumanPlayers();

  /**
   * Returns all the spaces in the world.
   *
   * @return the list of spaces
   */
  List<String> getSpaces();

  /**
   * Returns the clues for the player in turn.
   *
   * @return the list of clues
   */
  String getClues();

  /**
   * Moves the pet to the space specified by the player.
   *
   * @param spaceName the name of the space
   * @return the details of the movement.
   */
  String movePet(String spaceName);

  /**
   * Attacks the target by using the item chosen. The target's health is reduced by the damage
   * caused by the item. The item is removed from the player's inventory.
   *
   * @param itemName the item
   * @return the details of the attack
   */
  String attack(String itemName);

  /**
   * Attacks the target by poking in the eye. The target's health is reduced by 1.
   *
   * @return the details of the attack
   */
  String attack();

  /**
   * Returns the name of the player who killed the target.
   *
   * @return the player name or null if no winner
   */
  String getWinner();

  /**
   * Returns if the player A can see PlayerB.
   *
   * @param playerA one of the player
   * @param playerB second player
   * @return true both players can see each other else false
   */
  boolean playerCanSeeEachOther(String playerA, String playerB);

  /**
   * Returns the current location of the pet.
   *
   * @return the space name
   */
  String getPetLocation();

  /**
   * Returns the remaining health of the target.
   *
   * @return the health
   */
  int getTargetHealth();

  /**
   * Returns the name of the space based on the co-ordinates.
   * @param x the X coordinate
   * @param y the Y coordinate
   * @return the space
   */
  String getSpaceBasedOnCoordinates(int x, int y);

  /**
   * Returns the position of the current player in turn.
   * @return the space occupied by the player
   */
  String getCurrentPlayerPosition();
}
