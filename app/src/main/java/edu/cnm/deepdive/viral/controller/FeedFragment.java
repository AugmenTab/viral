package edu.cnm.deepdive.viral.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.viral.adapter.FeedRecyclerAdapter;
import edu.cnm.deepdive.viral.viewmodel.FeedViewModel;
import edu.cnm.deepdive.viral.databinding.FragmentFeedBinding;

/**
 * The primary gameplay screen. This is where the user is first redirected after starting a new
 * game, and where the user can view all of the posts made by their friends. From this page, they
 * can create a new message and then redirect to the
 * {@link MessagesFragment} without having to use the
 * navigation drawer, or can filter the feed to focus on posts made only by that
 * {@link edu.cnm.deepdive.viral.model.entity.Friend}.
 */
public class FeedFragment extends Fragment {

  private FragmentFeedBinding binding;
  private FeedViewModel feedViewModel;
  private FeedRecyclerAdapter adapter;

  /**
   * The constructor initializes the inflater for binding.
   *
   * @param inflater A {@code LayoutInflater} for use in view binding.
   * @param container A {@code ViewGroup}.
   * @param savedInstance A {@code Bundle}.
   * @return A {@code View}.
   */
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
    binding = FragmentFeedBinding.inflate(inflater);
//    adapter = new FeedRecyclerAdapter(this, action, friend);
//    binding.feedPostsList.setAdapter(adapter);
    return binding.getRoot();
  }

}