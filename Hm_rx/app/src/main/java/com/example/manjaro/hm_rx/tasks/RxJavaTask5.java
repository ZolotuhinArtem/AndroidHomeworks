package com.example.manjaro.hm_rx.tasks;

import android.support.annotation.NonNull;

import com.example.manjaro.hm_rx.SimpleTransformer;

import java.math.BigInteger;

import rx.Observable;

public class RxJavaTask5 {

    /**
     * TODO : you have two streams of integers of equal length
     * <p>
     * Return one stream of the same length with GCDs
     * of the original streams values
     * <p>
     * Example:
     * Stream 1 (100, 17, 63)
     * Stream 2 (15, 89, 27)
     * Result stream (5, 1, 9)
     * <p>
     * You can use {@link BigInteger#gcd(BigInteger)}
     */
    @NonNull
    public static Observable<Integer> gcdsObservable(@NonNull Observable<Integer> first,
                                                     @NonNull Observable<Integer> second) {
        return Observable.zip(first, second, (value1, value2) -> {
            return gcd(value1, value2);
        }).compose(new SimpleTransformer<>());
    }

    private static Integer gcd(Integer a, Integer b) {
        while ((a != 0) && (b != 0)) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

}
