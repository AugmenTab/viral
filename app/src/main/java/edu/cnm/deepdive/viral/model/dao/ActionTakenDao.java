package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ActionTakenDao {

  @Insert
  Single<Long> insert(ActionTaken actionTaken);

  @Insert
  Single<List<Long>> insert(ActionTaken... actionsTaken);

  @Insert
  Single<List<Long>> insert(Collection<ActionTaken> actionsTaken);

  @Update
  Single<Integer> update(ActionTaken actionTaken);

  @Update
  Single<Integer> update(ActionTaken... actionsTaken);

  @Update
  Single<Integer> update(Collection<ActionTaken> actionsTaken);

  // TODO Write queries.

}
