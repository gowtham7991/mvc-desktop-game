package layout;

/**
 * Represents a single unit of a space when seen as a 2-D plane.
 */
public class Unit {
  private final int row;
  private final int col;
  private final int spaceIndex;

  /**
   * Creates a unit in the 2-D plane when a row, columns
   * and the space index of the space is provided.
   * @param row the location of the unit as a row in the world
   * @param col the location of the unit as a column in the world
   * @param spaceIndex the index of the space occupied.
   */
  public Unit(int row, int col, int spaceIndex) {
    this.row = row;
    this.col = col;
    this.spaceIndex = spaceIndex;
  }

  /**
   * Returns the row in the 2-D plane of the unit.
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns the col in the 2-D plane of the unit.
   * @return the col
   */
  public int getCol() {
    return col;
  }

  /**
   * Returns the index of the space.
   * @return the space index
   */
  public int getSpaceIndex() {
    return spaceIndex;
  }
}
