package edu.cnm.deepdive.viral.service;

import android.app.Application;
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
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
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
        Map<Demeanor, List<Action>> demeanors = importDemeanors();
        importActions(demeanors);
        persistDemeanorsAndActions(demeanors);
//        importActionResponses();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private Map<Demeanor, List<Action>> importDemeanors() throws IOException {
      try (
          InputStream input = context.getResources().openRawResource(R.raw.demeanors);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(
              reader,
              CSVFormat.DEFAULT
                  .withFirstRecordAsHeader().withIgnoreEmptyLines().withIgnoreSurroundingSpaces());
      ) {
        Map<Demeanor, List<Action>> demeanors = new LinkedHashMap<>();
        for (CSVRecord item : parser) {
          Demeanor demeanor = new Demeanor();
          demeanor.setName(item.get(0));
          demeanor.setInfectionMin(Integer.parseInt(item.get(1)));
          demeanor.setInfectionMax(Integer.parseInt(item.get(2)));
          demeanors.put(demeanor, new LinkedList<>());
        }
        return demeanors;
      }
    }

    private void importActions(Map<Demeanor, List<Action>> demeanors) throws IOException {
      try (
          InputStream input = context.getResources().openRawResource(R.raw.actions);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(
              reader,
              CSVFormat.DEFAULT
                  .withFirstRecordAsHeader().withIgnoreEmptyLines().withIgnoreSurroundingSpaces());
      ) {
        for (CSVRecord item : parser) {
          Action action = new Action();
          action.setContent(item.get(0));
          action.setPublic(Boolean.parseBoolean(item.get(1)));
          Demeanor demeanor = new Demeanor();
          demeanor.setName(item.get(2));
          demeanors.get(demeanor).add(action);
        }
      }
    }

    private void persistDemeanorsAndActions(Map<Demeanor, List<Action>> demeanors) {
      DemeanorDao demeanorDao = ViralDatabase.getInstance().getDemeanorDao();
      ActionDao actionDao = ViralDatabase.getInstance().getActionDao();
      Set<Demeanor> keys = demeanors.keySet();
      demeanorDao.insert(keys)
          .flatMap((ids) -> {
            Iterator<Demeanor> iterDemeanor = keys.iterator();
            Iterator<Long> iterId = ids.iterator();
            List<Action> allActions = new LinkedList<>();
            while (iterId.hasNext()) {
              long id = iterId.next();
              Demeanor demeanor = iterDemeanor.next();
              List<Action> actions = demeanors.get(demeanor);
              actions.forEach((action) -> action.setDemeanor(id));
              allActions.addAll(actions);
            }
            return actionDao.insert(allActions);
          })
          .subscribeOn(Schedulers.io())
          .subscribe(
              (ids) -> {},
              (throwable) -> {throw new RuntimeException(throwable);}
          );
    }

    private void importActionResponses() throws IOException {
      // TODO: Create method to insert ActionResponses.
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
