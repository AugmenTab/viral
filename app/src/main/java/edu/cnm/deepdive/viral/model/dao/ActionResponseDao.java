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

@Dao
public interface ActionResponseDao {

  String AVAILABLE_RESPONSES_QUERY =
      "SELECT * "
      + "FROM ActionResponse AS ar "
      + "INNER JOIN `Action` AS ac ON ar.response_to_id = ac.action_id "
      + "WHERE ac.action_id = :action AND ac.demeanor_id = :demeanor "
      + "ORDER BY appearance_chance ASC";

  @Insert
  Single<Long> insert(ActionResponse actionResponse);

  @Insert
  Single<List<Long>> insert(ActionResponse... actionResponses);

  @Insert
  Single<List<Long>> insert(Collection<ActionResponse> actionResponses);

  @Update
  Single<Integer> update(ActionResponse actionResponse);

  @Update
  Single<Integer> update(ActionResponse... actionResponses);

  @Update
  Single<Integer> update(Collection<ActionResponse> actionResponses);

  // TODO: Get help from Nick on this section, and how to join the tables.
  @Query("SELECT * FROM ActionResponse WHERE action_response_id = :id")
  LiveData<ActionResponse> selectSpecificResponse(long id);

  @Query(AVAILABLE_RESPONSES_QUERY)
  LiveData<List<ActionResponse>> selectAvailableResponses(long action, long demeanor);

}
