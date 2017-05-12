package com.zlthnrtm.l_loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by arch on 4/15/17.
 */

public class MyLoader extends AsyncTaskLoader<Integer> {

    public MyLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Integer loadInBackground() {
        int randomInt = (int) Math.round(Math.random() * 100);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return randomInt;
    }

}
