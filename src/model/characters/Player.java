package model.characters;

import model.layout.Item;

public interface Player {

  /**
   * Moves the player to a given space index.
   *
   * @param position the space index
   */
  void moveTo(String position);

  /**
   * Returns the current position of the player.
   * @return
   */
  String getPosition();

  /**
   * Adds an item from a space to the player item list
   * @param item the item object
   */
  void pickUpItem(Item item);

  String getName();

  PlayerType getPlayerType();

  int getItemCount();
}
