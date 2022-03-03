package controller.commands;

import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.game.Model;

public class CreateLayout implements Command {
  private final Scanner scan;
  private final Appendable out;


  public CreateLayout(Scanner scan, Appendable out) {
    if ( scan == null || out == null) {
      throw new IllegalArgumentException("Invalid parameters passed!");
    }
    this.scan = scan;
    this.out = out;
  }

  @Override
  public void execute(Model m) {

    try {
      String cmdResponse;
      boolean validExec = false;
      while (!validExec) {
        try {
          WritableRenderedImage img = m.createGraphicalRepresentation();
          File file = new File("WorldRepresentation.png");
          ImageIO.write(img, "png", file);
          validExec = true;
        }
        catch (IllegalArgumentException e) {
          out.append("Could not create a layout!\n");
        }
      }

    }
    catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}
