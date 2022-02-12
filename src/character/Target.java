package character;

import character.AbstractCharacter;

/**
 * This class represents a target character.
 * The target has attributes like name, current position (space index) and health.
 * The class also allows the target to move.
 */
public class Target extends AbstractCharacter {
  private final String name;
  private int position;
  private final int health;

  /**
   * Constructs the target character given the health of the target, name and start position.
   * @param health the health of the target
   * @param name the target name
   * @param position the current position
   */
  public Target(int health, String name, int position) {
    this.name = name;
    this.health = health;
    this.position = position;
  }

  /**
   * Returns the health of the target.
   *
   * @return the health
   */
  public int getTargetHealth() {
    return health;
  }

  @Override
  public void moveTo(int newPosition) {
    position = newPosition;
  }

  @Override
  public int getPosition() {
    return position;
  }
}
