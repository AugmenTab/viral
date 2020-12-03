package edu.cnm.deepdive.viral.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.service.FriendRepository;
import java.util.List;

public class FriendsListViewModel extends AndroidViewModel {

  private final FriendRepository friendRepository;

  public FriendsListViewModel(@NonNull Application application) {
    super(application);
    friendRepository = new FriendRepository(application);
  }

  public LiveData<List<Friend>> getActive() {
    return friendRepository.getAllRemaining();
  }

}