package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface DemeanorDao {

  @Insert
  Single<Long> insert(Demeanor demeanor);

  @Insert
  Single<List<Long>> insert(Demeanor... demeanors);

  @Insert
  Single<List<Long>> insert(Collection<Demeanor> demeanors);

  @Update
  Single<Integer> update(Demeanor demeanor);

  @Update
  Single<Integer> update(Demeanor... demeanors);

  @Update
  Single<Integer> update(Collection<Demeanor> demeanors);

  // TODO Write queries.

}
