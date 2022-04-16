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

  /**
   * Returns the next random integer.
   * @return the random integer
   */
  public int getRandomInt() {
    return Math.abs(rand.nextInt());
  }
}
