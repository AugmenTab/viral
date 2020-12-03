package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.viral.service.FriendRepository;
import edu.cnm.deepdive.viral.service.GameRepository;
import java.util.Random;

public class NewGameViewModel extends AndroidViewModel {

  private static final int STARTING_FRIENDS_COUNT_DEFAULT = 15;

  private final FriendRepository friendRepository;
  private final GameRepository gameRepository;
  private final Random rng;
  private final MutableLiveData<Throwable> throwable;

  public NewGameViewModel(@NonNull Application application) {
    super(application);
    gameRepository = new GameRepository(application);
    friendRepository = new FriendRepository(application);
    rng = new Random();
    throwable = new MutableLiveData<>();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void newGame(String username, int startingFriendsCount) {
    gameRepository.createGameInDatabase(rng, username, 5,
        (startingFriendsCount > 0 ? startingFriendsCount : STARTING_FRIENDS_COUNT_DEFAULT))
        .subscribe(
            () -> {},
            throwable::postValue
        );
//    friendRepository.spreadInfection(rng);
      // TODO: Delete "user" file, if one exists.
    if (getApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
      // TODO: Use camera, and store photo taken by user as "user" img file. Where will it be saved? Picasso?
    }
  }

}
