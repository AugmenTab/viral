package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.FriendRepository;
import java.util.List;

/**
 * The view model for the {@link edu.cnm.deepdive.viral.controller.FriendsListFragment}.
 */
public class FriendsListViewModel extends AndroidViewModel {

  private final FriendRepository friendRepository;

  /**
   * The constructor initializes the {@link FriendRepository}.
   *
   * @param application The Viral application.
   */
  public FriendsListViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
  }

  /**
   * Gets all active friends.
   *
   * @return A {@code LiveData} {@code List} of {@link Friend} objects.
   */
  public LiveData<List<Friend>> getActive() {
    return friendRepository.getAllRemaining();
  }

}