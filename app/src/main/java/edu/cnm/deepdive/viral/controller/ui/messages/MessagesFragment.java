package edu.cnm.deepdive.viral.controller.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.databinding.FragmentMessagesBinding;
import edu.cnm.deepdive.viral.model.entity.Friend;

public class MessagesFragment extends Fragment {

  private MessagesViewModel messagesViewModel;
  private FragmentMessagesBinding binding;

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
      public void onNothingSelected(AdapterView<?> parent) {

      }
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