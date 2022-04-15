package model.layout;

import java.util.Objects;

/**
 * This is a concrete class of an item.
 * It has attributes like name, damage it can cause and location of the item (space index).
 * It also handles the operations on the item.
 */
public class ItemImpl implements Item {

  private final String name;
  private final int damage;

  /**
   * Creates an items in the designated space.
   *
   * @param name   the name of the item
   * @param damage the damage caused by the item
   */
  public ItemImpl(String name, int damage) {
    if (name == null || name.trim().length() < 1) {
      throw new IllegalArgumentException("Invalid item name!");
    }
    if (damage < 0) {
      throw new IllegalArgumentException("Damage for an item cannot be negative!");
    }
    this.name = name;
    this.damage = damage;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Item)) {
      return false;
    }
    ItemImpl s2 = (ItemImpl) o;
    return name.equals(s2.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sr = new StringBuilder();
    sr.append("Name : ").append(name).append("\n").append("Damage : ").append(damage).append("\n");

    return sr.toString();
  }
}
