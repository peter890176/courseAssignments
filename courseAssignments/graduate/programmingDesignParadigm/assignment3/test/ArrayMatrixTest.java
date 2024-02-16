import mat.ArrayMatrix;
import mat.SparseMatrix;
import mat.SquareMatrix;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This test is to test the array matrix related functions and basic properties.
 */
public class ArrayMatrixTest {

  /**
   * To test the Add method, should modify the result matrix correctly.
   */
  @Test
  public void testAdd() {
    /*

    1 2 3 4
    1 5 2 1
    0 1 -1 2
    0 0 1 0

    +

    2 1 3 1
    2 1 5 4
    -1 2 1 1
    1 1 1 1

    =

    3 3 6 5
    3 6 7 5
    -1 3 0 3
    1 1 2 1

    */
    SquareMatrix one = new ArrayMatrix(4);
    SquareMatrix two = new ArrayMatrix(4);
    float[][] oneValues = {
            {1, 2, 3, 4},
            {1, 5, 2, 1},
            {0, 1, -1, 2},
            {0, 0, 1, 0}};

    float[][] twoValues = {
            {2, 1, 3, 1},
            {2, 1, 5, 4},
            {-1, 2, 1, 1},
            {1, 1, 1, 1}
    };

    float[][] answer = {
            {3, 3, 6, 5},
            {3, 6, 7, 5},
            {-1, 3, 0, 3},
            {1, 1, 2, 1}
    };
    for (int i = 0; i < 4; i += 1) {
      for (int j = 0; j < 4; j += 1) {
        one.set(i, j, oneValues[i][j]);
        two.set(i, j, twoValues[i][j]);
      }
    }
    SquareMatrix result = one.add(two);
    for (int i = 0; i < 4; i += 1) {
      for (int j = 0; j < 4; j += 1) {
        assertEquals(answer[i][j], result.get(i, j), 0.001);
      }
    }
  }

  /**
   * To test the transformation method for the identity matrix.
   */
  @Test
  public void testLargeIdentities() {
    int dim = 1000;
    SquareMatrix one = new ArrayMatrix(dim);
    SquareMatrix two = new ArrayMatrix(dim);
    one.setIdentity();
    two.setIdentity();
    SquareMatrix add = one.add(two);
    SquareMatrix preMul = one.premul(two);
    SquareMatrix postMul = one.postmul(two);

    for (int i = 0; i < dim; i += 1) {
      for (int j = 0; j < dim; j += 1) {
        if (i == j) {
          assertEquals(2, add.get(i, j), 0.001);
          assertEquals(1, preMul.get(i, j), 0.001);
          assertEquals(1, postMul.get(i, j), 0.001);
        } else {
          assertEquals(0, add.get(i, j), 0.001);
          assertEquals(0, preMul.get(i, j), 0.001);
          assertEquals(0, postMul.get(i, j), 0.001);
        }
      }
    }
  }

