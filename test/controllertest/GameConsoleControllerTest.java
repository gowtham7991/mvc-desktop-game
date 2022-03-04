package controllertest;

import org.junit.Test;

import java.io.StringReader;

import controller.GameConsoleController;
import controller.GameController;
import controllertest.mocks.MockModel;
import model.Model;

import static org.junit.Assert.assertEquals;

public class GameConsoleControllerTest {

  @Test
  public void testStartWithModel() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartWithNullModel() {
    Appendable log = new FailingAppendable();
    StringBuffer out = new StringBuffer();
    int maxTurns = 20;
    Model model = new MockModel(log, "123456");
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(null);
  }

  @Test (expected = IllegalArgumentException.class)
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
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456123456\n" +
            "Enter the Space you wish to enter: \n" +
            "123456123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testPickUpItem() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\npickup\nRevolver\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456Available items : 123456\n" +
            "Enter the item name: \n" +
            "123456123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testLookAround() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nlookaround\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456123456123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testDisplayPlayerDescription() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\naddplayer\nGowtham1\nArmory\n5\nstart\nplayerdesc\nGowtham1\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456Enter a player's name: \n" +
            "123456123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testGetInfoOfSpace() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\naddplayer\nGowtham1\nArmory\n5\nstart\ngetinfo\nArmory\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456Enter the name of the space: \n" +
            "123456123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddPlayer () {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\naddplayer\nGowtham1\nArmory\n5\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testAddComputerPlayer () {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addcomputerplayer\naddcomputerplayer\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 10;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "123456\n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456### User quit the game ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }

  @Test
  public void testMoveAfterMaxTurns() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("addplayer\nGowtham\nArmory\n5\nstart\nmove\nDining Hall\nmove\nDrawing Room\nmove\nLilac Room\nmove\nBilliard Room\n");
    StringBuilder log = new StringBuilder();
    int maxTurns = 3;
    Model model = new MockModel(log, "123456");
    GameController controller = new GameConsoleController(in, out, maxTurns);
    controller.start(model);

    StringBuilder outBuilder = new StringBuilder();
    String outExpected = "\n" +
            "### Welcome to 123456 ###\n" +
            "\n" +
            "### Add Players ###\n" +
            "\n" +
            "addplayer - to add a new human player\n" +
            "addcomputerplayer - to add a new computer player\n" +
            "start - to start the game\n" +
            "Enter your name: \n" +
            "Enter the Space you wish to enter: \n" +
            "Enter the item limit: \n" +
            "123456\n" +
            "### Game has started ###\n" +
            "\n" +
            "Players : \n" +
            "123456--- Available Commands ---\n" +
            "\n" +
            "layout - generate a layout of the game\n" +
            "playerdesc - Displays the description of a player\n" +
            "getinfo - Displays information about a space\n" +
            "lookaround - Displays the details of a specific space the player currently is in\n" +
            "move - Move to the neighbouring space\n" +
            "pickup - Pickup an item from the current space\n" +
            "quit - quit the game\n" +
            "\n" +
            "123456123456123456\n" +
            "Enter the Space you wish to enter: \n" +
            "123456123456123456123456\n" +
            "Enter the Space you wish to enter: \n" +
            "123456123456123456123456\n" +
            "Enter the Space you wish to enter: \n" +
            "123456123456123456### Max turns in the reached ###\n" +
            "GAME HAS ENDED!\n";

    assertEquals(outExpected, out.toString());
  }
}