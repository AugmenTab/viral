package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(
        entity = Action.class,
        parentColumns = "action_id",
        childColumns = "response_to_id"
    ),
    @ForeignKey(
        entity = Action.class,
        parentColumns = "action_id",
        childColumns = "response_id"
    )
})
public class ActionResponse {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_response_id")
  private long id;

  @ColumnInfo(name = "response_to_id")
  private Action responseTo;

  @NonNull
  @ColumnInfo(name = "response_id")
  private Action response;

  @ColumnInfo(name = "appearance_chance")
  private int appearanceChance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Action getResponseTo() {
    return responseTo;
  }

  public void setResponseTo(Action responseTo) {
    this.responseTo = responseTo;
  }

  public Action getResponse() {
    return response;
  }

  public void setResponse(Action response) {
    this.response = response;
  }

  public int getAppearanceChance() {
    return appearanceChance;
  }

  public void setAppearanceChance(int appearanceChance) {
    this.appearanceChance = appearanceChance;
  }

}
