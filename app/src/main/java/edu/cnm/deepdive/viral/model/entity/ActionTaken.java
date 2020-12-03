package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * The entity class for an {@link Action} taken by an {@link Friend}.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Action.class,
            parentColumns = "action_id",
            childColumns = "action_id"
        ),
        @ForeignKey(
            entity = ActionResponse.class,
            parentColumns = "response_to_id",
            childColumns = "response_to_id"
        ),
        @ForeignKey(
            entity = Friend.class,
            parentColumns = "friend_id",
            childColumns = "friend_id"
        )
        // what sort of onDeletes are required if dependent entities are deleted together?
    },
    indices = {
        @Index(value = {"action_id"}),
        @Index(value = {"response_to_id"}),
        @Index(value = {"friend_id"})
    }
)
@SuppressWarnings({"NullableProblems", "NotNullFieldNotInitialized"})
public class ActionTaken {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_taken_id")
  private long id;

  @ColumnInfo(name = "friend_id")
  private long friend;

  @NonNull
  private Date timestamp = new Date();

  @ColumnInfo(name = "action_id")
  private long action;

  @ColumnInfo(name = "response_to_id")
  private Long responseTo;

  /**
   * Gets the ID of the action taken.
   *
   * @return The ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID of the action taken.
   *
   * @param id The new ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID of the {@link Friend} associated with the action taken.
   *
   * @return The ID of the {@link Friend}.
   */
  public long getFriend() {
    return friend;
  }

  /**
   * Sets the ID of the {@link Friend} associated with the action taken.
   *
   * @param friend The ID of the new {@link Friend}.
   */
  public void setFriend(long friend) {
    this.friend = friend;
  }

  /**
   * Gets the timestamp for the action taken.
   *
   * @return The timestamp for the action taken.
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp for the action taken.
   *
   * @param timestamp The new timestamp for the action taken.
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the ID of the {@link Action} associated with the action taken.
   *
   * @return The ID of the {@link Action} associated with the action taken.
   */
  public long getAction() {
    return action;
  }

  /**
   * Sets the ID of the {@link Action} associated with the action taken.
   *
   * @param action The ID of the new {@link Action} associated with the action taken.
   */
  public void setAction(long action) {
    this.action = action;
  }

  /**
   * Gets the ID of the {@link ActionResponse} associated with the action taken.
   *
   * @return The ID of the {@link ActionResponse} associated with the action taken.
   */
  public Long getResponseTo() {
    return responseTo;
  }

  /**
   * Gets the ID of the {@link ActionResponse} associated with the action taken.
   *
   * @return The ID of the {@link ActionResponse} associated with the action taken.
   */
  public void setResponseTo(Long responseTo) {
    this.responseTo = responseTo;
  }

}
