package sim;

/**
 * This interface provide the basic operations of the simulation of the ball.
 * Including constructor, start method, advancing method, final status, and other methods.
 */
public interface PoolSimulator {

  /**
   * Start the simulation with a ball at the given position, with the given radius and velocity.
   * This method throws an exception if its parameters are invalid.
   * The conditions for invalidity are dependent on the implementation.
   */
  void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException;

  /**
   * When called, this method advances the simulation by one discrete step.
   * To the next bounce, or the ball stopping.
   */
  void advance();

  /**
   * When called, this method return the width of the table.
   */
  int getTableWidth();

  /**
   * When called, this method return the Height of the table.
   */
  int getTableHeight();

  /**
   * When called, this method return the x coordinate of the current ball position.
   */
  double getBallPositionX();

  /**
   * When called, this method return the y coordinate of the current ball position.
   */
  double getBallPositionY();

  /**
   * When called, this method return the radius the current ball.
   */
  double getBallRadius();

  /**
   * When called, this method return the x Velocity of the current ball position.
   */
  double getBallVelocityX();

  /**
   * When called, this method return the y Velocity of the current ball position.
   */
  double getBallVelocityY();

  /**
   * When called, this method return the current status of the ball situation.
   */
  String getStatus();
}
