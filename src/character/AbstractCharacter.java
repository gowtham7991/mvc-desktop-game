package character;

/**
 * This is an abstract class of Character which has operations to move and get position.
 */
public abstract class AbstractCharacter implements Character {
  public abstract void moveTo(int position);

  public abstract int getPosition();
}
