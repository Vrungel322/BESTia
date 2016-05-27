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
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {
    public static final int W_POSTER = 230;
    public static final int H_POSTER = 318;
    public static final int X_POSTER0 = 236;
    public static final int Y_POSTER0 = 940;
    public static final int X_POSTER1 = 440;
    public static final int Y_POSTER1 = 1000;
    public static final int X_POSTER2 = 150;
    public static final int Y_POSTER2 = 5000;
    public static final int X_POSTER3 = 578;
    public static final int Y_POSTER3 = 4998;
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

        Log.wtf("wtf", pst.get(0));

        poster_1(pst.get(0), X_POSTER0, Y_POSTER0, ivPst0, 3f);
        poster_1(pst.get(1), X_POSTER1, Y_POSTER1, ivPst1, -1f);
        poster_1(pst.get(2), X_POSTER2, Y_POSTER2, ivPst2, 2f);
        poster_1(pst.get(3), X_POSTER3, Y_POSTER3, ivPst3, 2.5f);
        ivSticks.setScaleX(0.46f);
        ivSticks.setScaleY(0.46f);
        ivSticks.setX(-100);
        ivSticks.setY(500);
        ivSticks.setImageResource(R.drawable.main_sticks);

    }

    @OnClick(R.id.bNews)
    public void bNewsClicked() {
        Log.wtf("wtf", "CLicked");
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
        finish();
    }

    private void poster_1(String pst_cur, int x_location, int y_location, ImageView iv, float angle) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(decodedByte.getWidth(), decodedByte.getHeight());
        iv.setScaleX(1.2f);
        iv.setScaleY(1.2f);
        iv.setRotation(angle);
        iv.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, W_POSTER, H_POSTER, false));
        layoutParams.setMargins(x_location, y_location, 0, 0);
        iv.setLayoutParams(layoutParams);
    }

}
