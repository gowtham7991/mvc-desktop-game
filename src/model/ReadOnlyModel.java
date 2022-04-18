package model;

import java.awt.image.WritableRenderedImage;
import java.util.List;

public interface ReadOnlyModel {
  /**
   * Returns the name of the player currently in turn.
   *
   * @return the name of the current player in turn
   */
  String getTurn();

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
  WritableRenderedImage createGraphicalRepresentation(int width, int height);
}
