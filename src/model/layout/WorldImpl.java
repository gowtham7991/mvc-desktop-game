package model.layout;

import model.characters.Player;
import model.characters.PlayerImpl;
import model.characters.PlayerType;
import model.characters.PlayersIndex;
import model.characters.TargetImpl;
import model.characters.Target;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRenderedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;


/**
 * This represents the board of the game where all the spaces reside and also initlializes the grid
 * of units using which a space is constructed.
 */
public class WorldImpl implements World {
  private final int[][] grid;
  private final String name;
  private final int noOfRows;
  private final int noOfColumns;
  private final int noOfSpaces;
  private final int noOfItems;
  private Map<String, Space> spaceMap;
  private Map<String, Set<String>> neighboursMap;
  private final Target target;
  private PlayersIndex players;
  private int noOfPlayers;
  private int noOfComputerPlayers = 0;
  private int playerInTurn;

  /**
   * Constructs the using the specifications of the world, target and items.
   * @param worldDescription the description of the world in the format (rows, cols, name)
   * @param targetDescription the description of the target in the format (health, name)
   * @param noOfSpaces the number of spaces in the world
   * @param noOfItems the total number of items in the world
   * @param spaces the list of spaces as a string
   * @param items the list of items as a string
   */
  public WorldImpl(String worldDescription,
                   String targetDescription,
                   int noOfSpaces,
                   int noOfItems,
                   List<String> spaces,
                   List<String> items) {

    if ("".equals(worldDescription) || worldDescription == null ) {
      throw new IllegalArgumentException("Invalid World description!");
    }
    if ("".equals(targetDescription) || targetDescription == null ) {
      throw new IllegalArgumentException("Invalid Target description!");
    }
    if (noOfSpaces < 1) {
      throw new IllegalArgumentException("There has to be at least one space in the world!");
    }
    if (noOfItems < 0) {
      throw new IllegalArgumentException("No of items cannot be negative!");
    }
    if (noOfSpaces != spaces.size()) {
      throw new IllegalArgumentException("Sufficient description of spaces not provided!");
    }
    if (spaces == null || spaces.size() == 0) {
      throw new IllegalArgumentException("No space description provided!");
    }
    if (noOfItems != items.size()) {
      throw new IllegalArgumentException("Sufficient description of items not provided!");
    }
    if (items == null || items.size() == 0) {
      throw new IllegalArgumentException("No item description provided!");
    }

    this.noOfSpaces = noOfSpaces;
    this.noOfItems = noOfItems;

    List<Object> validatedWorldDesc = validateWorldDescription(worldDescription);
    this.noOfRows = (int) validatedWorldDesc.get(0);
    this.noOfColumns = (int) validatedWorldDesc.get(1);
    this.name = (String) validatedWorldDesc.get(2);

    List<Object> validatedTargetDesc = validateTargetDescription(targetDescription);
    this.target = new TargetImpl((Integer) validatedTargetDesc.get(0),
            (String) validatedTargetDesc.get(1),
            0);

    Map<String, Space> spaceMap = createSpaces(spaces, items);
    this.spaceMap = spaceMap;

    this.grid = populateGrid();

    Map<String, Set<String>> neighboursMap = populateNeighboursMap();
    this.neighboursMap = neighboursMap;

    this.noOfPlayers = 0;
    this.noOfComputerPlayers = 0;
    this.playerInTurn = 0;
    this.players = new PlayersIndex();
  }

  @Override
  public String getNeighboursOf(String name) throws IllegalArgumentException {
    if (! neighboursMap.containsKey(name)) {
      throw new IllegalArgumentException("Could not find the Space not found in the world!");
    }

    return neighboursMap.get(name).toString();
  }

  @Override
  public String getInfoOfSpace(String name) {
    if (spaceMap.containsKey(name)) {
      Space spaceObj = spaceMap.get(name);
      StringBuilder str = new StringBuilder();

      String spaceDetails = spaceObj.toString();
      String neighbours = String.format("Visible Spaces : %s\n", neighboursMap.get(name).toString());
      String playersInSpace = String.format("Players in Space : %s\n", players.getPlayersInSpace(name).toString());
      str.append(spaceDetails);
      str.append(neighbours);
      str.append(playersInSpace);

      return str.toString();
    }
    throw new IllegalArgumentException("Space not found in the world!");
  }

