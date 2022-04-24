package view;

import controller.Features;
import java.io.File;
import java.util.List;
import model.ReadOnlyModel;

public interface View {
  /**
   * This interface represents a set of features that the program offers. Each
   * feature is exposed as a function in this interface. This function is used
   * suitably as a callback by the view, to pass control to the controller. How
   * the view uses them as callbacks is completely up to how the view is designed
   * (e.g. it could use them as a callback for a button, or a callback for a
   * dialog box, or a set of text inputs, etc.)
   *
   * <p>Each function is designed to take in the necessary data to complete that
   * functionality.
   */
  void setFeatures(Features f);

  /**
   * Prompts the users to add players into the game.
   */
  void startGame();

  /**
   * Starts the game by displaying the game board.
   */
  void begin();

  /**
   * Quits the game by disposing all the screens.
   */
  void quit();

  /**
   * Resets the view and opens the first screen.
   */
  void reset();

  /**
   * Refreshes the screen.
   */
  void refresh();

  /**
   * Opens the add player prompt.
   * @return
   */
  List<String> openAddPlayerPrompt(List<String> spaces);

  /**
   * Opens the prompt with the given list of options
   * @param options the list of options in the dropdown
   * @return the option chosen
   */
  String openPrompt(List<String> options, String prompt);

  /**
   * Opens the lookaround prompt.
   * @param text the response
   */
  void openLookAroundPrompt(String text);

  /**
   * Opens the game over prompt.
   * @param winner the winner of the game if won or game tied.
   */
  int openGameOverPrompt(String winner);

  /**
   * Opens the file upload prompt for the game layout config file.
   */
  File openFileUploadPrompt();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Opens a success dialog.
   * @param title the title of the dialog
   * @param message the details of the operation.
   * @return
   */
  void showSuccessMessage(String title, String message);

  /**
   * Opens an error dialog.
   * @param title the title of the error dialog.
   * @param message the reason for failure.
   * @return
   */
  void showErrorMessage(String title, String message);
}
