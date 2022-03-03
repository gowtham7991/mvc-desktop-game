package utils;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

  private final Random r;

  public RandomGeneratorImpl () {
    this.r = new Random();
  }

  public RandomGeneratorImpl (int... ran) {
    this.r = new Random();
  }


  public int getRandomInt() {
    return r.nextInt(10);
  }
}
