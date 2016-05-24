package nanddgroup.bestia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import nanddgroup.bestia.Utils.JsonHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.wtf("wtf", JsonHelper.loadJSONFromAsset(getApplicationContext()));
    }
}