  /**
   * This test is to test whether the constructor is built successful or not.
   */
  @Test
  public void testArrayMatrixConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new ArrayMatrix(2500);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertFalse(testPass);
  }


  /**
   * This test is to test whether the constructor with size 0 is built successful or not.
   */
  @Test
  public void testArrayMatrixZeroSizeConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new ArrayMatrix(0);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertFalse(testPass);
  }

  /**
   * This test is to test whether the constructor with size 0 could return correct size.
   */
  @Test
  public void testReturnZeroSize() {
    SquareMatrix s = new ArrayMatrix(0);
    assertEquals(0, s.size());
  }


  /**
   * This test is to test whether the constructor with negative size throw exception.
   */
  @Test
  public void testArrayMatrixNegativeSizeConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new ArrayMatrix(-10);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }


  /**
   * This test is to test the set identity matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void setIdentity() {
    boolean testPass = false;
    try {
      SquareMatrix s = new ArrayMatrix(2500);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertFalse(testPass);
  }

  /**
   * This test is to test the random size identity matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void setRandomIdentity() {
    boolean testPass = false;
    Random random = new Random();
    for (int i = 0; i < 500; i++) {
      int ran = random.nextInt(500);
      try {
        SquareMatrix s = new ArrayMatrix(ran);
      } catch (Exception e) {
        testPass = true;
      }
    }
    assertFalse(testPass);
  }

  /**
   * This test is to test the random size identity matrix function's values are correct
   * with time limitation.
   */
  @Test(timeout = 2000)
  public void setRandomIdentityValue() {
    Random random = new Random();
    int ran = random.nextInt(500);
    SquareMatrix s = new ArrayMatrix(ran);
    s.setIdentity();
    for (int i = 0; i < ran; i++) {
      for (int j = 0; j < ran; j++) {
        if (i == j) {
          assertEquals(1, s.get(i, j), 0.00001);
        }
      }
    }
  }


  /**
   * This test is to test the set matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void setFunction() {
    SquareMatrix m = new ArrayMatrix(10000);
    m.setIdentity();
    m.set(5505, 4404, 2);
    m.set(9014, 6606, 4);
    assertEquals(2, m.get(5505, 4404), 0.000001);
  }

  /**
   * This test is to test the random set value for matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void setRandomFunction() {
    SquareMatrix m = new ArrayMatrix(10000);
    m.setIdentity();
    Random random = new Random();
    int ran = random.nextInt(5000);
    int ran2 = random.nextInt(5000);
    int ran3 = random.nextInt(5000);
    m.set(5505, 4404, 2);
    m.set(ran2, ran3, ran);
    assertEquals(ran, m.get(ran2, ran3), 0.000001);
  }

  /**
   * This test is to test the random set value for matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void getFunction() {
    SquareMatrix m = new ArrayMatrix(10000);
    m.setIdentity();
    Random random = new Random();
    int ran = random.nextInt(5000);
    int ran2 = random.nextInt(5000);
    int ran3 = random.nextInt(5000);
    m.set(5505, 4404, 2);
    m.set(ran2, ran3, ran);
    assertEquals(ran, m.get(ran2, ran3), 0.000001);
  }

  /**
   * This test is to test the get matrix function with a time limit.
   */
  @Test(timeout = 2000)
  public void get() {
    SquareMatrix m = new ArrayMatrix(2800);
    m.setIdentity();
    m.get(200, 874);
    m.get(100, 500);
    assertEquals(0, m.get(200, 874), 0.00001);
  }

  /**
   * This test is to test the add matrix function in array+array with a time limit.
   */
  @Test(timeout = 2000)
  public void addArrayArray() {
    SquareMatrix s = new ArrayMatrix(2000);
    s.setIdentity();
    SquareMatrix s2 = new ArrayMatrix(2000);
    s2.setIdentity();
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == j) {
          assertEquals(s3.get(i, j), 2, 0.00001);
        }
      }
    }

  }

  /**
   * This test is to test the add matrix function in Array+sparse with a time limit.
   */
  @Test(timeout = 2000)
  public void addSparse() {
    SquareMatrix s = new ArrayMatrix(2000);
    s.setIdentity();
    SquareMatrix s2 = new SparseMatrix(2000);
    s2.setIdentity();
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == j) {
          assertEquals(s3.get(i, j), 2, 0.00001);
        }
      }
    }

  }

  /**
   * This test is to test the pre multiplication in Array*sparse matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void preMul() {
    int size = 5;
    SquareMatrix m0 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m0.set(i, j, i);
      }
    }
    SquareMatrix m1 = new SparseMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m1.set(i, j, i);
      }
    }
    SquareMatrix result = m0.premul(m1);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        assertEquals(result.get(i, j), i * 10, 0.00001);
      }
    }
  }

  /**
   * This test is to test the pre multiplication in array*array matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void preMulArray() {
    int size = 5;
    SquareMatrix m0 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m0.set(i, j, i);
      }
    }
    SquareMatrix m1 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m1.set(i, j, i);
      }
    }
    SquareMatrix result = m0.premul(m1);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        assertEquals(result.get(i, j), i * 10, 0.00001);
      }
    }
  }

  /**
   * To test the post multiplication in array*sparse matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void postMul() {
    int size = 5;
    SquareMatrix m0 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m0.set(i, j, i);
      }
    }
    SquareMatrix m1 = new SparseMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m1.set(i, j, i);
      }
    }
    SquareMatrix result = m0.postmul(m1);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        assertEquals(result.get(i, j), i * 10, 0.00001);
      }
    }
  }

  /**
   * To test the post multiplication in array*array matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void postArrayMul() {
    int size = 5;
    SquareMatrix m0 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m0.set(i, j, i);
      }
    }
    SquareMatrix m1 = new ArrayMatrix(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        m1.set(i, j, i);
      }
    }
    SquareMatrix result = m0.postmul(m1);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        assertEquals(result.get(i, j), i * 10, 0.00001);
      }
    }
  }


  /**
   * To test upper row add in array+array.
   */
  @Test
  public void upperRowArrayArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row add in array+sparse.
   */
  @Test
  public void upperRowSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test left column add in Array+Array.
   */
  @Test
  public void leftColumnArrayArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(0, i, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == 0) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test left column add in Array+Sparse.
   */
  @Test
  public void leftColumnSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(0, i, 10);
    }
    SquareMatrix s2 = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == 0) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test right column add in Array+Sparse.
   */
  @Test
  public void rightColumnSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(1999, i, 10);
    }
    SquareMatrix s2 = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(1999, i, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == 1999) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test right column add in Array+Array.
   */
  @Test
  public void rightColumnSparseSparseTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(1999, i, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(1999, i, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (i == 1999) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row add in Array+sparse.
   */
  @Test
  public void bottomRowSparseSparseTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 1999, 10);
    }
    SquareMatrix s2 = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 1999, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 1999) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row add in Array+Array.
   */
  @Test
  public void bottomRowArrayArrayTest() {
    SquareMatrix s = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 1999, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 1999, 20);
    }
    SquareMatrix s3 = s.add(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 1999) {
          assertEquals(30, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row pre Multiplication in Array+sparse.
   */
  @Test
  public void upperRowMultiplicationArraySparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row pre Multiplication in Array+array.
   */
  @Test
  public void upperMultiplicationSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row post Multiplication in Array+array.
   */
  @Test
  public void upperPostMultiplicationArrayArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row post Multiplication in Array+Array.
   */
  @Test
  public void upperPostMultiplicationSparseSparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row left column pre Multiplication in Array+sparse.
   */
  @Test
  public void upperLeftPreMultiplicationArraySparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    assertEquals(200 * 200, s3.get(0, 0), 0.00001);
  }

  /**
   * To test upper row left column pre Multiplication in Array+array.
   */
  @Test
  public void upperLeftPreMultiplicationSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    assertEquals(200 * 200, s3.get(0, 0), 0.00001);
  }

  /**
   * To test upper row left column post Multiplication in Array+sparse.
   */
  @Test
  public void upperLeftPostMultiplicationArraySparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (i == 0) {
          assertEquals(200, s3.get(0, 0), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row left column post Multiplication in Array+sparse.
   */
  @Test
  public void upperLeftPostMultiplicationSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    for (int i = 0; i < 200; i++) {
      s2.set(0, i, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        if (i == 0) {
          assertEquals(200, s3.get(0, 0), 0.00001);
        }
      }
    }
  }

  /**
   * To diagonal pre Multiplication in Array+Array.
   */
  @Test
  public void diagonalPreMultiplicationArrayArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    s2.setIdentity();
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        assertEquals(s.get(i, j), s3.get(i, j), 0.00001);
      }
    }
  }

  /**
   * To diagonal pre Multiplication in Array+sparse.
   */
  @Test
  public void diagonalPreMultiplicationArraySparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    s2.setIdentity();
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        assertEquals(s.get(i, j), s3.get(i, j), 0.00001);
      }
    }
  }

  /**
   * To diagonal post Multiplication in Array+Array.
   */
  @Test
  public void diagonalPostMultiplicationSparseSparseTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
    s2.setIdentity();
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        assertEquals(s.get(i, j), s3.get(i, j), 0.00001);
      }
    }
  }

  /**
   * To diagonal post Multiplication in Array+sparse.
   */
  @Test
  public void diagonalPostMultiplicationSparseArrayTest() {
    SquareMatrix s = new ArrayMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(200);
    s2.setIdentity();
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 200; i++) {
      for (int j = 0; j < 200; j++) {
        assertEquals(s.get(i, j), s3.get(i, j), 0.00001);
      }
    }
  }

}