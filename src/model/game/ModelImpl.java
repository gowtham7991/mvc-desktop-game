package model.game;

import java.awt.image.WritableRenderedImage;
import java.util.Scanner;

import utils.RandomGenerator;
import model.layout.World;
import model.layout.WorldImpl;
import utils.ConfigFileParser;

/**
 * This is the implementation the game.
 */
public class ModelImpl implements Model {
  private final World world;
  private final RandomGenerator rg;

  /**
   * Creates the game based on the given configuration file.
   *
   * @param in the readable object of the file provided
   */
  public ModelImpl(Readable in, RandomGenerator rand) {

    Scanner scan = new Scanner(in);
    ConfigFileParser parsedData = new ConfigFileParser(scan);

    World world = new WorldImpl(parsedData.getWorldDescription(),
                                parsedData.getTargetDescription(),
                                parsedData.getNoOfSpaces(),
                                parsedData.getNoOfItems(),
                                parsedData.getSpaces(),
                                parsedData.getItems());
    this.world = world;
    this.rg = rand;
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    return world.getBufferedImage();
  }

  @Override
  public String getInfoOfSpace(String name) {
    return world.getInfoOfSpace(name);
  }

  @Override
  public String getNeighboursOf(String name) {
    return world.getNeighboursOf(name).toString();
  }

  @Override
  public void moveTarget() {
    world.moveTarget();
  }

  @Override
  public int getTargetPosition() {
    return world.getTargetPosition();
  }

  @Override
  public String addPlayer(String name, String space) throws IllegalArgumentException {
    return world.addPlayer(name, space);
  }

  @Override
  public String addComputerPlayer() {
    return world.addComputerPlayer();
  }

  @Override
  public String move(String space) {
    return world.move(space);
  }

  @Override
  public String getTurn() {
    return world.getTurn();

  }

  @Override
  public String lookAround() {
    return world.lookAround();
  }

  @Override
  public String pickUpItem(String itemName) {
    return world.pickUpItem(itemName);
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    return world.getNeighboursOfPlayerCurrentSpace();
  }

  @Override
  public String getItemsInCurrentSpace() {
    return world.getItemsInCurrentSpace();
  }

  @Override
  public String getPlayers() {
    return world.getPlayers();
  }

  @Override
  public String getName() {
    return world.getName();
  }

  @Override
  public int getTotalNumberOfPlayers() {
    return world.getTotalNumberOfPlayers();
  }

  @Override
  public String displayPlayerDescription(String name) {
    return world.displayPlayerDescription(name);
  }
}
