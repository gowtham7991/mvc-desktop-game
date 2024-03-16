package model.characters;

/**
 * This is the concrete class of the pet. A pet can move around the world.
 */
public class PetImpl implements Pet {
  private final String name;
  private String position;

  /**
   * Constructs the pet by taking in the assigned name and the starting location.
   *
   * @param name     the pet name
   * @param position the pet starting space
   */
  public PetImpl(String name, String position) {
    if (name == null || "".equals(name.trim()) || position == null || "".equals(position.trim())) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.name = name;
    this.position = position;
  }

  @Override
  public void moveTo(String newPosition) {
    if (newPosition == null) {
      throw new IllegalArgumentException("Invalid position!");
    }
    position = newPosition;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPosition() {
    return position;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name : ").append(name).append("\n");
    sb.append("Location : ").append(position).append("\n");
    return sb.toString();
  }
}
