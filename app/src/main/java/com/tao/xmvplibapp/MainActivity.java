package com.tao.xmvplibapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tao.logger.log.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.setEnable(true);
        Logger.setPriority(Logger.MIN_LOG_PRIORITY);
        Logger.setTag("[mayapp]");
        
        log();
    }

    private void log() {
        Logger.e(" Logger.e.msg", new Throwable(" Logger.e"));
        
        Logger.e(new Throwable(" Logger.e"));
        Logger.e(" Logger.e.msg");
//
//        Logger.i(" Logger.i.msg");
//        Logger.iTag(" Logger.i.tag" ," Logger.i.msg");
//
//
//        Logger.d(" Logger.d.msg");
//        Logger.dTag(" Logger.d.tag" ," Logger.d.msg");
//
//        Logger.v(" Logger.v.msg");
//        Logger.vTag(" Logger.v.tag" ," Logger.v.msg");
//
//        Logger.w(" Logger.w.msg");
//        Logger.wTag(" Logger.w.tag" ," Logger.w.msg");
//
//        Logger.wtf(" Logger.wf.msg");
//        Logger.wtfTag(" Logger.wf.tag" ," Logger.wf.msg");
    }

    public void logger(View view) {
        log();
    }
}