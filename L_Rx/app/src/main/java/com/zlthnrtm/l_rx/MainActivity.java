package com.zlthnrtm.l_rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observables.MathObservable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener((view) -> {
            if (view.getId() == R.id.tv) {
                Toast.makeText(MainActivity.this, "TV", Toast.LENGTH_LONG).show();
            }
        });

//        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
//
//        observable.filter(value -> value < 5)
//                .filter(value -> value > 2)
//                .map(value -> value * value)
//                .map(value -> value * 2)
//                .take(5)
//                .subscribe(value -> System.out.println(value));

        List<String> names = new ArrayList<>();
        names.add("SS");
        names.add("Borya");
        names.add("Simon");
        names.add("Dozelkfa;");
        names.add("Sss");
        names.add("asdfq");
        names.add("adfffffffff");

        method(names, "s").subscribe(System.out::println);


        Observable<Integer> obs = Observable.just(1, 2, 3);

        sum(obs).subscribe(System.out::println);
    }

    private Observable<Integer> method(List<String> strs, String str){
        return Observable.from(strs)
                .map(String::toLowerCase)
                .flatMap(name -> {
                    if (name.contains(str.toLowerCase())) {
                        return Observable.just(1);
                    } else {
                        return Observable.just(0);
                    }
                });
    }

    public Observable<Integer> sum(Observable<Integer> list) {
        return MathObservable.sumInteger(list);
    }
}
