package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
    entity = Demeanor.class,
    parentColumns = "demeanor_id",
    childColumns = "demeanor_id"
))
public class Action {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_id")
  private long id;

  @NonNull
  private String content;

  @ColumnInfo(name = "public")
  private boolean isPublic;

  @NonNull
  @ColumnInfo(name = "demeanor_id")
  private long demeanor;

  @ColumnInfo(name = "appearance_chance")
  private int appearanceChance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean aPublic) {
    isPublic = aPublic;
  }

  public long getDemeanor() {
    return demeanor;
  }

  public void setDemeanor(long demeanor) {
    this.demeanor = demeanor;
  }

  public int getAppearanceChance() {
    return appearanceChance;
  }

  public void setAppearanceChance(int appearanceChance) {
    this.appearanceChance = appearanceChance;
  }

}
