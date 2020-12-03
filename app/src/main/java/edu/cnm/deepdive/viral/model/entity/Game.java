package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * The entity class that holds all relevant game data, including that which persists after a game
 * has ended.
 */
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

  /**
   * Gets the game ID.
   *
   * @return The ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the game ID.
   *
   * @param id The new game ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the player's username.
   *
   * @return The player's username.
   */
  @NonNull
  public String getUsername() {
    return username;
  }

  /**
   * Sets the player's username.
   *
   * @param username The new username.
   */
  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  /**
   * Gets the {@code Date} when the game was created.
   *
   * @return The {@code Date} when the game was created.
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Sets the {@code Date} when the game was created.
   *
   * @param startTime The {@code Date} when the game was created.
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the {@code Date} when the game ended.
   *
   * @return The {@code Date} when the game ended.
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Sets the {@code Date} when the game ended.
   *
   * @param endTime The {@code Date} when the game ended.
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * Gets the number of moves the player made.
   *
   * @return The number of moves the player made.
   */
  public int getMoves() {
    return moves;
  }

  /**
   * Sets the number of moves the player made.
   *
   * @param moves The new number of moves.
   */
  public void setMoves(int moves) {
    this.moves = moves;
  }

  /**
   * Gets the number of friends remaining on the player's friends list.
   *
   * @return The number of friends remaining on the player's friends list.
   */
  public int getFriendsLeft() {
    return friendsLeft;
  }

  /**
   * Sets the number of friends remaining on the player's friends list.
   *
   * @param friendsLeft The new number of friends remaining on the player's friends list.
   */
  public void setFriendsLeft(int friendsLeft) {
    this.friendsLeft = friendsLeft;
  }

}
