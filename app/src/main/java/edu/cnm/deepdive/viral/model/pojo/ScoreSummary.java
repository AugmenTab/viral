package edu.cnm.deepdive.viral.model.pojo;

import androidx.room.ColumnInfo;

public class ScoreSummary {

  @ColumnInfo(name = "average_friends_left")
  private double averageFriendsLeft;

  @ColumnInfo(name = "average_moves")
  private double averageMoves;

  public double getAverageFriendsLeft() {
    return averageFriendsLeft;
  }

  public void setAverageFriendsLeft(double averageFriendsLeft) {
    this.averageFriendsLeft = averageFriendsLeft;
  }

  public double getAverageMoves() {
    return averageMoves;
  }

  public void setAverageMoves(double averageMoves) {
    this.averageMoves = averageMoves;
  }

}
