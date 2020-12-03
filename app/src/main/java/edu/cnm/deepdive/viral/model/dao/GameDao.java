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

/**
 * An interface containing various methods for interacting with the database table for the
 * {@link Game} object.
 */
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

  /**
   * Inserts a single game.
   *
   * @param game The {@link Game} object to be inserted.
   * @return A {@code Single} containing a {@code Long}.
   */
  @Insert
  Single<Long> insert(Game game);

  /**
   * Updates a single game.
   *
   * @param game The {@link Game} object to be updated.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Update
  Single<Integer> update(Game game);

  /**
   * Deletes a single game.
   *
   * @param game The {@link Game} object to be deleted.
   * @return A {@code Single} containing an {@code Integer}.
   */
  @Delete
  Single<Integer> delete(Game game);

  /**
   * Query to provide a specific game.
   *
   * @param id The ID of the {@link Game}.
   * @return A {@code LiveData} containing a {@link Game}.
   */
  @Query("SELECT * FROM Game WHERE game_id = :id")
  LiveData<Game> selectSpecificGame(long id);

  /**
   * Query to provide the current game (the only game with a null end time).
   *
   * @return A {@code LiveData} containing a {@link Game}.
   */
  @Query("SELECT * FROM Game WHERE end_time IS NULL")
  LiveData<Game> selectCurrentGame();

  /**
   * Query to provide all games the user has completed.
   *
   * @return A {@code LiveData} containing a {@code List} of {@link Game} objects.
   */
  @Query("SELECT * FROM Game WHERE end_time IS NOT NULL ORDER BY friends_left ASC, moves ASC")
  LiveData<List<Game>> selectAllCompletedGames();

  /**
   * Query to get a {@link ScoreSummary} sorted by friends remaining in the games.
   *
   * @return A {@code LiveData} containing a {@link ScoreSummary}.
   */
  @Query(AVERAGE_FRIENDS_LEFT_SUMMARY_QUERY)
  LiveData<ScoreSummary> selectSummaryByFriendsLeft();

  /**
   * Query to get a {@link ScoreSummary} sorted by moves made in the games.
   *
   * @return A {@code LiveData} containing a {@link ScoreSummary}.
   */
  @Transaction
  @Query(AVERAGE_MOVES_SUMMARY_QUERY)
  LiveData<ScoreSummary> selectSummaryByMoves();

}
