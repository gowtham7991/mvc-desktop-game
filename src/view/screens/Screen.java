package view.screens;

import controller.Features;
import model.ReadOnlyModel;

public interface Screen {

  /**
   * Sets all the action listeners and key listeners for the game screen.
   *
   * <p>Each function is designed to take in the necessary data to complete that
   * functionality.
   */
  void setFeatures(Features f);

  /**
   * Sets the screen visible.
   */
  void showScreen();

  /**
   * Hides the screen.
   */
  void hideScreen();

  /**
   * Resets the model in the screen
   * @param m the readonly model
   */
  void setModel(ReadOnlyModel m);

  /**
   * Refreshes the screen by re-generating the components.
   */
  void refresh();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();
}
