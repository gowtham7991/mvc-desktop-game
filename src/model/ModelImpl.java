package model;

import java.awt.image.WritableRenderedImage;
import java.util.List;
import java.util.Scanner;
import model.layout.World;
import model.layout.WorldImpl;
import utils.ConfigFileParser;
import utils.RandomGenerator;

/**
 * This is the implementation the game.
 */
public class ModelImpl implements Model {
  private World world;
  private final RandomGenerator rg;
  private final int maxTurns;
  private int turns;

  /**
   * Creates the game based on the given configuration file.
   *
   * @param in   the readable object of the file provided
   * @param rand the random generator class object
   * @param maxTurns the max number of turns
   */
  public ModelImpl(Readable in, RandomGenerator rand, int maxTurns) {

    if (in == null || rand == null) {
      throw new IllegalArgumentException("Invalid values passed!");
    }

    reConfigureWorld(in, rand);
    this.rg = rand;
    this.maxTurns = maxTurns;
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation(int width, int height) {
    return world.getBufferedImage(width, height);
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
  public String addPlayer(String name, String space, int limit) throws IllegalArgumentException {
    return world.addPlayer(name, space, limit);
  }

  @Override
  public String addComputerPlayer() {
    return world.addComputerPlayer();
  }

  @Override
  public String move(String space) {
    turns += 1;
    return world.move(space);
  }

  @Override
  public String movePet(String space) {
    return world.movePet(space);
  }

  @Override
  public String getTurn() {
    return world.getTurn();
  }

  @Override
  public String lookAround() {
    turns += 1;
    return world.lookAround();
  }

  @Override
  public String pickUpItem(String itemName) {
    turns += 1;
    return world.pickUpItem(itemName);
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    return world.getNeighboursOfPlayerCurrentSpace();
  }

  @Override
  public List<String> getItemsInCurrentSpace() {
    return world.getItemsInCurrentSpace();
  }

  @Override
  public List<String> getItemsOfPlayerInTurn() {
    return world.getItemsOfPlayerInTurn();
  }

  @Override
  public List<String> getPlayers() {
    return world.getPlayers();
  }

  @Override
  public String getName() {
    return world.getName();
  }

  @Override
  public String getCurrentPlayerPosition() {
    return world.getCurrentPlayerPosition();
  }

  @Override
  public int getTotalNumberOfHumanPlayers() {
    return world.getTotalNumberOfHumanPlayers();
  }

  @Override
  public List<String> getAllSpaces() {
    return world.getSpaces();
  }

  @Override
  public String getCluesForTurn() {
    return world.getClues();
  }

  @Override
  public boolean isGameOver() {
    return turns >= maxTurns || world.getWinner() != null;
  }

  @Override
  public String getWinner() {
    StringBuilder sb = new StringBuilder();
    if (world.getWinner() == null) {
      sb.append("Game is tied!\n");
    } else {
      sb.append(world.getWinner()).append(" has won the game!\n");
    }

    return sb.toString();
  }

  @Override
  public String attack(String itemName) {
    return world.attack(itemName);
  }

  @Override
  public String attack() {
    return world.attack();
  }

  @Override
  public String getSpaceBasedOnCoordinates(int x, int y) {
    return world.getSpaceBasedOnCoordinates(x, y);
  }

  @Override
  public void reInitializeGame(Readable r) {
    reConfigureWorld(r, rg);
  }

  @Override
  public String displayPlayerDescription(String name) {
    return world.displayPlayerDescription(name);
  }

  private void reConfigureWorld(Readable r, RandomGenerator rand) {
    Scanner scan = new Scanner(r);
    ConfigFileParser parsedData = new ConfigFileParser(scan);

    this.world = new WorldImpl(parsedData.getWorldDescription(), parsedData.getTargetDescription(),
        parsedData.getPetDescription(), parsedData.getNoOfSpaces(), parsedData.getNoOfItems(),
        parsedData.getSpaces(), parsedData.getItems(), rand);
  }
}
