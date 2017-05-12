package com.example.manjaro.hm_rx;

import java.util.List;

/**
 * Created by manjaro on 12.05.17.
 */

public class ListUtils<T> {

    private List<T> list;

    public ListUtils(List<T> list) {
        this.list = list;
    }

    public ListUtils<T> add(T ... obj){
        for (int i = 0; i < obj.length; i++) {
            this.list.add(obj[i]);
        }
        return this;
    }

    public ListUtils<T> remove(T ... obj){
        for (int i = 0; i < obj.length; i++) {
            this.list.remove(obj);
        }
        return this;
    }

    public ListUtils<T> clear(){
        this.list.clear();
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public ListUtils<T> setList(List<T> list) {
        this.list = list;
        return this;
    }


}
