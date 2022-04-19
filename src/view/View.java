package view;

import controller.Features;
import java.util.List;
import model.Model;
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
   *
   * @param m
   */
  void setModel(ReadOnlyModel m);
  void startGame();
  void begin();
  void refresh();
  List<String> openAddPlayerPrompt();
  String openPickUpItemPrompt(List<String> options);
  String openAttackPrompt(List<String> options);
  String openMovePetPrompt(List<String> options);
  String openMovePrompt();
  String openLookAroundPrompt(String text);
  void openGameOverPrompt(String winner);
  String showSuccessMessage(String title, String message);
  String showErrorMessage(String title, String message);
}
