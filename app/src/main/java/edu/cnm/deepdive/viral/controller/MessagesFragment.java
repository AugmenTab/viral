package edu.cnm.deepdive.viral.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.viral.viewmodel.MessagesViewModel;
import edu.cnm.deepdive.viral.databinding.FragmentMessagesBinding;
import edu.cnm.deepdive.viral.model.entity.Friend;

/**
 * This page shows the messages a user has received from any particular {@link Friend} on their
 * friend's list. By selecting a friend, it displays the messages for only that user. They are also
 * able to create messages from this page, which are immediately responded to by the friend.
 */
public class MessagesFragment extends Fragment {

  private MessagesViewModel messagesViewModel;
  private FragmentMessagesBinding binding;

  /**
   * The constructor initializes the inflater for binding.
   *
   * @param inflater A {@code LayoutInflater} for use in view binding.
   * @param container
   * @param savedInstanceState
   * @return A {@code View}.
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMessagesBinding.inflate(inflater);
    binding.messagesFriendsContainer.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Friend friend = (Friend) parent.getItemAtPosition(position);
        messagesViewModel.setSelectedFriend(friend);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {}
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
    messagesViewModel.getActive().observe(getViewLifecycleOwner(), (friends) -> {
      ArrayAdapter<Friend> adapter = new ArrayAdapter<>(
          getContext(), android.R.layout.simple_list_item_1, friends);
      binding.messagesFriendsContainer.setAdapter(adapter);
    });
    messagesViewModel.getFriendMessages().observe(getViewLifecycleOwner(), (messages) -> {
      // TODO Construct an instance or update an instance of RecyclerViewAdapter with these messages.
      // TODO If adapter is new, set RecyclerView to use this adapter. binding.messagesContainer.setAdapter(new adapter).
    });
  }

}