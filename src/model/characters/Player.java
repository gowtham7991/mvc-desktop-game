package model.characters;

import java.util.Set;
import model.layout.Item;

/**
 * Represents a player in the game. Allows operations like move a player to new space
 * and picking up an item from the space. The player has a location and has a set max limit of items
 * one can carry.
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

  /**
   * Returns the item from the player's bag.
   *
   * @param itemName the name of the item
   * @return the Item chosen
   */
  Item useItem(String itemName);

  /**
   * Returns the name of the player.
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the type of the player (Computer / Manual).
   *
   * @return the player type
   */
  PlayerType getPlayerType();

  /**
   * Returns the list of items carried by the player.
   *
   * @return the item list
   */
  Set<Item> getItemList();

  /**
   * Returns the no of items currently in player's possession.
   *
   * @return the count
   */
  int getItemCount();

  /**
   * Returns the maximum no of items a player can carry.
   *
   * @return the count of max items
   */
  int getMaxItemCount();
}
