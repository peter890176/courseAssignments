package mat;

/**
 * This class is an instance of the squareMatrix, which is SparseMatrix.
 * The feature of this structure is to represents the matrix using lesser nodes.
 */
public class SparseMatrix implements SquareMatrix {
  private final SparseNode root;
  private final SparseNode[] sparseNodesXArray;
  private final SparseNode[] sparseNodesYArray;
  private final int size;
  private final float epsilon = 0.0000001f;


  /**
   * The constructor of the SparseMatrix, should be initialized with the matrix size.
   *
   * @param size the size of the matrix
   * @throws IllegalArgumentException if the size is negative
   */
  public SparseMatrix(int size) throws IllegalArgumentException {
    if (size < 0) {
      throw new IllegalArgumentException("The size of an spare matrix cannot be non-positive");
    }
    this.size = size;
    root = new SparseNode(-1, -1, Float.NEGATIVE_INFINITY);
    SparseNode current = root;
    root.nodeNum = size;
    this.sparseNodesXArray = new SparseNode[size + 1];
    this.sparseNodesYArray = new SparseNode[size + 1];
    sparseNodesXArray[0] = root;
    sparseNodesYArray[0] = root;

    for (int i = 0; i < size; i++) {
      SparseNode nextNode = new SparseNode(i, -1, Float.NEGATIVE_INFINITY);
      current.xNext = nextNode;
      current = nextNode;
      current.nodeNum = 0;
      current.yNext = current;
      sparseNodesXArray[i + 1] = nextNode;
    }
    current = root;

    for (int i = 0; i < size; i++) {
      SparseNode nextNode = new SparseNode(-1, i, Float.NEGATIVE_INFINITY);
      current.yNext = nextNode;
      current = nextNode;
      current.nodeNum = 0;
      current.xNext = current;
      sparseNodesYArray[i + 1] = nextNode;
    }
  }

  /**
   * Set this matrix to the identity matrix.
   */
  @Override
  public void setIdentity() {
    if (size == 0) {
      return;
    }
    SparseNode currX = root;
    SparseNode currY = root;
    for (int i = 0; i < size; i++) {
      currX = currX.xNext;
      currY = currY.yNext;
      SparseNode newNode = new SparseNode(i, i, 1);
      currX.yNext = newNode;
      currY.xNext = newNode;
      newNode.yNext = currX;
      newNode.xNext = currY;
      currX.nodeNum = 1;
      currY.nodeNum = 1;
    }
  }

  /**
   * This method should set the specific value into the specific cell.
   *
   * @param i     the row of the cell
   * @param j     the column of the cell
   * @param value the floating point value
   * @throws IllegalArgumentException if the specific cell if out of bound
   */
  @Override
  public void set(int i, int j, float value) throws IllegalArgumentException {
    if (i < 0 || i >= size || j < 0 || j >= size) {
      throw new IllegalArgumentException("parameters only allow form 0 to size - 1");
    }
    if (size == 0) {
      return;
    }
    SparseNode newNode = new SparseNode(i, j, value);
    boolean zeroFlag = (Math.abs(value - 0) < epsilon);
    SparseNode currX = sparseNodesXArray[i + 1];
    SparseNode currY = sparseNodesYArray[j + 1];
    SparseNode sentinelX = currX;
    SparseNode sentinelY = currY;

    if (currX.nodeNum == 0) {
      if (!zeroFlag) {
        currX.yNext = newNode;
        newNode.yNext = currX;
        sentinelX.nodeNum = sentinelX.nodeNum + 1;
      }
    } else {
      while (currX.yNext.y < j && (currX.yNext.y != -1)) {
        currX = currX.yNext;
      }

      if (currX.yNext.y == -1) {
        if (!zeroFlag) {
          SparseNode temp = currX.yNext;
          currX.yNext = newNode;
          newNode.yNext = temp;
          sentinelX.nodeNum = sentinelX.nodeNum + 1;
        }
      } else if (currX.yNext.y == j) {

        if (zeroFlag) {
          currX.yNext = currX.yNext.yNext;
          sentinelX.nodeNum = sentinelX.nodeNum - 1;
        } else {
          currX.yNext.value = value;
          newNode = currX.yNext;
        }
      } else {
        if (!zeroFlag) {
          SparseNode temp = currX.yNext;
          currX.yNext = newNode;
          newNode.yNext = temp;
          sentinelX.nodeNum = sentinelX.nodeNum + 1;
        }
      }
    }

    if (currY.nodeNum == 0) {
      if (!zeroFlag) {
        currY.xNext = newNode;
        newNode.xNext = currY;
        sentinelY.nodeNum = sentinelY.nodeNum + 1;
      }
    } else {
      while (currY.xNext.x < i && (currY.xNext.x != -1)) {
        currY = currY.xNext;
      }

      if (currY.xNext.x == -1) {
        if (!zeroFlag) {
          SparseNode temp = currY.xNext;
          currY.xNext = newNode;
          newNode.xNext = temp;
          sentinelY.nodeNum = sentinelY.nodeNum + 1;
        }
      } else if (currY.xNext.x == i) {

        if (zeroFlag) {
          currY.xNext = currY.xNext.xNext;
          sentinelY.nodeNum = sentinelY.nodeNum - 1;
        }
      } else {
        if (!zeroFlag) {
          SparseNode temp = currY.xNext;
          currY.xNext = newNode;
          newNode.xNext = temp;
          sentinelY.nodeNum = sentinelY.nodeNum + 1;
        }
      }
    }
  }

