package model.characters;

import java.util.ArrayList;
import java.util.List;

import model.layout.Item;

public class PlayerImpl implements Player {
  private final String name;
  private String position;
  private final PlayerType type;
  private final int maxItems;
  private int noOfItems;
  private List<Item> itemsCarrying = new ArrayList<>();

  public PlayerImpl(String name, String position, PlayerType type, int maxItems) {
    if (name == null || name.trim().length() < 1) {
      throw new IllegalArgumentException("Invalid player name!");
    }
    if (position == null) {
      throw new IllegalArgumentException("Invalid Player location!");
    }
    if ( type == null) {
      throw new IllegalArgumentException("Invalid Player type!");
    }
    if (maxItems < 0) {
      throw new IllegalArgumentException("Max items cannot be non-negative!");
    }
    this.name = name;
    this.position = position;
    this.type = type;
    this.maxItems = maxItems;
    this.noOfItems = 0;
  }

  @Override
  public void moveTo(String newPosition) {
    position = newPosition;
  }

  @Override
  public String getPosition() {
    return position;
  }

  @Override
  public void pickUpItem(Item item) {
    if (noOfItems == maxItems) {
      throw new IllegalArgumentException("Max limit reached!");
    }

    itemsCarrying.add(item);
    noOfItems += 1;
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
  public PlayerType getPlayerType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (! (o instanceof Player) ) {
      return false;
    }

    PlayerImpl that = (PlayerImpl) o;

    return this.name == that.name;

  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sr = new StringBuilder();
    sr.append("Name : ");
    sr.append(this.name);
    sr.append("\n");
    sr.append("Items : ");
    List<String> items = new ArrayList<>();

    for (int i = 0; i < itemsCarrying.size(); i++) {
      items.add(itemsCarrying.get(i).getName());
    }
    sr.append(items);

    return sr.toString();
  }
}
