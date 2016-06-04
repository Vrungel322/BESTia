package nanddgroup.bestia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import nanddgroup.bestia.Utils.JsonHelper;
import nanddgroup.bestia.Utils.NewsAdapter;

public class NewsActivity extends AppCompatActivity {
    @Bind(R.id.news_best)
    ImageView news_best;
    @Bind(R.id.news)
    ImageView news;
    @Bind(R.id.bBackToMain)
    Button bBackToMain;
    @Bind(R.id.lvNews)
    ListView lvNews;
    private NewsAdapter nA;
    private ArrayList<String> pst;
    private String json;
    private ArrayList<Bitmap> alBitmaps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        this.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        pst = new ArrayList<String>();
        alBitmaps = new ArrayList<Bitmap>();
        lvNews.setDivider(null);
        OverScrollDecoratorHelper.setUpOverScroll(lvNews);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    private Bitmap poster_2(String pst_cur) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }

    @OnClick(R.id.bBackToMain)
    public void bBackToMainClicked() {
        startActivity(new Intent(NewsActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NewsActivity.this, MainActivity.class));
        finish();
    }


    private class MyAsyncTask extends AsyncTask {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            } else {
                mProgressDialog = new ProgressDialog(NewsActivity.this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(android.R.attr.progressBarStyleSmall);
                mProgressDialog.setMessage(getString(R.string.progress_authenticate));
                mProgressDialog.show();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            json = JsonHelper.loadJSONFromAsset(getApplicationContext(), "uk-news.json");
            pst = JsonHelper.getB64DataFromJson(json);
            try {
                for (int i = 0; i < JsonHelper.getCount(json); i++) {
                    alBitmaps.add(poster_2(pst.get(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            nA = new NewsAdapter(getApplicationContext(),
                    R.layout.each_news_for_lv, alBitmaps, NewsActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            lvNews.setAdapter(nA);
            mProgressDialog.dismiss();
        }
    }

}
