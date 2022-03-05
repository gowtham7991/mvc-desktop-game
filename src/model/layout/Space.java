package model.layout;

import java.util.Set;

/**
 * Space is a single unit of the world. Multiple spaces combine to form a world.
 * Every space has a name, index and list of items found in it.
 * A space which shares a wall with other spaces are its neighbours/ visible spaces.
 */
public interface Space {
  /**
   * Returns the name of the space.
   *
   * @return the name of the space
   */
  String getName();

  /**
   * Returns the index of the space.
   *
   * @return the space index
   */
  int getIndex();

  /**
   * Returns the list of items in the given space.
   *
   * @return the set of items found in the space
   */
  Set<String> getItems();

  /**
   * Remove a specified item from the space.
   *
   * @param name the name of the item
   * @return the item object removed
   */
  Item removeItem(String name);

  /**
   * Add an item to the space.
   *
   * @param item the item
   */
  void addItem(Item item);

  /**
   * Returns the top left row of the space.
   *
   * @return the top left row
   */
  int getTopLeftRow();

  /**
   * Returns the top left column of the space.
   *
   * @return the top left column
   */
  int getTopLeftCol();

  /**
   * Returns the bottom right row of the space.
   *
   * @return the bottom right row
   */
  int getBottomRightRow();

  /**
   * Returns the bottom right column.
   *
   * @return the bottom right column
   */
  int getBottomRightCol();
}
