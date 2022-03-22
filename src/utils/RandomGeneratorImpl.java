package utils;

import java.util.Random;

/**
 * Concrete class which implements the random generator.
 */
public class RandomGeneratorImpl implements RandomGenerator {

  private final Random rand;

  /**
   * Constructs the random generator class.
   */
  public RandomGeneratorImpl() {
    this.rand = new Random();
  }

  public int getRandomInt() {
    return Math.abs(rand.nextInt());
  }
}
