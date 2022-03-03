package model.layout;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator{

  private final Random r;

  public RandomGeneratorImpl () {
    this.r = new Random();
  }

  public int getRandomInt() {
    return r.nextInt(10);
  }
}
