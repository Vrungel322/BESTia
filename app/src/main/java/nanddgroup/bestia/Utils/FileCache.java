package nanddgroup.bestia.Utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nikita on 05.06.2016.
 */
public class FileCache {
    private File cacheDir;
    private File dir;

    public FileCache(Context context){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(),
                    "aMPdroid/.Cache");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }


    public void deleteDir(File fileOrDirectory){
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteDir(child);
            }
        }
        fileOrDirectory.delete();
    }

    public void storeBitmap(Bitmap _bitmap, File _file) {
        try {

            if (android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED)) {
                File parentDir = _file.getParentFile();
                dir = _file;
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                FileOutputStream f = new FileOutputStream(_file);
                _bitmap.compress(Bitmap.CompressFormat.JPEG, 100, f);
                f.flush();
                f.close();
            } else {
                // todo: write to internal memory here???
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

