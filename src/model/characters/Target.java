package model.characters;

/**
 * This class represent a character. Operations related a character
 * including the Target is done from here
 */
public interface Target {

  /**
   * Moves the position of the character from the current position.
   *
   * @param position the updated position
   */
  void moveTo(String position);

  /**
   * Return the current position of the character.
   *
   * @return the position (space index) of the character.
   */
  String getPosition();

  /**
   * Returns the current target health.
   *
   * @return the health of the target
   */
  int getTargetHealth();

  /**
   * Decrease the target health by the damage caused by the item.
   *
   * @param damage the damage caused by the item
   */
  void reduceHealth(int damage);
}
