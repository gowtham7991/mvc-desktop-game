package model;

import java.awt.image.WritableRenderedImage;
import java.util.Scanner;
import model.layout.World;
import model.layout.WorldImpl;
import utils.ConfigFileParser;
import utils.RandomGenerator;

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
   * @param rand the random generator class object
   */
  public ModelImpl(Readable in, RandomGenerator rand) {

    if (in == null || rand == null) {
      throw new IllegalArgumentException("Invalid values passed!");
    }

    Scanner scan = new Scanner(in);
    ConfigFileParser parsedData = new ConfigFileParser(scan);

    World world = new WorldImpl(parsedData.getWorldDescription(), parsedData.getTargetDescription(),
        parsedData.getNoOfSpaces(), parsedData.getNoOfItems(), parsedData.getSpaces(),
        parsedData.getItems(), rand);
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
    return world.getNeighboursOf(name);
  }

  @Override
  public String addPlayer(String name, String space, int limit) throws IllegalArgumentException {
    return world.addPlayer(name, space, limit);
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
  public int getTotalNumberOfHumanPlayers() {
    return world.getTotalNumberOfHumanPlayers();
  }

  @Override
  public String getAllSpaces() {
    return world.getSpaces();
  }

  @Override
  public String displayPlayerDescription(String name) {
    return world.displayPlayerDescription(name);
  }
}
