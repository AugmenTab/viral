package edu.cnm.deepdive.viral.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.model.pojo.ScoreSummary;
import io.reactivex.Completable;
import java.util.List;

public class GameRepository {

  private final Context context;
  private final GameDao gameDao;

  public GameRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    gameDao = database.getGameDao();
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

}

