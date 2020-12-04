package edu.cnm.deepdive.viral.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.databinding.ActivityNewGameBinding;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.viewmodel.NewGameViewModel;

/**
 * The NewGameActivity acts as a landing page for new users, and for users without an ongoing game.
 * It is designed to look like a login screen, where the player can enter a username and "create an
 * account," which starts a new {@link Game}.
 */
public class NewGameActivity extends AppCompatActivity {

  private ActivityNewGameBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO Check if a game with a null end time already exists. If one does, redirect to the
    //  MainActivity.
    binding = ActivityNewGameBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    NewGameViewModel viewModel = new ViewModelProvider(this).get(NewGameViewModel.class);
    viewModel.getThrowable().observe(this, (throwable) ->
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable));
    SharedPreferences preferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    String preferredUsername = preferences.getString("username", "");
    binding.accountNameInput.setText(preferredUsername);
    binding.newGameButton.setOnClickListener((v) -> {
      String username = binding.accountNameInput.getText().toString().trim();
      if (username.isEmpty()) {
        Toast.makeText(
            NewGameActivity.this, R.string.no_username_message, Toast.LENGTH_SHORT).show();
      } else {
        editor.putString("username", username);
        viewModel.newGame(username, 0);
        switchActivity();
      }
    });
  }

  private void switchActivity() {
    startActivity(new Intent(NewGameActivity.this, MainActivity.class));
  }

}