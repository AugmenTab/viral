package edu.cnm.deepdive.viral.generator;

import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * This class generates one post or message {@link ActionTaken}.
 */
public class ActionGenerator {

  private final Random rng;

  /**
   * The constructor creates an instance of {@code Random}.
   */
  public ActionGenerator() {
    rng = new Random();
  }

  /**
   * Creates a comment-type {@link ActionTaken}.
   *
   * @return An {@link ActionTaken}.
   */
  public ActionTaken makeComment() {
    ActionTaken action = new ActionTaken();
    return action;
  }

  /**
   * Creates a post- or message-type {@link ActionTaken}.
   *
   * @param friend A {@link Friend} object that represents the poster or sender.
   * @param actions A {@code List} of {@link Action} objects that represent all possible actions.
   * @return An {@link ActionTaken}.
   */
  public ActionTaken makePostOrMessage(Friend friend, List<Action> actions) {
    ActionTaken action = new ActionTaken();
    action.setAction(actions.get(rng.nextInt(actions.size())).getId());
    action.setTimestamp(new Date());
    action.setFriend(friend.getId());
    action.setResponseTo(null);
    return action;
  }

}
