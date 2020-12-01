package edu.cnm.deepdive.viral.service;

import android.app.Application;
import android.content.res.AssetManager;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.viral.R;
import edu.cnm.deepdive.viral.generator.CsvReader;
import edu.cnm.deepdive.viral.model.dao.ActionDao;
import edu.cnm.deepdive.viral.model.dao.ActionResponseDao;
import edu.cnm.deepdive.viral.model.dao.ActionTakenDao;
import edu.cnm.deepdive.viral.model.dao.DemeanorDao;
import edu.cnm.deepdive.viral.model.dao.FriendDao;
import edu.cnm.deepdive.viral.model.dao.GameDao;
import edu.cnm.deepdive.viral.model.entity.Action;
import edu.cnm.deepdive.viral.model.entity.ActionResponse;
import edu.cnm.deepdive.viral.model.entity.ActionTaken;
import edu.cnm.deepdive.viral.model.entity.Demeanor;
import edu.cnm.deepdive.viral.model.entity.Friend;
import edu.cnm.deepdive.viral.model.entity.Game;
import edu.cnm.deepdive.viral.service.ViralDatabase.Converters;
import io.reactivex.Single;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Database(
    entities = {
        Game.class,
        Friend.class,
        Demeanor.class,
        Action.class,
        ActionResponse.class,
        ActionTaken.class
    },
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class ViralDatabase extends RoomDatabase {

  private static final String DB_NAME = "viral_db";

  private static Application context;

  public static void setContext(Application context) {
    ViralDatabase.context = context;
  }

  public static ViralDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract GameDao getGameDao();

  public abstract FriendDao getFriendDao();

  public abstract DemeanorDao getDemeanorDao();

  public abstract ActionDao getActionDao();

  public abstract ActionResponseDao getActionResponseDao();

  public abstract ActionTakenDao getActionTakenDao();

  private static class InstanceHolder {

    private static final ViralDatabase INSTANCE =
        Room.databaseBuilder(context, ViralDatabase.class, DB_NAME)
            .addCallback(new Callback())
            .build();

  }

  public static class Callback extends RoomDatabase.Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try {
        importDemeanors();
        importActions();
      } catch (IOException e) {
        e.printStackTrace();
      }

      /* FriendDao friendDao = ViralDatabase.getInstance().getFriendDao();
      Demeanor demeanor = new Demeanor();
      demeanor.setName("aggressive");
      demeanorDao.insert(demeanor)
          .flatMap((id) -> {
            List<Friend> friends = new LinkedList<>();
            for (String name : new String[] {"Bob", "Alice", "Tim"}) {
              Friend friend = new Friend();
              friend.setName(name);
              friend.setDemeanor(id);
              friend.setProfilePicture(name);
              friends.add(friend);
            }
            return friendDao.insert(friends);
          })
          .subscribeOn(Schedulers.io())
          .subscribe(); */
    }

    private void importDemeanors() throws IOException {
      DemeanorDao demeanorDao = ViralDatabase.getInstance().getDemeanorDao();
      List<Demeanor> demeanors = new ArrayList<>();
      List<CSVRecord> list =
          CsvReader.parseCSV(context.getResources().openRawResource(R.raw.demeanors));
      for (CSVRecord item : list) {
        Demeanor demeanor = new Demeanor();
        demeanor.setName(item.get(0));
        demeanor.setInfectionMin(Integer.parseInt(item.get(1)));
        demeanor.setInfectionMax(Integer.parseInt(item.get(2)));
        demeanors.add(demeanor);
      }
      demeanorDao.insert(demeanors).subscribeOn(Schedulers.io()).subscribe();
    }

    private void importActions() throws IOException {
      DemeanorDao demeanorDao = ViralDatabase.getInstance().getDemeanorDao();
      ActionDao actionDao = ViralDatabase.getInstance().getActionDao();
      List<Action> actions = new ArrayList<>();
      List<CSVRecord> list =
          CsvReader.parseCSV(context.getResources().openRawResource(R.raw.actions));
      for (CSVRecord item : list) {
        Action action = new Action();
        action.setContent(item.get(0));
        action.setPublic(Boolean.parseBoolean(item.get(1)));
        action.setDemeanor(demeanorDao.selectDemeanorByName(item.get(2)).getValue().getId()); // TODO: Get help from Nick/Todd on why this is coming up null.
        actions.add(action);
      }
      actionDao.insert(actions).subscribeOn(Schedulers.io()).subscribe();
    }

  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }

}
