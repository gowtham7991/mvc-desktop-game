package controllertest;

import static org.junit.Assert.assertEquals;

import controller.GameConsoleController;
import controller.GameController;
import controllertest.mocks.FailingAppendable;
import controllertest.mocks.MockModel;
import controllertest.mocks.MockModel1;
import java.io.StringReader;
import model.Model;
import org.junit.Test;

/**
 * Test suite for the game console controller. The controller tests the functionality using the
 * mock model. The commands from the controller are tested using the output from mock.
 */
public class GameConsoleControllerTest {

  @Test
  public void testStartWithModel() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n"
        + "### Add Players ###\n" + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n"
        + "123456\n" + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n" + "lookaround - "
        + "Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n"
        + "\n" + "123456123456>> Enter a command\n" + "### User quit the game ###\n"
        + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n";
    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartWithNullModel() {
    Appendable log = new FailingAppendable();
    StringBuffer out = new StringBuffer();
    int maxTurns = 20;
    Model model = new MockModel(log, "123456");
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailingAppendable() {
    Appendable log = new FailingAppendable();
    StringBuffer out = new StringBuffer();
    int maxTurns = 20;
    Model model = new MockModel(log, "123456");
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
  }

  @Test
  public void testMovePlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader(
        "addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n" + "addcomputerplayer - to add a "
        + "new computer player\n" + "start - to start the game\n" + "Enter your name: \n"
        + "# List of available spaces #\n" + "123456Enter the Space you wish to enter: \n"
        + "Enter the item limit: \n" + "123456\n" + "### Game has started ###\n" + "\n"
        + "Players : \n" + "123456--- Available Commands ---\n" + "\n"
        + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n"
        + "\n" + "123456123456>> Enter a command\n" + "Neighbouring spaces : \n" + "123456\n"
        + "Enter the Space you wish to enter: \n" + "123456\n" + "123456123456>> Enter a command\n"
        + "### User quit the game ###\n" + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying neighbours of the player.\n" + "Player moved to space Dining Hall\n"
        + "The player is in turn.\n" + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testPickUpItem() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader(
        "addplayer\nGowtham\nArmory\n5\nstart\npickup\nRevolver\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);
    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n"
        + "123456\n" + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Available items : 123456\n"
        + "Enter the item name: \n"
        + "123456\n" + "123456123456>> Enter a command\n" + "### User quit the game ###\n"
        + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n"
        + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying the items in current space\n"
        + "Player has picked up the item.\n" + "The player is in turn.\n" + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testLookAround() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nlookaround\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n"
        + "start - to start the game\n" + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n"
        + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "123456\n" + "123456123456>> Enter a command\n"
        + "### User quit the game ###\n" + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Looking around the space player is in.\n" + "The player is in turn.\n"
        + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testDisplayPlayerDescription() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\nplayerdesc\nGowtham1\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n"
        + "start - to start the game\n" + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n"
        + "Enter the item limit: \n" + "123456\n" + "### Game has started ###\n" + "\n"
        + "Players : \n" + "123456--- Available Commands ---\n" + "\n"
        + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Enter a player's name: \n" + "123456\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n" + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n" + "List of spaces.\n"
        + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying player description.\n" + "The player is in turn.\n" + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testGetInfoOfSpace() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\ngetinfo\nArmory\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n" + "List of spaces.\n"
        + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying information of Armory\n" + "The player is in turn.\n"
        + "Clues for the turn.\n";
    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Enter the name of the space: \n" + "123456\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n" + "GAME HAS ENDED!\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader(
        "addplayer\nGowtham\nArmory\n5\naddplayer\nGowtham1\nArmory\n5\nstart\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n"
        + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n" + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n"
        + "List of spaces.\n" + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "List of spaces.\n" + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddComputerPlayer() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addcomputerplayer\naddcomputerplayer\nstart\nquit\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "123456\n" + "123456\n" + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n" + "GAME HAS ENDED!\n";
    String logExpected = "Displaying the name of the game.\n" + "Computer player added.\n"
        + "Computer player added.\n" + "The players in the game.\n" + "The player is in turn.\n"
        + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testMoveAfterMaxTurns() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall");
    inBuilder.append("\nmove\nDrawing Room\nmove\nLilac Room\nmove\nBilliard Room\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 3;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n"
        + "123456\n" + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Neighbouring spaces : \n" + "123456\n"
        + "Enter the Space you wish to enter: \n" + "123456\n" + "123456123456>> Enter a command\n"
        + "Neighbouring spaces : \n" + "123456\n" + "Enter the Space you wish to enter: \n"
        + "123456\n" + "123456123456>> Enter a command\n" + "Neighbouring spaces : \n" + "123456\n"
        + "Enter the Space you wish to enter: \n" + "123456\n" + "123456123456>> Enter a command\n"
        + "Neighbouring spaces : \n" + "123456\n" + "Enter the Space you wish to enter: \n"
        + "123456\n" + "123456123456>> Enter a command\n" + "### User quit the game ###\n"
        + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying neighbours of the player.\n" + "Player moved to space Dining Hall\n"
        + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying neighbours of the player.\n" + "Player moved to space Drawing Room\n"
        + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying neighbours of the player.\n" + "Player moved to space Lilac Room\n"
        + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying neighbours of the player.\n" + "Player moved to space Billiard Room\n"
        + "The player is in turn.\n" + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testMovePet() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\nmovepet\nDining Hall\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Available spaces : \n" + "123456\n"
        + "Enter the Space you wish to move the pet : \n" + "123456\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n"
        + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n" + "List of spaces.\n"
        + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "List of spaces.\n" + "Pet is moved to Dining Hall.\n" + "The player is in turn.\n"
        + "Clues for the turn.\n";

    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAttack() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\nattack\nItem1\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456123456>> Enter a command\n" + "Available items : \n" + "123456\n"
        + "Enter the item you would want to use or use poke: \n" + "123456\n"
        + "123456123456>> Enter a command\n" + "### User quit the game ###\n"
        + "GAME HAS ENDED!\n";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n" + "List of spaces.\n"
        + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The player is in turn.\n" + "Clues for the turn.\n"
        + "Displaying the items of player.\n" + "Target is attacked using Item1.\n"
        + "The player is in turn.\n" + "Clues for the turn.\n";
    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testGetWinner() {
    StringBuffer out = new StringBuffer();
    StringBuilder inBuilder = new StringBuilder();
    inBuilder.append("addplayer\nGowtham\nArmory\n5\naddplayer");
    inBuilder.append("\nGowtham1\nArmory\n5\nstart\nattack\nItem1\nquit\n");
    Readable in = new StringReader(inBuilder.toString());
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel1(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    String outExpected = "\n" + "### Welcome to 123456 ###\n" + "\n" + "### Add Players ###\n"
        + "\n" + "addplayer - to add a new human player\n"
        + "addcomputerplayer - to add a new computer player\n" + "start - to start the game\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "Enter your name: \n" + "# List of available spaces #\n"
        + "123456Enter the Space you wish to enter: \n" + "Enter the item limit: \n" + "123456\n"
        + "### Game has started ###\n" + "\n" + "Players : \n"
        + "123456--- Available Commands ---\n" + "\n" + "layout - generate a layout of the game\n"
        + "playerdesc - Displays the description of a player\n"
        + "getinfo - Displays information about a space\n"
        + "lookaround - Displays the details of a specific space the player currently is in\n"
        + "move - Move to the neighbouring space\n"
        + "pickup - Pickup an item from the current space\n"
        + "attack - Attack the target using an item in possession\n"
        + "movepet - Move the pet to a space in the world\n" + "quit - quit the game\n" + "\n"
        + "123456";

    String logExpected = "Displaying the name of the game.\n" + "List of spaces.\n"
        + "Player Gowtham added to Armory\n" + "Max items limit : 5.\n" + "List of spaces.\n"
        + "Player Gowtham1 added to Armory\n" + "Max items limit : 5.\n"
        + "The players in the game.\n" + "The winner of the game is PlayerA.\n";
    assertEquals(logExpected, log.toString());
    assertEquals(outExpected, out.toString());
  }
}