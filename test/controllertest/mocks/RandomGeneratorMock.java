package controllertest.mocks;

import utils.RandomGenerator;

public class RandomGeneratorMock implements RandomGenerator {

  private int cIdx;
  private int[] args;

  public RandomGeneratorMock(int... inArgs) {

    cIdx = 0;
    args = inArgs;
  }

  @Override
  public int getRandomInt() {
    int result = args[cIdx];
    cIdx = (cIdx+1)%args.length;
    return result;
  }
}
