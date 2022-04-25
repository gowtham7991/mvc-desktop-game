package controller;

import view.View;

/**
 * This is the controller for the MVC application.
 * The controller interacts with the model and the view. All model to view communication happen
 * using the controller.
 */
public interface Controller extends Features {
  /**
   * This method is used to set the view in the controller and configure features in the view.
   * @param v
   */
  void setView(View v);
}
