package com.example.manjaro.hm_rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.manjaro.hm_rx.tasks.RxJavaTask1;
import com.example.manjaro.hm_rx.tasks.RxJavaTask2;
import com.example.manjaro.hm_rx.tasks.RxJavaTask3;
import com.example.manjaro.hm_rx.tasks.RxJavaTask4;
import com.example.manjaro.hm_rx.tasks.RxJavaTask5;
import com.example.manjaro.hm_rx.tasks.RxJavaTask6;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListUtils<String> listStr = new ListUtils<>(new ArrayList<>());

        listStr.add("Vasya", "Dima", "Artur", "Petya", "Roma");
        RxJavaTask1.task1(listStr.getList()).subscribe(System.out::println);

        RxJavaTask2.task2(Observable.just("Dima", "Vasya", "Dima", "Roma", "Peter", "END", "Egor"))
                .subscribe(System.out::println);

        RxJavaTask3.sum(Observable.just(12, 35, 3))
                .subscribe(System.out::println);

        RxJavaTask4.task4(Observable.just(false), Observable.just(32, 45, 15), Observable.just(64, 210, 90))
                .subscribe(System.out::println, throwable -> {
                    System.out.println(throwable.getMessage());
                });

        RxJavaTask5.gcdsObservable(Observable.just(100, 17, 63), Observable.just(15, 89, 27))
                .subscribe(System.out::println);

        RxJavaTask6.task6Observable()
                .subscribe(System.out::println);
    }
}
