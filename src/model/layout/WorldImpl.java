package model.layout;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import model.characters.Pet;
import model.characters.PetImpl;
import model.characters.Player;
import model.characters.PlayerImpl;
import model.characters.PlayerType;
import model.characters.Target;
import model.characters.TargetImpl;
import utils.RandomGenerator;

/**
 * This represents the board of the game where all the spaces reside and also initializes the grid
 * of units using which a space is constructed.
 */
public class WorldImpl implements World {
  private final int[][] grid;
  private final String name;
  private final int noOfRows;
  private final int noOfColumns;
  private final int noOfSpaces;
  private final int noOfItems;
  private final RandomGenerator randGen;
  private final Target target;
  private final Pet pet;
  private final Map<String, Space> spaceMap;
  private final Map<String, Set<String>> neighboursMap;
  private final Map<Integer, Player> players;
  private int noOfPlayers;
  private int noOfComputerPlayers;
  private int playerInTurn;
  private String winner;
  private final Stack<String> dfsNodes;
  private final Vector<Boolean> visitedNodes;
  private final int maxNoPlayers;

  /**
   * Constructs the using the specifications of the world, target and items.
   *
   * @param worldDescription  the description of the world in the format (rows, cols, name)
   * @param targetDescription the description of the target in the format (health, name)
   * @param petDescription    the description of the pet in the format (name)
   * @param noOfSpaces        the number of spaces in the world
   * @param noOfItems         the total number of items in the world
   * @param spaces            the list of spaces as a string
   * @param items             the list of items as a string
   * @param rand              the random generator
   */
  public WorldImpl(String worldDescription, String targetDescription, String petDescription,
      int noOfSpaces, int noOfItems, List<String> spaces, List<String> items,
      RandomGenerator rand) {

    if ("".equals(worldDescription) || worldDescription == null) {
      throw new IllegalArgumentException("Invalid World description!");
    }
    if ("".equals(targetDescription) || targetDescription == null) {
      throw new IllegalArgumentException("Invalid Target description!");
    }
    if ("".equals(petDescription) || petDescription == null) {
      throw new IllegalArgumentException("Invalid Pet description!");
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
    this.grid = new int[noOfRows][noOfColumns];
    // Initialize the grid
    for (int i = 0; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        grid[i][j] = -1;
      }
    }

    Map<String, Space> spaceMap = createSpaces(spaces, items);
    this.spaceMap = spaceMap;

    List<Object> validatedTargetDesc = validateTargetDescription(targetDescription);
    this.target = new TargetImpl((Integer) validatedTargetDesc.get(0),
        (String) validatedTargetDesc.get(1), (String) spaceMap.keySet().toArray()[0]);

    Map<String, Set<String>> neighboursMap = populateNeighboursMap();
    this.neighboursMap = neighboursMap;

    this.noOfPlayers = 0;
    this.noOfComputerPlayers = 0;
    this.playerInTurn = 0;
    this.winner = null;
    this.players = new TreeMap<>();
    this.randGen = rand;
    this.visitedNodes = new Vector<>(noOfSpaces);
    this.dfsNodes = new Stack<>();
    initializeDfs(0);

    this.pet = new PetImpl(petDescription, useDfsNode());
    this.maxNoPlayers = 10;
  }

  @Override
  public Set<String> getNeighboursOf(String name) throws IllegalArgumentException {
    if (!neighboursMap.containsKey(name)) {
      throw new IllegalArgumentException("Could not find the Space not found in the world!");
    }

    return neighboursMap.get(name);
  }

  @Override
  public String getInfoOfSpace(String name) {
    if (spaceMap.containsKey(name)) {
      Space spaceObj = spaceMap.get(name);
      StringBuilder str = new StringBuilder();

      String spaceDetails = spaceObj.toString();
      String neighbours = String.format("Visible Spaces : %s\n",
          neighboursMap.get(name).toString());
      String playersInSpace = String.format("Players in Space : %s\n", getAllPlayersInSpace(name));
      str.append(spaceDetails);
      str.append(neighbours);
      str.append(playersInSpace);

      if (name.equals(pet.getPosition())) {
        str.append("Pet present : yes\n");
      } else {
        str.append("Pet present : no\n");
      }

      return str.toString();
    }
    throw new IllegalArgumentException("Space not found in the world!");
  }

