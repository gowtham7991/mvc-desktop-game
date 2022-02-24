package model.game;

import java.awt.image.WritableRenderedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import model.layout.World;
import model.layout.WorldImpl;
import utils.ConfigFileParser;

/**
 * This is the implementation the game.
 */
public class ModelImpl implements Model {
  private World world;

  /**
   * Creates the game based on the given configuration file.
   *
   * @param in the readable object of the file provided
   */
  public ModelImpl(Readable in) {

    Scanner scan = new Scanner(in);
    ConfigFileParser parsedData = new ConfigFileParser(scan);
    World world = new WorldImpl(parsedData.getWorldDescription(),
                                parsedData.getTargetDescription(),
                                parsedData.getNoOfSpaces(),
                                parsedData.getNoOfItems(),
                                parsedData.getSpaces(),
                                parsedData.getItems());
    this.world = world;

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
