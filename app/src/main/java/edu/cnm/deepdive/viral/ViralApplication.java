package edu.cnm.deepdive.viral;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class ViralApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }

}
