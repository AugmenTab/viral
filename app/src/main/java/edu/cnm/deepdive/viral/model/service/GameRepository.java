package edu.cnm.deepdive.viral.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Game;
import io.reactivex.Single;
import java.util.List;

public class GameRepository {

  // TODO: Ask why gameDao and demeanorDao are red in these repositories.
  private final Context context;
  private final GameDao gameDao;

  public GameRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    gameDao = database.getGameDao();
  }

}
