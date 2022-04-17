package controller.commands;

import model.Model;

public interface Command {

  void execute(Model m);
}
