package layout;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * This class  It also supports operations to return the name,
 * index and also the items found in the space.
 */
public class SpaceImpl implements Space {
  private String name;
  private int index;
  private final int topLeftRow;
  private final int topLeftCol;
  private final int bottomRightRow;
  private final int bottomRightCol;
  private Map<String, Item> items;

  /**
   * creates a space in the given world when a name, row and col of the top left and bottom right
   * corner and the list of items are provided.
   * @param name the name of the space
   * @param index the index of the space in the given list of spaces
   * @param topLeftRow the top left row of the space
   * @param topLeftCol the top left column of the space
   * @param bottomRightRow the bottom right row of the space
   * @param bottomRightCol the bottom right column of the space
   * @param itemMap the map of items and its name
   */
  public SpaceImpl(String name,
                   int index,
                   int topLeftRow,
                   int topLeftCol,
                   int bottomRightRow,
                   int bottomRightCol,
                   Map<String, Item> itemMap) {
    this.name = name;
    this.index = index;
    this.topLeftRow = topLeftRow;
    this.topLeftCol = topLeftCol;
    this.bottomRightRow = bottomRightRow;
    this.bottomRightCol = bottomRightCol;
    this.items = itemMap;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public Set<String> getItems() {
    return items.keySet();
  }

  @Override
  public int getTopLeftRow() {
    return topLeftRow;
  }

  @Override
  public int getTopLeftCol() {
    return topLeftCol;
  }

  @Override
  public int getBottomRightRow() {
    return bottomRightRow;
  }

  @Override
  public int getBottomRightCol() {
    return bottomRightCol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (! (o instanceof Space)) {
      return false;
    }
    SpaceImpl s2 = (SpaceImpl) o;
    return topLeftRow == s2.topLeftRow
            && topLeftCol == s2.topLeftCol
            && bottomRightRow == s2.bottomRightRow
            && bottomRightCol == bottomRightCol
            && name == s2.name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(topLeftRow, topLeftCol, bottomRightRow, bottomRightCol, name);
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    String title = String.format("Name : %s\n", name);
    String index = String.format("Index : %s\n", this.index);
    String items = String.format("Items : %s\n", this.items.keySet());
    str.append(title);
    str.append(index);
    str.append(items);

    return str.toString();
  }
}