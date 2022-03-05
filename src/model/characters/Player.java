package model.characters;

import model.layout.Item;

/**
 * Represents a player in the game. Allows operations like move a player to new space
 * and picking up an item from the space.
 */
public interface Player {

  /**
   * Moves the player to a given space index.
   *
   * @param position the space index
   */
  void moveTo(String position);

  /**
   * Returns the current position of the player.
   *
   * @return the position
   */
  String getPosition();

  /**
   * Adds an item from a space to the player item list.
   *
   * @param item the item object
   */
  void pickUpItem(Item item);

  String getName();

  PlayerType getPlayerType();

  int getItemCount();

  int getMaxItemCount();
}