  @Override
  public WritableRenderedImage getBufferedImage(int width, int height) {

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = bufferedImage.getGraphics();

    g.setColor(Color.white);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.black);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 14));

    for (Space s : spaceMap.values()) {
      int topLeftRow = s.getTopLeftRow() * (height / noOfRows);
      int topLeftCol = s.getTopLeftCol() * (width / noOfColumns);
      int bottomRightRow = s.getBottomRightRow() * (height / noOfRows);
      int bottomRightCol = s.getBottomRightCol() * (width / noOfColumns);

      int x = topLeftCol;
      int y = topLeftRow;

      int spaceWidth = (bottomRightCol - topLeftCol) + (width / noOfColumns);
      int spaceHeight = (bottomRightRow - topLeftRow) + (height / noOfRows);

      String spaceName = s.getName();

      g.drawRect(x, y, spaceWidth, spaceHeight);

      g.setFont(new Font("TimesRoman", Font.PLAIN, (int) ((width / noOfColumns)*0.4)));
      g.drawString(spaceName, x + width / 200, y + height / 80);
    }

    drawPlayers(g, width, height);
    drawTarget(g, width, height);
    g.dispose();

    return bufferedImage;

  }

  private void drawPlayers(Graphics g, int width, int height) {
    for (String space : spaceMap.keySet()) {
      Space s = spaceMap.get(space);
      int topLeftRow = s.getTopLeftRow() * (height / noOfRows);
      int topLeftCol = s.getTopLeftCol() * (width / noOfColumns);
      int bottomRightRow = s.getBottomRightRow() * (height / noOfRows);
      int bottomRightCol = s.getBottomRightCol() * (width / noOfColumns);

      int spaceWidth = (bottomRightCol - topLeftCol) + (width / noOfColumns);
      int spaceHeight = (bottomRightRow - topLeftRow) + (height / noOfRows);

      Set<String> players = getAllPlayersInSpace(space);

      for (int i = 0; i < players.size(); i++) {
        String playerName = findNthElementOfSet(players, i);
        int playerId = playerIdBasedOnName(playerName);
        int offsetX, offsetY;
        if (i < 4) {
          offsetX = spaceWidth/6
              + (i%3)*(spaceWidth/3);
          offsetY = spaceHeight/6
              + (i/3)*(spaceHeight/3);
        } else {
          offsetX = spaceWidth/6
              + ((i+1)%3)*(spaceWidth/3);
          offsetY = spaceHeight/6
              + ((i+1)/3)*(spaceHeight/3);
        }

        int playerCoordinateX = topLeftCol + offsetX - spaceWidth/10;
        int playerCoordinateY = topLeftRow + offsetY - spaceHeight/10;

        try {
          String img = "player" + playerId + ".png";
          BufferedImage image = ImageIO.read(new File("res/", img));
          g.drawImage(image, playerCoordinateX, playerCoordinateY, null);
        } catch (IOException io) {
          throw new IllegalArgumentException("Could not read the file!");
        }
      }
    }
  }

  private void drawTarget(Graphics g, int width, int height) {
    String targetPosition = target.getPosition();
    Space s = spaceMap.get(targetPosition);

    int topLeftRow = s.getTopLeftRow() * (height / noOfRows);
    int topLeftCol = s.getTopLeftCol() * (width / noOfColumns);
    int bottomRightRow = s.getBottomRightRow() * (height / noOfRows);
    int bottomRightCol = s.getBottomRightCol() * (width / noOfColumns);

    int spaceWidth = (bottomRightCol - topLeftCol) + (width / noOfColumns);
    int spaceHeight = (bottomRightRow - topLeftRow) + (height / noOfRows);

    int offsetX = spaceWidth/6  + (4%3)*(spaceWidth/3);
    int offsetY = spaceHeight/6  + (4/3)*(spaceHeight/3);

    int targetCoordinateX = topLeftCol + offsetX - spaceWidth/10;
    int targetCoordinateY = topLeftRow + offsetY - spaceHeight/10;

    try {
      BufferedImage image = ImageIO.read(new File("res/", "target.png"));
      g.drawImage(image, targetCoordinateX, targetCoordinateY, null);
    } catch (IOException io) {
      throw new IllegalArgumentException("Could not read the file!");
    }
  }

  @Override
  public void moveTarget() {
    String currentTargetPosition = target.getPosition();
    int currSpaceIdx = spaceMap.get(currentTargetPosition).getIndex();
    int newTargetIdx = (currSpaceIdx + 1) % noOfSpaces;
    String newTargetPosition = (String) spaceMap.keySet().toArray()[newTargetIdx];
    target.moveTo(newTargetPosition);
  }

  @Override
  public String getTargetPosition() {
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
    if (noOfPlayers == 10) {
      throw new IllegalArgumentException("Max number of players added!");
    }
    if (spaceMap.containsKey(space)) {
      StringBuilder sr = new StringBuilder();
      Player player = new PlayerImpl(name, space, PlayerType.MANUAL, maxItemsPerPlayer,
          noOfPlayers);
      players.put(noOfPlayers, player);
      noOfPlayers += 1;

      sr.append(name).append(" added to ").append(space);
      return sr.toString();
    } else {
      throw new IllegalArgumentException("Space not found!");
    }
  }

  // Computer player is given a limit of 5 items
  @Override
  public String addComputerPlayer() {
    if (noOfPlayers == 10) {
      throw new IllegalArgumentException("Max number of players added!");
    }

    String name = "Computer".concat(Integer.toString(noOfComputerPlayers + 1));
    String space = spaceMap.keySet().iterator().next();
    int maxItemsPerPlayer = 5;
    Player player = new PlayerImpl(name, space, PlayerType.COMPUTER, maxItemsPerPlayer,
        noOfPlayers);
    players.put(noOfPlayers, player);
    noOfPlayers += 1;
    noOfComputerPlayers += 1;

    StringBuilder sr = new StringBuilder();
    sr.append(name).append(" added to ").append(space);
    return sr.toString();
  }

  @Override
  public String move(String space) {
    if (space == null) {
      throw new IllegalArgumentException("Invalid space!");
    }

    StringBuilder sr = new StringBuilder();
    Player p = players.get(playerInTurn);

    if (p.getPosition().equals(space)) {
      throw new IllegalArgumentException("Currently in the same space!");
    } else {
      if (!spaceMap.containsKey(space)) {
        throw new IllegalArgumentException("Space not found!");
      } else {
        if (getNeighboursOf(p.getPosition()).contains(space)) {
          p.moveTo(space);
          sr.append(p.getName()).append(" moved to ").append(space).append("\n");
        } else {
          throw new IllegalArgumentException("Not a neighbour!");
        }
      }
    }

    turnHelper();
    return sr.toString();
  }

  @Override
  public String getTurn() {
    StringBuilder sr = new StringBuilder();

    Player player = players.get(playerInTurn);
    while (player.getPlayerType() == PlayerType.COMPUTER) {
      sr.append("Player").append(playerInTurn).append(" - ").append(player.getName())
          .append(" is in turn. Select a command.").append("\n");
      String str = controlComputerControlledPlayer();
      sr.append(str).append("\n");
      player = players.get(playerInTurn);
    }
    if (winner == null) {
      sr.append("Player").append(playerInTurn).append(" - ").append(player.getName())
          .append(" is in turn.").append("\n");
    }

    return sr.toString();
  }

  @Override
  public String lookAround() {
    StringBuilder sr = new StringBuilder();
    String currentSpace = players.get(playerInTurn).getPosition();
    String currentPetPosition = pet.getPosition();
    Set<String> neighbours = neighboursMap.get(currentSpace);
    sr.append("Current Space : ");
    sr.append(displayPlayersAndItemsInaSpace(currentSpace));
    if (neighbours == null || neighbours.size() == 0) {
      sr.append("No neighbours for this space.\n");
    } else {
      sr.append("Neighbours : \n");
      for (String n : neighbours) {
        if (n != currentPetPosition) {
          sr.append(displayPlayersAndItemsInaSpace(n));
        }
      }
    }

    turnHelper();
    return sr.toString();
  }

  private String displayPlayersAndItemsInaSpace(String name) {
    StringBuilder sr = new StringBuilder();
    sr.append(name);
    sr.append("\n");
    Set<String> items = spaceMap.get(name).getItems();
    sr.append("Items available : ").append(items.toString()).append("\n");
    String playersInSpace = String.format("Players in Space : %s\n", getAllPlayersInSpace(name));
    sr.append(playersInSpace);
    sr.append("\n");
    return sr.toString();
  }

  @Override
  public String displayPlayerDescription(String name) {
    int playerId = playerIdBasedOnName(name);
    if (playerId == -1) {
      throw new IllegalArgumentException("Player not found!");
    }
    return players.get(playerId).toString();

  }

  @Override
  public String pickUpItem(String itemName) {
    if (itemName == null) {
      throw new IllegalArgumentException("Invalid item!");
    }

    Player p = players.get(playerInTurn);
    StringBuilder sr = new StringBuilder();
    String playerCurrentSpace = p.getPosition();

    if (spaceMap.get(playerCurrentSpace).getItems().size() == 0) {
      throw new IllegalArgumentException("No items found!");
    } else {
      if (spaceMap.get(playerCurrentSpace).getItems().contains(itemName)) {
        if (p.getItemCount() < p.getMaxItemCount()) {
          Item item = spaceMap.get(playerCurrentSpace).removeItem(itemName);
          p.pickUpItem(item);

          sr.append(p.getName()).append(" picked up ").append(itemName).append(" from ")
              .append(playerCurrentSpace).append("\n");
        } else {
          throw new IllegalArgumentException("Max item limit reached!");
        }

      } else {
        throw new IllegalArgumentException("Item not found!");
      }
    }

    turnHelper();
    return sr.toString();
  }

  @Override
  public String getNeighboursOfPlayerCurrentSpace() {
    String playerCurrentSpace = players.get(playerInTurn).getPosition();
    String result = String.format("%s\n", getNeighboursOf(playerCurrentSpace).toString());
    return result;
  }

  @Override
  public List<String> getItemsInCurrentSpace() {
    String playerCurrentSpace = players.get(playerInTurn).getPosition();
    Set<String> result = spaceMap.get(playerCurrentSpace).getItems();
    List<String> list = new ArrayList<>();

    for(String s : result) {
      list.add(s);
    }
    return list;
  }

  @Override
  public List<String> getItemsOfPlayerInTurn() {
    return players.get(playerInTurn).getItemList().stream().map(o -> o.getName())
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getPlayers() {
    List<String> list = new ArrayList<>();

    for (int id : players.keySet()) {
        Player p = players.get(id);
        list.add(p.getName());
    }

    return list;
  }

  @Override
  public int getTotalNumberOfHumanPlayers() {
    return noOfPlayers - noOfComputerPlayers;
  }

  @Override
  public List<String> getSpaces() {
    List<String> list = new ArrayList<>();
    for (String s : spaceMap.keySet()) {
      list.add(s);
    }
    return list;
  }

  @Override
  public String getClues() {
    StringBuilder sb = new StringBuilder();
    String currentSpaceName = players.get(playerInTurn).getPosition();
    sb.append("## Hints ##").append("\n");
    sb.append("Current space : ").append(currentSpaceName).append("\n");
    Set<String> items = spaceMap.get(currentSpaceName).getItems();
    sb.append("Items available : ").append(items.toString()).append("\n");
    String playersInSpace = String.format("Players in Space : %s\n",
        getAllPlayersInSpace(currentSpaceName));
    sb.append(playersInSpace);
    sb.append("Target location : ").append(target.getPosition()).append("\n");
    sb.append("Target health : ").append(target.getTargetHealth()).append("\n");
    sb.append("Pet location : ").append(pet.getPosition()).append("\n");
    return sb.toString();
  }

  @Override
  public String movePet(String spaceName) {
    if (spaceName == null) {
      throw new IllegalArgumentException("Invalid space!");
    }

    StringBuilder sb = new StringBuilder();
    if (spaceMap.containsKey(spaceName)) {
      int spaceIdx = spaceMap.get(spaceName).getIndex();
      initializeDfs(spaceIdx);
      sb.append("Pet moved to ").append(spaceName).append("\n");
      pet.moveTo(useDfsNode());
    } else {
      throw new IllegalArgumentException("Space does not exist!");
    }

    playerInTurn = (playerInTurn + 1) % noOfPlayers;
    moveTarget();
    return sb.toString();
  }

  @Override
  public String attack(String itemName) {
    if (itemName == null) {

    }
    StringBuilder sb = new StringBuilder();
    Item item = players.get(playerInTurn).useItem(itemName);
    if (item == null) {
      throw new IllegalArgumentException("Invalid item name!");
    }

    if (!isAttackSeen() && target.getPosition().equals(players.get(playerInTurn).getPosition())) {
      target.reduceHealth(item.getDamage());
      if (target.getTargetHealth() == 0) {
        winner = players.get(playerInTurn).getName();
      }
      sb.append("Target attacked. Target Health reduced to ").append(target.getTargetHealth())
          .append("\n");
    } else {
      if (!target.getPosition().equals(players.get(playerInTurn).getPosition())) {
        sb.append("Attack failed! Target not in this space.\n");
      } else if (isAttackSeen()) {
        sb.append("Attack failed! Your attack was defended by other players.\n");
      }
    }

    turnHelper();
    return sb.toString();
  }

  @Override
  public String attack() {
    StringBuilder sb = new StringBuilder();

    if ((!isAttackSeen())
        && target.getPosition().equals(players.get(playerInTurn).getPosition())) {
      target.reduceHealth(1);
      if (target.getTargetHealth() == 0) {
        winner = players.get(playerInTurn).getName();
      }
      sb.append("Target attacked. Target Health reduced to ").append(target.getTargetHealth())
          .append("\n");
    } else {
      if (!target.getPosition().equals(players.get(playerInTurn).getPosition())) {
        sb.append("Attack failed! Target not in this space.\n");
      } else if (isAttackSeen()) {
        sb.append("Attack failed! Your attack was defended by other players.\n");
      }
    }

    turnHelper();
    return sb.toString();
  }

  @Override
  public String getWinner() {
    return winner;
  }

  @Override
  public boolean playerCanSeeEachOther(String playerA, String playerB) {
    int idA = playerIdBasedOnName(playerA);
    int idB = playerIdBasedOnName(playerB);

    String posA = players.get(idA).getPosition();
    String posB = players.get(idB).getPosition();

    if (posA.equals(posB) && !posB.equals(pet.getPosition())) {
      return true;
    }
    for (String n : getNeighboursOf(posA)) {
      if (n.equals(posB) && !n.equals(pet.getPosition())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getPetLocation() {
    return pet.getPosition();
  }

  @Override
  public int getTargetHealth() {
    return target.getTargetHealth();
  }

  @Override
  public String getSpaceBasedOnCoordinates(int x, int y) {
    int spaceId = grid[x][y];
    if (spaceId == -1) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    String space = (String) spaceMap.keySet().toArray()[spaceId];
    return space;
  }

  /**
   * Controls the game play of a computer player by randomly choosing an action.
   * @return the details of the action
   */
  private String controlComputerControlledPlayer() {
    Player player = players.get(playerInTurn);
    List<String> commands = new ArrayList<>(List.of("move", "lookAround", "pickUpItem"));
    boolean isValidCommand = false;
    String response = "";

    if (!isAttackSeen() && target.getPosition().equals(players.get(playerInTurn).getPosition())) {
      response = handleAttackComputerPlayer();
      isValidCommand = true;
    }

    while (!isValidCommand) {
      int randomCmdIdx = randGen.getRandomInt() % commands.size();
      String selectedCommand = commands.get(randomCmdIdx);

      if ("move".equals(selectedCommand)) {
        try {
          response = handleMoveComputerPlayer();
          isValidCommand = true;
        } catch (IllegalArgumentException e) {
          isValidCommand = false;
        }
      } else if ("lookAround".equals(selectedCommand)) {
        try {
          response = handleLookAroundComputerPlayer();
          isValidCommand = true;
        } catch (IllegalArgumentException e) {
          isValidCommand = false;
        }
      } else if ("pickUpItem".equals(selectedCommand)) {
        try {
          response = handlePickUpItemComputerPlayer();
          isValidCommand = true;
        } catch (IllegalArgumentException e) {
          isValidCommand = false;
        }
      }
    }
    return response;
  }

  /**
   * Handles the attack for a computer player.
   * @return the details of the action
   */
  private String handleAttackComputerPlayer() {
    Set<Item> items = players.get(playerInTurn).getItemList();
    if (items.size() == 0) {
      return attack();
    } else {
      Comparator<Item> damage = Comparator.comparing(Item::getDamage);
      List<Item> sortedItemsAsc = items.stream().sorted(damage).collect(Collectors.toList());
      Item maxDamageItem = sortedItemsAsc.get(sortedItemsAsc.size() - 1);
      return attack(maxDamageItem.getName());
    }
  }

  /**
   * Handles the move for a computer player.
   * @return the details of the action
   */
  private String handleMoveComputerPlayer() {
    String currentSpace = players.get(playerInTurn).getPosition();
    Set<String> neighbourSet = neighboursMap.get(currentSpace);
    String[] neighbours = neighbourSet.toArray(new String[neighbourSet.size()]);
    int randomNeighbourIdx = randGen.getRandomInt() % neighbours.length;
    String randomNeighbour = neighbours[randomNeighbourIdx];
    return move(randomNeighbour);
  }

  /**
   * Handles the pickup item for a computer player.
   * @return the details of the action
   */
  private String handlePickUpItemComputerPlayer() {
    String currentSpace = players.get(playerInTurn).getPosition();
    Set<String> items = spaceMap.get(currentSpace).getItems();

    int randomItemIdx = randGen.getRandomInt() % items.size();
    String randomItem = findNthElementOfSet(items, randomItemIdx);
    return pickUpItem(randomItem);
  }

  /**
   * Handles the lookaround for a computer player.
   * @return the details of the action
   */
  private String handleLookAroundComputerPlayer() {
    return lookAround();
  }

  /**
   * Returns if the attack can be seen by other players.
   * @return true if attack is seen else false
   */
  private boolean isAttackSeen() {
    String currentPlayerSpace = players.get(playerInTurn).getPosition();
    Set<String> neighbours = getNeighboursOf(currentPlayerSpace);
    if (getAllPlayersInSpace(currentPlayerSpace).size() > 1) {
      return true;
    }

    if (currentPlayerSpace.equals(pet.getPosition())) {
      return false;
    }
    for (String n : neighbours) {
      if (getAllPlayersInSpace(n).size() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method to find the nth element of a set.
   * @return the name
   */
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
   *
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
    } catch (ArrayIndexOutOfBoundsException e) {
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

    if (strList.length != 2) {
      throw new IllegalArgumentException("Invalid target description!");
    }

    int health = Integer.parseInt(strList[0]);
    String name = strList[1];

    if (health < 0) {
      throw new IllegalArgumentException("Target health cannot be negative!");
    }
    Collections.addAll(validatedTargetDesc, health, name);

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
      if (strList.length != 3) {
        throw new IllegalArgumentException("Invalid item description!");
      }

      int spaceIdx = Integer.parseInt(strList[0]);
      int itemDamage = Integer.parseInt(strList[1]);
      String itemName = strList[2];
      if ((spaceIdx >= 0 || spaceIdx < noOfSpaces) || itemDamage >= 0) {
        Item newItem = new ItemImpl(itemName, itemDamage);

        sortedItems.get(spaceIdx).put(itemName, newItem);
      } else {
        throw new IllegalArgumentException("Invalid item description!");
      }
    }

    return sortedItems;
  }

  /**
   * Helper method which checks if the given space description valid and updates the space mapping.
   *
   * @param input      the space description from the config file
   * @param spaceIndex the 0-indexed value of the space
   * @param itemMap    the mapping of items with respect to the space index
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

    if (topLeftRow >= noOfRows || topLeftCol >= noOfColumns
        || bottomRightRow >= noOfRows || bottomRightCol >= noOfColumns

    ) {
      throw new IllegalArgumentException("Invalid space description - invalid coordinates!");
    }

    Space newSpace = new SpaceImpl(spaceName, spaceIndex, topLeftRow, topLeftCol, bottomRightRow,
        bottomRightCol, itemMap);
    for (int i = topLeftRow; i <= bottomRightRow; i++) {
      for (int j = topLeftCol; j <= bottomRightCol; j++) {
        if (grid[i][j] != -1) {
          throw new IllegalArgumentException("Overlapping spaces not allowed!");
        }
        grid[i][j] = spaceIndex;
      }
    }
    return newSpace;

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

    for (String spaceName : spaceMap.keySet()) {
      map.put(spaceName, new TreeSet<>());
    }

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
   *
   * @param row  the row in the grid
   * @param col  the column in the grid
   * @param name the name of the specified space in the world
   * @param map  the neighbour map with space name and set of spaces
   */
  private Map<String, Set<String>> addNeighbour(int row, int col, String name,
      Map<String, Set<String>> map) {
    if (!(row < 0 || row >= noOfRows || col < 0 || col >= noOfColumns)) {
      int neighbourIndex = grid[row][col];
      if (neighbourIndex != -1 && neighbourIndex != spaceMap.get(name).getIndex()) {
        String neighbourName = (String) spaceMap.keySet().toArray()[neighbourIndex];
        if (map.containsKey(name) && neighbourName != name) {
          map.get(name).add(neighbourName);
        }
      }
    }

    return map;
  }

  /**
   * Returns all the players in the space specified.
   *
   * @param name the name of the space
   * @return the set of players
   */
  private Set<String> getAllPlayersInSpace(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Invalid space name!");
    }
    Set<String> set = new HashSet<>();
    for (Player p : players.values()) {

      if (name.equals(p.getPosition())) {
        set.add(p.getName());
      }
    }

    return set;
  }

  /**
   * Moves the pet to the next space determined by the DFS traversal.
   */
  private void dfsMovePet() {
    String space = useDfsNode();
    pet.moveTo(space);
  }

  /**
   * Re-initializes the DFS stack strating from the given index of the space.
   * @param idx the index of the space
   */
  private void initializeDfs(int idx) {
    //Initialize DFS visited nodes
    for (int i = 0; i < noOfSpaces; i++) {
      visitedNodes.add(false);
    }
    String spaceName = spaceMap.get(spaceMap.keySet().toArray()[idx]).getName();
    dfsNodes.push(spaceName);
  }

  /**
   * Returns the top element found in the DFS stack.
   *
   * @return the name of the space
   */
  private String useDfsNode() {
    String nextSpace = dfsNodes.peek();
    int nextSpaceIdx = spaceMap.get(nextSpace).getIndex();
    visitedNodes.set(nextSpaceIdx, true);
    boolean allNeighboursVisited = true;

    // check if all neighbours are visited
    for (String n : getNeighboursOf(nextSpace)) {
      int neighbourIdx = spaceMap.get(n).getIndex();
      allNeighboursVisited = allNeighboursVisited && visitedNodes.get(neighbourIdx);
    }

    if (allNeighboursVisited) {
      dfsNodes.pop();
    } else {
      // next unvisited neighbour
      for (String n : getNeighboursOf(spaceMap.get(nextSpace).getName())) {
        int neighbourIdx = spaceMap.get(n).getIndex();
        if (!visitedNodes.get(neighbourIdx)) {
          dfsNodes.push(n);
          break;
        }
      }
    }

    // re-initialize DFS if all nodes are visited
    if (!visitedNodes.contains(false)) {
      initializeDfs(0);
    }

    return nextSpace;
  }

  /**
   * Returns the player id for the given player name.
   *
   * @param name the name of the player
   * @return the player id
   */
  private int playerIdBasedOnName(String name) {
    for (int i : players.keySet()) {
      if (name.equals(players.get(i).getName())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Operations which take place after a turn.
   */
  private void turnHelper() {
    playerInTurn = (playerInTurn + 1) % noOfPlayers;
    moveTarget();
    dfsMovePet();
  }
}