package edu.cnm.deepdive.viral.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.viral.model.dao.DemeanorDao;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import io.reactivex.Single;
import java.util.List;

public class DemeanorRepository {

  private final Context context;
  private final DemeanorDao demeanorDao;

  public DemeanorRepository(Context context) {
    this.context = context;
    ViralDatabase database = ViralDatabase.getInstance();
    demeanorDao = database.getDemeanorDao();
  }

}
