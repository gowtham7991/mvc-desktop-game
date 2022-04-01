package modeltest;

import static org.junit.Assert.assertEquals;

import model.characters.Pet;
import model.characters.PetImpl;
import org.junit.Test;

/**
 * This is a test suite for the Pet class.
 * The testing covers the construction and operations over the pet.
 */
public class PetImplTest {

  private Pet createPet(String name, String startingPosition) {
    return new PetImpl(name, startingPosition);
  }

  @Test
  public void testPetConstruction() {
    Pet p = createPet("MyPet", "Space1");
    assertEquals("MyPet", p.getName());
    assertEquals("Space1", p.getPosition());
    assertEquals("Name : MyPet\n" + "Location : Space1\n", p.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPetConstructionWithInvalidName() {
    Pet p = createPet("", "Space1");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPetConstructionWithNullName() {
    Pet p = createPet(null, "Space1");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPetConstructionWithNullPosition() {
    Pet p = createPet("MyPet", null);
  }

  @Test
  public void moveTo() {
    Pet p = createPet("MyPet", "Space1");
    p.moveTo("Space2");
    assertEquals("Space2", p.getPosition());
  }

  @Test (expected = IllegalArgumentException.class)
  public void moveToInvalidSpace() {
    Pet p = createPet("MyPet", "Space1");
    p.moveTo(null);
  }
}