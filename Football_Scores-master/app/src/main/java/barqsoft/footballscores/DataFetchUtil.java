package barqsoft.footballscores;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DragooNART on 10/19/2015.
 */
public class DataFetchUtil {

    public static final String LOG_TAG = "FETCH UTIL";

    public static String getFootballDataJson(String timeFrame,String api_key) {
    //Creating fetch URL
    final String BASE_URL = "http://api.football-data.org/alpha/fixtures"; //Base URL
    final String QUERY_TIME_FRAME = "timeFrame"; //Time Frame parameter to determine days
    //final String QUERY_MATCH_DAY = "matchday";

    Uri.Builder fetch_builder = Uri.parse(BASE_URL).buildUpon();

            if(timeFrame != null && !timeFrame.isEmpty()) {
                fetch_builder.appendQueryParameter(QUERY_TIME_FRAME, timeFrame);
            }
        Uri fetch_build = fetch_builder.build();
    //Log.v(LOG_TAG, "The url we are looking at is: "+fetch_build.toString()); //log spam
    HttpURLConnection m_connection = null;
    BufferedReader reader = null;
    String JSON_data = null;
    //Opening Connection
    try {
        URL fetch = new URL(fetch_build.toString());
        m_connection = (HttpURLConnection) fetch.openConnection();
        m_connection.setRequestMethod("GET");
        m_connection.addRequestProperty("X-Auth-Token",api_key);
        m_connection.connect();

        // Read the input stream into a String
        InputStream inputStream = m_connection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
            // But it does make debugging a *lot* easier if you print out the completed
            // buffer for debugging.
            buffer.append(line + "\n");
        }
        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return null;
        }
        JSON_data = buffer.toString();
    }
    catch (Exception e)
    {
        Log.e(LOG_TAG, "Exception here" + e.getMessage());
    }
    finally {
        if(m_connection != null)
        {
            m_connection.disconnect();
        }
        if (reader != null)
        {
            try {
                reader.close();
            }
            catch (IOException e)
            {
                Log.e(LOG_TAG,"Error Closing Stream");
            }
        }
    }
        return JSON_data;
    }
}
