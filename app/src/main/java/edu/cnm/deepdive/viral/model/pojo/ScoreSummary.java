package edu.cnm.deepdive.viral.model.pojo;

import androidx.room.ColumnInfo;

public class ScoreSummary {

  @ColumnInfo(name = "friends_left")
  private int friendsLeft;

  @ColumnInfo(name = "average_friends_left")
  private double averageFriendsLeft;

  @ColumnInfo(name = "moves")
  private int moves;

  @ColumnInfo(name = "average_moves")
  private double averageMoves;

  public int getFriendsLeft() {
    return friendsLeft;
  }

  public void setFriendsLeft(int friendsLeft) {
    this.friendsLeft = friendsLeft;
  }

  public double getAverageFriendsLeft() {
    return averageFriendsLeft;
  }

  public void setAverageFriendsLeft(double averageFriendsLeft) {
    this.averageFriendsLeft = averageFriendsLeft;
  }

  public int getMoves() {
    return moves;
  }

  public void setMoves(int moves) {
    this.moves = moves;
  }

  public double getAverageMoves() {
    return averageMoves;
  }

  public void setAverageMoves(double averageMoves) {
    this.averageMoves = averageMoves;
  }

}
