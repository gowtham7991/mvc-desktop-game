package controller.commands;

import controller.Command;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.Model;

/**
 * Command to create a layout of the game.
 */
public class CreateLayout implements Command {
  private final Scanner scan;
  private final Appendable out;

  /**
   * Constructs the command to create a layout.
   * @param scan the scanner to read input
   * @param out the output buffer to write the data
   */
  public CreateLayout(Scanner scan, Appendable out) {
    if (scan == null || out == null) {
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
          cmdResponse = "Layout created!\n";
          validExec = true;
          out.append(cmdResponse);
        } catch (IllegalArgumentException e) {
          out.append("Could not create a layout!\n");
        }
      }

    } catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed");
    }
  }
}
