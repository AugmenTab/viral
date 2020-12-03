package edu.cnm.deepdive.viral.model.pojo;

import androidx.room.ColumnInfo;

/**
 * The POJO for getting summaries of {@link edu.cnm.deepdive.viral.model.entity.Game}.
 */
public class ScoreSummary {

  @ColumnInfo(name = "average_friends_left")
  private double averageFriendsLeft;

  @ColumnInfo(name = "average_moves")
  private double averageMoves;

  /**
   * Get the average friends left.
   *
   * @return The average friends left.
   */
  public double getAverageFriendsLeft() {
    return averageFriendsLeft;
  }

  /**
   * Sets the average friends left.
   *
   * @param averageFriendsLeft The new average friends left.
   */
  public void setAverageFriendsLeft(double averageFriendsLeft) {
    this.averageFriendsLeft = averageFriendsLeft;
  }

  /**
   * Gets the average moves made.
   *
   * @return The average moves.
   */
  public double getAverageMoves() {
    return averageMoves;
  }

  /**
   * Sets the average moves made.
   *
   * @param averageMoves The new average moves.
   */
  public void setAverageMoves(double averageMoves) {
    this.averageMoves = averageMoves;
  }

}
