package com.example.wood121.viewdemos.loader;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

/**
 * Created by wood121 on 2018/1/24.
 */

public class AlbumLoader extends CursorLoader {

    public AlbumLoader(Context context) {
        super(context);
    }

    public AlbumLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }




}
