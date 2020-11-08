package edu.cnm.deepdive.viral.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import java.util.List;

public class DemeanorWithActions extends Demeanor {

  @NonNull
  @Relation(
      entity = Action.class,
      entityColumn = "demeanor_id",
      parentColumn = "demeanor_id"
  )
  private List<Action> actions;

  @NonNull
  public List<Action> getActions() {
    return actions;
  }

  public void setActions(@NonNull List<Action> actions) {
    this.actions = actions;
  }

}
