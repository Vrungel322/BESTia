package nanddgroup.bestia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends AppCompatActivity {
    @Bind(R.id.news_best)
    ImageView news_best;
    @Bind(R.id.news)
    ImageView news;
    @Bind(R.id.bBackToMain)
    Button bBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        this.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);

        Drawable myDrawable = getResources().getDrawable(R.drawable.news);
        Bitmap news_background = ((BitmapDrawable) myDrawable).getBitmap();
        news.setImageBitmap(Bitmap.createScaledBitmap(news_background,
                getWindowManager().getDefaultDisplay().getWidth(),
                getWindowManager().getDefaultDisplay().getHeight(), true));


        Drawable myDrawable1 = getResources().getDrawable(R.drawable.news_best);
        Bitmap news_background1 = ((BitmapDrawable) myDrawable1).getBitmap();
        news_best.setImageBitmap(news_background1);
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
}
