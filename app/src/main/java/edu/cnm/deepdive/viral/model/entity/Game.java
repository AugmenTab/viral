package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@SuppressWarnings("NullableProblems")
@Entity(indices = {
    @Index(value = {"start_time"}, unique = true),
    @Index(value = {"end_time"}, unique = true)
})
public class Game {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_id")
  private long id;

  @NonNull
  private String username;

  @NonNull
  @ColumnInfo(name = "start_time")
  private Date startTime = new Date();

  @ColumnInfo(name = "end_time")
  private Date endTime;

  private int moves;

  @ColumnInfo(name = "friends_left")
  private int friendsLeft;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public int getMoves() {
    return moves;
  }

  public void setMoves(int moves) {
    this.moves = moves;
  }

  public int getFriendsLeft() {
    return friendsLeft;
  }

  public void setFriendsLeft(int friendsLeft) {
    this.friendsLeft = friendsLeft;
  }

}
