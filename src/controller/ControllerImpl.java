package controller;

import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controller.commands.Attack;
import controller.commands.Command;
import controller.commands.DisplayPlayerDescription;
import controller.commands.GameLayoutUpload;
import controller.commands.LookAround;
import controller.commands.Move;
import controller.commands.MovePet;
import controller.commands.PickUpItem;
import model.Model;
import view.View;

/**
 * This is the conrete implementation of the controller for the MVC application.
 * The controller implements all the functionalities required by the view which is the MVC game.
 */
public class ControllerImpl implements Controller {

  private final Model m;
  private View v;

  /**
   * Constructs the controller when a model is passed.
   * @param m the model
   */
  public ControllerImpl(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid model passed!");
    }
    this.m = m;
  }

  @Override
  public void startGame() {
    v.startGame();
  }

  @Override
  public void exit() {
    v.quit();
    System.exit(0);
  }

  @Override
  public void begin() {
    if (m.isGameInProgress()) {
      v.begin();
      perTurnChecks();
    } else {
      v.showErrorMessage("Cannot start the game", "Players not added.");
    }
  }

  @Override
  public void restart() {
    m.reInitializeGame();
    v.reset();
  }

  @Override
  public void pickUpItem() {
    Command c = new PickUpItem();
    c.execute(m, v);
    perTurnChecks();
  }

  @Override
  public void addComputerPlayer() {
    Command c = new AddComputerPlayer();
    c.execute(m, v);
  }

  @Override
  public void addPlayer() {
    Command c = new AddPlayer();
    c.execute(m, v);
  }

  @Override
  public void movePet() {
    Command c = new MovePet();
    c.execute(m, v);
    perTurnChecks();
  }

  @Override
  public void attack() {
    Command c = new Attack();
    c.execute(m, v);
    perTurnChecks();
  }

  @Override
  public void lookAround() {
    Command c = new LookAround();
    c.execute(m, v);
    perTurnChecks();
  }

  @Override
  public void handleGameFileUpload() {
    Command c = new GameLayoutUpload();
    c.execute(m, v);
  }

  @Override
  public void handleMouseClick(int x, int y) {
    try {
      String spaceClicked = m.getSpaceBasedOnCoordinates(x, y);
      String playerCurrentPosition = m.getCurrentPlayerPosition();

      Command c;
      if (spaceClicked.equalsIgnoreCase(playerCurrentPosition)) {
        c = new DisplayPlayerDescription();
      } else {
        c = new Move(x, y);
      }
      c.execute(m, v);
      perTurnChecks();
    } catch (IllegalArgumentException ie) {
      v.showErrorMessage("Invalid click", ie.getMessage());
    }
  }

  @Override
  public void setView(View v) {
    if (v == null) {
      throw new IllegalArgumentException("Invalid view passed!");
    }
    this.v = v;
    v.setFeatures(this);
  }

  private void perTurnChecks() {
    while (m.isComputerInTurn()) {
      System.out.println("player in turn " + m.playerInTurn());
      v.showSuccessMessage("", "Computer player took a turn!");
      v.refresh();
      if (m.isGameOver()) {
        String winner = m.getWinner();
        int response = v.openGameOverPrompt(winner);
        if (response == 0) {
          restart();
        } else {
          exit();
        }
      }
    }
    if (m.isGameOver()) {
      String winner = m.getWinner();
      int response = v.openGameOverPrompt(winner);
      if (response == 0) {
        restart();
      } else {
        exit();
      }
    }
  }
}
