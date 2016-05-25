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
    public static final int X_POSTER1 = 236;
    public static final int Y_POSTER1 = 1790;
    @Bind(R.id.ivPst0)
    ImageView ivPst0;
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

        byte[] decodedString = Base64.decode(pst[0].getBytes(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(decodedByte.getWidth(), decodedByte.getHeight());
        ivPst0.setRotation(8.021409f);
        ivPst0.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, W_POSTER, H_POSTER, false));
        Log.wtf("wtf", String.valueOf(ivPst0.getWidth()/2));
        layoutParams.setMargins(X_POSTER1, Y_POSTER1, 0, 0);
        ivPst0.setLayoutParams(layoutParams);
    }

}
