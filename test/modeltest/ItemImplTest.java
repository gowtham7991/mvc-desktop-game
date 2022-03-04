package modeltest;

import org.junit.Test;

import java.util.Objects;

import model.layout.Item;
import model.layout.ItemImpl;

import static org.junit.Assert.*;

public class ItemImplTest {

  private Item createItem(String name, int damage, int location) {
    return new ItemImpl(name, damage, location);
  }

  @Test
  public void testValidItemCreation() {
    Item i = createItem("Item1", 10, 0);
    StringBuilder sb = new StringBuilder();
    assertEquals("Item1", i.getName());
    assertEquals(10, i.getDamage());
    sb.append("Name : Item1\n").append("Damage : 10\n").append("Location : 0\n");
    assertEquals(  sb.toString(), i.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemWithInvalidItemName() {
    Item i = createItem("  ", 10, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemWithNullName() {
    Item i = createItem(null, 10, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemWithNegativeDamage() {
    Item i = createItem("Item1", -10, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemWithInvalidLocation() {
    Item i = createItem("Item1", 10, -1);
  }

  @Test
  public void testEquals() {
    Item i1 = createItem("Item1", 10, 1);
    Item i2 = createItem("Item1", 5, 1);
    Item i3 = createItem("Item3", 5, 2);

    assertTrue(i1.equals(i2));
    assertTrue(i2.equals(i1));
    assertFalse(i1.equals(i3));
    assertFalse(i3.equals(i1));
  }

  @Test
  public void testHashCode() {
    Item i1 = createItem("Item1", 10, 1);
    Item i2 = createItem("Item1", 5, 1);
    Item i3 = createItem("Item3", 5, 2);

    assertEquals(Objects.hash("Item1", 1), i1.hashCode());
    assertEquals(Objects.hash("Item1", 1), i2.hashCode());
    assertNotEquals(i3.hashCode(), i1.hashCode());
  }
}