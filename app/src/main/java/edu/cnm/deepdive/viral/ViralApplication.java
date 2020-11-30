package edu.cnm.deepdive.viral;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import io.reactivex.schedulers.Schedulers;

public class ViralApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    ViralDatabase.setContext(this);
    ViralDatabase.getInstance().getFriendDao().delete().subscribeOn(Schedulers.io()).subscribe();
  }

}
