package edu.cnm.deepdive.viral;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.viral.service.ViralDatabase;
import io.reactivex.schedulers.Schedulers;

/**
 * The main application class for the Viral application.
 */
public class ViralApplication extends Application {

  /**
   * The constructor initializes the database with an empty call.
   */
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    ViralDatabase.setContext(this);
    ViralDatabase.getInstance().getFriendDao().delete().subscribeOn(Schedulers.io()).subscribe();
  }

}
