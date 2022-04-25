package mocks;

import java.awt.image.WritableRenderedImage;
import java.util.ArrayList;
import java.util.List;

import model.Model;

public class MockModelValid implements Model{
  private StringBuffer log;
  private int uniqueNumber;
  
  public MockModelValid(StringBuffer log, int uniqueNumber) {
    this.log = log;
    this.uniqueNumber = uniqueNumber;
  }
  
  @Override
  public String playerInTurn() {
    log.append("Inside method playerInTurn. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public List<String> getPlayers() {
    List<String> list=new ArrayList<String>();  
    list.add("player");
    log.append("Inside method getPlayers. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public String getCluesForTurn() {
    log.append("Inside method getCluesForTurn. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getName() {
    log.append("Inside method getName. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getCurrentPlayerPosition() {
    log.append("Inside method getCurrentPlayerPosition. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    log.append("Inside method createGraphicalRepresentation. Id = ").append(uniqueNumber);
    return null;
  }

  @Override
  public String getSpaceBasedOnCoordinates(int x, int y) {
    log.append("Inside method getSpaceBasedOnCoordinates. Id = ").append(uniqueNumber).append(x).append(y);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public boolean isComputerInTurn() {
    log.append("Inside method isComputerInTurn. Id = ").append(uniqueNumber);
    return false;
  }

  @Override
  public String getInfoOfSpace(String name) {
    log.append("Inside method getInfoOfSpace. Id = ").append(uniqueNumber).append(name);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getNeighboursOf(String name) {
    log.append("Inside method getNeighboursOf. Id = ").append(uniqueNumber).append(name);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String addPlayer(String name, String space, int limit) {
    log.append("Inside method addPlayer. Id = ").append(uniqueNumber).append(name).append(space).append(limit);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String addComputerPlayer() {
    log.append("Inside method addComputerPlayer. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String move(String space) {
    log.append("Inside method move. Id = ").append(uniqueNumber).append(space);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String move(int x, int y) {
    log.append("Inside method move. Id = ").append(uniqueNumber).append(x).append(y);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String movePet(String space) {
    log.append("Inside method movePet. Id = ").append(uniqueNumber).append(space);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String lookAround() {
    log.append("Inside method lookAround. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String displayPlayerDescription(String name) {
    log.append("Inside method displayPlayerDescription. Id = ").append(uniqueNumber).append(name);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String displayPlayerDescription() {
    log.append("Inside method displayPlayerDescription. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String pickUpItem(String item) {
    log.append("Inside method pickUpItem. Id = ").append(uniqueNumber).append(item);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    log.append("Inside method getNeighboursOfPlayerCurrentSpace. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public List<String> getItemsInCurrentSpace() {
    List<String> list=new ArrayList<String>();  
    list.add("item"); 
    log.append("Inside method getItemsInCurrentSpace. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public List<String> getItemsOfPlayerInTurn() {
    List<String> list=new ArrayList<String>();  
    list.add("item");
    log.append("Inside method getItemsOfPlayerInTurn. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public List<String> getAllSpaces() {
    List<String> list=new ArrayList<String>();  
    list.add("space");
    log.append("Inside method getAllSpaces. Id = ").append(uniqueNumber);
    return list;
  }

  @Override
  public int getTotalNumberOfHumanPlayers() {
    log.append("Inside method getTotalNumberOfHumanPlayers. Id = ").append(uniqueNumber);
    return uniqueNumber;
  }

  @Override
  public boolean isGameOver() {
    log.append("Inside method isGameOver. Id = ").append(uniqueNumber);
    return false;
  }

  @Override
  public String getWinner() {
    log.append("Inside method getWinner. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String attack(String itemName) {
    log.append("Inside method attack. Id = ").append(uniqueNumber).append(itemName);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public String attack() {
    log.append("Inside method attack. Id = ").append(uniqueNumber);
    return String.valueOf(uniqueNumber);
  }

  @Override
  public void reInitializeGame(Readable r) {
    log.append("Inside method reInitializeGame. Id = ").append(uniqueNumber).append(r);
    
  }

  @Override
  public void reInitializeGame() {
    log.append("Inside method reInitializeGame. Id = ").append(uniqueNumber);
    
  }

  @Override
  public boolean isGameInProgress() {
    log.append("Inside method isGameInProgress. Id = ").append(uniqueNumber);
    return false;
  }

}
