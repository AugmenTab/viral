package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.ActionResponse;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link ActionResponse} object.
 */
@Dao
public interface ActionResponseDao {

  String AVAILABLE_RESPONSES_QUERY =
      "SELECT * "
      + "FROM ActionResponse AS ar "
      + "INNER JOIN `Action` AS ac ON ar.response_to_id = ac.action_id "
      + "WHERE ac.action_id = :action AND ac.demeanor_id = :demeanor";

  /**
   * Inserts a single action response.
   *
   * @param actionResponse The {@link ActionResponse} to be entered.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(ActionResponse actionResponse);

  /**
   * Inserts several action responses passed via varargs.
   *
   * @param actionResponses Varargs of {@link ActionResponse} to be entered.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(ActionResponse... actionResponses);

  /**
   * Inserts a collection of action responses.
   *
   * @param actionResponses A {@code Collection} of {@link ActionResponse} to be entered.
   * @return A {@code Single} {@code List} of {@code Long}.
   */
  @Insert
  Single<List<Long>> insert(Collection<ActionResponse> actionResponses);

  /**
   * Inserts a single action response.
   *
   * @param actionResponse The {@link ActionResponse} to be updated.
   * @return A {@code Single} containing a {@code Integer}.
   */
  @Update
  Single<Integer> update(ActionResponse actionResponse);

  /**
   * Inserts several action responses passed via varargs.
   *
   * @param actionResponses Varargs of {@link ActionResponse} to be updated.
   * @return A {@code Single} {@code List} of {@code Integer}.
   */
  @Update
  Single<Integer> update(ActionResponse... actionResponses);

  /**
   * Inserts a collection of action responses.
   *
   * @param actionResponses A {@code Collection} of {@link ActionResponse} to be updated.
   * @return A {@code Single} {@code List} of {@code Integer}.
   */
  @Update
  Single<Integer> update(Collection<ActionResponse> actionResponses);

  /**
   * Query to provide a specific action response.
   *
   * @param id The ID of the {@link ActionResponse}.
   * @return A {@code LiveData} containing the {@link ActionResponse}.
   */
  @Query("SELECT * FROM ActionResponse WHERE action_response_id = :id")
  LiveData<ActionResponse> selectSpecificResponse(long id);

  /**
   * Query to provide all action responses appropriate for a given action.
   *
   * @param action The ID of the associated {@link edu.cnm.deepdive.viral.model.entity.Action}.
   * @param demeanor The ID of the associated {@link edu.cnm.deepdive.viral.model.entity.Demeanor}.
   * @return A {@code LiveData} containing a {@code List} of {@link ActionResponse} objects.
   */
  @Query(AVAILABLE_RESPONSES_QUERY)
  LiveData<List<ActionResponse>> selectAvailableResponses(long action, long demeanor);

}
