package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.layout.Item;
import model.layout.ItemImpl;
import model.layout.Space;
import model.layout.SpaceImpl;
import org.junit.Test;

/**
 * Test suite for the space class. Test the validation of parameters.
 */
public class SpaceImplTest {

  private SpaceImpl createSpace(String name, int index, int topLeftRow, int topLeftCol,
      int bottomRightRow, int bottomRightCol, Map<String, Item> items) {
    return new SpaceImpl(name, index, topLeftRow, topLeftCol, bottomRightRow, bottomRightCol,
        items);
  }

  @Test
  public void testValidSpaceCreation() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace("Space0", 0, 0, 0, 5, 5, items);
    assertEquals("Space0", s.getName());
    assertEquals(0, s.getTopLeftRow());
    assertEquals(0, s.getTopLeftCol());
    assertEquals(5, s.getBottomRightRow());
    assertEquals(5, s.getBottomRightCol());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationInvalidName() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, 0, 0, 5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationNullName() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(null, 0, 0, 0, 5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationNegativeTopLeftXcoordinate() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, -5, 0, 5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationNegativeTopLeftYcoordinate() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, 0, -5, 5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationNegativeBottomRightXcoordinate() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, 0, 0, -5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationNegativeBottomRightYcoordinate() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, 0, 0, 5, -5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationTopLeftXgreaterThanBottomRight() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 10, 0, 0, 5, 5, items);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceCreationTopLeftYgreaterThanBottomRight() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s = createSpace(" ", 0, 10, 0, 5, 5, items);
  }

  @Test
  public void testEquals() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s1 = createSpace("Space1", 0, 0, 0, 10, 10, items);
    Space s2 = createSpace("Space1", 0, 0, 0, 10, 10, items);
    Space s3 = createSpace("Space3", 0, 0, 0, 10, 10, items);
    assertTrue(s1.equals(s2));
    assertTrue(s2.equals(s1));
    assertFalse(s1.equals(s3));
    assertFalse(s2.equals(s3));
  }

  @Test
  public void testHashCode() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s1 = createSpace("Space1", 0, 0, 0, 10, 10, items);
    Space s2 = createSpace("Space1", 0, 0, 0, 10, 10, items);
    Space s3 = createSpace("Space2", 0, 0, 0, 10, 10, items);

    int expected = Objects.hash(0, 0, 10, 10, "Space1");
    assertEquals(expected, s1.hashCode());
    assertNotEquals(s1.hashCode(), s3.hashCode());
  }
}