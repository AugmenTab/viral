package edu.cnm.deepdive.viral.controller.ui.newGame;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.GameRepository;

public class NewGameViewModel extends AndroidViewModel {

  private final GameRepository gameRepository;
  private final LiveData<Game> currentGame;

  public NewGameViewModel(@NonNull Application application) {
    super(application);
    gameRepository = new GameRepository(application);
    currentGame = gameRepository.getCurrentGame();
  }

}
