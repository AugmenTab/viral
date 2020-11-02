package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Action;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
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

  @Query("SELECT * FROM `Action` WHERE action_id = :id")
  LiveData<Action> selectSpecificAction(long id);

  @Query("SELECT * FROM `Action` WHERE public = :isPublic AND demeanor_id = :demeanor ORDER BY appearance_chance DESC")
  LiveData<List<Action>> selectActionByVisibility(boolean isPublic, long demeanor);

}