  /**
   * This method should return the specific value of the specific (x,y) of the matrix.
   *
   * @param i the row of the specific cell
   * @param j the column of the specific cell
   * @return the value at the specific row and column
   * @throws IllegalArgumentException if the row or column is out of bound
   */
  @Override
  public float get(int i, int j) throws IllegalArgumentException {
    if (i < 0 || i >= size || j < 0 || j >= size) {
      throw new IllegalArgumentException("parameters only allow form 0 to size - 1");
    }
    if (size == 0) {
      throw new IllegalArgumentException("could not get value from size 0 matrix");
    }

    if (sparseNodesXArray[i + 1].nodeNum == 0) {
      return 0.0f;
    }
    SparseNode currX = sparseNodesXArray[i + 1];
    SparseNode currY = sparseNodesYArray[j + 1];
    SparseNode currNode;
    float f = 0;
    if (currX.nodeNum <= currY.nodeNum) {
      currNode = currX;
      for (int a = 0; a < currX.nodeNum; a++) {
        currNode = currNode.yNext;
        if (currNode.y == -1) {
          return 0.0f;
        }
        if (currNode.y == j) {
          f = currNode.value;
          return f;
        }
      }
    } else {
      currNode = currY;
      for (int a = 0; a < currY.nodeNum; a++) {
        currNode = currNode.xNext;
        if (currNode.x == -1) {
          return 0.0f;
        }
        if (currNode.x == i) {
          f = currNode.value;
          return f;
        }
      }
    }
    return 0.0f;
  }

  /**
   * This method should return a result that is containing the sum of two matrix.
   * The two original matrix should not be changed.
   *
   * @param other the other matrix to be added to this
   * @return the sum of this and other matrix
   * @throws IllegalArgumentException if the size of two matrix are not the same
   */
  @Override
  public SquareMatrix add(SquareMatrix other) throws IllegalArgumentException {
    if (this.size != other.size()) {
      throw new IllegalArgumentException("two matrix should "
              + "be same size");
    }
    if (size == 0) {
      return this;
    }
    SquareMatrix result = new SparseMatrix(size);
    SparseNode thisCurr = this.root;
    if (other instanceof SparseMatrix) {
      SparseNode otherCurr = ((SparseMatrix) other).root;
      for (int i = 0; i < size; i++) {
        thisCurr = thisCurr.xNext;
        otherCurr = otherCurr.xNext;
        SparseNode tempThisCurr = thisCurr;
        SparseNode tempOtherCurr = otherCurr;
        while ((tempThisCurr.yNext.y != -1) && (tempOtherCurr.yNext.y != -1)) {
          if (tempThisCurr.yNext.y < tempOtherCurr.yNext.y) {
            result.set(i, tempThisCurr.yNext.y, tempThisCurr.yNext.value);
            tempThisCurr = tempThisCurr.yNext;
          } else if (tempThisCurr.yNext.y > tempOtherCurr.yNext.y) {
            result.set(i, tempOtherCurr.yNext.y, tempOtherCurr.yNext.value);
            tempOtherCurr = tempOtherCurr.yNext;
          } else {
            float sum = tempThisCurr.yNext.value + tempOtherCurr.yNext.value;

            float uns = Math.abs(sum);
            if (uns > epsilon) {
              result.set(i, tempThisCurr.yNext.y, sum);
            }
            tempOtherCurr = tempOtherCurr.yNext;
            tempThisCurr = tempThisCurr.yNext;
          }
        }
        if (tempThisCurr.yNext.y == -1 && tempOtherCurr.yNext.y != -1) {
          while (tempOtherCurr.yNext.y != -1) {
            result.set(i, tempOtherCurr.yNext.y, tempOtherCurr.yNext.value);
            tempOtherCurr = tempOtherCurr.yNext;
          }
        }
        if (tempThisCurr.yNext.y != -1 && tempOtherCurr.yNext.y == -1) {
          while (tempThisCurr.yNext.y != -1) {
            result.set(i, tempThisCurr.yNext.y, tempThisCurr.yNext.value);
            tempThisCurr = tempThisCurr.yNext;
          }
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          float thisValue = this.get(i, j);
          float otherValue = other.get(i, j);
          float sum = thisValue + otherValue;
          if (Math.abs(sum - 0) > epsilon) {
            result.set(i, j, sum);
          }
        }
      }
    }
    return result;
  }

