package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mocks.MockModelInvalid;
import mocks.MockModelValid;
import mocks.MockViewValid;
import model.Model;
import view.View;

public class ControllerImplTest {
  private StringBuffer log;

  @Before
  public void setUp() throws Exception {
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
    assertEquals(
        "",
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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
        "",
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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

  }
  
  //Failure when no players
  @Test
  public void testBeginFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    assertEquals(
        "",
        log.toString());

  }
  @Test
  public void testBeginSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

  }
  @Test
  public void testHandleMouseCLickSuccess() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.handleGameFileUpload();
    cmd.startGame();
    cmd.begin();
    cmd.handleMouseClick(1200,1200);
    assertEquals(
        "",
        log.toString());

  }
  //Failure when out of bounds 
  @Test
  public void testHandleMouseCLickFailure() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.handleGameFileUpload();
    cmd.startGame();
    cmd.begin();
    cmd.handleMouseClick(1300,1200);
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

  }
  
  @Test
  public void testSetViewWhenViewisNull() {
    int uniqueN = 545;
    Model m = new MockModelInvalid(log, uniqueN);
    View v = null;
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    cmd.begin();
    assertEquals(
        "",
        log.toString());

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
    assertEquals(
        "",
        log.toString());

  }
  @Test
  public void testStartGame() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    assertEquals(
        "",
        log.toString());

  }
  @Test
  public void testIsGameOver() {
    int uniqueN = 545;
    Model m = new MockModelValid(log, uniqueN);
    View v = new MockViewValid(log, uniqueN);
    Controller cmd = new ControllerImpl(m);
    cmd.setView(v);
    cmd.startGame();
    assertEquals(
        "",
        log.toString());

  }

  
}
