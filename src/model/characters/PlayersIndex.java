package model.characters;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PlayersIndex {

  private final Map<Integer, Player> players = new TreeMap<>();

  public void addPlayer(int playerId, Player player) {
    players.put(playerId, player);
  }

  public int getIdOf(String name) {
    for (int id : players.keySet()) {
      if (players.get(id).getName().equals(name)) {
        return id;
      }
    }
    return -1;
  }

  public Player getPlayerObj(int id) {
    return players.get(id);
  }

  public Set<String> getPlayersInSpace(String name) {
    Set<String> set = new HashSet<>();
    for (Player p : players.values()) {

      if (name.equals(p.getPosition())) {
        set.add(p.getName());
      }
    }

    return set;
  }

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
