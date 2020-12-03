package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link ActionTaken} object.
 */
@Dao
public interface ActionTakenDao {

  String FEED_ACTIONS_QUERY =
      "SELECT * "
      + "FROM ActionTaken AS at "
      + "INNER JOIN `Action` AS ac ON at.action_id = ac.action_id "
      + "WHERE ac.public = true "
      + "ORDER BY timestamp DESC";

  String FRIEND_POSTS_QUERY =
      "SELECT * "
          + "FROM ActionTaken AS at "
          + "INNER JOIN `Action` AS ac ON at.action_id = ac.action_id "
          + "WHERE ac.public = true AND friend_id = :friend "
          + "ORDER BY timestamp DESC";

  String FRIEND_MESSAGES_QUERY =
      "SELECT * "
      + "FROM ActionTaken AS at "
      + "INNER JOIN `Action` AS ac ON at.action_id = ac.action_id "
      + "WHERE ac.public = false AND friend_id = :friend "
      + "ORDER BY timestamp ASC";

  /**
   * Inserts a single action taken.
   * 
   * @param actionTaken The {@link ActionTaken} to be inserted.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(ActionTaken actionTaken);

  /**
   * Inserts several actions taken passed via varargs.
   * 
   * @param actionsTaken Varargs of {@link ActionTaken} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(ActionTaken... actionsTaken);

  /**
   * Inserts a collection of actions taken.
   * 
   * @param actionsTaken A {@code Collection} of {@link ActionTaken} to be inserted.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Collection<ActionTaken> actionsTaken);

  /**
   * Updated a single action taken.
   * 
   * @param actionTaken The {@link ActionTaken} to be updated.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Update
  Single<Integer> update(ActionTaken actionTaken);

  /**
   * Updates several actions taken passed via varargs.
   * 
   * @param actionsTaken Varargs of {@link ActionTaken} to be updated.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Update
  Single<Integer> update(ActionTaken... actionsTaken);

  /**
   * Updates a collection of actions taken.
   * 
   * @param actionsTaken A {@code Collection} of {@link ActionTaken} to be updated.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Update
  Single<Integer> update(Collection<ActionTaken> actionsTaken);

  /**
   * Deletes a single action taken.
   * 
   * @param actionTaken The {@link ActionTaken} to be deleted.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Delete
  Single<Integer> delete(ActionTaken actionTaken);

  /**
   * Deletes several actions taken passed via varargs.
   * 
   * @param actionsTaken Varargs of {@link ActionTaken} to be deleted.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Delete
  Single<Integer> delete(ActionTaken... actionsTaken);

  /**
   * Deletes a collection of actions taken.
   * 
   * @param actionsTaken A {@code Collection} of {@link ActionTaken} to be deleted.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Delete
  Single<Integer> delete(Collection<ActionTaken> actionsTaken);

  /**
   * Query to provide a specific action taken.
   * 
   * @param id The ID of the {@link ActionTaken}.
   * @return A {@code LiveData} containing the {@link ActionTaken}.
   */
  @Query("SELECT * FROM ActionTaken WHERE action_taken_id = :id")
  LiveData<ActionTaken> selectSpecificActionTaken(long id);

  /**
   * Query to provide the actions taken that make up the user's feed.
   * 
   * @return A {@code LiveData} containing a {@code List} of {@link ActionTaken} objects.
   */
  @Query(FEED_ACTIONS_QUERY)
  LiveData<List<ActionTaken>> selectFeedActions();

  /**
   * Query to provide the actions taken that are posts by a specific friend.
   * 
   * @param friend The ID of the {@link edu.cnm.deepdive.viral.model.entity.Friend}.
   * @return A {@code LiveData} containing a {@code List} of {@link ActionTaken} objects.
   */
  @Query(FRIEND_POSTS_QUERY)
  LiveData<List<ActionTaken>> selectPostsByFriend(long friend);

  /**
   * Query to provide the actions taken that are messages by a specific friend.
   * 
   * @param friend The ID of the {@link edu.cnm.deepdive.viral.model.entity.Friend}.
   * @return A {@code LiveData} containing a {@code List} of {@link ActionTaken} objects.
   */
  @Query(FRIEND_MESSAGES_QUERY)
  LiveData<List<ActionTaken>> selectMessagesByFriend(long friend);

}
