package nanddgroup.bestia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.ivMainPart1)
    ImageView ivMainPart1;
    @Bind(R.id.ivMainPart2)
    ImageView ivMainPart2;
    @Bind(R.id.ivMainPart3)
    ImageView ivMainPart3;
    @Bind(R.id.ivPst0)
    ImageView ivPst0;
    @Bind(R.id.ivPst1)
    ImageView ivPst1;
    @Bind(R.id.ivPst2)
    ImageView ivPst2;
    @Bind(R.id.ivPst3)
    ImageView ivPst3;
    @Bind(R.id.ivSticks)
    ImageView ivSticks;
    @Bind(R.id.bNews)
    Button bNews;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    private ArrayList<String> pst;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
        pst = new ArrayList<String>();
        json = JsonHelper.loadJSONFromAsset(getApplicationContext(), "uk-main.json");
        pst = JsonHelper.getB64DataFromJson(json);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        poster_1(pst.get(0),ivPst0, 3f);
        poster_1(pst.get(1), ivPst1, -1f);
        poster_1(pst.get(2), ivPst2, 2f);
        poster_1(pst.get(3), ivPst3, 2.5f);


    }

    @OnClick(R.id.bNews)
    public void bNewsClicked() {
        Log.wtf("wtf", "CLicked");
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
        finish();
    }


    private void poster_1(String pst_cur, ImageView iv, float angle) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        iv.setRotation(angle);
        iv.setImageBitmap(decodedByte);
    }

}