  @Override
  public WritableRenderedImage getBufferedImage() {
    int imgScaleFactor = 17;
    int layoutScaleFactor = 15;

    int width = noOfColumns * imgScaleFactor;
    int height = noOfRows * imgScaleFactor;

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = bufferedImage.getGraphics();

    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.black);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
    g.drawString(name, width / 2 - 50, 10);

    int spacesStartIdx = 3;
    int spacesEndIdx = spacesStartIdx + noOfSpaces;

    for (Space s : spaceMap.values()) {
      int topLeftRow = s.getTopLeftRow() * layoutScaleFactor;
      int topLeftCol = s.getTopLeftCol() * layoutScaleFactor;
      int bottomRightRow = s.getBottomRightRow() * layoutScaleFactor;
      int bottomRightCol = s.getBottomRightCol() * layoutScaleFactor;

      int x = topLeftCol;
      int y = topLeftRow;
      int spaceWidth = (bottomRightCol - topLeftCol) + layoutScaleFactor;
      int spaceHeight = (bottomRightRow - topLeftRow) + layoutScaleFactor;

      String spaceName = s.getName();

      g.drawRect(x,
              y,
              spaceWidth,
              spaceHeight);

      g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
      g.drawString(spaceName, x + 5, y + 10);
    }

    g.dispose();

