package nanddgroup.bestia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import nanddgroup.bestia.Utils.JsonHelper;
import nanddgroup.bestia.Utils.NewsAdapter;

public class NewsActivity extends AppCompatActivity {
    @Bind(R.id.news_best)
    ImageView news_best;
    @Bind(R.id.news)
    ImageView news;
    @Bind(R.id.bBackToMain)
    Button bBackToMain;
//    @Bind(R.id.lvNews)
//    ListView lvNews;
    private NewsAdapter nA;
    private ArrayList<String> pst;
    private String json;
    private ArrayList<Bitmap> alBitmaps;
    private ListView lvNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        this.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        pst = new ArrayList<String>();
        alBitmaps = new ArrayList<Bitmap>();


        initMainBackground();
        json = JsonHelper.loadJSONFromAsset(getApplicationContext(), "uk-news.json");
        pst = JsonHelper.getB64DataFromJson(json);
        try {
            for (int i = 0; i < JsonHelper.getCount(json); i++){
                alBitmaps.add(poster_2(pst.get(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nA = new NewsAdapter(getApplicationContext(), R.layout.each_news_for_lv, alBitmaps);
        lvNews = (ListView) findViewById(R.id.lvNews);
        lvNews.setAdapter(nA);



    }

    private Bitmap poster_2(String pst_cur) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }

    @OnClick(R.id.bBackToMain)
    public void bBackToMainClicked(){
        startActivity(new Intent(NewsActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NewsActivity.this, MainActivity.class));
        finish();
    }

    private void initMainBackground() {
        Drawable myDrawable = getResources().getDrawable(R.drawable.news);
        Bitmap news_background = ((BitmapDrawable) myDrawable).getBitmap();
        news.setImageBitmap(Bitmap.createScaledBitmap(news_background,
                getWindowManager().getDefaultDisplay().getWidth(),
                getWindowManager().getDefaultDisplay().getHeight(), true));

        Drawable myDrawable1 = getResources().getDrawable(R.drawable.news_best);
        Bitmap news_background1 = ((BitmapDrawable) myDrawable1).getBitmap();
        news_best.setImageBitmap(news_background1);
    }

}
