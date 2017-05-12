package com.example.manjaro.hm_rx.tasks;

import android.support.annotation.NonNull;

import com.example.manjaro.hm_rx.SimpleTransformer;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaTask1 {

    /**
     * TODO : implement this method
     * <p>
     * This method takes list of strings and creates an observable of integers,
     * that represents length of strings which contains letter 'r' (or 'R')
     * <p>
     * Example:
     * Input list: ("Vasya", "Dima", "Artur", "Petya", "Roma")
     * Result stream: (5, 4)
     */
    @NonNull
    public static Observable<Integer> task1(@NonNull List<String> list) {
        return Observable.from(list)
                .compose(new SimpleTransformer<>())
                .filter(value -> value.toLowerCase().contains("r"))
                .flatMap(value -> {
                    return Observable.just(value.length());
                });
    }

}