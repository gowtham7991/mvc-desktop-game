package model.characters;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class which stores the player objects referenced with their id.
 * Allows operations to add players, find players in a space, get name based on id.
 */
public class PlayersIndex {

  private final Map<Integer, Player> players;

  public PlayersIndex() {
    players = new TreeMap<>();
  }
  /**
   * Adds a player to based on their id.
   * @param playerId the id
   * @param player the player object
   */
  public void addPlayer(int playerId, Player player) {
    players.put(playerId, player);
  }

  /**
   * Returns the id of the player based on the name.
   * @param name the player name
   * @return the id
   */
  public int getIdOf(String name) {
    for (int id : players.keySet()) {
      if (players.get(id).getName().equals(name)) {
        return id;
      }
    }
    return -1;
  }

  /**
   * Returns the player object based on the id.
   * @param id the player id
   * @return the player object
   */
  public Player getPlayerObj(int id) {
    Player p = players.get(id);
    return new PlayerImpl(p.getName(), p.getPosition(), p.getPlayerType(), p.getMaxItemCount(), id);
  }

  /**
   * Returns the list of players in a the mentioned space.
   * @param name the space name
   * @return the set of player name
   */
  public Set<String> getPlayersInSpace(String name) {
    Set<String> set = new HashSet<>();
    for (Player p : players.values()) {

      if (name.equals(p.getPosition())) {
        set.add(p.getName());
      }
    }

    return set;
  }

  @Override
  public String toString() {
    StringBuilder sr = new StringBuilder();

    for (int id : players.keySet()) {
      Player p = players.get(id);
      sr.append("Player").append(id).append(" : ").append(p.getName()).append("\n")
          .append("Type - ").append(p.getPlayerType().toString()).append("\n").append("Location - ")
          .append(p.getPosition()).append("\n\n");
    }
    sr.append("\n");
    return sr.toString();
  }
}
