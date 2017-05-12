package com.example.arch.hw2_usersfromservice.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by arch on 3/12/17.
 */

public class CollectionTools {

    public static <T> ArrayList<T> listToArrayList(@NonNull List<T> list) {
        ArrayList<T> result = new ArrayList<>(list.size());
        for(T i: list) {
            result.add(i);
        }
        return result;
    }

    public static <T> List<T> changeSetToList(Set<T> set){
        ArrayList<T> list = new ArrayList<>(set.size());

        for(T i: set) {
            list.add(i);
        }

        return list;
    }

    public static <T> Set<T> changeListToSet(List<T> list){
        HashSet<T> set = new HashSet<>(list.size());

        for(T i: list) {
            set.add(i);
        }

        return set;
    }
}
