package edu.cnm.deepdive.viral.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The entity class for possible action responses taken by an {@link Friend}.
 */
@Entity(
    foreignKeys = {
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
    },
    indices = {
        @Index(value = {"response_to_id"}, unique = true),
        @Index(value = {"response_id"})
    }
)
@SuppressWarnings({"NullableProblems", "NotNullFieldNotInitialized"})
public class ActionResponse {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_response_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "response_to_id")
  private Long responseTo;

  @ColumnInfo(name = "response_id")
  private long response;

  /**
   * Gets the ID for the action response.
   *
   * @return The ID of the action response.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID for the action response.
   *
   * @param id The new ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the ID for the {@link Action} this action response is associated with.
   *
   * @return The ID of the {@link Action} this action response is associated with.
   */
  @NonNull
  public Long getResponseTo() {
    return responseTo;
  }

  /**
   * Sets the ID for the {@link Action} this action response is associated with.
   *
   * @param responseTo The ID of the new {@link Action} this action response is associated with.
   */
  public void setResponseTo(@NonNull Long responseTo) {
    this.responseTo = responseTo;
  }

  /**
   * Gets the ID for the {@link Action} this action response represents.
   *
   * @return The ID of the {@link Action} this action response represents.
   */
  public long getResponse() {
    return response;
  }

  /**
   * Sets the ID for the {@link Action} this action response represents.
   *
   * @param response The ID of the {@link Action} this action response represents.
   */
  public void setResponse(long response) {
    this.response = response;
  }

}
