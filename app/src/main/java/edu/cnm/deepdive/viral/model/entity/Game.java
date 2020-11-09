package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(indices = {
    @Index(value = {"start_time"}, unique = true),
    @Index(value = {"end_time"}, unique = true)
})
public class Game {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "start_time")
  private Date startTime;

  @ColumnInfo(name = "end_time")
  private Date endTime;

  private Integer moves;

  @ColumnInfo(name = "friends_left")
  private Integer friendsLeft;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public Integer getMoves() {
    return moves;
  }

  public void setMoves(Integer moves) {
    this.moves = moves;
  }

  public Integer getFriendsLeft() {
    return friendsLeft;
  }

  public void setFriendsLeft(Integer friendsLeft) {
    this.friendsLeft = friendsLeft;
  }

}
