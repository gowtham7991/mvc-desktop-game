package game;

import java.awt.image.WritableRenderedImage;
import java.util.Set;

/**
 * An interface representing one of the possibilities of a World for the Game.
 */
public interface Game {
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
  String getInfoOf(String name);

  /**
   * Returns the neighbours of a given space in the world,
   * or null if the space doesn't have any neighbours.
   *
   * @param name the name of the space
   * @return the list of spaces which can be viewed from a given space
   * @throws IllegalArgumentException - if space is not in the world
   */
  Set<String> getNeighboursOf(String name);

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
}
