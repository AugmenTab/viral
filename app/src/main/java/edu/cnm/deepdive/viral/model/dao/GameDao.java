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

  @Update
  Single<Integer> update(Game game);

  @Query("SELECT * FROM Game WHERE end_time IS NOT NULL ORDER BY friends_left ASC, moves ASC")
  LiveData<List<Game>> selectAll();

  @Query("SELECT * FROM Game WHERE end_time IS NULL")
  LiveData<Game> selectCurrent();
  
}
