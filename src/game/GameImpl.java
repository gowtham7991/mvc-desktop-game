package game;

import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import layout.World;
import layout.WorldImpl;
import utils.ConfigFileParser;

/**
 * This is the implementation the game.
 */
public class GameImpl implements Game {
  private World world;

  /**
   * Creates the game based on the given configuration file.
   *
   * @param configFilePath the path of the configuration file
   * @throws IOException - if the file given is unreadable
   */
  public GameImpl(String configFilePath) throws IOException {
    try {
      ConfigFileParser parsedData = new ConfigFileParser(configFilePath);
      World world = new WorldImpl(parsedData.getWorldDescription(),
                                  parsedData.getTargetDescription(),
                                  parsedData.getNoOfSpaces(),
                                  parsedData.getNoOfItems(),
                                  parsedData.getSpaces(),
                                  parsedData.getItems());
      this.world = world;
    } catch (IOException e) {
      throw new IOException("Could not open the given file!");
    }
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    return world.getBufferedImage();
  }

  @Override
  public String getInfoOf(String name) {
    return world.getInfoOf(name);
  }

  @Override
  public Set<String> getNeighboursOf(String name) {
    return world.getNeighboursOf(name);
  }

  @Override
  public void moveTarget() {
    world.moveTarget();
  }

  @Override
  public int getTargetPosition() {
    return world.getTargetPosition();
  }
}
