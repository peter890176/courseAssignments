package box;

import java.util.HashSet;
import java.util.Set;

/**
 * This SimpleBoxSet class is created by Yue Wen Peter Li.
 */
public class SimpleBoxSet implements BoxSet {
  private Set<int[]> rectSet;

  /**
   * Constructs a  {@code SimpleBoxSet} object represents the current set of boxes, and default is
   * an empty set.
   */
  public SimpleBoxSet() {
    rectSet = new HashSet<>();
  }

  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be integer and strictly bigger "
              + "than 0");
    }
    int[] nextRect = {x, y, width, height};
    Set<int[]> t = new HashSet<>();
    if (rectSet.isEmpty()) {
      return;
    } else {
      for (int[] rect : rectSet) {
        int[] newRect = {
                Math.max(rect[0], nextRect[0]),
                Math.max(rect[1], nextRect[1]),
                Math.min(rect[0] + rect[2], nextRect[0] + nextRect[2])
            - Math.max(rect[0], nextRect[0]),
                Math.min(rect[1] + rect[3], nextRect[1] + nextRect[3])
            - Math.max(rect[1], nextRect[1])};

        if (newRect[2] > 0 && newRect[3] > 0) {
          if (newRect[0] - rect[0] > 0 && rect[3] > 0) {
            int[] left = {rect[0], rect[1], newRect[0] - rect[0], rect[3]};
            t.add(left);
          }
          if (newRect[2] > 0 && newRect[1] - rect[1] > 0) {
            int[] bottom = {newRect[0], rect[1], newRect[2], newRect[1] - rect[1]};
            t.add(bottom);
          }
          if (newRect[2] > 0 && (rect[1] + rect[3] - newRect[1] - newRect[3]) > 0) {
            int[] upper = {newRect[0], newRect[1] + newRect[3],
                    newRect[2], rect[1] + rect[3] - newRect[1] - newRect[3]};
            t.add(upper);
          }
          if ((rect[0] + rect[2] - newRect[0] - newRect[2]) > 0 && rect[3] > 0) {
            int[] right = {newRect[0] + newRect[2], rect[1],
                    rect[0] + rect[2] - newRect[0] - newRect[2], rect[3]};
            t.add(right);
          }
        } else {
          t.add(rect);
        }
      }
    }
    rectSet = t;
  }

  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be integer "
              + "and strictly bigger than 0");
    }
    int[] rect = {x, y, width, height};
    this.subtract(x, y, width, height);
    rectSet.add(rect);
  }

  @Override
  public int[][] getBoxes() {
    int[][] arr = new int[rectSet.size()][];
    if (rectSet.isEmpty()) {
      return arr;
    } else {
      int i = 0;
      for (int[] rect : rectSet) {
        arr[i] = rect;
        i++;
      }
      return arr;
    }
  }

  @Override
  public int size() {
    return rectSet.size();
  }
}
