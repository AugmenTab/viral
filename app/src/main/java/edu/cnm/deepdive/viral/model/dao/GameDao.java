package edu.cnm.deepdive.viral.model.dao;

import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Game;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

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

  // TODO Write queries.

}
