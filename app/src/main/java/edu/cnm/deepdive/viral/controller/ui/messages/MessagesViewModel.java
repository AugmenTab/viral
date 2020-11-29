package edu.cnm.deepdive.viral.controller.ui.messages;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.model.service.FriendRepository;
import java.util.List;

public class MessagesViewModel extends AndroidViewModel {

  private final FriendRepository friendRepository;
  private final MutableLiveData<Friend> selectedFriend;
  private final LiveData<List<ActionTaken>> friendMessages;

  public MessagesViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
    selectedFriend = new MutableLiveData<>();
    friendMessages = Transformations.switchMap(selectedFriend,
        (friend) -> friendRepository.getMessagesByFriend(friend.getId()));
  }

  public LiveData<List<Friend>> getActive() {
    return friendRepository.getAll();
  }

  public LiveData<List<ActionTaken>> getFriendMessages() {
    return friendMessages;
  }

  public void setSelectedFriend(Friend friend) {
    selectedFriend.setValue(friend);
    // Controller can invoke this method when the user selects a different friend, and it will automatically switch.
  }

}