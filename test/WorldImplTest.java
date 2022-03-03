import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import model.layout.Item;
import model.layout.ItemImpl;
import model.layout.Space;
import model.layout.SpaceImpl;
import model.layout.World;
import model.layout.WorldImpl;

import org.junit.Test;

/**
 * Test suite for the World Implementation, spaces and items.
 */
public class WorldImplTest {

  private final String validWorldDescription = "30 30 MyWorld";
  private final String validTargetDescription = "50 MyTarget";
  private final int noOfSpaces  = 7;
  private final int noOfItems = 4;
  private final int maxTurnsPerGame = 50;
  private final List<String> spaces = new ArrayList<>(List.of("0 0 4 3 Space1",
          "0 4 1 14 Space2",
          "2 4 6 7 Space3",
          "2 8 11 9 Space4",
          "2 10 19 11 Space5",
          "5 2 12 3 Space6",
          "13 0 14 9 Space7"));
  private final List<String> items = new ArrayList<>(List.of("0 10 Item1",
          "1 20 Item2",
          "2 30 Item3",
          "3 40 Item4"));

  private World createWorld(String worldDesc,
                            String targetDesc,
                            int noOfSpaces,
                            int noOfItems,
                            List<String> spaces,
                            List<String> items) {
    return new WorldImpl(worldDesc, targetDesc, noOfSpaces, noOfItems, spaces, items);
  }

  private SpaceImpl createSpace(String name,
                                int index,
                                int topLeftRow,
                                int topLeftCol,
                                int bottomRightRow,
                                int bottomRightCol,
                                Map<String, Item> items
  ) {
    return new SpaceImpl(name,
            index,
            topLeftRow,
            topLeftCol,
            bottomRightRow,
            bottomRightCol,
            items);
  }

  @Test
  public void testValidWorldCreation() {
    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems, spaces,
            items);
    assertEquals("MyWorld", myWorld.getName());
    assertEquals(7, myWorld.getTotalNumberOfSpaces());
    assertEquals(4, myWorld.getTotalNumberOfItems());
    assertEquals("MyWorld", myWorld.getName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithNegativeRowsAndCols() {
    createWorld("-20 10 myWorld",
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithZeroRowsAndCols() {
    createWorld("0 20 World",
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithNoName() {
    createWorld("20 20",
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithNegativeHealth() {
    createWorld(validWorldDescription,
            "-50 myTarget",
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithInvalidHealth() {
    createWorld(validWorldDescription,
            "x myTarget",
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithoutTargetName() {
    createWorld(validWorldDescription,
            "100",
            noOfSpaces,
            noOfItems,
            spaces,
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithoutSpaceName() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            new ArrayList<>(List.of("0 0 10 10",
                    "0 11 10 20 Space2",
                    "11 10 20 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithNegativeCoordinates() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            new ArrayList<>(List.of("0 0 10 10 Space1",
                    "0 -11 10 20 Space2",
                    "-11 10 20 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithOutOfBoundCoordinates() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            new ArrayList<>(List.of("25 0 10 10 Space1",
                    "0 100 10 20 Space2",
                    "11 10 20 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithSameSpaceName() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            new ArrayList<>(List.of("0 0 10 10 Space1",
                    "0 11 10 20 Space1",
                    "11 10 20 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithTopRowGreaterThanBottomRow() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            new ArrayList<>(List.of("0 0 10 10 Space1",
                    "0 11 9 0 Space1",
                    "15 10 11 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOverLappingSpaces() {
    createWorld(validWorldDescription,
            validTargetDescription, noOfSpaces,
            noOfItems,
            new ArrayList<>(List.of("0 0 10 10 Space1",
                    "0 0 5 5 Space2",
                    "-11 10 20 10 Space3",
                    "21 10 30 10 Space4")),
            items);
  }

  @Test
  public void testEquals() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s1 = createSpace("Space1",
            0,
            0,
            0,
            10,
            10,
            items);
    Space s2 = createSpace("Space1",
            0,
            0,
            0,
            10,
            10,
            items);
    Space s3 = createSpace("Space3",
            0,
            0,
            0,
            10,
            10,
            items);
    assertTrue(s1.equals(s2));
    assertTrue(s2.equals(s1));
    assertFalse(s1.equals(s3));
    assertFalse(s2.equals(s3));
  }

  @Test
  public void testHashCode() {
    Map<String, Item> items = new HashMap<>();
    items.put("Item1", new ItemImpl("Item1", 30, 0));
    Space s1 = createSpace("Space1",
            0,
            0,
            0,
            10,
            10,
            items);
    Space s2 = createSpace("Space1",
            0,
            0,
            0,
            10,
            10,
            items);
    Space s3 = createSpace("Space2",
            0,
            0,
            0,
            10,
            10,
            items);

    int expected = Objects.hash(0, 0, 10, 10, "Space1");
    assertEquals(expected, s1.hashCode());
    assertNotEquals(s1.hashCode(), s3.hashCode());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemDescriptionWithOutOfBoundSpaceIndex() {
    createWorld(validWorldDescription,
            validTargetDescription,
            2,
            noOfItems,
            spaces,
            new ArrayList<>(List.of("25 Item1", "2 Item2", "4 Item3")));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testItemDescriptionWithoutItemName() {
    createWorld(validWorldDescription,
            validTargetDescription, noOfSpaces,
            3,
            spaces,
            new ArrayList<>(List.of("2", "2 Item2", "1 Item3")));
  }

  @Test (expected = IllegalArgumentException.class)
  public void getNeighboursOfSpaceNotInWorld() {
    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    myWorld.getNeighboursOf("Space10");
  }

  @Test
  public void getNeighboursOf() {
    String expected1 = "[Space2, Space3, Space6]";
    String expected2 = "[Space1, Space4, Space5, Space3]";
    String expected3 = "[Space1, Space4, Space2, Space6]";

    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    assertEquals(expected1, myWorld.getNeighboursOf("Space1"));
    assertEquals(expected2, myWorld.getNeighboursOf("Space2"));
    assertEquals(expected3, myWorld.getNeighboursOf("Space3"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void getInfoOfSpaceNotInWorld() {
    HashSet<String> expected1 = new HashSet<>();
    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    String required = myWorld.getInfoOfSpace("Space10");
  }

  @Test
  public void getInfoOfSpace() {
    StringBuilder str = new StringBuilder();

    str.append("Name : Space1\n");
    str.append("Index : 0\n");
    str.append("Items : [Item1]\n");
    str.append("Visible Spaces : [Space2, Space3, Space6]");
    String expected1 = str.toString();

    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    assertEquals(expected1, myWorld.getInfoOfSpace("Space1"));
  }

  @Test
  public void moveTargetFromInitialPosition() {
    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);

    myWorld.moveTarget();
    myWorld.moveTarget();
    myWorld.moveTarget();

    int expected = 3;
    assertEquals(expected, myWorld.getTargetPosition());
  }

  @Test
  public void moveTargetFromLastIndex() {
    int expected = 0;
    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);

    for (int i = 0; i < noOfSpaces; i++) {
      myWorld.moveTarget();
    }

    assertEquals(expected, myWorld.getTargetPosition());
  }
}