package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.model.pojo.ScoreSummary;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * The repository class for the {@link GameDao}, allowing the view model to access the
 * methods contained within the DAO.
 */
public class GameRepository {

  private final Context context;
  private final GameDao gameDao;
  private final FriendRepository friendRepository;

  /**
   * The constructor initializes the context, the {@link GameDao}, and the {@link FriendRepository}.
   *
   * @param context The application context.
   */
  public GameRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    gameDao = database.getGameDao();
    friendRepository = new FriendRepository(context);
  }

  /**
   * Provides a specific game.
   *
   * @param id The ID of the {@link Game}.
   * @return A {@code LiveData} containing a {@link Game} object.
   */
  public LiveData<Game> getSpecificGame(long id) {
    return gameDao.selectSpecificGame(id);
  }

  /**
   * Provides the current game - the only game in the database with a null end time.
   *
   * @return A {@code LiveData} containing a {@link Game} object.
   */
  public LiveData<Game> getCurrentGame() {
    return gameDao.selectCurrentGame();
  }

  /**
   * Provides a list of all completed games.
   *
   * @return A {@code LiveData} containing a {@code List} of {@link Game} objects.
   */
  public LiveData<List<Game>> getAllCompletedGames() {
    return gameDao.selectAllCompletedGames();
  }

  /**
   * Provides a game summary based on the number of friends remaining.
   *
   * @return A {@code LiveData} containing a {@link ScoreSummary} object.
   */
  public LiveData<ScoreSummary> getSummariesByFriendsLeft() {
    return gameDao.selectSummaryByFriendsLeft();
  }

  /**
   * Provides a game summary based on the number of moves remaining.
   *
   * @return A {@code LiveData} containing a {@link ScoreSummary} object.
   */
  public LiveData<ScoreSummary> getSummariesByMoves() {
    return gameDao.selectSummaryByMoves();
  }

  /**
   * Saves a {@link Game} object in the database.
   *
   * @param game The {@link Game} to be saved.
   * @return The result of the attempt as a {@code Completable}.
   */
  public Completable save(Game game) {
    return (game.getId() == 0)
        ? gameDao.insert(game).doAfterSuccess(game::setId).ignoreElement()
        : gameDao.update(game).ignoreElement();
  }

  /**
   * Deletes a {@link Game} object in the database.
   *
   * @param game The {@link Game} to be deleted.
   * @return The result of the attempt as a {@code Completable}.
   */
  public Completable delete(Game game) {
    return (game.getId() == 0)
        ? Completable.complete()
        : gameDao.delete(game).ignoreElement();
  }

  /**
   * Creates a new {@link Game} object in the database, along with the necessary
   * {@link edu.cnm.deepdive.viral.model.entity.Friend} objects to create a friend's list and the
   * necessary {@link edu.cnm.deepdive.viral.model.entity.ActionTaken} post objects to create the
   * initial posts made by the user's friends.
   *
   * @param rng An instance of {@code Random}.
   * @param username The username entered by the user on the new game screen.
   * @param postsToMake The number of posts to generate.
   * @param startingFriends The number of friends to generate.
   * @return The result of the attempt as a {@code Completable}.
   */
  public Completable createGameInDatabase(Random rng, String username, int postsToMake, int startingFriends) {
    return friendRepository.createFriendsListInDatabase(context, startingFriends)
        .andThen(
            Single.fromCallable(() -> {
              Game game = new Game();
              game.setUsername(username);
              game.setStartTime(new Date());
              game.setFriendsLeft(postsToMake);
              return game;
            })
                .flatMap((game) -> gameDao.insert(game)
                    .map((id) -> {
                      game.setId(id);
                      return game;
                    })
                )
                .flatMapCompletable((game) -> friendRepository.createPost(rng, postsToMake))
        )
        .subscribeOn(Schedulers.io());
  }

}
