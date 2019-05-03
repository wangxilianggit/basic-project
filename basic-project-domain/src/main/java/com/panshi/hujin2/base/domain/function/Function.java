package com.panshi.hujin2.base.domain.function;

/**
 * @author ZhangZhiHao 2018/7/31 17:47
 */
@FunctionalInterface
public interface Function<T1, T2> {

    void apply(T1 t1, T2 t2);
}