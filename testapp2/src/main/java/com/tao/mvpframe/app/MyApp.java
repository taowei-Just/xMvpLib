package com.tao.mvpframe.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import androidx.multidex.MultiDex;

import com.tao.logger.log.Logger;
import com.tao.mvpbaselibrary.app.crash.CrashHandler;
import com.tao.mvpbaselibrary.app.crash.OnCrashListener;
import com.tao.mvpbaselibrary.basic.utils.ToastUtil;
import com.tao.mvpbaselibrary.lib_http.retrofit.RequestHandler;
 
import com.tao.mvpframe.lekcanary.LeakCanaryWrapperImpl;
import com.tao.mvplibrary.app.BaseApplication;

 


public class MyApp extends BaseApplication {

    private static MyApp Application;
    private LeakCanaryWrapperImpl canaryWrapper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getExceptionHandler().init(this);
        CrashHandler.getExceptionHandler().setCrashListener(new MyCrashListener());
        CrashHandler.getExceptionHandler().setNeedCrashApp(true);
        Application = this;
    }

    public static MyApp getApplication() {
        return Application;
    }

    public static LeakCanaryWrapperImpl getCanaryWrapper(Context context) {
        MyApp leakApplication = (MyApp) context.getApplicationContext();
        return leakApplication.canaryWrapper;
    }

    @Override
    protected String configDefaultBaseUrl() {
        return null;
    }

    @Override
    protected RequestHandler configDefaultHandler() {
        return null;
    }

    private class MyCrashListener implements OnCrashListener {

        @Override
        public void onMainCrash(String strException, Throwable e) {
            Logger.e("onMainCrash",e);
            new Handler(Looper.myLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.shortShow("抱歉app遇到了一个问题");     
                }
            });
        }

        @Override
        public void onBackgroundCrash(String strCrashLog, Throwable e) {
            Logger.e("onBackgroundCrash",e);

        }
    }
}
