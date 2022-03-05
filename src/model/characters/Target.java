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
  void moveTo(int position);

  /**
   * Return the current position of the character.
   *
   * @return the position (space index) of the character.
   */
  int getPosition();
}
