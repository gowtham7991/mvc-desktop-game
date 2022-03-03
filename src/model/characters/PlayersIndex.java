package model.characters;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.characters.Player;

public class PlayersIndex {

  private Map<Integer, Player> players = new TreeMap<>();

  public void addPlayer(int playerId, Player player) {
    players.put(playerId, player);
  }

  public int getIdOf(String name) {
    for (int id : players.keySet()) {
      if (players.get(id).getName() == name) {
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
    for (Player p: players.values()) {
      if (p.getPosition() == name) {
        set.add(p.getName());
      }
    }

    return set;
  }

  public String toString() {
    StringBuilder sr = new StringBuilder();

    for (int id : players.keySet()) {
      sr.append("Player").append(id).append(" : ").append(players.get(id).getName());
    }
    sr.append("\n");
    return sr.toString();
  }
}
