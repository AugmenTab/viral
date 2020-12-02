package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.cnm.deepdive.viral.service.FriendRepository;
import edu.cnm.deepdive.viral.service.GameRepository;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import java.util.Random;

public class NewGameViewModel extends AndroidViewModel {

  private static final int STARTING_FRIENDS_COUNT_DEFAULT = 15;

  private final FriendRepository friendRepository;
  private final GameRepository gameRepository;
  private final Random rng;

  public NewGameViewModel(@NonNull Application application) {
    super(application);
    gameRepository = new GameRepository(application);
    friendRepository = new FriendRepository(application);
    rng = new Random();
  }

  public void newGame(String username, int n) {
     Disposable result = gameRepository.createGameInDatabase(
         username, (n > 0 ? n : STARTING_FRIENDS_COUNT_DEFAULT));
    try {
      friendRepository.createFriendsListInDatabase(
          getApplication(), n > 0 ? n : STARTING_FRIENDS_COUNT_DEFAULT);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // TODO: Create initial posts by friends
    for (int i = 0; i < 5; i++) {
      friendRepository.createPost(rng);
    }
    // TODO: Infect starting friend(s).
    // TODO: Delete "user" file, if one exists.
    // TODO: Use camera, and store photo taken by user as "user" img file. Where will it be saved? Picasso?
  }

}
