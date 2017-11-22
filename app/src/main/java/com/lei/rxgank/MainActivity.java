package com.lei.rxgank;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lei.rxgank.base.BaseActivity;
import com.lei.rxgank.bean.ResultsBean;
import com.lei.rxgank.loder.IndexLoder;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    private IndexLoder indexLoder;
    private ResultsBean resultsBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load(1);
    }

    private void load(int page) {
        indexLoder = new IndexLoder();
        Subscription subscription = indexLoder.getFuli(page).subscribe(new Action1<List<ResultsBean>>() {
            @Override
            public void call(List<ResultsBean> resultsBeanList) {
                for (int i = 0; i < resultsBeanList.size(); i++)
                    System.out.println(resultsBeanList.get(i).getUrl());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println(throwable.toString());
            }
        });
        sCompositeSubscription.add(subscription);
    }
}
