package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Date;

public class NewGameViewModel extends AndroidViewModel {

  private static final int STARTING_FRIENDS_COUNT_DEFAULT = 15;

  private final GameDao gameDao;

  public NewGameViewModel(@NonNull Application application, String username) {
    super(application);
    gameDao = ViralDatabase.getInstance().getGameDao();
    newGame(username);
  }

  // TODO: Define in constructor?
  public void newGame(String username) {
     createGameInDatabase(username);
    // TODO: Generate friends
    try {
      FriendGenerator generator = new FriendGenerator(this.getApplication());
      generator.makeFriends(STARTING_FRIENDS_COUNT_DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // TODO: Create initial posts by friends
  }

  private void createGameInDatabase(String username) {
    Game game = new Game();
    game.setUsername(username);
    game.setStartTime(new Date());
    game.setFriendsLeft(STARTING_FRIENDS_COUNT_DEFAULT);
    gameDao.insert(game).subscribeOn(Schedulers.io()).subscribe();
  }

}
