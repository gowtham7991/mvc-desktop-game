package controller.commands;

import model.Model;
import view.View;

public class AddPlayer implements Command{
  private final View view;

  public AddPlayer(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
