package nanddgroup.bestia.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Nikita on 31.05.2016.
 */
public class SizeHelper {

    public static int SCREEN_FULLHD = 1;
    public static int SCREEN_HD = 2;
    private Activity activity;
    private int height;
    private int width;

    public SizeHelper(Activity activity) {
        this.activity = activity;
        getScreenSize();
    }

    private void getScreenSize(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;
        Log.wtf("h_w", height + "_" + width);
    }

    private int getHeight() {
        return height;
    }

    private int getWidth() {
        return width;
    }

    public int getScreenType() {
        if ((getHeight() == 1920) && getWidth() == 1080) {
            return SCREEN_FULLHD;
        }
        if ((getHeight() == 1280) && getWidth() == 720) {
            return SCREEN_HD;
        }
        return 0;
    }

    public static Bitmap bitmapLoader(Resources res, int resId, double convert) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * convert), (int) (((double) bitmap.getHeight()) * convert), false);
    }
}
