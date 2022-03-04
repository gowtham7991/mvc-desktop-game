package modeltest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.layout.World;
import model.layout.WorldImpl;

import org.junit.Test;

/**
 * Test suite for the World Implementation, spaces and items.
 */
public class WorldImplTest {
  private World createWorld(String worldDesc,
                            String targetDesc,
                            int noOfSpaces,
                            int noOfItems,
                            List<String> spaces,
                            List<String> items) {
    return new WorldImpl(worldDesc, targetDesc, noOfSpaces, noOfItems, spaces, items);
  }

  private final String validWorldDescription = "30 30 MyWorld";
  private final String validTargetDescription = "50 MyTarget";
  private final int noOfSpaces  = 7;
  private final int noOfItems = 4;
  private final int maxTurnsPerGame = 50;

  private final List<String> spaces = new ArrayList<>(List.of(
          "0 0 4 3 Space1",
          "0 4 1 14 Space2",
          "2 4 6 7 Space3",
          "2 8 11 9 Space4",
          "2 10 19 11 Space5",
          "5 2 12 3 Space6",
          "13 0 14 9 Space7"));

  private final List<String> items = new ArrayList<>(List.of(
          "0 10 Item1",
          "0 10 Item111",
          "1 20 Item2",
          "2 30 Item3",
          "3 40 Item4"));
  private final World validWorld = createWorld(validWorldDescription,
          validTargetDescription,
          noOfSpaces,
          noOfItems,
          spaces,
          items);

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

  @Test
  public void testValidAdditionOfPlayer() {
    StringBuilder sr = new StringBuilder();
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems, spaces,
            items);
    String s1 = w.addPlayer("Player1", "Space1", 5);
    String s2 = w.addPlayer("Player2", "Space2", 5);
    String s3 = w.addPlayer("Player3", "Space3", 5);
    sr.append(s1).append("\n").append(s2).append("\n").append(s3).append("\n");
    assertEquals(3, w.getTotalNumberOfHumanPlayers());
    assertEquals("", w.getPlayers());
    assertEquals("", sr.toString());
  }

  @Test
  public void testValidAdditionOfPlayersToSameSpace() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems, spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    assertEquals(2, w.getTotalNumberOfHumanPlayers());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAdditionOfPlayerWithSpaceNotInWorld() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems, spaces,
            items);
    w.addPlayer("Player1", "SpaceX", 5);
  }

  @Test
  public void testAdditionOfPlayerWithSameName() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems, spaces,
            items);
    w.addPlayer("Player1", "Space3", 5);
    w.addPlayer("Player1", "Space4", 5);
    assertEquals("", w.getPlayers());
  }

  @Test
  public void testAdditionOfComputerPlayer() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);

    String response = w.addComputerPlayer();
    assertEquals("", response);
  }

  @Test
  public void testAdditionOfComputerPlayerAfterHumanPlayer() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space4", 5);
    assertEquals("", w.getPlayers());
  }

  @Test
  public void testMovePlayerToValidSpace() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    String response = w.move("Space2");
    assertEquals("", response);
    assertEquals("", w.getTurn());
  }

  @Test
  public void testMovePlayerWithOneNeighbour() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    String response = w.move("Space2");
    assertEquals("", response);
    assertEquals("", w.getTurn());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMovePlayerToSpaceNotInWorld() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.move("SpaceY");

  }

  @Test (expected = IllegalArgumentException.class)
  public void testMovePlayerToSameSpace() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space7", 5);
    w.move("Space7");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMovePlayerToNonNeighbourSpace() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.move("Space7s");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMovePlayerWithZeroNeighbours() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space7", 5);
    w.move("Space5");
  }

  @Test
  public void testPickUpItemInCurrentSpace () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space4", 5);
    w.addPlayer("Player3", "Space2", 5);
    String response = w.pickUpItem("Item1");
    assertEquals("", response);
    assertEquals("", w.getTurn());
  }

  @Test
  public void testPlayerDescriptionSpaceAfterPickUpItem () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space4", 5);
    w.addPlayer("Player3", "Space2", 5);
    w.pickUpItem("Item1");
    String response = w.displayPlayerDescription("Player1");
    assertEquals("", response);
  }

  @Test
  public void testPickUpWithOneItemInSpace () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space2", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    String response = w.pickUpItem("Item2");
    assertEquals("", response);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPickUpWithNoItemInSpace () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    String response = w.pickUpItem("Item4");
    assertEquals("", response);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPickUpItemNotInSpace () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    w.pickUpItem("ItemX");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPickUpItemNotInWorld () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    w.pickUpItem("ItemX");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPickUpItemAfterItemLimit () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");
    w.pickUpItem("Item4");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPickUpItemAlreadyInPossession () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");
    w.pickUpItem("Item1");
  }

  @Test
  public void testLookAround () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    String s = w.lookAround();
    assertEquals("", s);
    assertEquals("", w.getTurn());
  }

  @Test
  public void testLookAroundWithZeroNeighbours () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.move("Space2");
    w.move("Space3");
    String s = w.lookAround();
    assertEquals("", s);
    assertEquals("", w.getTurn());
  }

  @Test
  public void testLookAroundWithOneNeighbour () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    String s = w.lookAround();
    assertEquals("", s);
    assertEquals("", w.getTurn());
  }

  @Test
  public void testValidPlayerDescription () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.pickUpItem("Item1");
    String response = w.displayPlayerDescription("Player1");
    assertEquals("", response);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerDescriptionNotInGame () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    String expected = "";
    String response = w.displayPlayerDescription("PlayerX");
    assertEquals(expected, response);
  }

  @Test
  public void testPlayerDescriptionWithZeroItems () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    String expected = "";
    String response = w.displayPlayerDescription("Player1");
    assertEquals(expected, response);
  }


  @Test
  public void testComputerPlayerDescription () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    String expected = "";
    String response = w.displayPlayerDescription("Computer1");
    assertEquals(expected, response);
  }

  @Test
  public void testInitialGetTurn () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    String expected = "";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnAfterFewMoves () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.addComputerPlayer();
    w.move("Space4");
    w.move("Space3");
    String expected = "";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnAfterFullCycle () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.addComputerPlayer();
    w.move("Space4");
    w.move("Space3");
    w.move("Space1");
    String expected = "";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnForAComputerPlayer () {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    w.move("Space4");
    String expected = "";
    assertEquals(expected, w.getTurn());
  }
}