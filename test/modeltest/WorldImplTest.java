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
  private final int noOfSpaces  = 9;
  private final int noOfItems = 5;
  private final int maxTurnsPerGame = 50;

  private final List<String> spaces = new ArrayList<>(List.of(
          "0 0 4 3 Space1",
          "0 4 1 14 Space2",
          "2 4 6 7 Space3",
          "2 8 11 9 Space4",
          "2 10 19 11 Space5",
          "5 2 12 3 Space6",
          "13 0 14 9 Space7",
          "16 3 17 5 Space8",
          "15 7 17 8 Space9"));

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
    assertEquals(9, myWorld.getTotalNumberOfSpaces());
    assertEquals(5, myWorld.getTotalNumberOfItems());
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
    StringBuilder sb = new StringBuilder();

    String expected = "Name : Space1\n" +
            "Index : 0\n" +
            "Items : [Item1, Item111]\n" +
            "Visible Spaces : [Space2, Space3, Space6]\n" +
            "Players in Space : []\n";

    World myWorld = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    assertEquals(expected, myWorld.getInfoOfSpace("Space1"));
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
    String expected = "Player0 : Player1\n" +
            "Type - MANUAL\n" +
            "Location - Space1\n" +
            "\n" +
            "Player1 : Player2\n" +
            "Type - MANUAL\n" +
            "Location - Space2\n" +
            "\n" +
            "Player2 : Player3\n" +
            "Type - MANUAL\n" +
            "Location - Space3\n" +
            "\n" +
            "\n";
    sr.append(s1).append("\n").append(s2).append("\n").append(s3).append("\n");
    assertEquals(3, w.getTotalNumberOfHumanPlayers());
    assertEquals(expected, w.getPlayers());

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
    assertEquals("Player0 : Player1\n" +
            "Type - MANUAL\n" +
            "Location - Space3\n" +
            "\n" +
            "Player1 : Player1\n" +
            "Type - MANUAL\n" +
            "Location - Space4\n" +
            "\n" +
            "\n", w.getPlayers());
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
    assertEquals("Computer1 added to Space1", response);
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
    assertEquals("Player0 : Player1\n" +
            "Type - MANUAL\n" +
            "Location - Space1\n" +
            "\n" +
            "Player1 : Computer1\n" +
            "Type - COMPUTER\n" +
            "Location - Space1\n" +
            "\n" +
            "Player2 : Player2\n" +
            "Type - MANUAL\n" +
            "Location - Space4\n" +
            "\n" +
            "\n", w.getPlayers());
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
    assertEquals("Player1 moved to Space2\n" +
            "Neighbours : [Space1, Space4, Space5, Space3]\n" +
            "Items available : [Item2]\n", response);
  }

  @Test
  public void testMovePlayerWithOneNeighbour() {
    World w = createWorld(validWorldDescription,
            validTargetDescription,
            noOfSpaces,
            noOfItems,
            spaces,
            items);
    w.addPlayer("Player1", "Space9", 5);
    String response = w.move("Space7");
    String expected = "Player1 moved to Space7\n" +
            "Neighbours : [Space5, Space9, Space6]\n" +
            "Items available : []\n";
    assertEquals(expected, response);
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

  @Test
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
    String expected = "Player1 picked up Item1 from Space1\n";
    assertEquals(expected, response);
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
    String expected = "Name : Player1\n" +
            "Items : [Item1]";
    assertEquals(expected, response);
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
    String expected = "Player1 picked up Item2 from Space2\n";
    assertEquals(expected, response);
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
    assertEquals("Current Space : Space1\n" +
            "Neighbours : \n" +
            "Space2\n" +
            "Items available : [Item2]\n" +
            "\n" +
            "Space3\n" +
            "Items available : [Item3]\n" +
            "\n" +
            "Space6\n" +
            "Items available : []\n" +
            "\n", s);
    assertEquals("Player1 - Player2 is in turn. Select a command.\n", w.getTurn());
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
    w.addPlayer("Player3", "Space8", 1);
    w.move("Space2");
    w.move("Space3");
    String s = w.lookAround();
    assertEquals("Current Space : Space8\n" +
            "No neighbours for this space.\n", s);
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
    w.addPlayer("Player1", "Space9", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    String s = w.lookAround();
    String expected = "Current Space : Space9\n" +
            "Neighbours : \n" +
            "Space7\n" +
            "Items available : []\n" +
            "\n";
    assertEquals(expected, s);
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
    String expected = "Name : Player1\n" +
            "Items : [Item1]";
    assertEquals(expected, response);
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
    String expected = "Name : Player1\n" +
            "Items : []";
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
    String expected = "Name : Computer1\n" +
            "Items : []";
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
    w.addPlayer("Dr.Strange", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Thor", "Space7", 1);
    String expected = "Player0 - Dr.Strange is in turn. Select a command.\n";
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
    w.addPlayer("Batman", "Space1", 1);
    w.addPlayer("Superman", "Space7", 1);
    w.addPlayer("Ironman", "Space7", 1);
    w.move("Space4");
    w.move("Space3");
    String expected = "Player2 - Ironman is in turn. Select a command.\n";
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
    w.addPlayer("Batman", "Space1", 1);
    w.addPlayer("Superman", "Space7", 1);
    w.addPlayer("Ironman", "Space7", 1);
    w.move("Space4");
    w.move("Space3");
    w.move("Space1");
    String expected = "Player0 - Batman is in turn. Select a command.\n";
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

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space1", 5 );
    String expected = "Player0 - Computer1 is in turn. Select a command.\n" +
            "Current Space : Space1\n" +
            "Neighbours : \n" +
            "Space2\n" +
            "Items available : [Item2]\n" +
            "\n" +
            "Space3\n" +
            "Items available : [Item3]\n" +
            "\n" +
            "Space6\n" +
            "Items available : []\n" +
            "\n" +
            "\n" +
            "Player1 - Gowtham is in turn. Select a command.\n";
    assertEquals(expected, w.getTurn());
  }
}