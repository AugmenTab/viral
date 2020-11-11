package edu.cnm.deepdive.viral.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.model.pojo.ScoreSummary;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface GameDao {

  String AVERAGE_FRIENDS_LEFT_SUMMARY_QUERY =
      "SELECT AVG(friends_left) AS average_friends_left, AVG(moves) as average_moves "
          + "FROM Game "
          + "ORDER BY average_friends_left DESC";

  String AVERAGE_MOVES_SUMMARY_QUERY =
      "SELECT AVG(friends_left) AS average_friends_left, AVG(moves) as average_moves "
          + "FROM Game "
          + "ORDER BY average_moves ASC";

  @Insert
  Single<Long> insert(Game game);

  @Update
  Single<Integer> update(Game game);

  @Delete
  Single<Integer> delete(Game game);

  @Query("SELECT * FROM Game WHERE game_id = :id")
  LiveData<Game> selectSpecificGame(long id);

  @Query("SELECT * FROM Game WHERE end_time IS NULL")
  LiveData<Game> selectCurrentGame();

  @Query("SELECT * FROM Game WHERE end_time IS NOT NULL ORDER BY friends_left ASC, moves ASC")
  LiveData<List<Game>> selectAllCompletedGames();

  @Query(AVERAGE_FRIENDS_LEFT_SUMMARY_QUERY)
  LiveData<ScoreSummary> selectSummaryByFriendsLeft();

  @Transaction
  @Query(AVERAGE_MOVES_SUMMARY_QUERY)
  LiveData<ScoreSummary> selectSummaryByMoves();

}
