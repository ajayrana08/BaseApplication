package com.asquare.baselibrary

import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.File

//<*/created by Ajay Rana 2022*/>
object GeneralFunctions {

    private const val ALPHA_NUMERIC_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
    internal const val JPEG_FILE_PREFIX = "IMG_"
    internal const val JPEG_FILE_SUFFIX = ".jpg"
    internal const val VIDEO_FILE_PREFIX = "VID_"
    internal const val VIDEO_FILE_SUFFIX = ".mp4"
    internal const val VIDEO_THUMB_FILE_PREFIX = "Thumb_"
    internal const val VIDEO_THUMB_FILE_SUFFIX = ".jpg"
    private const val MIN_PASSWORD_LENGTH = 6
    private const val MAX_PASSWORD_LENGTH = 15

    val isAboveLollipopDevice: Boolean
        get() = Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT

    fun setUpImageFile(directory: String): File? {
        var imageFile: File? = null
        if (Environment.MEDIA_MOUNTED == Environment
                .getExternalStorageState()
        ) {
            val storageDir = File(directory)
            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    Log.d("CameraSample", "failed to create directory")
                    return null
                }
            }

            imageFile = File.createTempFile(
                JPEG_FILE_PREFIX + System.currentTimeMillis() + "_",
                JPEG_FILE_SUFFIX, storageDir
            )
        }
        return imageFile
    }


    fun setUpVideoFile(directory: String): File? {
        var videoFile: File? = null
        if (Environment.MEDIA_MOUNTED == Environment
                .getExternalStorageState()
        ) {
            val storageDir = File(directory)
            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    return null
                }
            }

            videoFile = File.createTempFile(
                VIDEO_FILE_PREFIX
                        + System.currentTimeMillis() + "_",
                VIDEO_FILE_SUFFIX, storageDir
            )
        }
        return videoFile
    }


    fun setUpVideoThumbFile(directory: String): File? {
        var videoThumbFile: File? = null
        if (Environment.MEDIA_MOUNTED == Environment
                .getExternalStorageState()
        ) {
            val storageDir = File(directory)
            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    return null
                }
            }
            videoThumbFile = File.createTempFile(
                VIDEO_THUMB_FILE_PREFIX + System.currentTimeMillis() + "_",
                VIDEO_THUMB_FILE_SUFFIX,
                storageDir
            )
        }
        return videoThumbFile
    }
}