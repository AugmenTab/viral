package edu.cnm.deepdive.viral.generator;

import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ActionGenerator {

  private final Random rng;

  public ActionGenerator() {
    rng = new Random();
  }

  public ActionTaken makeComment() {
    ActionTaken action = new ActionTaken();
    return action;
  }

  public ActionTaken makeMessage(Friend friend, List<Action> actions) {
    ActionTaken action = new ActionTaken();
    action.setAction(actions.get(rng.nextInt(actions.size())).getId());
    action.setTimestamp(new Date());
    action.setFriend(friend.getId());
    action.setResponseTo(null);
    return action;
  }

  public ActionTaken makePost(Friend friend, List<Action> actions) {
    ActionTaken action = new ActionTaken();
    action.setAction(actions.get(rng.nextInt(actions.size())).getId());
    action.setTimestamp(new Date());
    action.setFriend(friend.getId());
    action.setResponseTo(null);
    return action;
  }

}