  /**
   * This method should return the pre multiplication matrix result.
   * Two original matrix should remain unchanged.*
   *
   * @param other the other matrix
   * @return the product of the post-multiplication of this with other
   * @throws IllegalArgumentException if the size of two matrix are not the same
   */
  @Override
  public SquareMatrix premul(SquareMatrix other) throws IllegalArgumentException {
    if (this.size != other.size()) {
      throw new IllegalArgumentException("two matrix should " + "be same size");
    }
    if (size == 0) {
      return this;
    }
    SquareMatrix result = new SparseMatrix(size);
    if (other instanceof SparseMatrix) {
      for (int i = 0; i < other.size(); i += 1) {
        if (((SparseMatrix) other).sparseNodesXArray[i + 1].nodeNum == 0) {
          continue;
        }
        for (int j = 0; j < this.size(); j += 1) {
          if (this.sparseNodesYArray[j + 1].nodeNum == 0) {
            continue;
          }
          float product = 0;

          SparseNode tempNodeThis = this.sparseNodesYArray[j + 1].xNext;
          SparseNode tempNodeOther = ((SparseMatrix) other).sparseNodesXArray[i + 1].yNext;
          while (tempNodeThis.x != -1 && tempNodeOther.y != -1) {
            if (tempNodeThis.x == tempNodeOther.y) {
              product = product + tempNodeThis.value * tempNodeOther.value;
              tempNodeThis = tempNodeThis.xNext;
              tempNodeOther = tempNodeOther.yNext;
            } else if (tempNodeThis.x < tempNodeOther.y) {
              tempNodeThis = tempNodeThis.xNext;
            } else {
              //if(tempNodeOther.y<tempNodeThis.x)
              tempNodeOther = tempNodeOther.yNext;
            }
          }
          if (Math.abs(product - 0) > epsilon) {
            result.set(i, j, product);
          }
        }
      }
    } else {
      for (int i = 0; i < other.size(); i += 1) {
        for (int j = 0; j < this.size(); j += 1) {
          if (this.sparseNodesYArray[j + 1].nodeNum == 0) {
            continue;
          }
          float product = 0;

          SparseNode tempNodeThis = this.sparseNodesYArray[j + 1].xNext;
          while (tempNodeThis.x != -1) {
            product = product + tempNodeThis.value * other.get(i, tempNodeThis.x);
            tempNodeThis = tempNodeThis.xNext;
          }

          if (Math.abs(product - 0) > epsilon) {
            result.set(i, j, product);
          }
        }
      }
    }
    return result;
  }

  /**
   * This method should return the post multiplication matrix result.
   * Two original matrix should remain unchanged.*
   *
   * @param other the other matrix
   * @return the product of the post-multiplication of this with other
   * @throws IllegalArgumentException if the size of two matrix are not the same
   */
  @Override
  public SquareMatrix postmul(SquareMatrix other) throws IllegalArgumentException {
    if (this.size != other.size()) {
      throw new IllegalArgumentException("two matrix should " + "be same size");
    }
    if (size == 0) {
      return this;
    }
    SquareMatrix result = new SparseMatrix(size);
    if (other instanceof SparseMatrix) {
      for (int i = 0; i < other.size(); i += 1) {
        if (this.sparseNodesXArray[i + 1].nodeNum == 0) {
          continue;
        }
        for (int j = 0; j < this.size(); j += 1) {
          if (((SparseMatrix) other).sparseNodesYArray[j + 1].nodeNum == 0) {
            continue;
          }
          float product = 0;
          SparseNode tempNodeThis = this.sparseNodesXArray[i + 1].yNext;
          SparseNode tempNodeOther = ((SparseMatrix) other).sparseNodesYArray[j + 1].xNext;

          while (tempNodeThis.y != -1 && tempNodeOther.x != -1) {
            if (tempNodeThis.y == tempNodeOther.x) {
              product = product + tempNodeThis.value * tempNodeOther.value;
              tempNodeThis = tempNodeThis.yNext;
              tempNodeOther = tempNodeOther.xNext;
            } else if (tempNodeThis.y < tempNodeOther.x) {
              tempNodeThis = tempNodeThis.yNext;
            } else {
              //if(tempNodeOther.y<tempNodeThis.x)
              tempNodeOther = tempNodeOther.xNext;
            }
          }
          if (Math.abs(product - 0) > epsilon) {
            result.set(i, j, product);
          }
        }
      }
    } else {
      for (int i = 0; i < other.size(); i += 1) {
        if (this.sparseNodesXArray[i + 1].nodeNum == 0) {
          continue;
        }
        for (int j = 0; j < this.size(); j += 1) {
          float product = 0;
          SparseNode tempNodeThis = this.sparseNodesXArray[i + 1].yNext;
          while (tempNodeThis.y != -1) {
            product = product + tempNodeThis.value * other.get(tempNodeThis.y, j);
            tempNodeThis = tempNodeThis.yNext;
          }
          if (Math.abs(product - 0) > epsilon) {
            result.set(i, j, product);
          }
        }
      }
    }
    return result;
  }


  /**
   * Get the size of this matrix (number of rows or number of columns).
   *
   * @return the size of this matrix
   */
  @Override
  public int size() {
    return this.size;
  }
}