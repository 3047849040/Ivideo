package com.example.ivideo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }

    public static Context getContext(){
        return context;
    }
}
