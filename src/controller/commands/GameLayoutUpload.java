package controller.commands;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import model.Model;
import view.View;

public class GameLayoutUpload implements Command{

  @Override
  public void execute(Model m, View v) {
    try {
      File f = v.openFileUploadPrompt();
      Readable r = new FileReader(f);
      m.reInitializeGame(r);
      v.startGame();
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found!");
    }

  }
}
