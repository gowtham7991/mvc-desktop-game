package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Parser for the given configuration file.
 */
public class ConfigFileParser {
  private String worldDescription;
  private String targetDescription;
  private String petDescription;
  private int noOfSpaces;
  private int noOfItems;
  private List<String> spaces;
  private List<String> items;

  /**
   * Constructs the config file parser. The data is read from the scanner object.
   *
   * @param scan the scanner to read data
   */
  public ConfigFileParser(Scanner scan) {
    if (scan == null) {
      throw new IllegalArgumentException("Invalid values passed!");
    }
    parseFile(scan);
  }

  /**
   * Helper method to parse the input file.
   *
   * @param sc the scanner object
   */
  private void parseFile(Scanner sc) {
    String worldDescription = "";
    String targetDescription = "";
    String petDescription = "";
    int noOfSpaces = -1;
    int noOfItems = -1;
    List<String> spaces = new ArrayList<>();
    List<String> items = new ArrayList<>();
    int worldDescLine = 1;
    int targetDescLine = 2;
    int petDescLine = 3;
    int spaceCount = 4;

    int lineNo = 1;

    // extracts world desc, target desc, noOfSpaces
    while (sc.hasNext()) {
      if (lineNo == worldDescLine) {
        worldDescription = sc.nextLine();
      } else if (lineNo == targetDescLine) {
        targetDescription = sc.nextLine();
      } else if (lineNo == petDescLine) {
        petDescription = sc.nextLine();
      } else if (lineNo == spaceCount) {
        // check if the no of spaces is a number
        String givenNoOfSpaces = sc.nextLine();
        try {
          noOfSpaces = Integer.parseInt(givenNoOfSpaces);
          if (noOfSpaces < 0) {
            throw new IllegalArgumentException("There has to be at least one space in the world!");
          }

          // loop through the spaces and create a list
          for (int i = 0; i < noOfSpaces; i++) {
            spaces.add(sc.nextLine());
            lineNo++;
          }
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("No of spaces has to be a valid integer!");
        }

        // check if the no of items is a number
        String givenNofItems = sc.nextLine();
        try {
          noOfItems = Integer.parseInt(givenNofItems);
          if (noOfItems < 0) {
            throw new IllegalArgumentException("No of items cannot be negative!");
          }

          // loop through the items and create a list
          for (int j = 0; j < noOfItems; j++) {
            items.add(sc.nextLine());
            lineNo++;
          }
        } catch (IllegalArgumentException exception) {
          throw new IllegalArgumentException("No of items has to be a valid integer!");
        }
      }
      lineNo++;
    }

    this.worldDescription = worldDescription;
    this.targetDescription = targetDescription;
    this.petDescription = petDescription;
    this.noOfSpaces = noOfSpaces;
    this.noOfItems = noOfItems;
    this.spaces = spaces;
    this.items = items;
  }

  /**
   * Returns the world description extracted from the file.
   *
   * @return the world description
   */
  public String getWorldDescription() {
    return worldDescription;
  }

  /**
   * Returns the target description extracted from the file.
   *
   * @return the target description
   */
  public String getTargetDescription() {
    return targetDescription;
  }

  /**
   * Returns the pet description extracted from the file.
   *
   * @return the pet description
   */
  public String getPetDescription() {
    return petDescription;
  }

  /**
   * Returns the total number of spaces defined.
   *
   * @return the total spaces
   */
  public int getNoOfSpaces() {
    return noOfSpaces;
  }

  /**
   * Returns the number of items in the world.
   *
   * @return the total items
   */
  public int getNoOfItems() {
    return noOfItems;
  }

  /**
   * Returns the list of space descriptions.
   *
   * @return the list of strings
   */
  public List<String> getSpaces() {
    List<String> list = new ArrayList<>();
    for (String str : spaces) {
      list.add(str);
    }
    return list;
  }

  /**
   * Returns the list of item description present in the world.
   *
   * @return the list string
   */
  public List<String> getItems() {
    List<String> list = new ArrayList<>();
    for (String str : items) {
      list.add(str);
    }
    return list;
  }
}
