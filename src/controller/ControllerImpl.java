package controller;

import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controller.commands.Attack;
import controller.commands.Command;
import controller.commands.DisplayPlayerDescription;
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
  private final Map<String, Function<View, Command>> commands;

  public ControllerImpl(View v, Model m) {
    this.m = m;
    this.commands = new HashMap<>();

    commands.put("attack", (view) -> new Attack(view));
    commands.put("pickup", (view) -> new PickUpItem(view));
//    commands.put("move", (view, x, y) -> new Move(view, x, y));
    commands.put("movepet", (view) -> new MovePet(view));
    commands.put("lookaround", (view) -> new LookAround(view));
    commands.put("addplayer", (view) -> new AddPlayer(view));
    commands.put("addcomputerplayer", (view) -> new AddComputerPlayer(view));
    commands.put("displayplayerdesc", (view) -> new DisplayPlayerDescription(view));
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
    v.begin();
  }

  @Override
  public void restart() {

  }

  @Override
  public void pickUpItem() {
    Command c = new PickUpItem(v);
    c.execute(m);
  }

  @Override
  public void addComputerPlayer() {
    Command c = new AddComputerPlayer(v);
    c.execute(m);
  }

  @Override
  public void addPlayer() {
    Command c = new AddPlayer(v);
    c.execute(m);
  }

  @Override
  public void movePet() {
    Command c = new MovePet(v);
    c.execute(m);
  }

  @Override
  public void attack() {
    Command c = new Attack(v);
    c.execute(m);
  }

  @Override
  public void handleMouseClick(int x, int y) {
    String spaceClicked = m.getSpaceBasedOnCoordinates(x, y);
    String playerCurrentPosition = m.getCurrentPlayerPosition();

    Command c;
    if (spaceClicked.equalsIgnoreCase(playerCurrentPosition)) {
      c = new DisplayPlayerDescription(v);
    } else {
      c = new Move(v, x, y);
    }
    c.execute(m);
  }

  @Override
  public void setView(View v) {
    this.v = v;
    v.setFeatures(this);
  }
}
