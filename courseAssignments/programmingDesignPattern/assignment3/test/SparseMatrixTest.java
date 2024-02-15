import org.junit.Test;

import java.util.Random;

import mat.ArrayMatrix;
import mat.SparseMatrix;
import mat.SquareMatrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This test is to test the functions related to sparse matrix.
 */
public class SparseMatrixTest {


  /**
   * This test is to test whether the constructor is built successful or not.
   */
  @Test
  public void testSparseMatrixConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new SparseMatrix(2500);
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertFalse(testPass);
  }


  /**
   * This test is to test whether the constructor with size 0 is built successful or not.
   */
  @Test
  public void testSparseMatrixZeroSizeConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new SparseMatrix(0);
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
    SquareMatrix s = new SparseMatrix(0);
    assertEquals(0, s.size());
  }


  /**
   * This test is to test whether the constructor with negative size throw exception.
   */
  @Test
  public void testSparseMatrixNegativeSizeConstructor() {
    boolean testPass = false;
    try {
      SquareMatrix s = new SparseMatrix(-10);
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
      SquareMatrix s = new SparseMatrix(2500);
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
    for (int i = 0; i < 5000; i++) {
      int ran = random.nextInt(5000);
      try {
        SparseMatrix s = new SparseMatrix(ran);
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
    int ran = random.nextInt(5000);
    SparseMatrix s = new SparseMatrix(ran);
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
    SquareMatrix m = new SparseMatrix(10000);
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
    SquareMatrix m = new SparseMatrix(10000);
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
    SquareMatrix m = new SparseMatrix(10000);
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
    SquareMatrix m = new SparseMatrix(28000);
    m.setIdentity();
    m.get(2000, 8704);
    m.get(1000, 5000);
    assertEquals(0, m.get(2000, 8704), 0.00001);
  }

  /**
   * This test is to test the add matrix function in sparse+array with a time limit.
   */
  @Test(timeout = 2000)
  public void addSparseArray() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * This test is to test the add matrix function in sparse+sparse with a time limit.
   */
  @Test(timeout = 2000)
  public void addSparse() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * This test is to test the pre multiplication in sparse*sparse matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void preMul() {
    int size = 5;
    SquareMatrix m0 = new SparseMatrix(size);
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
   * This test is to test the pre multiplication in sparse*array matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void preMulArray() {
    int size = 5;
    SquareMatrix m0 = new SparseMatrix(size);
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
   * To test the post multiplication in sparse*sparse matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void postMul() {
    int size = 5;
    SquareMatrix m0 = new SparseMatrix(size);
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
   * To test the post multiplication in sparse*array matrix function with a time limit.
   */
  @Test(timeout = 3000)
  public void postArrayMul() {
    int size = 5;
    SquareMatrix m0 = new SparseMatrix(size);
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
   * To test upper row add in sparse+sparse.
   */
  @Test
  public void upperRowSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test upper row add in sparse+array.
   */
  @Test
  public void upperRowSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test left column add in sparse+sparse.
   */
  @Test
  public void leftColumnSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test left column add in sparse+array.
   */
  @Test
  public void leftColumnSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test right column add in sparse+array.
   */
  @Test
  public void rightColumnSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test right column add in sparse+sparse.
   */
  @Test
  public void rightColumnSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test upper row add in sparse+sparse.
   */
  @Test
  public void bottomRowSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
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
   * To test upper row pre Multiplication in sparse+sparse.
   */
  @Test
  public void upperRowMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row pre Multiplication in sparse+array.
   */
  @Test
  public void upperMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.premul(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row post Multiplication in sparse+array.
   */
  @Test
  public void upperPostMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row post Multiplication in sparse+sparse.
   */
  @Test
  public void upperPostMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(2000);
    for (int i = 0; i < 2000; i++) {
      s2.set(i, 0, 20);
    }
    SquareMatrix s3 = s.postmul(s2);
    for (int i = 0; i < 2000; i++) {
      for (int j = 0; j < 2000; j++) {
        if (j == 0) {
          assertEquals(200, s3.get(i, j), 0.00001);
        }
      }
    }
  }

  /**
   * To test upper row left column pre Multiplication in sparse+sparse.
   */
  @Test
  public void upperLeftPreMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(200);
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
   * To test upper row left column pre Multiplication in sparse+array.
   */
  @Test
  public void upperLeftPreMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(200);
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
   * To test upper row left column post Multiplication in sparse+sparse.
   */
  @Test
  public void upperLeftPostMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(200);
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
   * To test upper row left column post Multiplication in sparse+sparse.
   */
  @Test
  public void upperLeftPostMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(200);
    for (int i = 0; i < 200; i++) {
      s.set(i, 0, 10);
    }
    SquareMatrix s2 = new ArrayMatrix(200);
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
   * To diagonal pre Multiplication in sparse+sparse.
   */
  @Test
  public void diagonalPreMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(200);
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
   * To diagonal pre Multiplication in sparse+sparse.
   */
  @Test
  public void diagonalPreMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(200);
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
   * To diagonal post Multiplication in sparse+sparse.
   */
  @Test
  public void diagonalPostMultiplicationSparseSparseTest() {
    SquareMatrix s = new SparseMatrix(200);
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

  /**
   * To diagonal post Multiplication in sparse+sparse.
   */
  @Test
  public void diagonalPostMultiplicationSparseArrayTest() {
    SquareMatrix s = new SparseMatrix(200);
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