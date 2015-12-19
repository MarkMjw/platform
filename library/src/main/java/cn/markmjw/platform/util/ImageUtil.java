/*
 * Copyright (C) 2015 MarkMjw
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.markmjw.platform.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Image util.
 *
 * @author markmjw
 * @since 1.0.0
 */
public class ImageUtil {
    private static final String TAG = "ImageUtil";

    /**
     * Convert Bitmap to byte[]
     *
     * @param bitmap      the source bitmap
     * @param needRecycle need recycle
     * @return byte[]
     */
    public static byte[] bitmapToBytes(Bitmap bitmap, boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * Scale bitmap with width and height.
     *
     * @param bitmap the source bitmap
     * @param w      the width
     * @param h      the height
     * @return the bitmap
     */
    public static Bitmap zoom(Bitmap bitmap, int w, int h) {
        if (null == bitmap) {
            return null;
        }

        try {
            float scaleWidth = w * 1.0f / bitmap.getWidth();
            float scaleHeight = h * 1.0f / bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(result);

            canvas.drawBitmap(bitmap, matrix, null);

            return result;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * Get bitmap from file with the path.
     *
     * @param path the file path
     * @return the bitmap
     */
    public static Bitmap getBitmapFromFile(String path) {
        Bitmap bitmap = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(path);
            }
        } catch (OutOfMemoryError e) {
            Log.e(TAG, e.getMessage());
        }
        return bitmap;
    }
}
