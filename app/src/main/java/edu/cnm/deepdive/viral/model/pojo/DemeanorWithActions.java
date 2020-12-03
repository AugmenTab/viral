package edu.cnm.deepdive.viral.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import java.util.List;

/**
 * The POJO for getting {@link Demeanor} objects with their associated {@link Action} objects.
 */
public class DemeanorWithActions extends Demeanor {

  @NonNull
  @Relation(
      entity = Action.class,
      entityColumn = "demeanor_id",
      parentColumn = "demeanor_id"
  )
  private List<Action> actions;

  /**
   * Gets the list of actions.
   *
   * @return A {@code List} of {@link Action} objects.
   */
  @NonNull
  public List<Action> getActions() {
    return actions;
  }

  /**
   * Sets the list of actions.
   *
   * @param actions The new {@code List} of {@link Action} objects.
   */
  public void setActions(@NonNull List<Action> actions) {
    this.actions = actions;
  }

}
