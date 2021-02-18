package com.hewking.test.concurrent;

import java.util.List;

/**
 * @Classname SetObserver
 * @Description TODO
 * @Date 2021/2/17 5:19 下午
 * @Created by jianhao
 */
@FunctionalInterface
public interface SetObserver<E> {
    // Invoked when an element is added to the observable set
    void added(ObservableSet<E> set, E element);
}
