package sim;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This test is to test the methods and attributes of SimplePoolSimulator class.
 */
public class SimplePoolSimulatorTest {
  private SimplePoolSimulator simplePoolSimulator;


  /**
   * Width and height should be 400.
   */
  @Test
  public void testSimpleConstructorWidth() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    int t = simplePoolSimulator.getTableWidth();
    assertEquals(t, 400);
  }

  /**
   * Width and height should be 400.
   */
  @Test
  public void testSimpleConstructorHeight() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    int t = simplePoolSimulator.getTableHeight();
    assertEquals(t, 400);
  }

  /**
   * Width and height should be 400.
   */
  @Test
  public void testFrictionConstructorWidth() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "friction");
    int t = simplePoolSimulator.getTableWidth();
    assertEquals(t, 400);
  }

  /**
   * Width and height should be 400.
   */
  @Test
  public void testFrictionConstructorHeight() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "friction");
    int t = simplePoolSimulator.getTableHeight();
    assertEquals(t, 400);
  }

  /**
   * Width and height should be 400 and not negative.
   */
  @Test
  public void testSimpleConstructorWidthException() {
    boolean testPass = false;
    try {
      simplePoolSimulator = new SimplePoolSimulator(-1, 400, "simple");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Width and height should be 400 and not negative.
   */
  @Test
  public void testSimpleConstructorHeightException() {

    boolean testPass = false;

    try {
      simplePoolSimulator = new SimplePoolSimulator(400, -1, "simple");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;

    }
    assertTrue(testPass);
  }

  /**
   * Type should be either simple or friction.
   */
  @Test
  public void testFrictionConstructorTypeException() {
    boolean testPass = false;

    try {
      simplePoolSimulator = new SimplePoolSimulator(400, 400, "ABC");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;

    }
    assertTrue(testPass);
  }

  /**
   * Width and height should be 400 and not negative.
   */
  @Test
  public void testFrictionConstructorWidthException() {
    boolean testPass = false;

    try {
      simplePoolSimulator = new SimplePoolSimulator(-1, 400, "friction");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;

    }
    assertTrue(testPass);
  }

  /**
   * Width and height should be 400 and not negative.
   */
  @Test
  public void testFrictionConstructorHeightException() {
    boolean testPass = false;

    try {
      simplePoolSimulator = new SimplePoolSimulator(400, -1, "friction");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;

    }
    assertTrue(testPass);
  }

  /**
   * Type should be either simple or friction.
   */
  @Test
  public void testSimpleConstructorTypeException() {
    boolean testPass = false;

    try {
      simplePoolSimulator = new SimplePoolSimulator(400, 400, "CBA");
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;

    }
    assertTrue(testPass);
  }

  /**
   * Before start, simple constructor status should be not set up.
   */
  @Test
  public void testSimpleConstructorStatus() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    String s = "Status: Ball not set up";
    assertEquals(s, simplePoolSimulator.getStatus());
  }

  /**
   * Before start, friction constructor status should be not set up.
   */
  @Test
  public void testFrictionConstructorStatus() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "friction");
    String s = "Status: Ball not set up";
    assertEquals(s, simplePoolSimulator.getStatus());
  }

  /**
   * Before start, x should be negative infinity.
   */
  @Test
  public void testSimpleConstructorNegativeX() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double d = Double.NEGATIVE_INFINITY;
    int zero = (int) (d - simplePoolSimulator.getBallPositionX());
    assertEquals(0, zero);
  }

  /**
   * Before start, y should be negative infinity.
   */
  @Test
  public void testSimpleConstructorNegativeY() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double d = Double.NEGATIVE_INFINITY;
    int zero = (int) (d - simplePoolSimulator.getBallPositionY());
    assertEquals(0, zero);
  }

  /**
   * Before start, radius should be negative infinity.
   */
  @Test
  public void testSimpleConstructorNegativeRadius() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double d = Double.NEGATIVE_INFINITY;
    int zero = (int) (d - simplePoolSimulator.getBallRadius());
    assertEquals(0, zero);
  }

  /**
   * Before start, Vx should be 0.
   */
  @Test
  public void testSimpleConstructorNegativeVx() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double d = 0;
    int zero = (int) (d - simplePoolSimulator.getBallVelocityX());
    assertEquals(0, zero);
  }

  /**
   * Before start, Vy should be zero.
   */
  @Test
  public void testSimpleConstructorNegativeVy() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double d = 0;
    int zero = (int) (d - simplePoolSimulator.getBallVelocityY());
    assertEquals(0, zero);
  }

  /**
   * Negative x in start method should throw an exception.
   */
  @Test
  public void testStartNegativeX() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(-10, 100, 20, 10, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Negative y in start method should throw an exception.
   */
  @Test
  public void testStartNegativeY() {
    boolean testPass = false;

    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    try {
      simplePoolSimulator.start(100, -10, 20, 10, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Negative radius in start method should throw an exception.
   */
  @Test
  public void testStartNegativeRadius() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(100, 100, -10, 10, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Negative speed in start method should throw an exception.
   */
  @Test
  public void testStartNegativeSpeed() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;

    try {
      simplePoolSimulator.start(100, 100, 10, -10, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Out of left bound in start method should throw an exception.
   */
  @Test
  public void testStartOutOfBoundLeft() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(100, 200, 150, 110, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Out of right bound in start method should throw an exception.
   */
  @Test
  public void testStartOutOfBoundRight() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(300, 200, 150, 110, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Out of upper bound in start method should throw an exception.
   */
  @Test
  public void testStartOutOfBoundUpper() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(200, 300, 150, 110, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * Out of bottom bound in start method should throw an exception.
   */
  @Test
  public void testStartOutOfBoundBottom() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(200, 100, 150, 110, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * If speed is 0 in start method, after 1 advance(), the ball should be stationary.
   */
  @Test
  public void testStartSpeedZero() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 150, 0, 10, 10);
    simplePoolSimulator.advance();
    String status = simplePoolSimulator.getStatus();
    String s = "Status: Ball is stationary";
    assertEquals(s, status);
  }

  /**
   * The ball can not touch the top and bottom sides, or the left and right sides at the same time.
   * The assignment do not define this situation, so the status can not be determined.
   */
  @Test
  public void testStartUndefined() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(200, 200, 200, 110, 10, 10);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * At least one of dx and dy must be non-zero.
   * It does not make sense to have velocity without a vector.
   */
  @Test
  public void testStartDxDyAreZero() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    boolean testPass = false;
    try {
      simplePoolSimulator.start(200, 200, 100, 110, 0, 0);
      fail("should be an exception there");
    } catch (IllegalArgumentException e) {
      testPass = true;
    }
    assertTrue(testPass);
  }

  /**
   * The status after start should be Simulation started.
   */
  @Test
  public void testStarStatus() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 110, 10, 10);
    String status = simplePoolSimulator.getStatus();
    String s = "Status: Simulation started";
    assertEquals(s, status);
  }

  /**
   * If an advance() is called before started, the status should be Ball not set up.
   */
  @Test
  public void testAdvanceBeforeStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.advance();
    String status = simplePoolSimulator.getStatus();
    String s = "Status: Ball not set up";
    assertEquals(s, status);
  }

  /**
   * To test the getBallPositionX can be called after construction, should be negative infinity.
   */
  @Test
  public void testGetXAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double x = simplePoolSimulator.getBallPositionX();
    int t = (int) (Double.NEGATIVE_INFINITY - x);
    assertEquals(0, t);
  }


  /**
   * To test the getBallPositionX can be called after start.
   */
  @Test
  public void testGetXAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    double x = simplePoolSimulator.getBallPositionX();
    int t = (int) (200 - x);
    assertEquals(0, t);
  }

  /**
   * To test the getBallPositionX can be called after one advance.
   */
  @Test
  public void testGetXAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    try {
      double x = simplePoolSimulator.getBallPositionX();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallPositionX can be called after multiple advance.
   */
  @Test
  public void testGetXAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        double x = simplePoolSimulator.getBallPositionX();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallPositionX can be called after stationary.
   */
  @Test
  public void testGetXAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    boolean testPass = false;
    testPass = true;
    assertTrue(testPass);
    for (int i = 0; i < 10000; i++) {
      try {
        double x = simplePoolSimulator.getBallPositionX();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }


  /**
   * To test the getBallPositionY can be called after construction, should be negative infinity.
   */
  @Test
  public void testGetYAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double y = simplePoolSimulator.getBallPositionX();
    int t = (int) (Double.NEGATIVE_INFINITY - y);
    assertEquals(0, t);
  }

  /**
   * To test the getBallPositionY can be called after start.
   */
  @Test
  public void testGetYAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    double y = simplePoolSimulator.getBallPositionX();
    int t = (int) (200 - y);
    assertEquals(0, t);
  }

  /**
   * To test the getBallPositionY can be called after one advance.
   */
  @Test
  public void testGetYAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    simplePoolSimulator.advance();
    try {
      double y = simplePoolSimulator.getBallPositionY();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallPositionY can be called after multiple advance.
   */
  @Test
  public void testGetYAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
      try {
        double y = simplePoolSimulator.getBallPositionY();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallPositionY can be called after stationary.
   */
  @Test
  public void testGetYAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        double y = simplePoolSimulator.getBallPositionY();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallRadius can be called after construction, should be negative infinity.
   */
  @Test
  public void testGetRadiusAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double r = simplePoolSimulator.getBallRadius();
    int t = (int) (Double.NEGATIVE_INFINITY - r);
    assertEquals(0, t);
  }

  /**
   * To test the getBallRadius can be called after start.
   */
  @Test
  public void testGetRadiusAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    double r = simplePoolSimulator.getBallRadius();
    int t = (int) (100 - r);
    assertEquals(0, t);
  }

  /**
   * To test the getBallRadius can be called after one advance.
   */
  @Test
  public void testGetRadiusAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    simplePoolSimulator.advance();
    try {
      double y = simplePoolSimulator.getBallRadius();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallRadius can be called after multiple advance.
   */
  @Test
  public void testGetRadiusAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
      try {
        double y = simplePoolSimulator.getBallPositionY();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallRadius can be called after stationary.
   */
  @Test
  public void testGetRadiusAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    try {
      double radius = simplePoolSimulator.getBallRadius();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }


  /**
   * To test the getBallVelocityX can be called after construction, should be 0.
   */
  @Test
  public void testGetVelocityXAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double vX = simplePoolSimulator.getBallVelocityX();
    int t = (int) (0.0 - vX);
    assertEquals(0, t);
  }

  /**
   * To test the getBallVelocityX can be called after start.
   */
  @Test
  public void testGetVelocityXAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    try {
      simplePoolSimulator.getBallVelocityX();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallVelocityX can be called after one advance.
   */
  @Test
  public void testGetVelocityXAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    simplePoolSimulator.advance();
    try {
      simplePoolSimulator.getBallVelocityX();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallVelocityX can be called after multiple advance.
   */
  @Test
  public void testGetVelocityXAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
      try {
        double y = simplePoolSimulator.getBallVelocityX();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallVelocityX can be called after stationary.
   */
  @Test
  public void testGetVelocityXAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    try {
      double radius = simplePoolSimulator.getBallVelocityX();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallVelocityY can be called after construction, should be negative infinity.
   */
  @Test
  public void testGetVelocityYAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    double vY = simplePoolSimulator.getBallVelocityY();
    int t = (int) (0.0 - vY);
    assertEquals(0, t);
  }

  /**
   * To test the getBallVelocityY can be called after start.
   */
  @Test
  public void testGetVelocityYAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    try {
      simplePoolSimulator.getBallVelocityY();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallVelocityY can be called after one advance.
   */
  @Test
  public void testGetVelocityYAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    simplePoolSimulator.advance();
    try {
      simplePoolSimulator.getBallVelocityY();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallVelocityY can be called after multiple advance.
   */
  @Test
  public void testGetVelocityYAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
      try {
        double y = simplePoolSimulator.getBallVelocityY();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallVelocityY can be called after stationary.
   */
  @Test
  public void testGetVelocityYAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    try {
      double radius = simplePoolSimulator.getBallVelocityY();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallStatus can be called after construction, should be Ball not set up."
   */
  @Test
  public void testGetStatusAfterConstructor() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball not set up", s);
  }

  /**
   * To test the getBallStatus can be called after start, should be Simulation started.
   */
  @Test
  public void testGetStatusAfterStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Simulation started", s);
  }

  /**
   * To test the getBallStatus can be called after one advance.
   */
  @Test
  public void testGetStatusAfterAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    simplePoolSimulator.advance();
    try {
      simplePoolSimulator.getStatus();
    } catch (Exception e) {
      fail("should not have any exception here");
    }
  }

  /**
   * To test the getBallStatus can be called after multiple advance.
   */
  @Test
  public void testGetStatusAfterMultipleAdvance() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
      try {
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the getBallStatus can be called after stationary, should be Ball is stationary.
   */
  @Test
  public void testGetStatusAfterStationary() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 0, 10, 10);
    simplePoolSimulator.advance();
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball is stationary", s);
  }

  /**
   * To test the ball edge touch the upper bound at beginning, should not have exception.
   */
  @Test
  public void testUpperEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 300, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the bottom bound at beginning, should not have exception.
   */
  @Test
  public void testBottomEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 100, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the left bound at beginning, should not have exception.
   */
  @Test
  public void testLeftEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(100, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the right bound at beginning, should not have exception.
   */
  @Test
  public void testRightEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(300, 200, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }


  /**
   * To test the ball edge touch the top right bound at beginning, should not have exception.
   */
  @Test
  public void testTopRightCornerTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(300, 300, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the top left bound at beginning, should not have exception.
   */
  @Test
  public void testTopLeftEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(100, 300, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the bottom right bound at beginning, should not have exception.
   */
  @Test
  public void testBottomRightEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(300, 100, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the ball edge touch the bottom left bound at beginning, should not have exception.
   */
  @Test
  public void testBottomLeftEdgeTouchStart() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(100, 100, 100, 100, 10, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test start with dx = 0. Should not have any exception,
   */
  @Test
  public void testDxZero() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(300, 200, 100, 100, 0, 10);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test start with dy = 0. Should not have any exception,
   */
  @Test
  public void testDyZero() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(300, 200, 100, 100, 10, 0);
    for (int i = 0; i < 10000; i++) {
      try {
        simplePoolSimulator.advance();
        simplePoolSimulator.getStatus();
      } catch (Exception e) {
        fail("should not have any exception here");
      }
    }
  }

  /**
   * To test the correctness of the hit upper bound status.
   */
  @Test
  public void testHitTop() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 250, 100, 10, 1, 2);
    simplePoolSimulator.advance();
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball hit top edge", s);
  }

  /**
   * To test the correctness of the hit right bound status.
   */
  @Test
  public void testHitRight() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(250, 200, 100, 10, 2, 1);
    simplePoolSimulator.advance();
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball hit right edge", s);
  }

  /**
   * To test the correctness of the hit bottom bound status.
   */
  @Test
  public void testHitBottom() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 300, 100, 10, 1, -2);
    simplePoolSimulator.advance();
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball hit bottom edge", s);
  }

  /**
   * To test the correctness of the hit left bound status.
   */
  @Test
  public void testHitLeft() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(150, 200, 100, 10, -2, 1);
    simplePoolSimulator.advance();
    String s = simplePoolSimulator.getStatus();
    assertEquals("Status: Ball hit left edge", s);
  }


  /**
   * To test the overall correctness of the simple simulation.
   */
  @Test
  public void testSimple() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "simple");
    simplePoolSimulator.start(200, 200, 100, 10, 3, 4);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
    }
    double x = 400 - 100;
    double rX = simplePoolSimulator.getBallPositionX();

    assertEquals(x, rX, 0.001);
    double y = 266.6666667;
    double rY = simplePoolSimulator.getBallPositionY() - y;
    assertEquals(0.0, rY, 0.00001);
    String s = "Status: Ball is stationary";
    String rS = simplePoolSimulator.getStatus();
    assertEquals(s, rS);
  }

  /**
   * To test the correctness of the friction simulation.
   */
  @Test
  public void testFriction() {
    simplePoolSimulator = new SimplePoolSimulator(400, 400, "friction");
    simplePoolSimulator.start(200, 200, 100, 100, 3, 4);
    for (int i = 0; i < 10000; i++) {
      simplePoolSimulator.advance();
    }
    double x = 141.896;
    double rX = simplePoolSimulator.getBallPositionX();
    assertEquals(x, rX, 0.001);
    double y = 277.471;
    double rY = simplePoolSimulator.getBallPositionY() - y;
    assertEquals(0.0, rY, 0.001);
    String s = "Status: Ball is stationary";
    String rS = simplePoolSimulator.getStatus();
    assertEquals(s, rS);
  }


}
