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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import model.Model;
import model.ReadOnlyModel;
import view.View;

public class ControllerImpl implements Controller{

  private final Model m;
  private View v;

  public ControllerImpl(Model m) {
    this.m = m;
  }

  @Override
  public void startGame() {
    v.startGame();
  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void begin() {
    if (m.isGameInProgress()) {
      v.begin();
      if (m.isComputerInTurn()) {
        v.showSuccessMessage("", "Computer player took a turn!");
        v.refresh();
      }
    } else {
      v.showErrorMessage("Cannot start the game", "Players not added.");
    }
  }

  @Override
  public void restart() {

  }

  @Override
  public void pickUpItem() {
    Command c = new PickUpItem();
    c.execute(m, v);
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
  }

  @Override
  public void attack() {
    Command c = new Attack();
    c.execute(m, v);
  }

  @Override
  public void lookAround() {
    Command c = new LookAround();
    c.execute(m, v);
  }

  @Override
  public void handleGameFileUpload() {
    Command c = new GameLayoutUpload();
    c.execute(m, v);
  }

  @Override
  public void handleMouseClick(int x, int y) {
    String spaceClicked = m.getSpaceBasedOnCoordinates(x, y);
    String playerCurrentPosition = m.getCurrentPlayerPosition();

    Command c;
    if (spaceClicked.equalsIgnoreCase(playerCurrentPosition)) {
      c = new DisplayPlayerDescription();
    } else {
      c = new Move(x, y);
    }
    c.execute(m, v);
  }

  @Override
  public void setView(View v) {
    this.v = v;
    v.setFeatures(this);
  }
}
