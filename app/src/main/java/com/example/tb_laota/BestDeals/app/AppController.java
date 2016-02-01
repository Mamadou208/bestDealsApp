package com.example.tb_laota.BestDeals.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.tb_laota.BestDeals.BitmapCache;

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppController getApplication() {
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue, new BitmapCache());
        }
        return this.imageLoader;
    }

    public <T> void addToRequesQueue(Request<T> request, String tag) {
        request.setTag((TextUtils.isEmpty(tag) ? TAG : tag));
        getRequestQueue().add(request);
    }

    public <T> void addToRequesQueue(Request<T> request) {
        request.setTag(AppController.class.getName());
        getRequestQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
