package edu.cnm.deepdive.viral.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.viral.R;

public class NewGameActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_game);
    configureNewGameButton();
  }

  private void configureNewGameButton() {
    Button newGameButton = (Button) findViewById(R.id.new_game_button);
    /* newGameButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(NewGameActivity.this, MainActivity.class));
      }
    }); */
  }

}