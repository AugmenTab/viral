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

/**
 * The view model for the {@link NewGameViewModel}.
 */
public class NewGameViewModel extends AndroidViewModel {

  private static final int STARTING_FRIENDS_COUNT_DEFAULT = 15;

  private final FriendRepository friendRepository;
  private final GameRepository gameRepository;
  private final Random rng;
  private final MutableLiveData<Throwable> throwable;

  /**
   * The constructor initializes the {@link GameRepository}, {@link FriendRepository}, a new source
   * of {@code Random}, and a {@code MutableLiveData} representing a throwable.
   *
   * @param application The Viral application.
   */
  public NewGameViewModel(@NonNull Application application) {
    super(application);
    gameRepository = new GameRepository(application);
    friendRepository = new FriendRepository(application);
    rng = new Random();
    throwable = new MutableLiveData<>();
  }

  /**
   * Gets a throwable.
   *
   * @return {@code throwable}
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Creates a new game.
   *
   * @param username The String entered by the user as their username.
   * @param startingFriendsCount The number of friends the user will start the game with.
   */
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
