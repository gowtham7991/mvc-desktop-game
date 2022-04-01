package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import model.layout.Item;
import model.layout.ItemImpl;
import org.junit.Test;

/**
 * Test suite for the concrete item class. Test the validation of parameters.
 */
public class ItemImplTest {

  private Item createItem(String name, int damage) {
    return new ItemImpl(name, damage);
  }

  @Test
  public void testValidItemCreation() {
    Item i = createItem("Item1", 10);
    StringBuilder sb = new StringBuilder();
    assertEquals("Item1", i.getName());
    assertEquals(10, i.getDamage());
    sb.append("Name : Item1\n").append("Damage : 10\n");
    assertEquals(sb.toString(), i.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemWithInvalidItemName() {
    Item i = createItem("  ", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemWithNullName() {
    Item i = createItem(null, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemWithNegativeDamage() {
    Item i = createItem("Item1", -10);
  }

  @Test
  public void testEquals() {
    Item i1 = createItem("Item1", 10);
    Item i2 = createItem("Item1", 5);
    Item i3 = createItem("Item3", 5);

    assertTrue(i1.equals(i2));
    assertTrue(i2.equals(i1));
    assertFalse(i1.equals(i3));
    assertFalse(i3.equals(i1));
  }

  @Test
  public void testHashCode() {
    Item i1 = createItem("Item1", 10);
    Item i2 = createItem("Item1", 5);
    Item i3 = createItem("Item3", 5);

    assertEquals(Objects.hash("Item1"), i1.hashCode());
    assertEquals(Objects.hash("Item1"), i2.hashCode());
    assertNotEquals(i3.hashCode(), i1.hashCode());
  }
}