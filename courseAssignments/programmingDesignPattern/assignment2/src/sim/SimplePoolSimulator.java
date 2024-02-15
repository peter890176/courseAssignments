package sim;

/**
 * This class is a simulator. The main goal is to simulate the ball motion.
 */
public class SimplePoolSimulator implements PoolSimulator {
  private final double width;
  private final double height;
  private final String type;
  private String status;
  private String nextStatus;
  private double x;
  private double y;
  private double radius;
  private double speed;
  private double dx;
  private double dy;
  private double normalizedDx;
  private double normalizedDy;
  private double distanceX;
  private double distanceY;
  private double discriminantX;
  private double discriminantY;
  private boolean lastCall = false;
  private double nextX;
  private double nextY;
  private double nSpeed;
  private double nDx;
  private double nDy;
  private boolean startFlag = false;

  /**
   * This constructor is to build the table and also decide the type of this simulation.
   *
   * @param width  the width of the table.
   * @param height the height of the table.
   * @param type   the type of this simulation.
   */
  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be integer and strictly bigger "
              + "than 0");
    }
    if (!type.equals("simple") && !type.equals("friction")) {
      throw new IllegalArgumentException("type must be either simple or friction"
              + "than 0");
    }
    this.width = width;
    this.height = height;
    this.type = type;
    status = "Status: Ball not set up";
    x = Double.NEGATIVE_INFINITY;
    y = Double.NEGATIVE_INFINITY;
    speed = Double.NEGATIVE_INFINITY;
    radius = Double.NEGATIVE_INFINITY;
  }

  /**
   * Start the simulation and give some starting information, only should be called once.
   *
   * @param x      the starting x position.
   * @param y      the starting y position.
   * @param radius the radius of the ball.
   * @param speed  the speed of the ball.
   * @param dx     the divided vector of the ball on x coordinate.
   * @param dy     the divided vector of the ball on y coordinate.
   */
  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy) throws
          IllegalArgumentException {
    startFlag = true;
    if (x <= 0 || y <= 0 || radius <= 0 || speed < 0) {
      throw new IllegalArgumentException("x, y and radius must be strictly larger than 0. "
              + "Speed must be larger than 0");
    }
    if (x + radius > width || x - radius < 0 || y + radius > height || y - radius < 0) {
      throw new IllegalArgumentException("the ball is out of bound");
    }

    if (speed == 0) {
      nextX = x;
      nextY = y;
      nSpeed = 0;
      nDx = dx;
      lastCall = true;
    } else {
      if (x + radius == width && x - radius == 0 || y + radius == height && y - height == 0) {
        throw new IllegalArgumentException("undefined, the ball stuck or non-stop status");
      }
      if (dx == 0 && dy == 0) {
        throw new IllegalArgumentException("dx and dy should be at least one not 0");
      }
      this.x = x;
      this.y = y;
      this.radius = radius;
      this.speed = speed;
      this.dx = dx;
      this.dy = dy;
      status = "Status: Simulation started";
      double normal = Math.sqrt(Math.pow(Math.abs(dx), 2) + Math.pow(Math.abs(dy), 2));
      normalizedDx = Math.abs(dx) / normal;
      normalizedDy = Math.abs(dy) / normal;
    }
  }

  /**
   * When called, this method advances the simulation by one discrete step.
   * To the next bounce, or the ball stopping.
   */

  private void nextStep() {
    if (speed == 0) {
      nextX = x;
      nextY = y;
      nSpeed = 0;
      nDx = dx;
      lastCall = true;
    } else {
      displacement();
      double xT;
      double yT;
      double minT;
      if (type.equals("simple")) {
        if (normalizedDx != 0) {
          xT = distanceX / (speed * normalizedDx);
        } else {
          xT = Double.MAX_VALUE;
        }
        if (normalizedDy != 0) {
          yT = distanceY / (speed * normalizedDy);
        } else {
          yT = Double.MAX_VALUE;
        }
        if (xT < yT) {
          hitXNext(xT);
        } else {
          hitYNext(yT);
        }

      } else {
        calculateDiscriminant();
        double xT1 = calculateT(normalizedDx, distanceX, 1);
        double xT2 = calculateT(normalizedDx, distanceX, -1);
        double yT1 = calculateT(normalizedDy, distanceY, 1);
        double yT2 = calculateT(normalizedDy, distanceY, -1);
        if (xT1 < xT2) {
          xT = xT1;
        } else {
          xT = xT2;
        }
        if (yT1 < yT2) {
          yT = yT1;
        } else {
          yT = yT2;
        }

        if (discriminantX >= 0 && discriminantY >= 0) {
          if (xT < yT) {
            hitXNext(xT);
          } else {
            hitYNext(yT);
          }
        }
        if (discriminantX >= 0 && discriminantY < 0) {
          hitXNext(xT);
        }
        if (discriminantX < 0 && discriminantY >= 0) {
          hitYNext(yT);
        }
        if (discriminantX < 0 && discriminantY < 0) {
          minT = speed / 0.981;
          double mx = speed * normalizedDx * minT - 0.5 * 0.981 * normalizedDx * Math.pow(minT, 2);
          if (dx > 0) {
            nextX = x + mx;
          } else {
            nextX = x - mx;
          }
          double my = speed * normalizedDy * minT - 0.5 * 0.981 * normalizedDy * Math.pow(minT, 2);
          if (dy > 0) {
            nextY = y + my;
          } else {
            nextY = y - my;
          }
          nDx = dx;
          nDy = dy;
          nSpeed = 0;
          lastCall = true;
        }
      }
    }
  }

  private void calculateDiscriminant() {
    if (dx != 0) {
      discriminantX = Math.pow(speed * normalizedDx, 2) - 2 * 0.981 * normalizedDx * distanceX;
    } else {
      discriminantX = -1;
    }
    if (dy != 0) {
      discriminantY = Math.pow(speed * normalizedDy, 2) - 2 * 0.981 * normalizedDy * distanceY;
    } else {
      discriminantY = -1;
    }
  }

  private double calculateT(double normalized, double distance, double pn) {
    if (normalized != 0) {
      return (speed * normalized + (pn * (Math.sqrt(Math.pow(speed * normalized, 2)
              - 2 * 0.981 * normalized * distance)))) / (0.981 * normalized);
    } else {
      return Double.MAX_VALUE;
    }
  }

  private void hitXNext(double minT) {
    if (dx > 0) {
      nextX = width - radius;
      nextStatus = "Status: Ball hit right edge";
    } else {
      nextX = radius;
      nextStatus = "Status: Ball hit left edge";
    }
    double my;
    if (type.equals("friction")) {
      my = speed * normalizedDy * minT - 0.5 * 0.981 * normalizedDy * Math.pow(minT, 2);
      nSpeed = speed - minT * 0.981;
    } else {
      my = speed * normalizedDy * minT;
      nSpeed = speed - 5;
      if (nSpeed <= 0) {
        nSpeed = 0;
        lastCall = true;
      }
    }
    if (dy > 0) {
      nextY = y + my;
    } else {
      nextY = y - my;
    }
    nDx = -dx;
    nDy = dy;
  }

  private void hitYNext(double minT) {
    if (dy > 0) {
      nextY = height - radius;
      nextStatus = "Status: Ball hit top edge";
    } else {
      nextY = radius;
      nextStatus = "Status: Ball hit bottom edge";
    }
    double mx;
    if (type.equals("friction")) {
      mx = speed * normalizedDx * minT - 0.5 * 0.981 * normalizedDx * Math.pow(minT, 2);
      nSpeed = speed - minT * 0.981;
    } else {
      mx = speed * normalizedDx * minT;
      nSpeed = speed - 5;
      if (nSpeed <= 0) {
        nSpeed = 0;
        lastCall = true;
      }
    }
    if (dx > 0) {
      nextX = x + mx;
    } else {
      nextX = x - mx;
    }
    nDx = dx;
    nDy = -dy;
  }

  private void displacement() {
    if (dx > 0) {
      distanceX = width - radius - x;
    } else {
      distanceX = x - radius;
    }
    if (dy > 0) {
      distanceY = height - radius - y;
    } else {
      distanceY = y - radius;
    }
  }

  /**
   * When called, this method advances the next discrete step of the simulation.
   * To the next bounce, or the ball stopping.
   */
  @Override
  public void advance() {
    if (!startFlag) {
      status = "Status: Ball not set up";
    } else {
      nextStep();
      x = nextX;
      y = nextY;
      speed = nSpeed;
      dx = nDx;
      dy = nDy;
      status = nextStatus;
      if (!lastCall) {
        nextStep();
      }
    }
  }

  /**
   * When called, this method return the width of the table.
   */
  @Override
  public int getTableWidth() {
    return (int) width;
  }

  /**
   * When called, this method return the height of the table.
   */
  @Override
  public int getTableHeight() {
    return (int) height;
  }

  /**
   * When called, this method return the position of the ball on X coordinate.
   */
  @Override
  public double getBallPositionX() {
    return x;
  }

  /**
   * * When called, this method return the position of the ball on Y coordinate.
   */
  @Override
  public double getBallPositionY() {
    return y;
  }

  /**
   * When called, this method return the radius of the ball.
   */
  @Override
  public double getBallRadius() {
    return radius;
  }

  /**
   * When called, this method return the velocity of the ball in divided vector on X coordinate.
   */
  @Override
  public double getBallVelocityX() {
    if (status.equals("Status: Ball not set up")) {
      return 0.0;
    } else {
      if (dx < 0) {
        return -speed * normalizedDx;
      } else {
        return speed * normalizedDx;
      }
    }
  }


  @Override
  public double getBallVelocityY() {
    if (status.equals("Status: Ball not set up")) {
      return 0.0;
    } else {
      if (dy < 0) {
        return -speed * normalizedDy;
      } else {
        return speed * normalizedDy;
      }
    }
  }

  /**
   * When called, this method return the status of the ball, and if the speed is 0, then is final.
   */
  @Override
  public String getStatus() {
    if (speed == 0) {
      return "Status: Ball is stationary";
    } else {
      return status;
    }
  }

}