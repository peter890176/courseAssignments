import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import box.SimpleBoxSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This is the test class created by Yue Wen Peter Li, and in order to test the class SimpleBoxSet.
 */
public class SimpleBoxSetTest {
  public SimpleBoxSet simpleBoxSet;

  /**
   * Create new object before each test to ensure that each test is an independent unit test.
   */
  @Before
  public void setUp() {
    simpleBoxSet = new SimpleBoxSet();
  }

  /**
   * Test testSimpleBoxSet's constructor is created correctly with empty Set.
   */
  @Test
  public void testSimpleBoxSetCreation() {
    assertEquals(0, simpleBoxSet.size());
  }

  /**
   * Test add() and getBoxes() operate correctly in simple case.
   */
  @Test
  public void testAddAndGetBoxes() {
    simpleBoxSet.add(0, 0, 1, 1);
    int[][] arr = simpleBoxSet.getBoxes();
    int[][] expected = {{0, 0, 1, 1}};
    assertArrayEquals(expected, arr);
  }

  /**
   * Test subtract() operate correctly in partial overlapped case.
   */
  @Test
  public void testSubtractPartialOverlapped() {
    simpleBoxSet.add(0, 0, 2, 2);
    simpleBoxSet.subtract(1, 1, 2, 2);
    int[][] arr = simpleBoxSet.getBoxes();
    int[] rectangle1 = {0, 0, 1, 2};
    int[] rectangle2 = {1, 0, 1, 1};
    if (arr[0][0] == 0) {
      assertArrayEquals(arr[0], rectangle1);
      assertArrayEquals(arr[1], rectangle2);
    } else {
      assertArrayEquals(arr[0], rectangle2);
      assertArrayEquals(arr[1], rectangle1);
    }
  }

  /**
   * Test subtract() operate correctly in with-in overlapped case.
   */
  @Test
  public void testSubtractWithinOverlapped() {
    simpleBoxSet.add(0, 0, 3, 3);
    simpleBoxSet.subtract(1, 1, 1, 1);
    int[][] arr = simpleBoxSet.getBoxes();
    int[] rectangle1 = {0, 0, 1, 3};
    int[] rectangle2 = {1, 0, 1, 1};
    int[] rectangle3 = {1, 2, 1, 1};
    int[] rectangle4 = {2, 0, 1, 3};
    for (int i = 0; i < 4; i++) {
      switch (arr[i][0]) {
        case 0:
          assertArrayEquals(arr[i], rectangle1);
          break;
        case 1:
          if (arr[i][1] == 0) {
            assertArrayEquals(arr[i], rectangle2);
          } else {
            assertArrayEquals(arr[i], rectangle3);
          }
          break;
        case 2:
          assertArrayEquals(arr[i], rectangle4);
          break;
        default:
          //default should cover other unexpected cases.
      }
    }
  }

  /**
   * Test subtract() operate correctly in over-covered overlapped case.
   */
  @Test
  public void testOverCoveredOverlapped() {
    simpleBoxSet.add(0, 0, 1, 1);
    simpleBoxSet.subtract(0, 0, 2, 2);
    assertEquals(0, simpleBoxSet.size());
  }

  /**
   * Test subtract() operate correctly in adjacent rectangles case.
   */
  @Test
  public void testAdjacentRectangles() {
    simpleBoxSet.add(0, 0, 1, 1);
    simpleBoxSet.subtract(1, 1, 2, 2);
    int[][] arr = simpleBoxSet.getBoxes();
    int[] rectangle = {0, 0, 1, 1};
    assertArrayEquals(arr[0], rectangle);
  }

