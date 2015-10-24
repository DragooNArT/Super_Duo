package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import barqsoft.footballscores.service.FootballWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class FootballScoresWidget extends AppWidgetProvider {
    private ScoresAdapter mAdapter;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
    }


    @Override
    public void onEnabled(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.football_scores_widget);
        mAdapter = new ScoresAdapter(context,null,0);


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent svcIntent = new Intent(context, FootballWidgetService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews widget = new RemoteViews(context.getPackageName(),R.layout.football_scores_widget);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            widget.setRemoteAdapter(R.id.widget_list, svcIntent);
        else
            widget.setRemoteAdapter(appWidgetId, R.id.widget_list, svcIntent);


        ComponentName localComponentName = new ComponentName(context, FootballScoresWidget.class);
       // ComponentName localComponentName = new ComponentName(this.ctx, MyWidgetProvider.class);
        AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(context);
      //  localAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
        localAppWidgetManager.updateAppWidget(appWidgetId, widget);



    }
}

