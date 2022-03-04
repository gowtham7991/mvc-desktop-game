package controllertest;

import java.awt.image.WritableRenderedImage;

import model.Model;

public class MockModel implements Model {

  private StringBuilder log;
  private final String code;

  public MockModel(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.code = uniqueCode;
  }
  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    log.append("Created the graphical representation for the world.\n");

    return null;
  }

  @Override
  public String getInfoOfSpace(String name) {
    log.append("Displaying information of ").append(name).append("\n");
    return code;
  }

  @Override
  public String getNeighboursOf(String name) {
    log.append("Displaying neighbours of ").append(name);
    return code;
  }

  @Override
  public String addPlayer(String name, String space, int limit) {
    log.append("Player ")
            .append(name)
            .append(" added to ")
            .append(space)
            .append("\n")
            .append("Max items limit : ")
            .append(limit)
            .append(".\n");
    return code;
  }

  @Override
  public String addComputerPlayer() {
    log.append("Computer player added.\n");
    return code;
  }

  @Override
  public String move(String space) {
    log.append("Player moved to space ").append(space).append("\n");
    return code;
  }

  @Override
  public String getTurn() {
    log.append("The player is in turn.\n");
    return code;
  }

  @Override
  public String lookAround() {
    log.append("Looking around the space player is in.\n");
    return code;
  }

  @Override
  public String displayPlayerDescription(String name) {
    log.append("Displaying player description.\n");
    return code;
  }

  @Override
  public String pickUpItem(String item) {
    log.append("Player has picked up the item.\n");
    return code;
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    log.append("Displaying neighbours of the player.\n");
    return code;
  }

  @Override
  public String getItemsInCurrentSpace() {
    log.append("Displaying the items in current space\n");
    return code;
  }

  @Override
  public String getPlayers() {
    log.append("The players in the game.\n");
    return code;
  }

  @Override
  public String getName() {
    log.append("Displaying the name of the game.\n");
    return code;
  }

  @Override
  public int getTotalNumberOfPlayers() {
    return 0;
  }
}
