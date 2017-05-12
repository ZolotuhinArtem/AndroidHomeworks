package com.example.arch.hw1_mvvm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by arch on 3/8/17.
 */

public class CollectionsTools {

    public static <T> List<T> setToList(Set<T> set){
        ArrayList<T> list = new ArrayList<>(set.size());

        for(T i: set) {
            list.add(i);
        }

        return list;
    }
}
