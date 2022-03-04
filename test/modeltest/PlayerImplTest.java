package modeltest;

import org.junit.Test;

import model.characters.Player;
import model.characters.PlayerImpl;
import model.characters.PlayerType;

import static org.junit.Assert.*;

public class PlayerImplTest {

  private Player createPlayer(String name, String location, PlayerType type, int itemLimit) {
      return new PlayerImpl(name, location, type, itemLimit);
  }

  @Test
  public void testValidPlayerCreation() {
  }

  @Test
  public void testPlayerWithZeroItemLimit () {}

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerWithInvalidName() {}

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerWithNullName () {}

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerWithNullPosition () {}

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerWithInvalidPLayerType () {}

  @Test (expected = IllegalArgumentException.class)
  public void testPlayerWithNegativeItemLimit () {}

  @Test
  public void testEquals () {

  }

  @Test
  public void testHashCode() {

  }
}