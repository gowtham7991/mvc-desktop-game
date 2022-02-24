package controller;

import java.util.Scanner;

import model.game.Model;

public class GameConsoleController implements GameController{


  private final Scanner scan;
  private final Appendable out;

  public GameConsoleController(Readable in, Appendable out) {
    this.scan = new Scanner(in);
    this.out = out;
  }


  @Override
  public void start(Model m) {

  }
}
