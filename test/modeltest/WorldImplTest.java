package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controllertest.mocks.RandomGeneratorMock;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.Model;
import model.ModelImpl;
import model.layout.World;
import model.layout.WorldImpl;
import org.junit.Test;
import utils.RandomGenerator;

/**
 * Test suite for the World Implementation, spaces and items.
 */
public class WorldImplTest {

  private final String validWorldDescription;
  private final String validTargetDescription;
  private final String validPetDescription;
  private final int noOfSpaces;
  private final int noOfItems;
  private final RandomGenerator rand;
  private final List<String> spaces;
  private final List<String> items;
  private final World validWorld;

  /**
   * Constructs the World class test suite.
   */
  public WorldImplTest() {
    this.validWorldDescription = "30 30 MyWorld";
    this.validTargetDescription = "50 MyTarget";
    this.validPetDescription = "My Pet";
    this.noOfSpaces = 9;
    this.noOfItems = 5;
    this.rand = new RandomGeneratorMock();
    this.spaces = new ArrayList<>(
        List.of("0 0 4 3 Space1", "0 4 1 14 Space2", "2 4 6 7 Space3", "2 8 11 9 Space4",
            "2 10 19 11 Space5", "5 2 12 3 Space6", "13 0 14 9 Space7", "15 3 17 5 Space8",
            "15 7 17 8 Space9"));
    this.items = new ArrayList<>(
        List.of("0 10 Item1", "0 20 Item111", "1 20 Item2", "2 30 Item3", "3 40 Item4"));
    this.validWorld = createWorld(validWorldDescription, validTargetDescription,
        validPetDescription, noOfSpaces, noOfItems, spaces, items, rand);
  }

