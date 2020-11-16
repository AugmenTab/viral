package edu.cnm.deepdive.viral.controller.ui.friendsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendsListViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public FriendsListViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is gallery fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}