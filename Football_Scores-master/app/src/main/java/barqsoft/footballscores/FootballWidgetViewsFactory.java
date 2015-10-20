package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import barqsoft.footballscores.service.myFetchService;

/**
 * Created by DragooNART on 10/19/2015.
 */
public class FootballWidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Intent intent;
    Context applicationContext;
    private int mAppWidgetId;
    private int entries = 0;

    public FootballWidgetViewsFactory(Context applicationContext, Intent intent) {
        this.applicationContext=applicationContext;
        this.intent=intent;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    @Override
    public void onCreate() {
    String JSON_data = DataFetchUtil.getFootballDataJson(null,applicationContext.getString(R.string.api_key));
        JSONArray matches =  null;
        try {
            matches = new JSONObject(JSON_data).getJSONArray("fixtures");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(matches!=null && matches.length()>0) {
            entries = matches.length();
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
        return entries;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
