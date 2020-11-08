package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

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
    },
    indices = {
        @Index(value = "action_id"),
        @Index(value = "response_to_id"),
        @Index(value = "friend_id")
    }
)
public class ActionTaken {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_taken_id")
  private long id;

  @ColumnInfo(name = "friend_id")
  private long friend;

  @NonNull
  private Date timestamp;

  @ColumnInfo(name = "action_id")
  private long action;

  @ColumnInfo(name = "response_to_id")
  private long responseTo;

  @NonNull
  private String content; // Do I even need this if it can just reference the content of the action?

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getFriend() {
    return friend;
  }

  public void setFriend(long friend) {
    this.friend = friend;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  public long getAction() {
    return action;
  }

  public void setAction(long action) {
    this.action = action;
  }

  public long getResponseTo() {
    return responseTo;
  }

  public void setResponseTo(long responseTo) {
    this.responseTo = responseTo;
  }

  @NonNull
  public String getContent() {
    return content;
  }

  public void setContent(@NonNull String content) {
    this.content = content;
  }
}
