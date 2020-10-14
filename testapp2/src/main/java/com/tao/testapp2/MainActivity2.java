package com.tao.testapp2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.view.View;

import androidx.lifecycle.Lifecycle;

import com.tao.mvpbaselibrary.lekcanary.LeakCanaryHelper;
import com.tao.mvpbaselibrary.mvp.base.BaseObserver;
import com.tao.mvpframe.test.contract.MainActivtyContract;
import com.tao.mvpframe.test.presenter.MainActivityPresent;
import com.tao.mvplibrary.mvp.IView;
import com.tao.mvplibrary.mvp.base.BaseActivity;
import com.tao.mvplibrary.utils.RxUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


public class MainActivity2 extends BaseActivity<MainActivityPresent> implements MainActivtyContract.IMainActivtyView<MainActivityPresent> {

    @Override
    public void beforeSetContentView() {
        noActionBar();
//        fullScreen();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("AutoDispose")
    @Override
    public void initData() {
        LeakCanaryHelper.getLeakCanary(this).watch(this);
        
        RxPermissions rxPermissions = new RxPermissions(this);
        RxUtils.toSubscribe( rxPermissions.requestEach(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                new BaseObserver<Permission>(){
            @Override
            protected void accept(Permission permission) {
                if (permission.granted) {
                    // 用户允许权限
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户单次拒绝权限                    
                } else {
                    // 用户拒绝权限并不再询问
                }
            }
        } ,getLifecycle());

        Testleakcancry();
        
    }

    private void Testleakcancry() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void test(View view) {
        try {
            getP().test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post(View view) {
        try {
            getP().testPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pushFiles(View view) {
        try {
            getP().testPostFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void switchui(View view) {
        try {
            getP().switchUi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test001(View view) {
        try {
            getP().test001();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } public void test002(View view) {
        try {
            getP().test002();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected IView getAttachView() {
        return new Myview();
    }

    class Myview implements IView<MainActivityPresent> {

        @Override
        public MainActivityPresent getP() throws Exception {
            return MainActivity2.this.getP();
        }

        @Override
        public MainActivityPresent getP(IView view) throws Exception {
            return MainActivity2.this.getP(view);
        }

        @Override
        public Lifecycle getLifecycle() {
            return MainActivity2.this.getLifecycle();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

