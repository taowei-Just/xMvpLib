package com.tao.xmvplibapp;

import com.tao.mvpbaselibrary.lib_http.retrofit.RequestHandler;
import com.tao.mvplibrary.app.BaseApplication;

public class App extends BaseApplication {
    @Override
    protected String configDefaultBaseUrl() {
        return null;
    }

    @Override
    protected RequestHandler configDefaultHandler() {
        return null;
    }
}
