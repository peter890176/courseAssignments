import org.junit.Test;

import mat.SparseNode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * This test class is to test the SparseNode class as structure for implementing sparse matrix.
 */
public class SparseNodeTest {

  /**
   * This test is to test whether the constructor is built successful or not.
   */
  @Test
  public void testSparseNodeConstructor() {
    boolean testPass = false;
    try {
      SparseNode root = new SparseNode(-1, -1, Float.NEGATIVE_INFINITY);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertFalse(testPass);
  }

  /**
   * This test is to test whether the root is built successful or not.
   */
  @Test
  public void testSparseNodeRoot() {

    SparseNode root = new SparseNode(-1, -1, Float.NEGATIVE_INFINITY);
    assertEquals(-1, root.x);
    assertEquals(-1, root.y);
    assertEquals(Float.NEGATIVE_INFINITY, root.value, 0.00001);
  }

  /**
   * This test is to test whether the node's next x node function is built successful or not.
   */
  @Test
  public void testXNextNode() {
    SparseNode node1 = new SparseNode(0, 1, 10);
    SparseNode node2 = new SparseNode(1, 1, 20);
    node1.xNext = node2;
    assertEquals(20, node1.xNext.value, 0.00001);
  }

  /**
   * This test is to test whether the node's next y node function is built successful or not.
   */
  @Test
  public void testYNextNode() {
    SparseNode node1 = new SparseNode(0, 1, 10);
    SparseNode node2 = new SparseNode(0, 2, 20);
    node1.yNext = node2;
    assertEquals(20, node1.yNext.value, 0.00001);
  }

  /**
   * This test is to test whether the nodeNum correctly store the number.
   */
  @Test
  public void testNodeNum() {
    SparseNode node = new SparseNode(0, 1, 10);
    node.nodeNum = 100;

    assertEquals(100, node.nodeNum, 0.00001);
  }


}