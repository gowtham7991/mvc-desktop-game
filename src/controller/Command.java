package controller;

import model.Model;

public interface Command {
    /**
     * Executes the given command on the model passed.
     * @param m the Model object
     * @return the response of the command
     */
    void execute(Model m);
}
