package model.characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.layout.Item;

/**
 * This is the concrete class of the player. The class always a player to move and pickup items.
 * Every player has a set limit on the items which he can carry.
 */
public class PlayerImpl implements Player {
  private final String name;
  private final PlayerType type;
  private final int maxItems;
  private final int id;
  private final String startingPosition;
  private String position;
  private int noOfItems;
  private final Map<String, Item> itemsInBag;

  /**
   * Constructs the player class when given the required details.
   *
   * @param name     the player name
   * @param position the starting position
   * @param type     the player type human or computer
   * @param maxItems the item limit of the player
   * @param id       the player id
   * @throws IllegalArgumentException if name is null or invalid
   * @throws IllegalArgumentException if position or type is null
   * @throws IllegalArgumentException if maxItems is negative
   */
  public PlayerImpl(String name, String position, PlayerType type, int maxItems, int id) {
    if (name == null || name.trim().length() < 1) {
      throw new IllegalArgumentException("Invalid player name!");
    }
    if (position == null) {
      throw new IllegalArgumentException("Invalid Player location!");
    }
    if (type == null) {
      throw new IllegalArgumentException("Invalid Player type!");
    }
    if (maxItems < 0) {
      throw new IllegalArgumentException("Max items cannot be non-negative!");
    }
    this.name = name;
    this.position = position;
    this.startingPosition = position;
    this.type = type;
    this.maxItems = maxItems;
    this.itemsInBag = new HashMap<>();
    this.noOfItems = 0;
    this.id = id;
  }

  @Override
  public void moveTo(String newPosition) {
    if (newPosition == null) {
      throw new IllegalArgumentException("Invalid position!");
    }
    position = newPosition;
  }

  @Override
  public String getPosition() {
    return position;
  }

  @Override
  public void pickUpItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Invalid item!");
    }

    if (noOfItems == maxItems) {
      throw new IllegalArgumentException("Max limit reached!");
    }

    itemsInBag.put(item.getName(), item);
    noOfItems += 1;
  }

  @Override
  public Item useItem(String itemName) {
    if (itemName == null) {
      throw new IllegalArgumentException("Invalid item name!");
    }

    noOfItems -= 1;
    return itemsInBag.remove(itemName);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getItemCount() {
    return noOfItems;
  }

  @Override
  public int getMaxItemCount() {
    return maxItems;
  }

  @Override
  public PlayerType getPlayerType() {
    return type;
  }

  @Override
  public Set<Item> getItemList() {

    Set<Item> items = new HashSet<>();
    for (Item i : itemsInBag.values()) {
      items.add(i);
    }

    return items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Player)) {
      return false;
    }

    PlayerImpl that = (PlayerImpl) o;

    return this.id == that.id;

  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }

  @Override
  public String toString() {
    StringBuilder sr = new StringBuilder();
    sr.append("Name : ");
    sr.append(this.name);
    sr.append("\n");
    sr.append("Items : ");
    sr.append(itemsInBag.keySet()).append("\n");
    sr.append("Current Position : ");
    sr.append(position).append("\n");

    return sr.toString();
  }
}
