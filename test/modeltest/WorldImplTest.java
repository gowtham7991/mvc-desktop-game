package modeltest;

import static org.junit.Assert.assertEquals;

import controllertest.mocks.RandomGeneratorMock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.layout.World;
import model.layout.WorldImpl;
import org.junit.Test;
import utils.RandomGenerator;

/**
 * Test suite for the World Implementation, spaces and items.
 */
public class WorldImplTest {
  private final String validWorldDescription = "30 30 MyWorld";
  private final String validTargetDescription = "50 MyTarget";
  private final int noOfSpaces = 9;
  private final int noOfItems = 5;
  private final int maxTurnsPerGame = 50;
  private final RandomGenerator rand = new RandomGeneratorMock();
  private final List<String> spaces = new ArrayList<>(
      List.of("0 0 4 3 Space1", "0 4 1 14 Space2", "2 4 6 7 Space3", "2 8 11 9 Space4",
          "2 10 19 11 Space5", "5 2 12 3 Space6", "13 0 14 9 Space7", "16 3 17 5 Space8",
          "15 7 17 8 Space9"));
  private final List<String> items = new ArrayList<>(
      List.of("0 10 Item1", "0 10 Item111", "1 20 Item2", "2 30 Item3", "3 40 Item4"));
  private final World validWorld = createWorld(validWorldDescription, validTargetDescription,
      noOfSpaces, noOfItems, spaces, items, rand);

