package barqsoft.footballscores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xnml on 21.10.2015 ..
 */
public class FootballDataBean {


    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public String getAway_name() {
        return away_name;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHome_crest() {
        return home_crest;
    }

    public void setHome_crest(int home_crest) {
        this.home_crest = home_crest;
    }

    public int getAway_crest() {
        return away_crest;
    }

    public void setAway_crest(int away_crest) {
        this.away_crest = away_crest;
    }

    public double getMatch_id() {
        return match_id;
    }

    public void setMatch_id(double match_id) {
        this.match_id = match_id;
    }

    public String home_name;
        public String away_name;

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int away_score) {
        this.away_score = away_score;
    }

    public int home_score;
    public int away_score;
        public String date;
        public int home_crest;
        public int away_crest;
        public double match_id;


}
