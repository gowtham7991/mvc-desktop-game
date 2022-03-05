package modeltest;

import static org.junit.Assert.assertEquals;

import model.characters.Target;
import model.characters.TargetImpl;
import org.junit.Test;

public class TargetImplTest {

  private Target createTarget(int health, String name, int location) {
    return new TargetImpl(health, name, location);
  }

  @Test
  public void testValidTargetCreation() {
    Target t = createTarget(50, "Target", 0);
    assertEquals(0, t.getPosition());
    StringBuilder str = new StringBuilder();
    String expected = str.append("Name : Target\n").append("Health : 50\n").append("Position : 0\n")
        .toString();
    assertEquals(expected, t.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithNullName() {
    Target t = createTarget(50, null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidName() {
    Target t = createTarget(50, "  ", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidHealth() {
    Target t = createTarget(-10, "Target1", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetCreationWithInvalidPosition() {
    Target t = createTarget(50, "TargetX", -1);
  }
}