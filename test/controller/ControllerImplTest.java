package controller;

import static org.junit.Assert.assertEquals;

import mocks.MockModelInvalid;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.View;

/**
 * This is the test class for the main controller.
 *
 */
public class ControllerImplTest {
  private StringBuffer log;

  @Before
  public void setUp() {
    log = new StringBuffer();
  }

  // Testing basic functionalities
  @Test
  public void testPickUpItemSuccess() {
    int uniqueN = 145;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller c = new ControllerImpl(m);
    c.setView(v);
    c.startGame();
    c.begin();
    c.pickUpItem();
    assertEquals("Inside method setFeatures. Id = 145"
        + "Inside method startGame. Id = 145"
        + "Inside method isGameInProgress. Id = 145"
        + "Inside method showErrorMessage. Id = 145"
        + "Cannot start the gamePlayers not added."
        + "Inside method getItemsInCurrentSpace. Id = 145"
        + "Inside method openPrompt. Id = 145Pick an "
        + "item[item]Inside method pickUpItem. Id = 145145I"
        + "nside method showSuccessMessage. Id = 145"
        + "Item picked up!145Inside method refresh. Id = 145"
        + "Inside method isComputerInTurn. Id = 145"
        + "Inside method isGameOver. Id = 145",
        log.toString());

  }

  @Test
  public void testPickUpItemFailure() {
    int uniqueN = 445;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller c = new ControllerImpl(m);
    c.setView(v);
    c.startGame();
    c.begin();
    c.pickUpItem();
    assertEquals("Inside method setFeatures. Id = 445" 
        + "Inside method startGame. Id = 445Inside " + "invalid model method isGameInProgress. "
        + "Id = 445Inside method showErrorMessage. " + "Id = 445Cannot start the gamePlayers not "
        + "added.Inside invalid model method " + "getItemsInCurrentSpace. Id = 445"
        + "Inside method openPrompt. Id = 445" + "Pick an item[item]Inside invalid model method "
        + "pickUpItem. Id = 445445Inside method "
        + "showErrorMessage. Id = 445Failed to pick up item"
        + "No item selectedInside invalid model method " + "isComputerInTurn. Id = 445Inside "
        + "invalid model method isGameOver. Id = 445", log.toString());

  }

  @Test
  public void testAddComputerPlayerSuccess() {
    int uniqueN = 535;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.addComputerPlayer();
    assertEquals("Inside method setFeatures. Id = 535" 
        + "Inside method startGame. Id = 535" + "Inside method isGameInProgress. Id = 535"
        + "Inside method showErrorMessage. Id = 535" + "Cannot start the gamePlayers not added."
        + "Inside method addComputerPlayer. Id = 535" + "Inside method showSuccessMessage. Id = 535"
        + "Player added!535Inside method refresh. Id = 535", log.toString());

  }

  @Test
  public void testAddComputerPlayerFailure() {
    int uniqueN = 535;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.addComputerPlayer();
    assertEquals("Inside method setFeatures. Id = 535" 
        + "Inside method startGame. Id = 535" + "Inside invalid model method isGameInProgress. "
        + "Id = 535Inside method showErrorMessage. "
        + "Id = 535Cannot start the gamePlayers not added."
        + "Inside invalid model method addComputerPlayer. Id = 535"
        + "Inside method showErrorMessage. Id = 535" + "Failed to add the playerNo player."
        + "Inside method refresh. Id = 535", log.toString());

  }

