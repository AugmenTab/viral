package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The entity class for possible actions taken by an {@link Friend}.
 */
@Entity(
    foreignKeys = @ForeignKey(
        entity = Demeanor.class,
        parentColumns = "demeanor_id",
        childColumns = "demeanor_id"
    ),
    indices = {@Index(value = {"demeanor_id"})}
)
@SuppressWarnings({"NullableProblems", "NotNullFieldNotInitialized"})
public class Action {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_id")
  private long id;

  @NonNull
  private String content;

  @ColumnInfo(name = "public")
  private boolean isPublic;

  @ColumnInfo(name = "demeanor_id")
  private long demeanor;

  /**
   * Gets the ID for the action.
   *
   * @return The ID of the action.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID for the action.
   *
   * @param id The new ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the content for the action.
   *
   * @return The content of the action.
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content for the action.
   *
   * @param content The new action content.
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Gets the visibility level of the action (whether it's public or private).
   *
   * @return The visibility boolean of the action.
   */
  public boolean isPublic() {
    return isPublic;
  }

  /**
   * Sets the visibility level of the action (whether it's public or private).
   *
   * @param aPublic The new visibility boolean of the action.
   */
  public void setPublic(boolean aPublic) {
    isPublic = aPublic;
  }

  /**
   * Gets the ID for the demeanor the action is associated with.
   *
   * @return The ID for the {@link Demeanor}.
   */
  public long getDemeanor() {
    return demeanor;
  }

  /**
   * Sets the ID for the demeanor the action is associated with.
   *
   * @param demeanor The new {@link Demeanor} ID.
   */
  public void setDemeanor(long demeanor) {
    this.demeanor = demeanor;
  }

}