  private World createWorld(String worldDesc, String targetDesc, String petDescription,
      int noOfSpaces, int noOfItems, List<String> spaces, List<String> items,
      RandomGenerator rand) {
    return new WorldImpl(worldDesc, targetDesc, petDescription, noOfSpaces, noOfItems, spaces,
        items, rand);
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
    createWorld("-20 10 myWorld", validTargetDescription, validPetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithZeroRowsAndCols() {
    createWorld("0 20 World", validTargetDescription, validPetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWorldDescriptionWithNoName() {
    createWorld("20 20", validTargetDescription, validPetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithNegativeHealth() {
    createWorld(validWorldDescription, "-50 myTarget", validPetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithInvalidHealth() {
    createWorld(validWorldDescription, "x myTarget", validPetDescription, noOfSpaces,
        noOfItems, spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTargetDescriptionWithoutTargetName() {
    createWorld(validWorldDescription, "100", validPetDescription, noOfSpaces, noOfItems,
        spaces, items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithoutSpaceName() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription, 2,
        noOfItems, new ArrayList<>(
            List.of("0 0 10 10", "0 11 10 20 Space2", "11 10 20 10 Space3", "21 10 30 10 Space4")),
        items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithNegativeCoordinates() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription, 2,
        noOfItems,
        new ArrayList<>(List.of("0 0 10 10 Space1", "0 -11 10 20 Space2", "-11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithOutOfBoundCoordinates() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        2, noOfItems,
        new ArrayList<>(List.of("25 0 10 10 Space1", "0 100 10 20 Space2", "11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithSameSpaceName() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        2, noOfItems,
        new ArrayList<>(List.of("0 0 10 10 Space1", "0 11 10 20 Space1", "11 10 20 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSpaceDescriptionWithTopRowGreaterThanBottomRow() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        2, noOfItems,
        new ArrayList<>(List.of("0 0 10 10 Space1", "0 11 9 0 Space1", "15 10 11 10 Space3",
            "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverLappingSpaces() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription, noOfSpaces,
        noOfItems, new ArrayList<>(
            List.of("0 0 10 10 Space1", "0 0 5 5 Space2", "-11 10 20 10 Space3",
                "21 10 30 10 Space4")), items, rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemDescriptionWithOutOfBoundSpaceIndex() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        2, noOfItems,
        spaces, new ArrayList<>(List.of("25 Item1", "2 Item2", "4 Item3")), rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testItemDescriptionWithoutItemName() {
    createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, 3,
        spaces, new ArrayList<>(List.of("2", "2 Item2", "1 Item3")), rand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getNeighboursOfSpaceNotInWorld() {
    World myWorld = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    myWorld.getNeighboursOf("Space10");
  }

  @Test
  public void getNeighboursOf() {
    String expected1 = "[Space2, Space3, Space6]";
    String expected2 = "[Space1, Space3, Space4, Space5]";
    String expected3 = "[Space1, Space2, Space4, Space6]";

    World myWorld = validWorld;
    assertEquals(expected1, myWorld.getNeighboursOf("Space1").toString());
    assertEquals(expected2, myWorld.getNeighboursOf("Space2").toString());
    assertEquals(expected3, myWorld.getNeighboursOf("Space3").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getInfoOfSpaceNotInWorld() {
    HashSet<String> expected1 = new HashSet<>();
    World myWorld = validWorld;
    String required = myWorld.getInfoOfSpace("Space10");
  }

  @Test
  public void getInfoOfSpace() {
    String expected = "Name : Space1\n" + "Index : 0\n" + "Items : [Item1, Item111]\n"
        + "Visible Spaces : [Space2, Space3, Space6]\n" + "Players in Space : []\n"
        + "Pet present : yes\n";

    World myWorld = validWorld;
    assertEquals(expected, myWorld.getInfoOfSpace("Space1"));
  }

  @Test
  public void moveTargetFromInitialPosition() {
    World myWorld = validWorld;

    myWorld.moveTarget();
    myWorld.moveTarget();
    myWorld.moveTarget();

    assertEquals("Space4", myWorld.getTargetPosition());
  }

  @Test
  public void moveTargetFromLastIndex() {
    World myWorld = validWorld;

    for (int i = 0; i < noOfSpaces; i++) {
      myWorld.moveTarget();
    }

    assertEquals("Space1", myWorld.getTargetPosition());
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
    assertEquals(sb.toString(), w.getPlayers());
  }

  @Test
  public void testMovePlayerToValidSpace() {
    String expected = "Player1 moved to Space2\n";
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    String response = w.move("Space2");
    assertEquals(expected, response);
  }

  @Test
  public void testMovePlayerWithOneNeighbour() {
    String expected = "Player1 moved to Space7\n";
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    String response = w.move("Space7");
    assertEquals(expected, response);
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
    String expected = "Name : Player1\n" + "Items : [Item1]\n" + "Current Position : Space1\n";
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
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space3", 1);
    String s = w.lookAround();
    String expected = "Current Space : Space1\n" + "Items available : [Item1, Item111]\n"
        + "Players in Space : [Player1]\n" + "\n" + "Neighbours : \n" + "Space2\n"
        + "Items available : [Item2]\n" + "Players in Space : [Player2]\n" + "\n" + "Space3\n"
        + "Items available : [Item3]\n" + "Players in Space : [Player3]\n" + "\n" + "Space6\n"
        + "Items available : []\n" + "Players in Space : []\n" + "\n";

    assertEquals(expected, s);
    assertEquals("Player1 - Player2 is in turn.\n", w.getTurn());
  }

  @Test
  public void testLookAroundWithOneNeighbour() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    String s = w.lookAround();
    String expected = "Current Space : Space9\n" + "Items available : []\n"
        + "Players in Space : [Player1]\n" + "\n" + "Neighbours : \n" + "Space7\n"
        + "Items available : []\n" + "Players in Space : [Player3]\n\n";
    assertEquals(expected, s);
  }

  @Test
  public void testLookAroundWhenPetInNeighbour() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);

    w.movePet("Space3");
    String res = w.lookAround();
    StringBuilder sb = new StringBuilder();
    String expected = "Current Space : Space2\n" + "Items available : [Item2]\n"
        + "Players in Space : [Player2]\n" + "\n" + "Neighbours : \n" + "Space1\n"
        + "Items available : [Item1, Item111]\n" + "Players in Space : [Player1]\n"
        + "\n" + "Space4\n" + "Items available : [Item4]\n" + "Players in Space : []\n" + "\n"
        + "Space5\n" + "Items available : []\n" + "Players in Space : []\n\n";
    assertEquals(expected, res);
  }

  @Test
  public void testLookAroundWhenPetInCurrentSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);

    String res = w.lookAround();
    StringBuilder sb = new StringBuilder();
    String expected = "Current Space : Space1\n" + "Items available : [Item1, Item111]\n"
        + "Players in Space : [Player1]\n" + "\n" + "Neighbours : \n" + "Space2\n"
        + "Items available : [Item2]\n" + "Players in Space : [Player2]\n" + "\n" + "Space3\n"
        + "Items available : [Item3]\n" + "Players in Space : []\n" + "\n" + "Space6\n"
        + "Items available : []\n" + "Players in Space : []\n" + "\n";
    assertEquals(expected, res);
  }

  @Test
  public void testValidPlayerDescription() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 1);
    w.addPlayer("Player2", "Space2", 1);
    w.addPlayer("Player3", "Space7", 1);
    w.pickUpItem("Item1");
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Player1\n");
    sb.append("Items : [Item1]\n");
    sb.append("Current Position : Space1\n");
    String response = w.displayPlayerDescription("Player1");
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
    sb.append("Items : []\n");
    sb.append("Current Position : Space1\n");
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
    sb.append("Items : []\n");
    sb.append("Current Position : Space1\n");
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
    String expected = "Player0 - Dr.Strange is in turn.\n";
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
    String expected = "Player2 - Ironman is in turn.\n";
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
    String expected = "Player0 - Batman is in turn.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testGetTurnForaComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space1", 5);
    String expected = "Player0 - Computer1 is in turn. Select a command.\n"
        + "Current Space : Space1\n" + "Items available : [Item1, Item111]\n"
        + "Players in Space : [Gowtham, Computer1]\n" + "\n" + "Neighbours : \n" + "Space2\n"
        + "Items available : [Item2]\n" + "Players in Space : []\n" + "\n" + "Space3\n"
        + "Items available : [Item3]\n" + "Players in Space : []\n" + "\n" + "Space6\n"
        + "Items available : []\n" + "Players in Space : []\n" + "\n" + "\n"
        + "Player1 - Gowtham is in turn.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testMoveForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(0, 1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    String expected = "Player0 - Computer1 is in turn. Select a command.\n"
        + "Computer1 moved to Space3\n" + "\n" + "Player1 - Gowtham is in turn.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testLookAroundForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    String expected = "Player0 - Computer1 is in turn. Select a command.\n"
        + "Current Space : Space1\n" + "Items available : [Item1, Item111]\n"
        + "Players in Space : [Computer1]\n" + "\n" + "Neighbours : \n" + "Space2\n"
        + "Items available : [Item2]\n" + "Players in Space : []\n" + "\n" + "Space3\n"
        + "Items available : [Item3]\n" + "Players in Space : []\n" + "\n" + "Space6\n"
        + "Items available : []\n" + "Players in Space : []\n" + "\n" + "\n"
        + "Player1 - Gowtham is in turn.\n";

    assertEquals(expected, w.getTurn());
  }

  @Test
  public void testPickUpItemForComputerPlayer() {
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Gowtham", "Space4", 5);
    StringBuilder sb = new StringBuilder();
    String expected = "Player0 - Computer1 is in turn. Select a command.\n"
        + "Computer1 picked up Item111 from Space1\n" + "\n" + "Player1 - Gowtham is in turn.\n";
    assertEquals(expected, w.getTurn());
  }

  @Test
  public void checkTargetPositionAfterMove() {
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.move("Space2");
    w.move("Space2");
    assertEquals("Space3", w.getTargetPosition());
  }

  @Test
  public void checkSpaceDescriptionAfterItemPickUp() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name : Space1\n");
    sb.append("Index : 0\n");
    sb.append("Items : [Item111]\n");
    sb.append("Visible Spaces : [Space2, Space3, Space6]\n");
    sb.append("Players in Space : [Player2, Player1, Computer1]\n");
    sb.append("Pet present : no\n");
    RandomGenerator rand = new RandomGeneratorMock(2, 1);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.pickUpItem("Item1");
    String result = w.getInfoOfSpace("Space1");

    assertEquals(sb.toString(), result);
  }

  @Test
  public void testMovePetToValidSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    String response = w.movePet("Space7");
    String expected = "Pet moved to Space7\n";
    assertEquals(expected, response);
  }

  @Test
  public void testMovePetToInvalidSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    String expected = "Space doesn't exist. Your turn is up!\n";
    String response = w.movePet("SpaceX");
    assertEquals(expected, response);
  }

  @Test
  public void testMovePetAfterEveryTurn() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    w.addPlayer("Player2", "Space1", 3);
    w.lookAround();
    w.lookAround();
    w.move("Space2");
    String expected = "Name : Space4\n" + "Index : 3\n" + "Items : [Item4]\n"
        + "Visible Spaces : [Space2, Space3, Space5]\n" + "Players in Space : []\n"
        + "Pet present : yes\n";
    assertEquals(expected, w.getInfoOfSpace("Space4"));
  }

  @Test
  public void testPetReturnsAfterFullDfsCycle() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    w.addPlayer("Player2", "Space1", 3);
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();
    w.lookAround();

    assertEquals("Space1", w.getPetLocation());
  }

  @Test
  public void testPetDfsTraversalAfterPlayerMoves() {
    World w = validWorld;
    w.addPlayer("Player1", "Space9", 5);
    w.addPlayer("Player2", "Space1", 3);
    w.movePet("Space2");
    w.lookAround();
    String expected = "Name : Space3\n" + "Index : 2\n" + "Items : [Item3]\n"
        + "Visible Spaces : [Space1, Space2, Space4, Space6]\n" + "Players in Space : []\n"
        + "Pet present : yes\n";
    assertEquals(expected, w.getInfoOfSpace("Space3"));
  }

  @Test
  public void testPlayerVisibilityWhenInCurrentSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 3);

    assertTrue(w.playerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerVisibilityWhenInNeighbouringSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space2", 3);

    assertTrue(w.playerCanSeeEachOther("Player1", "Player2"));
  }

  @Test
  public void testPlayerAttack() {
    World w = validWorld;
    w.addPlayer("Player1", "Space2", 5);
    w.pickUpItem("Item2");
    String res = w.attack("Item2");
    assertEquals(30, w.getTargetHealth());
    assertEquals("Name : Player1\nItems : []\nCurrent Position : Space2\n",
        w.displayPlayerDescription("Player1"));
  }

  @Test
  public void testComputerPlayerAttack() {
    RandomGenerator rand = new RandomGeneratorMock(2, 0, 0, 0, 1, 1, 1, 3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addComputerPlayer();
    w.addPlayer("Player1", "Space9", 5);
    assertEquals(50, w.getTargetHealth());
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();

    assertEquals(40, w.getTargetHealth());
    assertEquals("Name : Computer1\nItems : []\nCurrent Position : Space2\n",
        w.displayPlayerDescription("Computer1"));
  }

  @Test
  public void testComputerPlayerAttackWithMultipleItems() {
    RandomGenerator rand = new RandomGeneratorMock(2, 0, 2, 0, 0, 0, 1, 1, 3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addComputerPlayer();
    w.addPlayer("Player1", "Space9", 5);

    w.getTurn();
    w.lookAround();
    w.getTurn();
    assertEquals("Name : Computer1\nItems : [Item1, Item111]\nCurrent Position : Space1\n",
        w.displayPlayerDescription("Computer1"));

    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();
    w.lookAround();
    w.getTurn();

    assertEquals(30, w.getTargetHealth());

    assertEquals("Name : Computer1\nItems : [Item1]\nCurrent Position : Space2\n",
        w.displayPlayerDescription("Computer1"));
  }

  @Test
  public void testPlayerPoke() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    w.attack();
    assertEquals(49, w.getTargetHealth());
  }

  @Test
  public void testComputerPlayerPoke() {
    RandomGenerator rand = new RandomGeneratorMock(0, 1, 3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addComputerPlayer();
    w.addPlayer("Gow", "Space1", 5);
    w.getTurn();
    w.lookAround();
    w.getTurn();
    assertEquals(49, w.getTargetHealth());
  }

  @Test
  public void testPlayerAttackWhenPlayerInCurrentSpace() {
    RandomGenerator rand = new RandomGeneratorMock(3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.attack();
    assertEquals(50, w.getTargetHealth());
  }

  @Test
  public void testPlayerAttackWhenPlayerInNeighbour() {
    RandomGenerator rand = new RandomGeneratorMock(3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.pickUpItem("Item1");
    w.lookAround();
    w.move("Space6");
    w.lookAround();
    w.attack("Item1");
    assertEquals(50, w.getTargetHealth());
    assertEquals("Name : Player1\nItems : []\nCurrent Position : Space6\n",
        w.displayPlayerDescription("Player1"));
  }

  @Test
  public void testPlayerAttackWithPetInSpace() {
    RandomGenerator rand = new RandomGeneratorMock(3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space2", 5);
    w.attack();
    assertEquals(49, w.getTargetHealth());
  }

  @Test
  public void testPlayerAttackWhenTargetNotPresent() {
    RandomGenerator rand = new RandomGeneratorMock(3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space3", 5);
    w.addPlayer("Player2", "Space1", 5);
    w.attack();
    assertEquals(50, w.getTargetHealth());
  }

  @Test
  public void testPlayerAttackWithInvalidItem() {
    RandomGenerator rand = new RandomGeneratorMock(3);
    World w = createWorld(validWorldDescription, validTargetDescription, validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space1", 5);
    w.pickUpItem("Item1");
    String res = w.attack("Item2");
    String expected = "Attack failed! You don't have this item.\n";
    assertEquals(expected, res);
  }

  @Test
  public void testGameOverWhenPlayerKillsTarget() {
    World w = createWorld(validWorldDescription, "12 myTarget", validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);;
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space2", 5);
    w.addPlayer("Player3", "Space3", 5);

    w.pickUpItem("Item111");
    w.attack();
    w.attack();

    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");

    w.lookAround();
    w.move("Space5");
    w.move("Space4");

    w.attack("Item1");

    assertEquals(0, w.getTargetHealth());
    assertEquals("Player1", w.getWinner());
  }

  @Test
  public void testGameOverWhenComputerPlayerKillsTarget() {
    RandomGenerator rand = new RandomGeneratorMock(2, 0, 2, 0, 1, 3);
    World w = createWorld(validWorldDescription, "12 myTarget", validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Player2", "Space2", 5);
    w.addPlayer("Player3", "Space3", 5);

    w.getTurn();
    w.attack();
    w.attack();

    w.getTurn();
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");

    w.getTurn();
    w.move("Space5");
    w.move("Space4");

    w.getTurn();

    assertEquals(0, w.getTargetHealth());
    assertEquals("Computer1", w.getWinner());
  }

  @Test
  public void testGameOverWhenPlayerKillsTargetByPoke() {
    World w = createWorld(validWorldDescription, "3 myTarget", validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);
    w.addPlayer("Player1", "Space1", 5);
    w.addPlayer("Player2", "Space2", 5);
    w.addPlayer("Player3", "Space3", 5);

    w.pickUpItem("Item111");
    w.attack();
    w.attack();

    w.pickUpItem("Item1");
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");

    w.lookAround();
    w.move("Space5");
    w.move("Space4");

    w.attack();

    assertEquals(0, w.getTargetHealth());
    assertEquals("Player1", w.getWinner());
  }

  @Test
  public void testGameOverWhenComputerPlayerKillsTargetByPoke() {

    RandomGenerator rand = new RandomGeneratorMock(1, 1, 1, 3);
    World w = createWorld(validWorldDescription, "3 myTarget", validPetDescription,
        noOfSpaces, noOfItems, spaces, items, rand);

    w.addComputerPlayer();
    w.addPlayer("Player2", "Space2", 5);
    w.addPlayer("Player3", "Space3", 5);

    w.getTurn();
    w.attack();
    w.attack();

    w.getTurn();
    w.pickUpItem("Item2");
    w.pickUpItem("Item3");

    w.getTurn();
    w.move("Space5");
    w.move("Space4");

    w.getTurn();

    assertEquals(0, w.getTargetHealth());
    assertEquals("Computer1", w.getWinner());
  }

  @Test
  public void testGameTies() {
    String input = "30 30 MyWorld\n" + "50 MyTarget\n" + "My Pet\n" + "9\n" + "0 0 4 3 Space1 \n"
        + "0 4 1 14 Space2 \n" + "2 4 6 7 Space3 \n" + "2 8 11 9 Space4\n" + "2 10 19 11 Space5 \n"
        + "5 2 12 3 Space6 \n" + "13 0 14 9 Space7 \n" + "15 3 17 5 Space8\n" + "15 7 17 8 Space9\n"
        + "5\n" + "0 10 Item1\n" + "0 10 Item111 \n" + "1 20 Item2 \n" + "2 30 Item3 \n"
        + "3 40 Item4";
    Readable r = new StringReader(input);
    Model m = new ModelImpl(r, rand, 5);
    m.addPlayer("Gow", "Space1", 5);
    m.addPlayer("Gow2", "Space2", 5);
    m.lookAround();
    m.lookAround();
    m.lookAround();
    m.lookAround();
    m.lookAround();

    assertTrue(m.isGameOver());
    assertEquals("Game is tied!\n", m.getWinner());
  }

  @Test
  public void testIfPetAndTargetStartAtSameSpace() {
    World w = validWorld;
    w.addPlayer("Player1", "Space1", 5);
    assertTrue(w.getPetLocation().equals(w.getTargetPosition()));
  }
}