package nanddgroup.bestia.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    Context context;
    ArrayList<Bitmap> alNewsPosters;


    public NewsAdapter(Context context, int resource, ArrayList<Bitmap> alNewsPosters) {
        super(context, resource, alNewsPosters);
        this.context = context;
        this.alNewsPosters = alNewsPosters;
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
        ivPoster.setImageBitmap(scaleBitmap(alNewsPosters.get(position), 0.35d));
        ImageView ivHeader = (ImageView) rootView.findViewById(R.id.ivHeader);
        if (position % 2 == 0) {
            ivHeader.setImageBitmap(bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_0, 0.5d));
            ivFrame.setImageBitmap(bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.35d));
            ivFrame.setRotation(1f);
            ivPoster.setRotation(1f);
        }
        else {
            ivHeader.setImageBitmap(bitmapLoader(context.getResources(), R.drawable.news_poster_sprt_1, 0.5d));
            ivFrame.setImageBitmap(bitmapLoader(context.getResources(), R.drawable.news_poster_frame, 0.35d));
            ivFrame.setRotation(-0.7f);
            ivPoster.setRotation(-0.7f);
        }
        return rootView;
    }

    private Bitmap bitmapLoader(Resources res, int resId, double convert) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * convert), (int) (((double) bitmap.getHeight()) * convert), false);
    }

    private Bitmap scaleBitmap(Bitmap bitmap, double convert){
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * convert), (int) (((double) bitmap.getHeight()) * convert), false);
    }
}
