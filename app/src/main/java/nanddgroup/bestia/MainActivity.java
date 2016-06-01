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
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanddgroup.bestia.Utils.JsonHelper;
import nanddgroup.bestia.Utils.SizeHelper;

public class MainActivity extends AppCompatActivity {
    //-------------------------------------------------------------------Coorginates for FHD
    public static final int W_POSTER_FHD = 230;
    public static final int H_POSTER_FHD = 318;

    public static final int X_POSTER0_FHD = 246;
    public static final int Y_POSTER0_FHD = 960;

    public static final int X_POSTER1_FHD = 520;
    public static final int Y_POSTER1_FHD = 1020;

    public static final int X_POSTER2_FHD = 160;
    public static final int Y_POSTER2_FHD = 5160;

    public static final int X_POSTER3_FHD = 568;
    public static final int Y_POSTER3_FHD = 5152;
//-------------------------------------------------------------------Coorginates for HD
    public static final int X_POSTER0_HD = 116;
    public static final int Y_POSTER0_HD = 590;

    public static final int X_POSTER1_HD = 310;
    public static final int Y_POSTER1_HD = 630;

    public static final int X_POSTER2_HD = 80;
    public static final int Y_POSTER2_HD = 3420;

    public static final int X_POSTER3_HD = 345;
    public static final int Y_POSTER3_HD = 3420;
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
    private SizeHelper sizeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
        pst = new ArrayList<String>();
        json = JsonHelper.loadJSONFromAsset(getApplicationContext(), "uk-main.json");
        pst = JsonHelper.getB64DataFromJson(json);
        sizeHelper = new SizeHelper(this);
        if (sizeHelper.getScreenType() == SizeHelper.SCREEN_FULLHD){
            posForFHD();
        }
        if (sizeHelper.getScreenType() == SizeHelper.SCREEN_HD){
            posForHD();
        }



    }

    @OnClick(R.id.bNews)
    public void bNewsClicked() {
        Log.wtf("wtf", "CLicked");
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
        finish();
    }

    private void posForHD() {
        ivMainPart1.setImageBitmap(SizeHelper.bitmapLoader(getResources(), R.drawable.main_part1, 0.479d));
        ivMainPart3.setImageBitmap(SizeHelper.bitmapLoader(getResources(), R.drawable.main_part3, 0.479d));
        poster_1(pst.get(0), X_POSTER0_HD, Y_POSTER0_HD, ivPst0, 3f);
        poster_1(pst.get(1), X_POSTER1_HD, Y_POSTER1_HD, ivPst1, -1f);
        poster_1(pst.get(2), X_POSTER2_HD, Y_POSTER2_HD, ivPst2, 2f);
        poster_1(pst.get(3), X_POSTER3_HD, Y_POSTER3_HD, ivPst3, 2.5f);
        ivSticks.setImageBitmap(SizeHelper.bitmapLoader(getResources(), R.drawable.main_sticks, 0.47d));
        ivSticks.setX(100);
        ivSticks.setY(560);
    }

    private void posForFHD() {

        ivMainPart1.setImageBitmap(SizeHelper.bitmapLoader(getResources(), R.drawable.main_part1, 0.487d));
        ivMainPart3.setImageBitmap(SizeHelper.bitmapLoader(getResources(), R.drawable.main_part3, 0.4795d));
        poster_1(pst.get(0), X_POSTER0_FHD, Y_POSTER0_FHD, ivPst0, 3f);
        poster_1(pst.get(1), X_POSTER1_FHD, Y_POSTER1_FHD, ivPst1, -1f);
        poster_1(pst.get(2), X_POSTER2_FHD, Y_POSTER2_FHD, ivPst2, 2f);
        poster_1(pst.get(3), X_POSTER3_FHD, Y_POSTER3_FHD, ivPst3, 2.5f);
        ivSticks.setScaleX(0.55f);
        ivSticks.setScaleY(0.55f);
        ivSticks.setX(-65);
        ivSticks.setY(515);
        ivSticks.setImageResource(R.drawable.main_sticks);
    }


    private void poster_1(String pst_cur, int x_location, int y_location, ImageView iv, float angle) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(decodedByte.getWidth(), decodedByte.getHeight());
        if (sizeHelper.getScreenType() == SizeHelper.SCREEN_FULLHD){
            iv.setScaleX(1.4f);
            iv.setScaleY(1.4f);
        }
        if (sizeHelper.getScreenType() == SizeHelper.SCREEN_HD){
            iv.setScaleX(1.0f);
            iv.setScaleY(1.0f);
        }
        iv.setRotation(angle);
        iv.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, W_POSTER_FHD, H_POSTER_FHD, false));
        layoutParams.setMargins(x_location, y_location, 0, 0);
        iv.setLayoutParams(layoutParams);
    }

}
