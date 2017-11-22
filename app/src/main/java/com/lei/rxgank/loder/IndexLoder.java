package com.lei.rxgank.loder;

import com.google.gson.Gson;
import com.lei.rxgank.base.BaseResp;
import com.lei.rxgank.bean.ResultsBean;
import com.lei.rxgank.http.ObjectLoader;
import com.lei.rxgank.http.RetrofitServiceManager;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lei on 2017/11/22.
 */

public class IndexLoder extends ObjectLoader {

    private IndexPageService indexPageService;

    public IndexLoder() {
        indexPageService = RetrofitServiceManager.getInstance().create(IndexPageService.class);
    }

    public Observable<List<ResultsBean>> getFuli(int page) {
        return observe(indexPageService.getFuLiData(page).map(new Func1<BaseResp, List<ResultsBean>>() {
            @Override
            public List<ResultsBean> call(BaseResp resp) {
                return resp.results;
            }
        }));
    }

    public interface IndexPageService {
        @GET("福利/10/{page}")
        Observable<BaseResp> getFuLiData(@Path("page") int i);
    }

}
