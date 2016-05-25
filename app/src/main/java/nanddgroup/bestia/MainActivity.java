package nanddgroup.bestia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {
    public static final int W_POSTER = 230;
    public static final int H_POSTER = 318;
    public static final int X_POSTER0 = 236;
    public static final int Y_POSTER0 = 1790;
    public static final int X_POSTER1 = 440;
    public static final int Y_POSTER1 = 1850;
    public static final int X_POSTER2 = 190;
    public static final int Y_POSTER2 = 2370;
    public static final int X_POSTER3 = 478;
    public static final int Y_POSTER3 = 2369;
    @Bind(R.id.ivPst0)
    ImageView ivPst0;
    @Bind(R.id.ivPst1)
    ImageView ivPst1;
    @Bind(R.id.ivPst2)
    ImageView ivPst2;
    @Bind(R.id.ivPst3)
    ImageView ivPst3;
    @Bind(R.id.imageView)
    ImageView imageView;
    String[] pst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pst = new String[4];
        pst = JsonHelper.getB64DataFromJson(JsonHelper.loadJSONFromAsset(getApplicationContext()));

        Log.wtf("wtf", pst[0]);

        poster_1(pst[0], X_POSTER0, Y_POSTER0, ivPst0, 3f);
        poster_1(pst[1], X_POSTER1, Y_POSTER1, ivPst1, -1f);
        poster_1(pst[2], X_POSTER2, Y_POSTER2, ivPst2, 2f);
        poster_1(pst[3], X_POSTER3, Y_POSTER3, ivPst3, 2.5f);
    }

    private void poster_1(String pst_cur, int x_location, int y_location, ImageView iv, float angle) {
        byte[] decodedString = Base64.decode(pst_cur.getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(decodedByte.getWidth(), decodedByte.getHeight());
        iv.setRotation(angle);
        iv.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, W_POSTER, H_POSTER, false));
        layoutParams.setMargins(x_location, y_location, 0, 0);
        iv.setLayoutParams(layoutParams);
    }

}
