package model;

import java.awt.image.WritableRenderedImage;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;
import model.layout.GameStatus;
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
  private GameStatus status;
  private final String defaultGameFilePath;

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
    status = GameStatus.NOTSTARTED;
    defaultGameFilePath = "assets/defaultGame.txt";
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    return world.getBufferedImage();
  }

  @Override
  public boolean isComputerInTurn() {
    boolean isComputerTurn = world.isComputerInTurn();
    if (isComputerTurn) {
      turns += 1;
    }
    return isComputerTurn;
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
    status = GameStatus.INPROGRESS;
    return world.addPlayer(name, space, limit);
  }

  @Override
  public String addComputerPlayer() {
    status = GameStatus.INPROGRESS;
    return world.addComputerPlayer();
  }

  @Override
  public String move(String space) {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.move(space);
  }

  @Override
  public String move(int x, int y) {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.move(x, y);
  }

  @Override
  public String movePet(String space) {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.movePet(space);
  }

  @Override
  public String lookAround() {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.lookAround();
  }

  @Override
  public String pickUpItem(String itemName) {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
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
  public String playerInTurn() {
    return world.playerInTurn();
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
    if (turns >= maxTurns || world.getWinner() != null) {
      status = GameStatus.COMPLETED;
      return true;
    }
    return false;
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
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.attack(itemName);
  }

  @Override
  public String attack() {
    if (status != GameStatus.INPROGRESS) {
      throw new IllegalArgumentException("Game not in progress!");
    }
    turns += 1;
    return world.attack();
  }

  @Override
  public String getSpaceBasedOnCoordinates(int x, int y) {
    return world.getSpaceBasedOnCoordinates(x, y);
  }

  @Override
  public void reInitializeGame(Readable r) {
    reConfigureWorld(r, rg);
    turns = 0;
    status = GameStatus.NOTSTARTED;
  }

  @Override
  public void reInitializeGame() {
    InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(defaultGameFilePath);
    Readable r = new InputStreamReader(in);
    reConfigureWorld(r, rg);
    turns = 0;
    status = GameStatus.NOTSTARTED;
  }

  @Override
  public boolean isGameInProgress() {
    if (status == GameStatus.INPROGRESS) {
      return true;
    }
    return false;
  }

  @Override
  public String displayPlayerDescription(String name) {
    return world.displayPlayerDescription(name);
  }

  @Override
  public String displayPlayerDescription() {
    return world.displayPlayerDescription();
  }

  private void reConfigureWorld(Readable r, RandomGenerator rand) {
    Scanner scan = new Scanner(r);
    ConfigFileParser parsedData = new ConfigFileParser(scan);

    this.world = new WorldImpl(parsedData.getWorldDescription(), parsedData.getTargetDescription(),
        parsedData.getPetDescription(), parsedData.getNoOfSpaces(), parsedData.getNoOfItems(),
        parsedData.getSpaces(), parsedData.getItems(), rand);
  }
}
