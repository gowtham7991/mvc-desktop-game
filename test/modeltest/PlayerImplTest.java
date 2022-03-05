package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.characters.Player;
import model.characters.PlayerImpl;
import model.characters.PlayerType;
import org.junit.Test;

public class PlayerImplTest {

  private Player createPlayer(String name, String location, PlayerType type, int itemLimit,
      int id) {
    return new PlayerImpl(name, location, type, itemLimit, id);
  }

  @Test
  public void testValidPlayerCreation() {
    Player p = createPlayer("Player1", "Armory", PlayerType.MANUAL, 5, 0);
    assertEquals("Player1", p.getName());
    assertEquals("Armory", p.getPosition());
    assertEquals("Name : Player1\n" + "Items : []", p.toString());
  }

  @Test
  public void testPlayerWithZeroItemLimit() {
    Player p = createPlayer("Player1", "Armory", PlayerType.MANUAL, 0, 0);
    assertEquals("Player1", p.getName());
    assertEquals("Armory", p.getPosition());
    StringBuilder sr = new StringBuilder();
    sr.append("Name : Player1\n");
    sr.append("Items : []");
    assertEquals(sr.toString(), p.toString());
    assertEquals(0, p.getItemCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWithInvalidName() {
    Player p = createPlayer("   ", "  ", PlayerType.MANUAL, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWithNullName() {
    Player p = createPlayer(null, "Armory", PlayerType.MANUAL, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWithNullPosition() {
    Player p = createPlayer("Player1", null, PlayerType.MANUAL, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWithInvalidPLayerType() {
    Player p = createPlayer("Player1", "Dining Hall", null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWithNegativeItemLimit() {
    Player p = createPlayer("Player1", "Drawing Room", PlayerType.MANUAL, -5, 0);
  }

  @Test
  public void testEquals() {
    Player p1 = createPlayer("Player1", "Drawing Room", PlayerType.MANUAL, 5, 0);
    Player p2 = createPlayer("Player1", "Dining Hall", PlayerType.MANUAL, 3, 0);
    Player p3 = createPlayer("Player2", "Lilac Room", PlayerType.MANUAL, 2, 1);
    Player p4 = createPlayer("Player3", "Lilac Room", PlayerType.MANUAL, 2, 1);

    assertTrue(p1.equals(p2));
    assertTrue(p3.equals(p4));
    assertTrue(p2.equals(p1));
    assertFalse(p1.equals(p3));
  }

  @Test
  public void testHashCode() {
    Player p1 = createPlayer("Player1", "Drawing Room", PlayerType.MANUAL, 5, 0);
    Player p2 = createPlayer("Player2", "Lilac Room", PlayerType.MANUAL, 2, 1);

    assertEquals(Integer.hashCode(0), p1.hashCode());
    assertEquals(Integer.hashCode(1), p2.hashCode());
  }
}