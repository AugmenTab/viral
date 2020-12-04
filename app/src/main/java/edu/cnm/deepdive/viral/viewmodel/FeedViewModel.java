package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.service.FriendRepository;
import java.util.List;

/**
 * The view model for the {@link edu.cnm.deepdive.viral.controller.FeedFragment}.
 */
public class FeedViewModel extends AndroidViewModel {

  private final FriendRepository friendRepository;

  /**
   * The constructor initializes a {@link FriendRepository}.
   *
   * @param application
   */
  public FeedViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
  }

  /**
   * Gets all posts sent by a friend.
   *
   * @return A {@code LiveData} {@code List} of {@link ActionTaken} objects.
   */
  public LiveData<List<ActionTaken>> getPosts(long id) {
    return friendRepository.getPostsByFriend(id);
  }

}