    return bufferedImage;

  }

  @Override
  public void moveTarget() {
    int currentTargetPosition = target.getPosition();
    int newTargetPosition = (currentTargetPosition + 1) % noOfSpaces;
    target.moveTo(newTargetPosition);
  }

  @Override
  public int getTargetPosition() {
    return target.getPosition();
  }

  @Override
  public int getTotalNumberOfSpaces() {
    return noOfSpaces;
  }

  @Override
  public int getTotalNumberOfItems() {
    return noOfItems;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String addPlayer(String name, String space, int maxItemsPerPlayer) {
    if (spaceMap.containsKey(space)) {
      StringBuilder sr = new StringBuilder();
      Player player = new PlayerImpl(name, space, PlayerType.MANUAL, maxItemsPerPlayer);
      players.addPlayer(noOfPlayers, player);
      noOfPlayers += 1;

      sr.append(name).append(" added to ").append(space);
      return sr.toString();
    }
    else {
      throw new IllegalArgumentException("Space not found!");
    }
  }

  // Computer player is given a limit of 5 items
  @Override
  public String addComputerPlayer() {
    StringBuilder sr = new StringBuilder();
    String name = "Computer".concat(Integer.toString(noOfComputerPlayers+1));
    String space = spaceMap.keySet().iterator().next();
    int maxItemsPerPlayer = 5;
    Player player = new PlayerImpl(name, space, PlayerType.COMPUTER, maxItemsPerPlayer);
    players.addPlayer(noOfPlayers, player);
    noOfPlayers += 1;
    noOfComputerPlayers += 1;

    sr.append(name).append(" added to ").append(space);
    return sr.toString();
  }

  @Override
  public String move(String space) {
    StringBuilder sr = new StringBuilder();
    Player p =  players.getPlayerObj(playerInTurn);
    if (p.getPosition().equals(space)) {
      throw new IllegalArgumentException("You are currently in this space!");
    }
    p.moveTo(space);
    sr.append(p.getName())
            .append(" moved to ")
            .append(space)
            .append("\n");
    sr.append("Neighbours : ")
            .append(getNeighboursOf(space))
            .append("\n");
    sr.append("Items available : ")
            .append(spaceMap.get(space).getItems().toString())
            .append("\n");

    playerInTurn = (playerInTurn + 1) % noOfPlayers;
    moveTarget();
    return sr.toString();
  }

  @Override
  public String getTurn() {
    StringBuilder sr = new StringBuilder();

    Player player = players.getPlayerObj(playerInTurn);
    while (player.getPlayerType() == PlayerType.COMPUTER) {
      sr.append("Player")
              .append(playerInTurn)
              .append(" - ")
              .append(player.getName())
              .append(" is in turn. Select a command.")
              .append("\n");
      String str = controlComputerControlledPlayer();
      sr.append(str).append("\n");
      playerInTurn = (playerInTurn + 1) % noOfPlayers;
      player = players.getPlayerObj(playerInTurn);
    }
    sr.append("Player")
            .append(playerInTurn)
            .append(" - ")
            .append(player.getName())
            .append(" is in turn. Select a command.")
            .append("\n");
    return sr.toString();
  }

  @Override
  public String lookAround() {
    StringBuilder sr = new StringBuilder();
    String currentSpace = players.getPlayerObj(playerInTurn).getPosition();
    Set<String> neighbours = neighboursMap.get(currentSpace);

    sr.append("Current Space : ");
    sr.append(currentSpace);
    sr.append("\n");
    sr.append("Neighbours : \n");
    for (String n : neighbours) {
      sr.append(n).append("\n");
      Set<String> items = spaceMap.get(n).getItems();
      sr.append("Items available : ").append(items.toString()).append("\n");
      sr.append("\n");
    }

    playerInTurn = (playerInTurn + 1) % noOfPlayers;
    moveTarget();
    return sr.toString();
  }

  @Override
  public String displayPlayerDescription(String name) {
    int playerId = players.getIdOf(name);
    return players.getPlayerObj(playerId).toString();
  }

  @Override
  public String pickUpItem(String itemName) {
    Player p = players.getPlayerObj(playerInTurn);
    StringBuilder sr = new StringBuilder();
    String playerCurrentSpace = p.getPosition();

    if (spaceMap.get(playerCurrentSpace).getItems().contains(itemName)) {
      Item item = spaceMap.get(playerCurrentSpace).removeItem(itemName);
      p.pickUpItem(item);

      sr.append(p.getName())
              .append(" picked up ")
              .append(itemName)
              .append(" from ")
              .append(playerCurrentSpace)
              .append("\n");
      playerInTurn = (playerInTurn + 1) % noOfPlayers;
      moveTarget();
      return sr.toString();
    }
    else {
      throw new IllegalArgumentException("Item not found!");
    }
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    String playerCurrentSpace = players.getPlayerObj(playerInTurn).getPosition();
    String result = String.format("%s\n", getNeighboursOf(playerCurrentSpace));
    return result;
  }

  @Override
  public String getItemsInCurrentSpace() {
    String playerCurrentSpace = players.getPlayerObj(playerInTurn).getPosition();
    String result = spaceMap.get(playerCurrentSpace).getItems().toString();
    return result;
  }

  @Override
  public String getPlayers() {
    return players.toString();
  }

  @Override
  public int getTotalNumberOfPlayers() {
    return noOfPlayers;
  }

  private String controlComputerControlledPlayer() {
    Player player = players.getPlayerObj(playerInTurn);
    Random rnd = new Random();
    List<String> commands = new ArrayList<>(List.of("move", "lookAround", "pickUpItem"));
    boolean isValidCommand = false;
    String response = "";
    while (!isValidCommand) {
      int randomCmdIdx = rnd.nextInt(commands.size());
      String selectedCommand = commands.get(randomCmdIdx);

      if ("move".equals(selectedCommand)) {
        try {
          response = handleMoveComputerPlayer(player);
          isValidCommand = true;
        }
        catch (IllegalArgumentException e) {
          isValidCommand = false;
        }
      }
      else if ("lookAround".equals(selectedCommand)) {
        try {
          response =  handleLookAroundComputerPlayer(player);
          isValidCommand = true;
        }
        catch (IllegalArgumentException e) {
          isValidCommand = false;
        }
      }
      else if ("pickUpItem".equals(selectedCommand)) {
        try {
          response = handlePickUpItemComputerPlayer(player);
          isValidCommand = true;
        }
        catch (IllegalArgumentException e) {
          isValidCommand = false;
        }

      }
    }

    return response;
  }

  private String handleMoveComputerPlayer(Player p) {
    Random rnd = new Random();
    String currentSpace = players.getPlayerObj(playerInTurn).getPosition();
    Set<String> neighbourSet = neighboursMap.get(currentSpace);
    String[] neighbours = neighbourSet.toArray(new String[neighbourSet.size()]);
    int randomNeighbourIdx = rnd.nextInt(neighbours.length);
    String randomNeighbour = neighbours[randomNeighbourIdx];
    return move(randomNeighbour);
  }

  private String handlePickUpItemComputerPlayer(Player p) {
    Random rnd = new Random();
    String currentSpace = players.getPlayerObj(playerInTurn).getPosition();
    Set<String> items = spaceMap.get(currentSpace).getItems();

    int randomItemIdx = rnd.nextInt(items.size());
    String randomItem = findNthElementOfSet(items, randomItemIdx);
    return pickUpItem(randomItem);
  }

  private String handleLookAroundComputerPlayer(Player p) {
    return lookAround();
  }

  private String findNthElementOfSet(Set<String> set, int n) {
    int currIdx = 0;
    for (String str : set) {
      if (currIdx == n) {
        return str;
      }
      currIdx++;
    }
    return null;
  }

  /**
   * Helper method which validates the given description of the world
   * and returns rows, cols and name of the world.
   * @param input the description of the world as a string
   * @return the no of rows, cols and name
   * @throws IllegalArgumentException - if the rows/cols is negative
   */
  private List<Object> validateWorldDescription(String input) throws IllegalArgumentException {
    String str = input.trim();
    String[] strList = str.split("\\s+", 3);
    List<Object> validatedWorldDesc = new ArrayList<>();

    try {
      int rows = Integer.parseInt(strList[0]);
      int cols = Integer.parseInt(strList[1]);
      String name = strList[2];
      if (rows < 1 || cols < 1) {
        throw new IllegalArgumentException("There should be at least 1 row and col in the world!");
      }

      Collections.addAll(validatedWorldDesc, rows, cols, name);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid world description!");
    }
    return validatedWorldDesc;

  }

  /**
   * Helper method which validates the given description of the target and returns the values.
   *
   * @param input the target description
   * @return the target name and target health
   * @throws IllegalArgumentException - if the target health is negative
   */
  private List<Object> validateTargetDescription(String input) throws IllegalArgumentException {
    String str = input.trim();
    String[] strList = str.split("\\s+", 2);
    List<Object> validatedTargetDesc = new ArrayList<>();

    try {
      int health = Integer.parseInt(strList[0]);
      String name = strList[1];

      if (health < 0) {
        throw new IllegalArgumentException("Target health cannot be negative!");
      }

      Collections.addAll(validatedTargetDesc, health, name);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid target description!");
    }
    return validatedTargetDesc;
  }

  /**
   * Helper method which parses the config file data and updates the space map.
   */
  private Map<String, Space> createSpaces(List<String> spaces, List<String> items)
          throws IllegalArgumentException {

    Map<String, Space> spaceMap = new TreeMap<>(); // retains the insertion order
    List<Map<String, Item>> sortedItemList = sortItemsBasedOnSpaceIndex(items);

    for (int i = 0; i < spaces.size(); i++) {
      Space space = checkIfValidSpace(spaces.get(i), i, sortedItemList.get(i));
      if (spaceMap.containsKey(space.getName())) {
        throw new IllegalArgumentException("Spaces with the same name cannot exist!");
      }
      spaceMap.put(space.getName(), space);
    }

    return spaceMap;
  }

  /**
   * Helper method which maps the items to designated spaces in the world.
   *
   * @param items the list of items as a string
   * @return the ordered list of items a map
   */
  private List<Map<String, Item>> sortItemsBasedOnSpaceIndex(List<String> items)
          throws IllegalArgumentException {
    List<Map<String, Item>> sortedItems = new ArrayList<>();
    for (int i = 0; i < noOfSpaces; i++) {
      sortedItems.add(new HashMap<>());
    }

    for (String input : items) {
      String str = input.trim();
      String[] strList = str.split("\\s+", 3);
      try {
        int spaceIdx = Integer.parseInt(strList[0]);
        int itemDamage = Integer.parseInt(strList[1]);
        String itemName = strList[2];
        if ((spaceIdx >= 0 || spaceIdx < noOfSpaces) || itemDamage >= 0) {
          Item newItem = new ItemImpl(itemName, itemDamage, spaceIdx);

          sortedItems.get(spaceIdx).put(itemName, newItem);
        } else {
          throw new IllegalArgumentException("Invalid item description!");
        }
      }
      catch (ArrayIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Invalid item location!");
      }
    }

    return sortedItems;
  }

  /**
   * Helper method which checks if the given space description valid and updates the space mapping.
   *  @param input the space description from the config file
   * @param spaceIndex the 0-indexed value of the space
   * @param itemMap the mapping of items with respect to the space index
   */
  private Space checkIfValidSpace(String input, int spaceIndex, Map<String, Item> itemMap)
          throws IllegalArgumentException {
    String str = input.trim();
    String[] strList = str.split("\\s+", 5);

    int topLeftRow = Integer.parseInt(strList[0]);
    int topLeftCol = Integer.parseInt(strList[1]);
    int bottomRightRow = Integer.parseInt(strList[2]);
    int bottomRightCol = Integer.parseInt(strList[3]);
    String spaceName = strList[4];

    if (topLeftRow >= noOfRows
            || topLeftCol >= noOfColumns
            || bottomRightRow >= noOfRows
            || bottomRightCol >= noOfColumns

    ) {
      throw new IllegalArgumentException("Invalid space description - invalid coordinates!");
    }

    Space newSpace = new SpaceImpl(spaceName,
            spaceIndex,
            topLeftRow,
            topLeftCol,
            bottomRightRow,
            bottomRightCol,
            itemMap
    );
    return newSpace;

  }

  /**
   * Helper method which populates the grid with a given space index.
   *
   * @params the space map
   */
  private int[][] populateGrid() throws IllegalArgumentException {

    int[][] grid = new int[noOfRows][noOfColumns];

    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        grid[i][j] = -1;
      }
    }

    for (Space space : spaceMap.values()) {
      for (int i = space.getTopLeftRow();
           i < (space.getBottomRightRow() - space.getTopLeftRow()) + space.getTopLeftRow() + 1;
           i++) {
        for (int j = space.getTopLeftCol();
             j < (space.getBottomRightCol() - space.getTopLeftCol()) + space.getTopLeftCol() + 1;
             j++) {
          if (grid[i][j] != -1) {
            throw new IllegalArgumentException("Overlapping spaces not allowed!");
          }

          grid[i][j] = space.getIndex();
        }
      }
    }

    return grid;
  }

  /**
   * Helper method maps a space in the world with its neighbours.
   * Neighbours are spaces which share the same wall
   * and also are visible spaces from the given space.
   *
   * @return the neighbour map with space name and set of spaces
   */
  private Map<String, Set<String>> populateNeighboursMap() {
    Map<String, Set<String>> map = new HashMap();

    for (int row = 0; row < noOfRows; row++) {
      for (int col = 0; col < noOfColumns; col++) {
        int spaceIndex = grid[row][col];
        if (spaceIndex != -1) {
          String spaceName = (String) spaceMap.keySet().toArray()[spaceIndex];
          map = addNeighbour(row, col + 1, spaceName, map);
          map = addNeighbour(row, col - 1, spaceName, map);
          map = addNeighbour(row + 1, col, spaceName, map);
          map = addNeighbour(row - 1, col, spaceName, map);
        }
      }
    }

    return map;
  }

  /**
   * Helper method which adds a neighbour to a set for a specific space.
   *  @param row the row in the grid
   * @param col the column in the grid
   * @param name the name of the specified space in the world
   * @param map the neighbour map with space name and set of spaces
   */
  private Map<String, Set<String>> addNeighbour(int row,
                                                int col,
                                                String name,
                                                Map<String,
                                                        Set<String>> map) {
    if (! (row < 0 || row >= noOfRows || col < 0 || col >= noOfColumns)) {
      int neighbourIndex = grid[row][col];
      if (neighbourIndex != -1 && neighbourIndex != spaceMap.get(name).getIndex()) {
        String neighbourName = (String) spaceMap.keySet().toArray()[neighbourIndex];
        if (map.containsKey(name)) {
          map.get(name).add(neighbourName);
        } else {
          Set<String> newSet = new HashSet();
          newSet.add(neighbourName);
          map.put(name, newSet);
        }
      }
    }

    return map;
  }
}