  @Test
  public void testAddPlayerSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.addPlayer();
    assertEquals(
        "Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method getAllSpaces. Id = 545"
        + "Inside method openAddPlayerPrompt. Id = 545"
        + "[space]Inside method addPlayer. Id = 545"
        + "playerspace1Inside method showSuccessMessage. "
        + "Id = 545Player added!545Inside method refresh. Id = 545",
        log.toString());

  }

  @Test
  public void testAddPlayerFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.addPlayer();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method getAllSpaces. Id = 545"
        + "Inside method openAddPlayerPrompt. Id = 545[space]"
        + "Inside invalid model method addPlayer. Id = 545"
        + "playerspace1Inside method showErrorMessage. Id = 545"
        + "Failed to add the playerInvalid player.", log.toString());

  }

  @Test
  public void testAttackSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.attack();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method getItemsOfPlayerInTurn. Id = 545"
        + "Inside method openPrompt. Id = 545"
        + "Choose an item[item, poke]"
        + "Inside method attack. Id = 545545"
        + "Inside method showSuccessMessage. Id = 545"
        + "Target attacked!545Inside method refresh. Id = 545"
        + "Inside method isComputerInTurn. Id = 545"
        + "Inside method isGameOver. Id = 545", log.toString());

  }

  @Test
  public void testAttackFailure() {
    int uniqueN = 543;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.attack();
    assertEquals("Inside method setFeatures. Id = 543"
        + "Inside method startGame. Id = 543"
        + "Inside invalid model method isGameInProgress. Id = 543"
        + "Inside method showErrorMessage. Id = 543"
        + "Cannot start the gamePlayers not added."
        + "Inside invalid model method getItemsOfPlayerInTurn. Id = 543"
        + "Inside method openPrompt. Id = 543Choose an item[player, poke]"
        + "Inside invalid model method getPlayers. Id = 543543"
        + "Inside method showErrorMessage. Id = 543"
        + "Attack failed!Invalid attackInside "
        + "invalid model method isComputerInTurn. Id = 543"
        + "Inside invalid model method isGameOver. Id = 543", log.toString());

  }

  // Failure when no players
  @Test
  public void testBeginFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added.", log.toString());

  }

  @Test
  public void testBeginSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.addPlayer();
    cmd.begin();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method getAllSpaces. Id = 545"
        + "Inside method openAddPlayerPrompt. Id = 545[space]"
        + "Inside method addPlayer. Id = 545playerspace1"
        + "Inside method showSuccessMessage. Id = 545"
        + "Player added!545Inside method refresh. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added.", log.toString());

  }

  @Test
  public void testHandleGameFileUpload() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.handleGameFileUpload();
    cmd.startGame();
    cmd.begin();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method openFileUploadPrompt. Id = 545"
        + "Inside method reInitializeGame. Id = 545"
        + "java.io.FileReader@12d3a4e9Inside method startGame."
        + " Id = 545Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added.", log.toString());

  }

  @Test
  public void testHandleMouseClickSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.handleMouseClick(1200, 1200);
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method getSpaceBasedOnCoordinates. "
        + "Id = 54512001200Inside method getCurrentPlayerPosition. "
        + "Id = 545Inside method displayPlayerDescription. "
        + "Id = 545Inside method showSuccessMessage. Id = 545"
        + "Player details545Inside method refresh. Id = 545"
        + "Inside method isComputerInTurn. Id = 545"
        + "Inside method isGameOver. Id = 545", log.toString());

  }

  // Failure when clicked on any other whitespace.
  @Test
  public void testHandleMouseClickFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.handleMouseClick(1300, 1200);
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside invalid model method getSpaceBasedOnCoordinates. "
        + "Id = 54513001200Inside method showErrorMessage. "
        + "Id = 545Invalid clickinvalid coordinates", log.toString());

  }

  @Test
  public void testLookAroundSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.lookAround();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method lookAround. Id = 545"
        + "Inside method openLookAroundPrompt. Id = 545545"
        + "Inside method refresh. Id = 545Inside method "
        + "isComputerInTurn. Id = 545Inside method "
        + "isGameOver. Id = 545", log.toString());

  }

  @Test
  public void testLookAroundFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.lookAround();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added.Inside"
        + " invalid model method lookAround. Id = 545Inside "
        + "method showErrorMessage. Id = 545Could not retrieve "
        + "information!invalid look aroundInside invalid model "
        + "method isComputerInTurn. Id = 545"
        + "Inside invalid model method isGameOver. Id = 545", log.toString());

  }

  @Test
  public void testMovePetSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.movePet();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method getAllSpaces. Id = 545"
        + "Inside method openPrompt. Id = 545"
        + "Choose a space[space]Inside method movePet. Id = 545545"
        + "Inside method showSuccessMessage. Id = 545Pet moved!545"
        + "Inside method refresh. Id = 545Inside method is"
        + "ComputerInTurn. Id = 545"
        + "Inside method isGameOver. Id = 545", log.toString());

  }

  @Test
  public void testMovePetFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.movePet();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside invalid model method getAllSpaces. Id = 545"
        + "Inside method openPrompt. Id = 545Choose a space[space]"
        + "Inside invalid model method movePet. Id = 545545"
        + "Inside method showErrorMessage. Id = 545"
        + "Failed to moveInvalid Move Pet"
        + "Inside invalid model method isComputerInTurn. Id = 545"
        + "Inside invalid model method isGameOver. Id = 545", log.toString());

  }

  @Test
  public void testRestartSucess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.restart();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside method reInitializeGame. Id = 545"
        + "Inside method reset. Id = 545", log.toString());

  }

  @Test
  public void testRestartFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    cmd.restart();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside invalid model method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added."
        + "Inside invalid model method reInitializeGame. Id = 545"
        + "Inside method reset. Id = 545", log.toString());

  }

  @Test
  public void testSetView() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameInProgress. Id = 545"
        + "Inside method showErrorMessage. Id = 545"
        + "Cannot start the gamePlayers not added.", log.toString());

  }

  @Test
  public void testStartGame() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545", log.toString());

  }

  @Test
  public void testIsGameOver() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    m.isGameOver();
    assertEquals("Inside method setFeatures. Id = 545"
        + "Inside method startGame. Id = 545"
        + "Inside method isGameOver. Id = 545", log.toString());

  }

}
