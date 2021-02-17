package com.hewking.demo.basic;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

/**
 * @Classname NumberUtil
 * @Description TODO
 * @Date 2021/2/10 5:17 下午
 * @Created by jianhao
 */
public class NumberUtil {

    private NumberUtil(){
        throw new IllegalArgumentException("不允许创建对象");
    }

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c){
        return c.stream().max(Comparator.naturalOrder());
    }

}
