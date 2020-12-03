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
import edu.cnm.deepdive.viral.viewmodel.FriendsListViewModel;
import edu.cnm.deepdive.viral.databinding.FragmentFriendsListBinding;
import edu.cnm.deepdive.viral.model.entity.Friend;

/**
 * This is where the user is able to see friends remaining in their friends list. From this page,
 * they are able to filter (and redirect to) their
 * {@link FeedFragment} to display posts only by that
 * {@link Friend}, and can delete that friend from their friends list, setting them inactive in the
 * database.
 */
public class FriendsListFragment extends Fragment {

  private FragmentFriendsListBinding binding;
  private FriendsListViewModel friendsListViewModel;

  /**
   * The constructor initializes the inflater for binding.
   *
   * @param inflater A {@code LayoutInflater} for use in view binding.
   * @param container
   * @param savedInstanceState
   * @return A {@code View}.
   */
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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