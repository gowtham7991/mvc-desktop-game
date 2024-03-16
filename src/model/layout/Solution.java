package model.layout;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
  static class ArrayComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
      int aBitCount = Integer.bitCount(a);
      int bBitCount = Integer.bitCount(b);

      if(aBitCount == bBitCount) return Integer.compare(a, b);
      else if(aBitCount < bBitCount) return -1;
      else return 1;
    }
  }
  public int[] sortByBits(int[] arr) {
    //         Comparator<Integer> comp = new Comparator<Integer>() {
    //             @Override
    //             public int compare(Integer a, Integer b) {
    //                 int aBitCount = Integer.bitCount(a);
    //                 int bBitCount = Integer.bitCount(b);

    //                 if(aBitCount == bBitCount) return Integer.compare(a, b);
    //                 else if(aBitCount < bBitCount) return -1;
    //                 else return 1;
    //             }
    //         };

    Arrays.sort(arr, new ArrayComparator());
    return arr;
  }
}