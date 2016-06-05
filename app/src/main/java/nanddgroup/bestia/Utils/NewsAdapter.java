package nanddgroup.bestia.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import nanddgroup.bestia.R;

/**
 * Created by Nikita on 27.05.2016.
 */
public class NewsAdapter extends ArrayAdapter<Bitmap> {
    private Context context;
    private ArrayList<Bitmap> alNewsPosters;
    private Activity activity;
    private FileCache fc;


    public NewsAdapter(Context context, int resource, ArrayList<Bitmap> alNewsPosters, Activity activity) {
        super(context, resource, alNewsPosters);
        this.context = context;
        this.alNewsPosters = alNewsPosters;
        this.activity = activity;
        fc = new FileCache(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.each_news_for_lv, parent, false);
            Log.wtf("h_w", "cV = null");
        }
        else {
            rootView = convertView;
        }
        ImageView ivFrame = (ImageView) rootView.findViewById(R.id.ivFrame);
        final ImageView ivPoster = (ImageView) rootView.findViewById(R.id.ivPoster);
        ImageView ivHeader = (ImageView) rootView.findViewById(R.id.ivHeader);

        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        ivPoster.setImageBitmap(alNewsPosters.get(position));
                        Picasso.with(context).
                                load(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/" + "BESTia" + "/" + position + ".png")))
                                .into(ivPoster);
                    }
                });
            }
        }).start();

        if (position % 2 == 0) {
            ivHeader.setImageResource(R.drawable.news_poster_sprt_0);
            ivFrame.setRotation(1f);
            ivPoster.setRotation(1f);
        }
        else {
            ivHeader.setImageResource(R.drawable.news_poster_sprt_1);
            ivFrame.setRotation(-0.7f);
            ivPoster.setRotation(-0.7f);
        }
        return rootView;
    }
}
