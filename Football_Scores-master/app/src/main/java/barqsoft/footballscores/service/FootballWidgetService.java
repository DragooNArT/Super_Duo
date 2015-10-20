package barqsoft.footballscores.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.FootballWidgetViewsFactory;

/**
 * Created by DragooNART on 10/19/2015.
 */
public class FootballWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new FootballWidgetViewsFactory(this.getApplicationContext(), intent));
    }

}
