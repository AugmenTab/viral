package edu.cnm.deepdive.viral.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.databinding.ActivityNewGameBinding;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import edu.cnm.deepdive.viral.viewmodel.NewGameViewModel;

public class NewGameActivity extends AppCompatActivity {

  private ActivityNewGameBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    LiveData<Game> currentGame = ViralDatabase.getInstance().getGameDao().selectCurrentGame();
//    if (currentGame.getValue().getEndTime() != null) {
//      switchActivity();
//    }
    binding = ActivityNewGameBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.newGameButton.setOnClickListener((v) -> {
      String username = binding.accountNameInput.getText().toString().trim();
      if (username.isEmpty()) {
        Toast.makeText(
            NewGameActivity.this, R.string.no_username_message, Toast.LENGTH_SHORT).show();
      } else {
        // NewGameViewModel viewModel = new NewGameViewModel(/* TODO: HELP */, username, 0);
        switchActivity();
      }
    });
  }

  private void switchActivity() {
    startActivity(new Intent(NewGameActivity.this, MainActivity.class));
  }

}