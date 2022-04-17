package controller.commands;

import model.Model;
import view.View;

public class AddComputerPlayer implements Command{
  private final View view;

  public AddComputerPlayer(View view) {
    this.view = view;
  }

  @Override
  public void execute(Model m) {

  }
}
