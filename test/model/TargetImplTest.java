package model;

import static org.junit.Assert.assertEquals;

import model.characters.Target;
import model.characters.TargetImpl;
import org.junit.Test;

/**
 * Test suite for the target class. Test the validation of parameters.
 */
public class TargetImplTest {

  private Target createTarget(int health, String name, String location) {
    return new TargetImpl(health, name, location);
  }

  @Test
  public void testValidTargetCreation() {
    Target t = createTarget(50, "Target", "Space1");
    assertEquals("Space1", t.getPosition());
    StringBuilder str = new StringBuilder();
    String expected = str.append("Name : Target\n").append("Health : 50\n")
        .append("Position : Space1\n")
        .toString();
    assertEquals(expected, t.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithNullName() {
    Target t = createTarget(50, null, "Space1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidName() {
    Target t = createTarget(50, "  ", "Space1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidHealth() {
    Target t = createTarget(-10, "Target1", "Space1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidPosition() {
    Target t = createTarget(50, "TargetX", null);
  }
}