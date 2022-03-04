package controllertest;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameConsoleControllerTest {

  @Test
  public void testStartWithModel() {
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartWithNullModel() {
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFailingAppendable() {}



}