  /**
   * Test for subtract() that if the width is a random negative value,
   * an exception should be thrown.
   */
  @Test
  public void testSubNegativeWidthShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int negWidth = -Math.abs(rand.nextInt());
      int x = rand.nextInt();
      int y = rand.nextInt();
      int h = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, negWidth, h);
        fail("The subtract() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for subtract() that if the width is a 0, an exception should be thrown.
   */
  @Test
  public void testSubZeroWidthShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int zeroWidth = 0;
      int x = rand.nextInt();
      int y = rand.nextInt();
      int h = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, zeroWidth, h);
        fail("The subtract() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for subtract() that if the height is a random negative value,
   * an exception should be thrown.
   */
  @Test
  public void testSubNegativeHeightShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int negHeight = -Math.abs(rand.nextInt());
      int x = rand.nextInt();
      int y = rand.nextInt();
      int w = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, w, negHeight);
        fail("The subtract() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for subtract() that if the height is a 0, an exception should be thrown.
   */
  @Test
  public void testSubZeroHeightShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int zeroHeight = 0;
      int x = rand.nextInt();
      int y = rand.nextInt();
      int w = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, w, zeroHeight);
        fail("The subtract() method was expected to throw an IllegalArgumentException exception " +
                "but it did not");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for add() that if the width is a random negative value, an exception should be thrown.
   */
  @Test
  public void testAddNegativeWidthShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int negWidth = -Math.abs(rand.nextInt());
      int x = rand.nextInt();
      int y = rand.nextInt();
      int h = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.add(x, y, negWidth, h);
        fail("The add() method was expected to throw an IllegalArgumentException exception " +
                "but it did not");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for add() that if the width is a 0, an exception should be thrown.
   */
  @Test
  public void testAddZeroWidthShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int zeroWidth = 0;
      int x = rand.nextInt();
      int y = rand.nextInt();
      int h = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, zeroWidth, h);
        fail("The add() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for add() that if the height is a random negative value, an exception should be thrown.
   */
  @Test
  public void testAddNegativeHeightShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int negHeight = -Math.abs(rand.nextInt());
      int x = rand.nextInt();
      int y = rand.nextInt();
      int w = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.add(x, y, w, negHeight);
        fail("The add() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for add() that if the height is a 0, an exception should be thrown.
   */
  @Test
  public void testAddZeroHeightShouldThrow() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      int zeroHeight = 0;
      int x = rand.nextInt();
      int y = rand.nextInt();
      int w = Math.abs(rand.nextInt()) + 1;
      try {
        simpleBoxSet.subtract(x, y, w, zeroHeight);
        fail("The add() method was expected to throw an IllegalArgumentException exception " +
                "but it did not.");
      } catch (IllegalArgumentException e) {
        continue;
      }
    }
  }

  /**
   * Test for getBoxes() that should return correct 2-D array at the beginning.
   */
  @Test
  public void testGetBoxFunctionAtBeginning() {
    int[][] expectedBoxes = new int[][]{};
    int[][] boxes = simpleBoxSet.getBoxes();
    assertArrayEquals(expectedBoxes, boxes);
  }

  /**
   * Test for getBoxes() that should return 2-D array without throwing exception after random
   * multiple add() or subtract() operations.
   * The operations were temporarily set to at most 10 times,
   * since this test took a lot of time.
   */
  @Test
  public void testGetBoxFunctionAfterOperationsNoException() {
    Random rand = new Random();
    for (int i = 0; i < 10000; i++) {
      simpleBoxSet = new SimpleBoxSet();
      int addTimes = Math.abs(rand.nextInt(10)) + 1;
      int subtractTimes = Math.abs(rand.nextInt(10)) + 1;

      for (int j = 0; j < addTimes; j++) {
        int x = rand.nextInt();
        int y = rand.nextInt();
        int w = Math.abs(rand.nextInt()) + 1;
        int h = Math.abs(rand.nextInt()) + 1;
        simpleBoxSet.add(x, y, w, h);
      }
      for (int k = 0; k < subtractTimes; k++) {
        int x = rand.nextInt();
        int y = rand.nextInt();
        int w = Math.abs(rand.nextInt()) + 1;
        int h = Math.abs(rand.nextInt()) + 1;
        simpleBoxSet.subtract(x, y, w, h);
      }
      int size = simpleBoxSet.size();
      assertTrue((0 <= size && size <= Integer.MAX_VALUE));
    }
  }

  /**
   * Test for size() that should always return correct size, using non-overlapped rectangles.
   */
  @Test
  public void testSize() {
    for (int i = 0; i < 10000; i++) {
      int x = i;
      int y = i;
      int w = 1;
      int h = 1;
      simpleBoxSet.add(x, y, w, h);
      int size = simpleBoxSet.size();
      assertEquals(i + 1, size);
    }
  }
}