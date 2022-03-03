import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceImplTest {

  @Test
  public void testValidSpaceCreation() {}

  @Test
  public void testSpaceCreationInvalidName() {}

  @Test
  public void testSpaceCreationNullName() {}

  @Test
  public void testSpaceCreationNegativeTopLeftXcoordinate() {}

  @Test
  public void testSpaceCreationNegativeTopLeftYcoordinate() {}

  @Test
  public void testSpaceCreationNegativeBottomRightXcoordinate() {}

  @Test
  public void testSpaceCreationNegativeBottomRightYcoordinate() {}

  @Test
  public void testSpaceCreationTopLeftXgreaterThanBottomRight() {}

  @Test
  public void testSpaceCreationTopLeftYgreaterThanBottomRight() {}

  @Test
  public void testSpaceCreationTopLeftXequalsBottomRight() {}

  @Test
  public void testSpaceCreationTopLeftYequalsBottomRight() {}
}