package model.characters;

/**
 * This class represents a Pet in the world. A pet starts in the same position as of the target.
 * A pet has the ability to make a Space invisible and can also assist in attacking the target by
 * making the attacker invisible to other players
 */
public interface Pet {

  /**
   * Moves the pet to the designated space.
   *
   * @param position the name of the destination space
   */
  void moveTo(String position);

  /**
   * Returns the name of the pet.
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the current position of the pet.
   *
   * @return the current space of pet
   */
  String getPosition();
}
