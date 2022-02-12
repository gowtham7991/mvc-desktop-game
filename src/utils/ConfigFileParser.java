package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Parser for the given configuration file.
 */
public class ConfigFileParser {
  private String worldDescription;
  private String targetDescription;
  private int noOfSpaces;
  private int noOfItems;
  private List<String> spaces;
  private List<String> items;

  public ConfigFileParser(String configFilePath) throws FileNotFoundException {
    parseFile(configFilePath);
  }

  private void parseFile(String configFilePath) throws FileNotFoundException {
    String worldDescription = "";
    String targetDescription = "";
    int noOfSpaces = -1;
    int noOfItems = -1;
    List<String> spaces = new ArrayList<>();
    List<String> items = new ArrayList<>();

    File configFile = new File(configFilePath);
    Scanner sc = new Scanner(configFile);
    int lineNo = 1;

    // extracts world desc, target desc, noOfSpaces
    while (sc.hasNext()) {
      if (lineNo == 1) {
        worldDescription = sc.nextLine();
      } else if (lineNo == 2) {
        targetDescription = sc.nextLine();
      } else if (lineNo == 3) {
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
          if (noOfItems < 0)  {
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
    this.noOfSpaces = noOfSpaces;
    this.noOfItems = noOfItems;
    this.spaces = spaces;
    this.items = items;
  }

  public String getWorldDescription() {
    return worldDescription;
  }


  public String getTargetDescription() {
    return targetDescription;
  }

  public int getNoOfSpaces() {
    return noOfSpaces;
  }

  public int getNoOfItems() {
    return noOfItems;
  }

  public List<String> getSpaces() {
    return spaces;
  }

  public List<String> getItems() {
    return items;
  }
}
