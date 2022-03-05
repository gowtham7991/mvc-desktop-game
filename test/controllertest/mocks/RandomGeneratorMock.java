package controllertest.mocks;

import utils.RandomGenerator;

/**
 * Mock for the random generator which is used to test the computer player.
 */
public class RandomGeneratorMock implements RandomGenerator {

  private int currentIdx;
  private int[] args;

  /**
   * Constructs the random generator mock. The mock accepts variable number of arguments.
   * @param inArgs the variable arguments
   */
  public RandomGeneratorMock(int... inArgs) {

    currentIdx = 0;
    args = inArgs;
  }

  @Override
  public int getRandomInt() {
    int result = args[currentIdx];
    currentIdx = (currentIdx + 1) % args.length;
    return result;
  }
}