  private World createWorld(String worldDesc, String targetDesc, int noOfSpaces, int noOfItems,
      List<String> spaces, List<String> items, RandomGenerator rand) {
    return new WorldImpl(worldDesc, targetDesc, noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test
  public void testValidWorldCreation() {
    World myWorld = validWorld;
    assertEquals("MyWorld", myWorld.getName());
    assertEquals(9, myWorld.getTotalNumberOfSpaces());
    assertEquals(5, myWorld.getTotalNumberOfItems());
    assertEquals("MyWorld", myWorld.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithNegativeRowsAndCols() {
    createWorld("-20 10 myWorld", validTargetDescription, noOfSpaces, noOfItems, spaces, items,
        rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithZeroRowsAndCols() {
    createWorld("0 20 World", validTargetDescription, noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithNoName() {
    createWorld("20 20", validTargetDescription, noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithNegativeHealth() {
    createWorld(validWorldDescription, "-50 myTarget", noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithInvalidHealth() {
    createWorld(validWorldDescription, "x myTarget", noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithoutTargetName() {
    createWorld(validWorldDescription, "100", noOfSpaces, noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithoutSpaceName() {
    createWorld(validWorldDescription, validTargetDescription, 2, noOfItems, new ArrayList<>(
            List.of("0 0 10 10", "0 11 10 20 Space2", "11 10 20 10 Space3", "21 10 30 10 Space4")),
        items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithNegativeCoordinates() {
    createWorld(validWorldDescription, validTargetDescription, 2, noOfItems, new ArrayList<>(
        List.of("0 0 10 10 Space1", "0 -11 10 20 Space2", "-11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithOutOfBoundCoordinates() {
    createWorld(validWorldDescription, validTargetDescription, 2, noOfItems, new ArrayList<>(
        List.of("25 0 10 10 Space1", "0 100 10 20 Space2", "11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithSameSpaceName() {
    createWorld(validWorldDescription,
        validTargetDescription,
        2,
        noOfItems,
        new ArrayList<>(
        List.of("0 0 10 10 Space1", "0 11 10 20 Space1", "11 10 20 10 Space3",
            "21 10 30 10 Space4")),
        items,
        rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithTopRowGreaterThanBottomRow() {
    createWorld(validWorldDescription,
        validTargetDescription,
        2,
        noOfItems,
        new ArrayList<>(
            List.of("0 0 10 10 Space1",
                "0 11 9 0 Space1", "15 10 11 10 Space3", "21 10 30 10 Space4")),
        items,
        rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverLappingSpaces() {
    createWorld(validWorldDescription, validTargetDescription, noOfSpaces, noOfItems,
        new ArrayList<>(List.of("0 0 10 10 Space1", "0 0 5 5 Space2", "-11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemDescriptionWithOutOfBoundSpaceIndex() {
    createWorld(validWorldDescription, validTargetDescription, 2, noOfItems, spaces,
        new ArrayList<>(List.of("25 Item1", "2 Item2", "4 Item3")), rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemDescriptionWithoutItemName() {
    createWorld(validWorldDescription, validTargetDescription, noOfSpaces, 3, spaces,
        new ArrayList<>(List.of("2", "2 Item2", "1 Item3")), rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getNeighboursOfSpaceNotInWorld() {
    World myWorld = createWorld(validWorldDescription, validTargetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
    myWorld.getNeighboursOf("Space10");
  }

  @Test
  public void getNeighboursOf() {
    String expected1 = "[Space2, Space3, Space6]";
    String expected2 = "[Space1, Space4, Space5, Space3]";
    String expected3 = "[Space1, Space4, Space2, Space6]";

    World myWorld = validWorld;
    assertEquals(expected1, myWorld.getNeighboursOf("Space1"));
    assertEquals(expected2, myWorld.getNeighboursOf("Space2"));
    assertEquals(expected3, myWorld.getNeighboursOf("Space3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getInfoOfSpaceNotInWorld() {
    HashSet<String> expected1 = new HashSet<>();
    World myWorld = validWorld;
    String required = myWorld.getInfoOfSpace("Space10");
  }

  @Test
  public void getInfoOfSpace() {
    StringBuilder sb = new StringBuilder();

    sb.append("Name : Space1\n");
    sb.append("Index : 0\n");
    sb.append("Items : [Item1, Item111]\n");
    sb.append("Visible Spaces : [Space2, Space3, Space6]\n");
    sb.append("Players in Space : []\n");

    World myWorld = validWorld;
    assertEquals(sb.toString(), myWorld.getInfoOfSpace("Space1"));
  }

  @Test
  public void moveTargetFromInitialPosition() {
    World myWorld = validWorld;

    myWorld.moveTarget();
    myWorld.moveTarget();
    myWorld.moveTarget();

    int expected = 3;
    assertEquals(expected, myWorld.getTargetPosition());
  }

  @Test
  public void moveTargetFromLastIndex() {
    int expected = 0;
    World myWorld = validWorld;

    for (int i = 0; i < noOfSpaces; i++) {
      myWorld.moveTarget();
    }

    assertEquals(expected, myWorld.getTargetPosition());
  }

  @Test
  public void testValidAdditionOfPlayer() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space2", 5);
    w.addPlayer("Player3", "Space3", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 : Player1\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space1\n");
    sb.append("\n");
    sb.append("Player1 : Player2\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space2\n");
    sb.append("\n");
    sb.append("Player2 : Player3\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space3\n");
    sb.append("\n");
    sb.append("\n");
    assertEquals(3, w.getTotalNumberOfHumanPlayers());
    assertEquals(sb.toString(), w.getPlayers());

  }

  @Test
  public void testValidAdditionOfPlayersToSameSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    assertEquals(2, w.getTotalNumberOfHumanPlayers());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdditionOfPlayerWithSpaceNotInWorld() {
    World w = validWorld;
    w.addPlayer("Player1", "SpaceX", 5);
  }

  @Test
  public void testAdditionOfPlayerWithSameName() {
    World w = validWorld;
    w.addPlayer("Player1", "Space3", 5);
    w.addPlayer("Player1", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 : Player1\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space3\n");
    sb.append("\n");
    sb.append("Player1 : Player1\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space4\n");
    sb.append("\n");
    sb.append("\n");
    assertEquals(sb.toString(), w.getPlayers());
  }

  @Test
  public void testAdditionOfComputerPlayer() {
    World w = validWorld;

    String response = w.addComputerPlayer();
    assertEquals("Computer1 added to Space1", response);
  }

  @Test
  public void testAdditionOfComputerPlayerAfterHumanPlayer() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 : Player1\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space1\n");
    sb.append("\n");
    sb.append("Player1 : Computer1\n");
    sb.append("Type - COMPUTER\n");
    sb.append("Location - Space1\n");
    sb.append("\n");
    sb.append("Player2 : Player2\n");
    sb.append("Type - MANUAL\n");
    sb.append("Location - Space4\n");
    sb.append("\n");
    sb.append("\n");
    assertEquals(
        sb.toString(),
        w.getPlayers());
  }

  @Test
  public void testMovePlayerToValidSpace() {
    StringBuilder sb = new StringBuilder();
    sb.append("Player1 moved to Space2\n");
    sb.append("Neighbours : [Space1, Space4, Space5, Space3]\n");
    sb.append("Items available : [Item2]\n");
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    String response = w.move("Space2");
    assertEquals(sb.toString(), response);
  }

  @Test
  public void testMovePlayerWithOneNeighbour() {
    StringBuilder sb = new StringBuilder();
    sb.append("Player1 moved to Space7\n");
    sb.append("Neighbours : [Space5, Space9, Space6]\n");
    sb.append("Items available : []\n");
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    String response = w.move("Space7");
    assertEquals(sb.toString(), response);
  }

  @Test
  public void testMovePlayerToSpaceNotInWorld() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    String out = w.move("SpaceY");
    String expected = "Space not found in the world. Your turn is up!\n";
    assertEquals(expected, out);
  }

  @Test
  public void testMovePlayerToSameSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space7", 5);
    String out = w.move("Space7");
    String expected = "You are currently in this space. Your turn is up!\n";
    assertEquals(expected, out);
  }

  @Test
  public void testMovePlayerWhenNoNeighbours() {
    World w = validWorld;
    w.addPlayer("Player1", "Space8", 5);
    String response = w.move("Space7");
    String expected = "Space is not a neighbour. Your turn is up!\n";
    assertEquals(expected, response);
  }

  @Test
  public void testPickUpItemInCurrentSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space4", 5);
    w.addPlayer("Player3", "Space2", 5);
    String response = w.pickUpItem("Item1");
    String expected = "Player1 picked up Item1 from Space1\n";
    assertEquals(expected, response);
  }

  @Test
  public void testPickUpItemWhenNoItemsInSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    w.addPlayer("Player2", "Space4", 5);
    w.addPlayer("Player3", "Space2", 5);
    String response = w.pickUpItem("Item1");
    String expected = "No items found. Your turn is up!\n";
    assertEquals(expected, response);
  }

  @Test
  public void testPlayerDescriptionSpaceAfterPickUpItem() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space4", 5);
    w.addPlayer("Player3", "Space2", 5);
    w.pickUpItem("Item1");
    String response = w.displayPlayerDescription("Player1");
    String expected = "Name : Player1\n" + "Items : [Item1]";
    assertEquals(expected, response);
  }

  @Test
  public void testPickUpWithOneItemInSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space2", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    String response = w.pickUpItem("Item2");
    String expected = "Player1 picked up Item2 from Space2\n";
    assertEquals(expected, response);
  }

  @Test
  public void testPickUpWithNoItemInSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    String response = w.pickUpItem("Item4");
    assertEquals("No items found. Your turn is up!\n", response);
  }

  @Test
  public void testPickUpItemNotInSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    w.pickUpItem("ItemX");
  }

  @Test
  public void testPickUpItemNotInWorld() {
    World w = validWorld;
    w.addPlayer("Player1", "Space7", 5);
    w.addPlayer("Player2", "Space3", 5);
    w.addPlayer("Player3", "Space5", 5);
    w.pickUpItem("ItemX");
  }

  @Test
  public void testPickUpItemAfterItemLimit() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");
    String out = w.pickUpItem("Item111");
    String expected = "Max item limit reached. Cannot pick up the item!\n";
    assertEquals(expected, out);
  }

  @Test
  public void testPickUpItemAlreadyInPossession() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");
    w.pickUpItem("Item1");
  }

  @Test
  public void testLookAround() {
    StringBuilder sb = new StringBuilder();
    sb.append("Current Space : Space1\n");
    sb.append("Neighbours : \n");
    sb.append("Space2\n");
    sb.append("Items available : [Item2]\n");
    sb.append("\n");
    sb.append("Space3\n");
    sb.append("Items available : [Item3]\n");
    sb.append("\n");
    sb.append("Space6\n");
    sb.append("Items available : []\n");
    sb.append("\n");
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    String s = w.lookAround();

    assertEquals(
        sb.toString(),
        s);
    assertEquals("Player1 - Player2 is in turn. Select a command.\n", w.getTurn());
  }

  @Test
  public void testLookAroundWithZeroNeighbours() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space8", 1);
    w.move("Space2");
    w.move("Space3");
    String s = w.lookAround();
    StringBuilder sb = new StringBuilder();
    sb.append("Current Space : Space8\n");
    sb.append("No neighbours for this space.\n");
    assertEquals(sb.toString(), s);
  }

  @Test
  public void testLookAroundWithOneNeighbour() {
    StringBuilder sb = new StringBuilder();
    sb.append("Current Space : Space9\n");
    sb.append("Neighbours : \n");
    sb.append("Space7\n");
    sb.append("Items available : []\n");
    sb.append("\n");
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    String s = w.lookAround();

    assertEquals(sb.toString(), s);
  }

  @Test
  public void testValidPlayerDescription() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.pickUpItem("Item1");
    String response = w.displayPlayerDescription("Player1");
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Player1\n");
    sb.append("Items : [Item1]");
    String expected = sb.toString();
    assertEquals(expected, response);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerDescriptionNotInGame() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    String expected = "";
    String response = w.displayPlayerDescription("PlayerX");
    assertEquals(expected, response);
  }

  @Test
  public void testPlayerDescriptionWithZeroItems() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Player1\n");
    sb.append("Items : []");
    String expected = sb.toString();
    String response = w.displayPlayerDescription("Player1");
    assertEquals(expected, response);
  }

  @Test
  public void testComputerPlayerDescription() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Player2", "Space7", 1);
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Computer1\n");
    sb.append("Items : []");
    String expected = sb.toString();
    String response = w.displayPlayerDescription("Computer1");
    assertEquals(expected, response);
  }

  @Test
  public void testInitialGetTurn() {
    World w = validWorld;
    w.addPlayer("Dr.Strange", "Space1", 1);
    w.addComputerPlayer();
    w.addPlayer("Thor", "Space7", 1);
    String expected = "Player0 - Dr.Strange is in turn. Select a command.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnAfterFewMoves() {
    World w = validWorld;
    w.addPlayer("Batman", "Space1", 1);
    w.addPlayer("Superman", "Space7", 1);
    w.addPlayer("Ironman", "Space7", 1);
    w.move("Space4");
    w.move("Space3");
    String expected = "Player2 - Ironman is in turn. Select a command.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnAfterFullCycle() {
    World w = validWorld;
    w.addPlayer("Batman", "Space1", 1);
    w.addPlayer("Superman", "Space1", 1);
    w.addPlayer("Ironman", "Space1", 1);
    w.move("Space2");
    w.move("Space2");
    w.move("Space2");
    String expected = "Player0 - Batman is in turn. Select a command.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnForaComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces, noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space1", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 - Computer1 is in turn. Select a command.\n");
    sb.append("Current Space : Space1\n");
    sb.append("Neighbours : \n");
    sb.append("Space2\n");
    sb.append("Items available : [Item2]\n");
    sb.append("\n");
    sb.append("Space3\n");
    sb.append("Items available : [Item3]\n");
    sb.append("\n");
    sb.append("Space6\n");
    sb.append("Items available : []\n");
    sb.append("\n");
    sb.append("\n");
    sb.append("Player1 - Gowtham is in turn. Select a command.\n");
    String expected = sb.toString();
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testMoveForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(0, 1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces,
        noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 - Computer1 is in turn. Select a command.\n");
    sb.append("Computer1 moved to Space3\n");
    sb.append("Neighbours : [Space1, Space4, Space2, Space6]\n");
    sb.append("Items available : [Item3]\n");
    sb.append("\n");
    sb.append("Player1 - Gowtham is in turn. Select a command.\n");
    String expected = sb.toString();
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testLookAroundForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces,
        noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 - Computer1 is in turn. Select a command.\n");
    sb.append("Current Space : Space1\n");
    sb.append("Neighbours : \n");
    sb.append("Space2\n");
    sb.append("Items available : [Item2]\n");
    sb.append("\n");
    sb.append("Space3\n");
    sb.append("Items available : [Item3]\n");
    sb.append("\n");
    sb.append("Space6\n");
    sb.append("Items available : []\n");
    sb.append("\n");
    sb.append("\n");
    sb.append("Player1 - Gowtham is in turn. Select a command.\n");

    assertEquals(sb.toString(), w.getTurn());
  }

  @Test
  public void testPickUpItemForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces,
        noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    sb.append("Player0 - Computer1 is in turn. Select a command.\n");
    sb.append("Computer1 picked up Item111 from Space1\n");
    sb.append("\n");
    sb.append("Player1 - Gowtham is in turn. Select a command.\n");
    String expected = sb.toString();
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void checkTargetPositionAfterMove() {
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces,
        noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.move("Space2");
    w.move("Space2");
    assertEquals(2, w.getTargetPosition());
  }

  @Test
  public void checkSpaceDescriptionAfterItemPickUp() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Space1\n");
    sb.append("Index : 0\n");
    sb.append("Items : [Item111]\n");
    sb.append("Visible Spaces : [Space2, Space3, Space6]\n");
    sb.append("Players in Space : [Player2, Player1, Computer1]\n");
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription,
        validTargetDescription,
        noOfSpaces,
        noOfItems,
        spaces,
        items,
        rand);

    w.addComputerPlayer();
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.pickUpItem("Item1");
    String result = w.getInfoOfSpace("Space1");

    assertEquals(sb.toString(), result);
  }

}