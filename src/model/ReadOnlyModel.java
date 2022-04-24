package model;

import java.awt.image.WritableRenderedImage;
import java.util.List;

/**
 * This is a read only model which only supports operations to retrieve static data from the model
 * to the client.
 */
public interface ReadOnlyModel {
  /**
   * Returns the name of the player currently in turn.
   * @return the name
   */
  String playerInTurn();

  /**
   * Returns all the players in the game as a string.
   * @return the list of players
   */
  List<String> getPlayers();

  /**
   * Returns a set of clues for the player in turn to help progress in the game.
   * @return the clues
   */
  String getCluesForTurn();

  /**
   * Returns the name of the game.
   * @return the name
   */
  String getName();

  /**
   * Returns the current player's position.
   * @return the position of the player in turn
   */
  String getCurrentPlayerPosition();

  /**
   * Returns the graphical representation of the world with spaces.
   * @return the buffered image of the world
   */
  WritableRenderedImage createGraphicalRepresentation();

  /**
   * Returns the name of the space based on the co-ordinates.
   * @param x the X coordinate
   * @param y the Y coordinate
   * @return the space
   */
  String getSpaceBasedOnCoordinates(int x, int y);
}
