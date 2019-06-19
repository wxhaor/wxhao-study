package com.wxhao.study.pattern.singleton;

import com.wxhao.study.pattern.singleton.lazy.LazySimpleSingleton;


public class ExectorThread implements Runnable {
    @Override
    public void run() {
        LazySimpleSingleton singleton = LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}
