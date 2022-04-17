package controller.commands;

import model.Model;
import view.View;

public class Move implements Command{
  private final View view;

  public Move(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
