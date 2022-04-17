package controller.commands;

import model.Model;
import view.View;

public class MovePet implements Command{
  private final View view;

  public MovePet(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
