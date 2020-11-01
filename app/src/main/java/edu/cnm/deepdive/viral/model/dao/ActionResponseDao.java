package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.ActionResponse;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ActionResponseDao {

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

  // TODO Write queries.

}
