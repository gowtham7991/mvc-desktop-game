package controller.commands;

import model.Model;
import view.View;

public class LookAround implements Command{
  private final View view;

  public LookAround(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
