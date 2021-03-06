package nanddgroup.bestia.Utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Nikita on 24.05.2016.
 */
public class JsonHelper {

    public JsonHelper(Context context) {
    }

    public static String loadJSONFromAsset(Context context, String asset_name) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(asset_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<String> getB64DataFromJson(String json) {

        ArrayList<String> result = null;
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject nfo = obj.getJSONObject("nfo");
            JSONArray nws = nfo.getJSONArray("nws");
            result = new ArrayList<String>();
            for (int i = 0; i < nws.length(); i++) {
                JSONObject t0 = nws.getJSONObject(i);
                result.add(t0.getString("pst"));
                Log.wtf("wtf", i + "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getCount(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONObject nfo = obj.getJSONObject("nfo");
        JSONArray nws = nfo.getJSONArray("nws");
        return nws.length();
    }
}
