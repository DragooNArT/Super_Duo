package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DragooNART on 10/19/2015.
 */
public class FootballWidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Intent intent;
    Context applicationContext;
    private int mAppWidgetId;
    private int entriesCount;
    private Cursor cursor;
    List<FootballDataBean> entries;

    public FootballWidgetViewsFactory(Context applicationContext, Intent intent) {
        this.applicationContext=applicationContext;
        this.intent=intent;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {
        ContentProviderClient client = applicationContext.getContentResolver().acquireContentProviderClient(DatabaseContract.CONTENT_AUTHORITY);
        entries = new ArrayList<FootballDataBean>();
        try {
            SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
            cursor =  client.query(DatabaseContract.scores_table.buildScoreWithDate(),null,null,new String[]{mformat.format(new Date())},null);
            if(cursor != null) {

                while (cursor.moveToNext()) {
                    FootballDataBean entry = new FootballDataBean();

                    entry.setHome_name(cursor.getString(ScoresAdapter.COL_HOME));
                    entry.setAway_name(cursor.getString(ScoresAdapter.COL_AWAY));
                    entry.setHome_score(cursor.getInt(ScoresAdapter.COL_HOME_GOALS));
                    entry.setAway_score(cursor.getInt(ScoresAdapter.COL_AWAY_GOALS));
                    entry.setDate(cursor.getString(ScoresAdapter.COL_MATCHTIME));
                    entry.setMatch_id(cursor.getInt(ScoresAdapter.COL_ID));
                    entries.add(entry);
                    cursor.moveToNext();
                }
                entriesCount = entries.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return entriesCount;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(applicationContext.getPackageName(), R.layout.widget_scores_list_item);
        if(entries != null && (entries.size()-1)>i) {
            FootballDataBean entry = entries.get(i);
            rv.setTextViewText(R.id.widget_home_name, entry.getHome_name());
            rv.setTextViewText(R.id.widget_away_name, entry.getAway_name());
            rv.setTextViewText(R.id.widget_score_textview, Utilies.getScores(entry.getHome_score(), entry.getAway_score()));
            rv.setTextViewText(R.id.widget_data_textview,entry.getDate());
        }


        // Return the remote views object.
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        if(entries!= null && (entries.size()-1)>i) {
            return (long)entries.get(i).getMatch_id();
        }

        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
