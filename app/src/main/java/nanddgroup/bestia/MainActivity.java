package nanddgroup.bestia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.ivPst0)
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
    }

}
