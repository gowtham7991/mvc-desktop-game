package view;

import controller.Features;
import java.util.List;

public interface View {
  void setFeatures(Features f);
  void setModel();
  void startGame();
  void begin();
  void refresh();
  List<String> openAddPlayerPrompt();
  String openPickUpItemPrompt(List<String> options);
  String openAttackPrompt(List<String> options);
  String openMovePetPrompt(List<String> options);
  String openMovePrompt();
  String openLookAroundPrompt(String text);
  String showSuccessMessage(String title, String message);
  String showErrorMessage(String title, String message);
}
