package edu.cnm.deepdive.viral.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(
        entity = Action.class,
        parentColumns = "id",
        childColumns = "responseTo"
    ),
    @ForeignKey(
        entity = Action.class,
        parentColumns = "id",
        childColumns = "response"
    )
})
public class ActionResponse {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_response_id")
  private long id;

  // Nullable - wrapper used instead of primitive
  @ColumnInfo(name = "response_to_id")
  private Long responseTo;

  @ColumnInfo(name = "response_id")
  private long response;

  private int appearanceChance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getResponseTo() {
    return responseTo;
  }

  public void setResponseTo(Long responseTo) {
    this.responseTo = responseTo;
  }

  public long getResponse() {
    return response;
  }

  public void setResponse(long response) {
    this.response = response;
  }

  public int getAppearanceChance() {
    return appearanceChance;
  }

  public void setAppearanceChance(int appearanceChance) {
    this.appearanceChance = appearanceChance;
  }

}
