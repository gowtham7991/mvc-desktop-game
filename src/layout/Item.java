package layout;

/**
 * The items present in a space. Every item has a name, the damage it can cause and location.
 */
public interface Item {

  /**
   * Returns the damage caused by the item.
   * @return the damage
   */
  int getDamage();

  /**
   * Returns the name of the item.
   * @return the name
   */
  String getName();
}
