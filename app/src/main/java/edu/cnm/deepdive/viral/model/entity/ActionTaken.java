package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(foreignKeys = {
    @ForeignKey(
        entity = Action.class,
        parentColumns = "id",
        childColumns = "action"
    ),
    @ForeignKey(
        entity = ActionResponse.class,
        parentColumns = "responseTo",
        childColumns = "responseTo"
    ),
    @ForeignKey(
        entity = Friend.class,
        parentColumns = "id",
        childColumns = "friend"
    )
})
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

  // Nullable - wrapper used instead of primitive
  @ColumnInfo(name = "response_to_id")
  private Long responseTo;

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

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public long getAction() {
    return action;
  }

  public void setAction(long action) {
    this.action = action;
  }

  public Long getResponseTo() {
    return responseTo;
  }

  public void setResponseTo(Long responseTo) {
    this.responseTo = responseTo;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
