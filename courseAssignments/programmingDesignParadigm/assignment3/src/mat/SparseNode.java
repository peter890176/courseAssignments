package mat;

/**
 * The SparseNode class represents the node in the matrix, plus the sentinels for the matrix.
 */
public class SparseNode {
  /**
   * x represents the coordinate of x.
   */
  public int x;
  /**
   * y represents the coordinate of y.
   */
  public int y;
  /**
   * value represents the specific value for (x,y).
   */
  public float value;
  /**
   * xNext represents the node on the x coordinate.
   */
  public SparseNode xNext;
  /**
   * yNext represents the node on the y coordinate.
   */
  public SparseNode yNext;
  /**
   * nodeNum represents the number of nodes for the sentinel node.
   */
  public int nodeNum;

  /**
   * The constructor of SparseNode should include values of x-axis, y-axis
   * and the float value in that node.
   */
  public SparseNode(int x, int y, float value) {
    this.x = x;
    this.y = y;
    this.value = value;
  }
}
