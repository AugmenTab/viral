package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.cnm.deepdive.viral.generator.FriendGenerator;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class NewGameViewModel extends AndroidViewModel {

  private static final int STARTING_FRIENDS_COUNT_DEFAULT = 15;

  private final GameDao gameDao;
  private final FriendDao friendDao;

  public NewGameViewModel(@NonNull Application application, String username, int n) {
    super(application);
    gameDao = ViralDatabase.getInstance().getGameDao();
    friendDao = ViralDatabase.getInstance().getFriendDao();
    newGame(username, n);
  }

  public void newGame(String username, int n) {
     createGameInDatabase(username);
    // TODO: Generate friends
    try {
      createFriendsListInDatabase(n);
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

  private void createFriendsListInDatabase(int n) throws IOException {
    FriendGenerator generator = new FriendGenerator(this.getApplication());
    List<Friend> friends = generator.makeFriends(n > 0 ? n : STARTING_FRIENDS_COUNT_DEFAULT);
    friendDao.insert(friends).subscribeOn(Schedulers.io()).subscribe();
  }

}
