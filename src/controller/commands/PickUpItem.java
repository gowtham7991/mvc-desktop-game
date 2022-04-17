package controller.commands;

import model.Model;
import view.View;

public class PickUpItem implements Command{
  private final View view;

  public PickUpItem(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
