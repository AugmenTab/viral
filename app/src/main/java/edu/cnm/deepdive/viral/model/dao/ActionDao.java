package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Action;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

public interface ActionDao {

  @Insert
  Single<Long> insert(Action action);

  @Insert
  Single<List<Long>> insert(Action... actions);

  @Insert
  Single<List<Long>> insert(Collection<Action> actions);

  @Update
  Single<Integer> update(Action action);

  @Update
  Single<Integer> update(Action... actions);

  @Update
  Single<Integer> update(Collection<Action> actions);

  // TODO Write queries.

}
