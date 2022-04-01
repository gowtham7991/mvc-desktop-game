package controllertest.mocks;

import java.awt.image.WritableRenderedImage;
import java.io.IOException;
import model.Model;

/**
 * Mock model used for testing the model.
 */
public class MockModel1 implements Model {

  private final String code;
  private final Appendable log;

  /**
   * Constructs the mock model with the log and uniqueCode as an input.
   * @param log the log
   * @param uniqueCode the unique code
   */
  public MockModel1(Appendable log, String uniqueCode) {
    this.log = log;
    this.code = uniqueCode;
  }

  @Override
  public WritableRenderedImage createGraphicalRepresentation() {
    try {
      log.append("Created the graphical representation for the world.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return null;
  }

  @Override
  public String getInfoOfSpace(String name) {
    try {
      log.append("Displaying information of ").append(name).append("\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getNeighboursOf(String name) {
    try {
      log.append("Displaying neighbours of ").append(name);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String addPlayer(String name, String space, int limit) {
    try {
      log.append("Player ").append(name).append(" added to ").append(space).append("\n")
          .append("Max items limit : ").append(Integer.toString(limit)).append(".\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String addComputerPlayer() {
    try {
      log.append("Computer player added.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String move(String space) {
    try {
      log.append("Player moved to space ").append(space).append("\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String movePet(String space) {
    try {
      log.append("Pet is moved to ").append(space).append(".\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public String getTurn() {
    try {
      log.append("The player is in turn.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String lookAround() {
    try {
      log.append("Looking around the space player is in.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String displayPlayerDescription(String name) {
    try {
      log.append("Displaying player description.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String pickUpItem(String item) {
    try {
      log.append("Player has picked up the item.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    try {
      log.append("Displaying neighbours of the player.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getItemsInCurrentSpace() {
    try {
      log.append("Displaying the items in current space\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getItemsOfPlayerInTurn() {
    try {
      log.append("Displaying the items of player.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getPlayers() {
    try {
      log.append("The players in the game.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }

    return code;
  }

  @Override
  public String getName() {
    try {
      log.append("Displaying the name of the game.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public int getTotalNumberOfHumanPlayers() {
    return 5;
  }

  @Override
  public String getAllSpaces() {
    try {
      log.append("List of spaces.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public String getCluesForTurn() {
    try {
      log.append("Clues for the turn.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public boolean isGameOver() {
    return true;
  }

  @Override
  public String getWinner() {
    try {
      log.append("The winner of the game is PlayerA.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public String attack(String itemName) {
    try {
      log.append("Target is attacked using ").append(itemName).append(".\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }

  @Override
  public String attack() {
    try {
      log.append("Target is poked in the eye.\n");
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Failed to append!");
    }
    return code;
  }
}