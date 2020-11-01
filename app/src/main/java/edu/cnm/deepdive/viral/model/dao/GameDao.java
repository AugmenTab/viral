package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Game;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface GameDao {

  @Insert
  Single<Long> insert(Game game);

  @Insert
  Single<List<Long>> insert(Game... games);

  @Insert
  Single<List<Long>> insert(Collection<Game> games);

  @Update
  Single<Integer> update(Game game);

  @Update
  Single<Integer> update(Game... games);

  @Update
  Single<Integer> update(Collection<Game> games);

  @Query("SELECT * FROM Game WHERE end_time IS NOT NULL ORDER BY friends_left ASC, moves ASC")
  LiveData<List<Game>> selectAll();

  @Query("SELECT * FROM Game WHERE end_time IS NULL")
  LiveData<Game> selectCurrent();
  
}
