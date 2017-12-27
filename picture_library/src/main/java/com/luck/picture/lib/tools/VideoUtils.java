package com.luck.picture.lib.tools;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

import static android.provider.MediaStore.Video.Thumbnails.MINI_KIND;

/**
 * Created by ss on 2017/12/27 0027.
 */

public class VideoUtils {


    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MINI_KIND);

       /* MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }*/
        return bitmap;
    }
}
