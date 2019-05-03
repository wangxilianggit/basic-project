package com.panshi.hujin2.base.domain.result;

import com.panshi.hujin2.base.common.util.DozerUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author ZhangZhiHao 2018/7/18 14:37
 */
public class BasicResultUtils {

    /**
     * 将BasicResult中的数据类型转换为另一种类型
     *
     * @param result 源数据
     * @param target 目标类型Class
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> BasicResult<T> transformObject(BasicResult<S> result, Class<T> target) {
        if (result.getSuccess()) {
            S source = result.getData();
            if (source != null) {
                return BasicResult.ok(DozerUtils.convert(source, target));
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将BasicResult中的数据类型转换为另一种类型
     *
     * @param result   源数据
     * @param target   目标类型Class
     * @param <S>      源类型
     * @param <T>      目标类型
     * @param consumer 对象转换完成后需要执行的函数
     */
    public static <S, T> BasicResult<T> transformObject(BasicResult<S> result, Class<T> target, Consumer<T> consumer) {
        if (result.getSuccess()) {
            S source = result.getData();
            if (source != null) {
                T data = DozerUtils.convert(source, target);
                consumer.accept(data);
                return BasicResult.ok(data);
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将BasicResult中的数据类型转换为另一种类型
     *
     * @param result   源数据
     * @param target   目标类型Class
     * @param <S>      源类型
     * @param <T>      目标类型
     * @param consumer 要执行的函数
     * @param u        执行函数所需参数
     */
    public static <S, T, U> BasicResult<T> transformObject(BasicResult<S> result, Class<T> target, BiConsumer<T, U> consumer, U u) {
        if (result.getSuccess()) {
            S source = result.getData();
            if (source != null) {
                T data = DozerUtils.convert(source, target);
                consumer.accept(data, u);
                return BasicResult.ok(data);
            }
            return BasicResult.ok();
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将BasicResult中的数据集合类型转换为另一种类型
     *
     * @param result 源数据
     * @param target 目标类型Class
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> BasicResult<List<T>> transformList(BasicResult<List<S>> result, Class<T> target) {
        if (result.getSuccess()) {
            List<S> list = result.getData();
            if (CollectionUtils.isNotEmpty(list)) {
                if (result.getPage() == null) {
                    return BasicResult.ok(DozerUtils.convertList(list, target));
                }
                return BasicResult.ok(DozerUtils.convertList(list, target), result.getPage());
            }
            return BasicResult.ok(Collections.emptyList());
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将BasicResult中的数据集合类型转换为另一种类型
     *
     * @param result 源数据
     * @param target 目标类型Class
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> BasicResult<List<T>> transformList(BasicResult<List<S>> result, Class<T> target, Consumer<T> consumer) {
        if (result.getSuccess()) {
            List<S> list = result.getData();
            if (CollectionUtils.isNotEmpty(list)) {
                List<T> data = DozerUtils.convertList(list, target);
                data.forEach(consumer);
                if (result.getPage() == null) {
                    return BasicResult.ok(data);
                }
                return BasicResult.ok(data, result.getPage());
            }
            return BasicResult.ok(Collections.emptyList());
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将BasicResult中的数据集合类型转换为另一种类型
     *
     * @param result 源数据
     * @param target 目标类型Class
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T, U> BasicResult<List<T>> transformList(BasicResult<List<S>> result, Class<T> target, BiConsumer<T, U> consumer, U u) {
        if (result.getSuccess()) {
            List<S> list = result.getData();
            if (CollectionUtils.isNotEmpty(list)) {
                List<T> data = DozerUtils.convertList(list, target);
                data.forEach(datum -> consumer.accept(datum, u));
                if (result.getPage() == null) {
                    return BasicResult.ok(data);
                }
                return BasicResult.ok(data, result.getPage());
            }
            return BasicResult.ok(Collections.emptyList());
        }
        return BasicResult.error(result.getCode(), result.getMessage());
    }

    /**
     * 将optional中包裹的值取出来放入BasicResult中
     *
     * @param <T> 值的类型
     */
    public static <T> BasicResult<T> optTransformResult(Optional<T> optional) {
        return optional.map(BasicResult::ok).orElseGet(BasicResult::ok);
    }
}
