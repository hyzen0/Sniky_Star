package com.joad.sinkystar.Utility;

import android.content.Context;

import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class videoCache {

    public static SimpleCache simpleCache;

    public static SimpleCache getInstance(Context context, long maxCacheSize) {

        LeastRecentlyUsedCacheEvictor evictor = new LeastRecentlyUsedCacheEvictor(maxCacheSize);
        DatabaseProvider databaseProvider = new ExoDatabaseProvider(context);

        if (simpleCache != null) {
            return simpleCache;
        } else {
            simpleCache = new SimpleCache(new File(context.getCacheDir(), "media"), evictor, databaseProvider);

            return simpleCache;
        }


    }
}
