package model.layout;

import java.awt.image.WritableRenderedImage;
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
  Set<String> getNeighboursOf(String name);

  /**
   * Returns the information of specific space in the world like, the name of the space,
   * the items in the space and the neighbours of the space.
   *
   * @param name the name of the space
   * @return the details of the space.
   * @throws IllegalArgumentException - if space is not in the world.
   */
  String getInfoOf(String name);

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
}
