package nanddgroup.bestia.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import nanddgroup.bestia.R;

/**
 * Created by Nikita on 27.05.2016.
 */
public class NewsAdapter extends ArrayAdapter<Bitmap> {
    private Context context;
    private ArrayList<Bitmap> alNewsPosters;
    private SizeHelper sizeHelper;
    private Activity activity;


    public NewsAdapter(Context context, int resource, ArrayList<Bitmap> alNewsPosters, Activity activity) {
        super(context, resource, alNewsPosters);
        this.context = context;
        this.alNewsPosters = alNewsPosters;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.each_news_for_lv, parent, false);
        }
        else {
            rootView = convertView;
        }
        ImageView ivFrame = (ImageView) rootView.findViewById(R.id.ivFrame);
        ImageView ivPoster = (ImageView) rootView.findViewById(R.id.ivPoster);
        ImageView ivHeader = (ImageView) rootView.findViewById(R.id.ivHeader);
        ivPoster.setImageBitmap(alNewsPosters.get(position));
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
