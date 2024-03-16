package mocks;

import java.awt.image.WritableRenderedImage;
import java.util.ArrayList;
import java.util.List;
import model.Model;

/**
 * This is an invalid mock model class. Its function is to test the controller
 * when invalid arguments are passed to the functions.
 * 
 *
 */
public class MockModelInvalid implements Model {
  private StringBuffer log;
  private int uniqueNumber;

  /**
   * This is the constructor of the class.
   * @param log the log for each method.
   * @param uniqueNumber the uniwue number.
   */
  public MockModelInvalid(StringBuffer log, int uniqueNumber) {
    this.log = log;
    this.uniqueNumber = uniqueNumber;
  }

  @Override
  public String playerInTurn() {
    log.append("Inside invalid model method playerInTurn. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public List<String> getPlayers() {
    List<String> list = new ArrayList<String>();
    list.add("player");
    log.append("Inside invalid model method getPlayers. Id = ").append(uniqueNumber);
    return list;

  }

  @Override
  public String getCluesForTurn() {
    log.append("Inside invalid model method getCluesForTurn. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getName() {
    log.append("Inside invalid model method getName. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getCurrentPlayerPosition() {
    log.append("Inside invalid model method getCurrentPlayerPosition. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    log.append("Inside invalid model method createGraphicalRepresentation. Id = ")
        .append(uniqueNumber);
    throw new IllegalArgumentException("No player.");
  }

  @Override
  public String getSpaceBasedOnCoordinates(int x, int y) {
    log.append("Inside invalid model method getSpaceBasedOnCoordinates. Id = ").append(uniqueNumber)
        .append(x).append(y);
    throw new IllegalArgumentException("invalid coordinates");
  }

  @Override
  public boolean isComputerInTurn() {
    log.append("Inside invalid model method isComputerInTurn. Id = ").append(uniqueNumber);
    return false;
  }

  @Override
  public String getInfoOfSpace(String name) {
    log.append("Inside invalid model method getInfoOfSpace. Id = ").append(uniqueNumber)
        .append(name);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getNeighboursOf(String name) {
    log.append("Inside invalid model method getNeighboursOf. Id = ").append(uniqueNumber)
        .append(name);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String addPlayer(String name, String space, int limit) {
    log.append("Inside invalid model method addPlayer. Id = ").append(uniqueNumber).append(name)
        .append(space).append(limit);
    throw new IllegalArgumentException("Invalid player.");
  }

  @Override
  public String addComputerPlayer() {
    log.append("Inside invalid model method addComputerPlayer. Id = ").append(uniqueNumber);
    throw new IllegalArgumentException("No player.");
  }

  @Override
  public String move(String space) {
    log.append("Inside invalid model method move. Id = ").append(uniqueNumber).append(space);
    throw new IllegalArgumentException("Invalid move.");
  }

  @Override
  public String move(int x, int y) {
    log.append("Inside invalid model method move. Id = ").append(uniqueNumber).append(x).append(y);
    throw new IllegalArgumentException("Invalid move.");
  }

  @Override
  public String movePet(String space) {
    log.append("Inside invalid model method movePet. Id = ").append(uniqueNumber).append(space);
    throw new IllegalArgumentException("Invalid Move Pet");
  }

  @Override
  public String lookAround() {
    log.append("Inside invalid model method lookAround. Id = ").append(uniqueNumber);
    throw new IllegalArgumentException("invalid look around");
  }

  @Override
  public String displayPlayerDescription(String name) {
    log.append("Inside invalid model method displayPlayerDescription. Id = ").append(uniqueNumber)
        .append(name);
    throw new IllegalArgumentException("Invalid player description.");
  }

  @Override
  public String displayPlayerDescription() {
    log.append("Inside invalid model method displayPlayerDescription. Id = ").append(uniqueNumber);
    throw new IllegalArgumentException("Invalid player description.");
  }

  @Override
  public String pickUpItem(String item) {
    log.append("Inside invalid model method pickUpItem. Id = ").append(uniqueNumber).append(item);
    throw new IllegalArgumentException("No item selected");
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    log.append("Inside invalid model method getNeighboursOfPlayerCurrentSpace. Id = ")
        .append(uniqueNumber);
    throw new IllegalArgumentException("Invalid neighbours.");
  }

  @Override
  public List<String> getItemsInCurrentSpace() {
    List<String> list = new ArrayList<String>();
    list.add("item");
    log.append("Inside invalid model method getItemsInCurrentSpace. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public List<String> getItemsOfPlayerInTurn() {
    List<String> list = new ArrayList<String>();
    list.add("player");
    log.append("Inside invalid model method getItemsOfPlayerInTurn. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public List<String> getAllSpaces() {
    List<String> list = new ArrayList<String>();
    list.add("space");
    log.append("Inside invalid model method getAllSpaces. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public int getTotalNumberOfHumanPlayers() {
    log.append("Inside invalid model method getTotalNumberOfHumanPlayers. Id = ")
        .append(uniqueNumber);
    throw new IllegalArgumentException("Invalid human players.");
  }

  @Override
  public boolean isGameOver() {
    log.append("Inside invalid model method isGameOver. Id = ").append(uniqueNumber);
    return false;
  }

  @Override
  public String getWinner() {
    log.append("Inside invalid model method getWinner. Id = ").append(uniqueNumber);
    throw new IllegalArgumentException("Invalid get winner.");
  }

  @Override
  public String attack(String itemName) {
    log.append("Inside invalid model method getPlayers. Id = ").append(uniqueNumber)
        .append(itemName);
    throw new IllegalArgumentException("Invalid attack");
  }

  @Override
  public String attack() {
    log.append("Inside invalid model method attack. Id = ").append(uniqueNumber);
    throw new IllegalArgumentException("Invalid attack");
  }

  @Override
  public void reInitializeGame(Readable r) {
    log.append("Inside invalid model method getPlayers. Id = ").append(uniqueNumber).append(r);

  }

  @Override
  public void reInitializeGame() {
    log.append("Inside invalid model method reInitializeGame. Id = ").append(uniqueNumber);

  }

  @Override
  public boolean isGameInProgress() {
    log.append("Inside invalid model method isGameInProgress. Id = ").append(uniqueNumber);
    return false;
  }

}
