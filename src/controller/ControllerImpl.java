package controller;

import controller.commands.AddComputerPlayer;
import controller.commands.AddPlayer;
import controller.commands.Attack;
import controller.commands.Command;
import controller.commands.LookAround;
import controller.commands.Move;
import controller.commands.MovePet;
import controller.commands.PickUpItem;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import model.Model;
import view.View;

public class ControllerImpl implements Controller{

  private final Model m;
  private View v;
  private final Map<String, Function<View, Command>> commands;

  public ControllerImpl(View v, Model m) {
    this.m = m;
    this.commands = new HashMap<>();

    commands.put("attack", (view) -> new Attack(v));
    commands.put("pickup", (view) -> new PickUpItem(v));
    commands.put("move", (view) -> new Move(v));
    commands.put("movepet", (view) -> new MovePet(v));
    commands.put("lookaround", (view) -> new LookAround(v));
    commands.put("addplayer", (view) -> new AddPlayer(v));
    commands.put("addcomputerplayer", (view) -> new AddComputerPlayer(v));
    commands.put("displayplayerdesc", (view) -> new AddComputerPlayer(v));
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
    execCommand("pickup");
  }

  @Override
  public void addComputerPlayer() {
    execCommand("addcomputerplayer");
  }

  @Override
  public void addPlayer() {
    execCommand("addplayer");
  }

  @Override
  public void movePet() {
    execCommand("movepet");
  }

  @Override
  public void attack() {
    execCommand("attack");
  }

  @Override
  public void handleMouseClick(int x, int y) {
    String spaceClicked = m.getSpaceBasedOnCoordinates(x, y);
    String playerCurrentPosition = m.getCurrentPlayerPosition();

    if (spaceClicked.equalsIgnoreCase(playerCurrentPosition)) {
      execCommand("displayplayerdesc");
    } else {
      execCommand("move");
    }
  }

  @Override
  public void setView(View v) {
    this.v = v;
    v.setFeatures(this);
  }

  private void execCommand(String command) {
    Command c;
    Function<View, Command> cmd = commands.getOrDefault(command, null);
    if (cmd == null) {
      throw new IllegalArgumentException("Command not found!");
    }
    c = cmd.apply(v);
    c.execute(m);
  }
}
