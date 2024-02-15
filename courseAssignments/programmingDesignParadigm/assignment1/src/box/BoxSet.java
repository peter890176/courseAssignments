package box;

/**
 * This interface represents a set of non-overlapping axis-aligned boxes.
 */
public interface BoxSet {
  /**
   * Add a given rectangle to this set, and make this set the result.
   *
   * @param x      the x-coordinate of the rectangle to be added
   * @param y      the y-coordinate of the rectangle to be added
   * @param width  the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  void add(int x, int y, int width, int height) throws IllegalArgumentException;

  /**
   * Subtract the given rectangle from this set, and make this set the result.
   *
   * @param x      the x-coordinate of the rectangle to be subtracted
   * @param y      the y-coordinate of the rectangle to be subtracted
   * @param width  the width of the rectangle to be subtracted
   * @param height the height of the rectangle to be subtracted
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  void subtract(int x, int y, int width, int height) throws IllegalArgumentException;

  /**
   * Get all the rectangles in this set.
   *
   * @return an array with each element containing exactly four numbers: the x, y, width and height
   *     of the rectangle in that order. For example, if there are two rectangles in this set, then
   *     the first rectangle would be (arr[0][0],arr[0][1],arr[0][2],arr[0][3]) and the second
   *     rectangle would be (arr[1][0],arr[1][1],arr[1][2],arr[1][3])
   */
  int[][] getBoxes();

  int size();
}
