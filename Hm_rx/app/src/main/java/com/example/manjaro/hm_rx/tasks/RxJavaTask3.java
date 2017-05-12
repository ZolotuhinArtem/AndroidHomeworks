package com.example.manjaro.hm_rx.tasks;

import android.support.annotation.NonNull;

import com.example.manjaro.hm_rx.SimpleTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;

public class RxJavaTask3 {

    /**
     * TODO :
     * Sum all elements from observable and return observable with this single sum
     * <p>
     * Example:
     * Input stream (1, 2, 3, 4, 5)
     * Result stream (15)
     * <p>
     * Find suitable operator for this task using Google
     */
    @NonNull
    public static Observable<Integer> sum(@NonNull Observable<Integer> observable) {
        return MathObservable.sumInteger(observable)
                .compose(new SimpleTransformer<>());
    }

}
