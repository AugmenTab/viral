package edu.cnm.deepdive.viral.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.viral.viewmodel.MapViewModel;
import edu.cnm.deepdive.viral.databinding.FragmentMapBinding;

/**
 * This page shows a fictional map using a {@code WebView} with markers indicating the location of
 * an "outbreak", with the user's location fixed in the middle of the map. Below the map, a
 * list of addresses pair with the markers to show the street address, which allows the user to
 * identify infected friends on their friends list from the
 * {@link FeedFragment}.
 */
public class MapFragment extends Fragment {

  private MapViewModel mapViewModel;
  private FragmentMapBinding binding;

  /**
   * The constructor initializes the inflater for binding.
   *
   * @param inflater A {@code LayoutInflater} for use in view binding.
   * @param container A {@code ViewGroup}.
   * @param savedInstanceState A {@code Bundle}.
   * @return A {@code View}.
   */
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMapBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
  }

  }