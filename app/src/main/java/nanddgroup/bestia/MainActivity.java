package nanddgroup.bestia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.ivPst0)
    ImageView ivPst0;
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
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(decodedByte.getWidth(), decodedByte.getHeight());
        layoutParams.setMargins(236, 0 , 0, 1790);
        ivPst0.setLayoutParams(layoutParams);
        ivPst0.setRotation(-171.887f);
        ivPst0.setImageBitmap(decodedByte);
    }

}
