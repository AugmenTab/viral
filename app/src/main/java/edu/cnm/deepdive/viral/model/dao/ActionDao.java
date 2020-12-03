package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.pojo.DemeanorWithActions;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link Action} entity.
 */
@Dao
public interface ActionDao {

  String SELECT_ACTION_BY_VISIBILITY_QUERY =
      "SELECT * "
          + "FROM `Action` "
          + "WHERE public = :isPublic AND demeanor_id = :demeanor";

  /**
   * Inserts a single action.
   *
   * @param action The {@link Action} to be entered.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(Action action);

  /**
   * Inserts several actions passed via varargs.
   *
   * @param actions Varargs of {@link Action} objects to be entered.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Action... actions);

  /**
   * Inserts a collection of actions.
   *
   * @param actions A {@code Collection} of {@link Action} objects to be entered.
   * @return A {@code Single} {@code List} of {@code Long}
   */
  @Insert
  Single<List<Long>> insert(Collection<Action> actions);

  /**
   * Updates a single action.
   *
   * @param action The {@link Action} to be updated.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Update
  Single<Integer> update(Action action);

  /**
   * Updates several actions passed via varargs.
   *
   * @param actions Varargs of {@link Action} objects to be updated.
   * @return A {@code Single} {@code List} of {@code Integer}.
   */
  @Update
  Single<Integer> update(Action... actions);

  /**
   * Updates a collection of actions.
   *
   * @param actions A {@code Collection} of {@link Action} objects to be updated.
   * @return A {@code Single} {@code List} of {@code Integer}.
   */
  @Update
  Single<Integer> update(Collection<Action> actions);

  /**
   * Query to provide a specific action.
   *
   * @param id The ID of the {@link Action}.
   * @return A {@code LiveData} containing the {@link Action}.
   */
  @Query("SELECT * FROM `Action` WHERE action_id = :id")
  LiveData<Action> selectSpecificAction(long id);

  /**
   * Query to provide actions by their visibility and demeanor.
   *
   * @param isPublic Whether the actions to be returned are posts (true) or messages (false).
   * @param demeanor The ID of the {@link Demeanor} the action will be associated with.
   * @return A {@code LiveData} containing a {@code List} of {@link Action} objects.
   */
  @Query(SELECT_ACTION_BY_VISIBILITY_QUERY)
  LiveData<List<Action>> selectActionsByVisibility(boolean isPublic, long demeanor);

  /**
   * Query to provide actions by their visibility and demeanor, used outside the view model.
   *
   * @param isPublic Whether the actions to be returned are posts (true) or messages (false).
   * @param demeanor The ID of the {@link Demeanor} the action will be associated with.
   * @return A {@code Single} containing a {@code List} of {@link Action} objects.
   */
  @Query(SELECT_ACTION_BY_VISIBILITY_QUERY)
  Single<List<Action>> selectActionsByVisibilitySync(boolean isPublic, long demeanor);

  /**
   * Query to provide all actions that can be performed by a
   * {@link edu.cnm.deepdive.viral.model.entity.Friend} with a particular {@link Demeanor}.
   *
   * @param id The ID of the {@link Demeanor} the actions are associated with.
   * @return A {@code LiveData} containing a {@link DemeanorWithActions} object.
   */
  @Transaction
  @Query("SELECT * FROM `Demeanor` WHERE demeanor_id = :id")
  LiveData<DemeanorWithActions> selectAllDemeanorActions(long id);

}
