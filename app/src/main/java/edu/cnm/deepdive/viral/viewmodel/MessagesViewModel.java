package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.FriendRepository;
import java.util.List;

/**
 * The view model for the {@link edu.cnm.deepdive.viral.controller.MessagesFragment}.
 */
public class MessagesViewModel extends AndroidViewModel {

  private final FriendRepository friendRepository;
  private final MutableLiveData<Friend> selectedFriend;
  private final LiveData<List<ActionTaken>> friendMessages;

  /**
   * The constructor initializes the {@link FriendRepository}, a {@code MutableLiveData}
   * representing the currently selected friend, and a {@code Transformations} to automatically
   * update the selected friend.
   *
   * @param application The Viral application.
   */
  public MessagesViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
    selectedFriend = new MutableLiveData<>();
    friendMessages = Transformations.switchMap(selectedFriend,
        (friend) -> friendRepository.getMessagesByFriend(friend.getId()));
  }

  /**
   * Gets all active friends.
   *
   * @return A {@code LiveData} {@code List} of {@link Friend} objects.
   */
  public LiveData<List<Friend>> getActive() {
    return friendRepository.getAllRemaining();
  }

  /**
   * Gets all messages sent by a friend.
   *
   * @return A {@code LiveData} {@code List} of {@link ActionTaken} objects.
   */
  public LiveData<List<ActionTaken>> getFriendMessages() {
    return friendMessages;
  }

  /**
   * Sets the selected friend.
   *
   * @param friend The new selected {@link Friend} object.
   */
  public void setSelectedFriend(Friend friend) {
    selectedFriend.setValue(friend);
    // Controller can invoke this method when the user selects a different friend, and it will automatically switch.
  }

}