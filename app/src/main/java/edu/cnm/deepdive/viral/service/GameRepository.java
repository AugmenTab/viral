package edu.cnm.deepdive.viral.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.model.pojo.ScoreSummary;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GameRepository {

  private final Context context;
  private final GameDao gameDao;
  private final FriendRepository friendRepository;

  public GameRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    gameDao = database.getGameDao();
    friendRepository = new FriendRepository(context);
  }

  public LiveData<Game> getSpecificGame(long id) {
    return gameDao.selectSpecificGame(id);
  }

  public LiveData<Game> getCurrentGame() {
    return gameDao.selectCurrentGame();
  }

  public LiveData<List<Game>> getAllCompletedGames() {
    return gameDao.selectAllCompletedGames();
  }

  public LiveData<ScoreSummary> getSummariesByFriendsLeft() {
    return gameDao.selectSummaryByFriendsLeft();
  }

  public LiveData<ScoreSummary> getSummariesByMoves() {
    return gameDao.selectSummaryByMoves();
  }

  public Completable save(Game game) {
    return (game.getId() == 0)
        ? gameDao.insert(game).doAfterSuccess(game::setId).ignoreElement()
        : gameDao.update(game).ignoreElement();
  }

  public Completable delete(Game game) {
    return (game.getId() == 0)
        ? Completable.complete()
        : gameDao.delete(game).ignoreElement();
  }

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

