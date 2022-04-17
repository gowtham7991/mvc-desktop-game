package controller;

import controller.commands.Command;
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
  }

  public void ControllerImpl() {

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
