package com.lei.rxgank.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by lei on 2017/11/22.
 */

public class BaseActivity extends Activity {
    public CompositeSubscription sCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (sCompositeSubscription == null || sCompositeSubscription.isUnsubscribed()) {
            sCompositeSubscription = new CompositeSubscription();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sCompositeSubscription != null)
            sCompositeSubscription.unsubscribe();
    }
}
