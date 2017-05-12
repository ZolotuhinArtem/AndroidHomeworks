package com.example.manjaro.hm_rx.tasks;

import android.support.annotation.NonNull;

import com.example.manjaro.hm_rx.SimpleTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaTask2 {

    /**
     * TODO:
     * <p>
     * Method takes observable of strings as a parameter
     * <p>
     * Supply all elements until you meet word END in the stream (END word should be excluded)
     * After that remove all repeated values from the stream
     */
    @NonNull
    public static Observable<String> task2(@NonNull Observable<String> observable) {
        return observable.compose(new SimpleTransformer<>())
                .takeWhile(value -> !value.equals("END"))
                .distinct();
    }

}
