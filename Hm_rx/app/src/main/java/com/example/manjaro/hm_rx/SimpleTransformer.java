package com.example.manjaro.hm_rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by manjaro on 12.05.17.
 */

public class SimpleTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> obs) {
        return obs.doOnSubscribe(() -> {
                    System.out.println("--------------------------------------------");
                });
    }
}
