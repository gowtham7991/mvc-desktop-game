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
  }

  @Override
  public void startGame() {

  }

  @Override
  public void exit() {

  }

  @Override
  public void begin() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void pickUpItem() {

  }

  @Override
  public void addComputerPlayer() {

  }

  @Override
  public void addPlayer() {

  }

  @Override
  public void move() {

  }

  @Override
  public void movePet() {

  }

  @Override
  public void displayPlayerDesc() {

  }

  @Override
  public void attack() {

  }

  @Override
  public void setView(View v) {
    this.v = v;
    v.setFeatures(this);
  }
}
