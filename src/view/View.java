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
  String openPickUpItemPrompt();
  String openAttackPrompt();
  String openMovePetPrompt();
  String openMovePrompt();
  String openLookAroundPrompt();
  String showSuccessMessage();
  String showErrorMessage();
}
