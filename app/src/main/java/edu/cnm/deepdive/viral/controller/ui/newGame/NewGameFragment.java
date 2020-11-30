package edu.cnm.deepdive.viral.controller.ui.newGame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.viral.databinding.FragmentNewGameBinding;

public class NewGameFragment extends Fragment {

  private NewGameViewModel newGameViewModel;
  private FragmentNewGameBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentNewGameBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    newGameViewModel = new ViewModelProvider(this).get(NewGameViewModel.class);
  }

}