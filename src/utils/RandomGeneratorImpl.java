package utils;

import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

  private final Random r;
  private int count = 0;
  private boolean isRandom;

  public RandomGeneratorImpl () {
    this.r = new Random();
    isRandom = true;
  }

  public RandomGeneratorImpl (int... rand) {
    this.r = new Random();
    isRandom = false;
    count++;
  }

  public int getRandomInt() {
    if (isRandom) {
      return r.nextInt();
    }
    count++;
    return count;
  }
}
