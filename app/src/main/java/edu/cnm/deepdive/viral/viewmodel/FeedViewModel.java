package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.service.FriendRepository;
import java.util.List;

public class FeedViewModel extends AndroidViewModel {

  private FriendRepository friendRepository;

  public FeedViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
  }

  public LiveData<List<ActionTaken>> getMessages(long id) {
    return friendRepository.getPostsByFriend(id);
  }

}