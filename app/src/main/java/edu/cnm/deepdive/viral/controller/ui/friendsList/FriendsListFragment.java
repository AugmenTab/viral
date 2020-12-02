package edu.cnm.deepdive.viral.controller.ui.friendsList;

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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.databinding.FragmentFriendsListBinding;
import edu.cnm.deepdive.viral.model.entity.Friend;

public class FriendsListFragment extends Fragment {

  private FragmentFriendsListBinding binding;
  private FriendsListViewModel friendsListViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentFriendsListBinding.inflate(getLayoutInflater());
    binding.friendsListContainer.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Friend friend = (Friend) parent.getItemAtPosition(position);

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
    friendsListViewModel = new ViewModelProvider(this).get(FriendsListViewModel.class);
    friendsListViewModel.getActive().observe(getViewLifecycleOwner(), (friends) -> {
      ArrayAdapter<Friend> adapter = new ArrayAdapter<>(
          getContext(), android.R.layout.simple_list_item_1, friends);
      binding.friendsListContainer.setAdapter(adapter);
    });
  }

}