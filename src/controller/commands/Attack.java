package controller.commands;

import model.Model;
import view.View;

public class Attack implements Command{
  private final View view;

  public Attack(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
