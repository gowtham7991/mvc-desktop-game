package controller.commands;

import model.Model;
import view.View;

public interface Command {

  void execute(Model m, View v);
}
