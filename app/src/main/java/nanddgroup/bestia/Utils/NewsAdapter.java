package nanddgroup.bestia.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
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
    private int SCREEN_TYPE;


    public NewsAdapter(Context context, int resource, ArrayList<Bitmap> alNewsPosters, Activity activity) {
        super(context, resource, alNewsPosters);
        this.context = context;
        this.alNewsPosters = alNewsPosters;
        this.activity = activity;
        sizeHelper = new SizeHelper(activity);
        SCREEN_TYPE = sizeHelper.getScreenType();
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
//      >>------------------------------------------------------------------------------------------
        if (SCREEN_TYPE == SizeHelper.SCREEN_FULLHD) {
            ivPoster.setImageBitmap(scaleBitmap(alNewsPosters.get(position), 0.35d));
            if (position % 2 == 0) {
                ivHeader.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_0, 0.52d));
                ivFrame.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.35d));
                ivFrame.setRotation(1f);
                ivPoster.setRotation(1f);
            }
            else {
                ivHeader.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_1, 0.5d));
                ivFrame.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.35d));
                ivFrame.setRotation(-0.7f);
                ivPoster.setRotation(-0.7f);
            }
        }

//      >>------------------------------------------------------------------------------------------
        if (SCREEN_TYPE == SizeHelper.SCREEN_HD) {
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            flp.setMargins(23, 63, 0, 0);
            ivPoster.setImageBitmap(scaleBitmap(alNewsPosters.get(position), 0.925d));
            ivPoster.setLayoutParams(flp);
            if (position % 2 == 0) {
                ivHeader.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_0, 0.45d));
                ivFrame.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.46d));
                ivFrame.setRotation(1f);
                ivPoster.setRotation(1f);
            }
            else {
                ivHeader.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_1, 0.45d));
                ivFrame.setImageBitmap(SizeHelper.bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.46d));
                ivFrame.setRotation(-0.7f);
                ivPoster.setRotation(-0.7f);
            }
        }
        return rootView;
    }



    private Bitmap scaleBitmap(Bitmap bitmap, double convert){
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * convert), (int) (((double) bitmap.getHeight()) * convert), true);
    }
}
