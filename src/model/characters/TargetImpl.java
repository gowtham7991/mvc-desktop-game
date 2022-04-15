package model.characters;

/**
 * This class represents a target character.
 * The target has attributes like name, current position (space index) and health.
 * The class also allows the target to move.
 */
public class TargetImpl implements Target {
  private final String name;
  private int health;
  private String position;

  /**
   * Constructs the target character given the health of the target, name and start position.
   *
   * @param health   the health of the target
   * @param name     the target name
   * @param position the current position
   */
  public TargetImpl(int health, String name, String position) {
    if (name == null || name.trim().length() < 1) {
      throw new IllegalArgumentException("Invalid target name!");
    }
    if (health < 1) {
      throw new IllegalArgumentException("Target's health cannot be less than 1!");
    }
    if ("".equals(position) || position == null) {
      throw new IllegalArgumentException("Invalid position!");
    }
    if ("".equals(position.trim())) {
      throw new IllegalArgumentException("Invalid position!");
    }

    this.name = name;
    this.health = health;
    this.position = position;
  }

  @Override
  public int getTargetHealth() {
    return health;
  }

  @Override
  public void reduceHealth(int damage) {
    if (damage < 0) {
      throw new IllegalArgumentException("Damage cannot be negative!");
    }
    if (damage > health) {
      health = 0;
    } else {
      health = health - damage;
    }
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
  public String toString() {
    StringBuilder sr = new StringBuilder();
    sr.append("Name : ").append(name).append("\n");
    sr.append("Health : ").append(health).append("\n");
    sr.append("Position : ").append(position).append("\n");

    return sr.toString();
  }
}
