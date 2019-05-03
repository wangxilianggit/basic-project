package com.panshi.hujin2.base.service.utils;

import com.panshi.hujin2.base.service.Context;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * rpc服务上下文工具类
 *
 */
public class ContextUtils {

    /**
     * 获取默认上下文
     *
     * @return 系统默认语言上下文
     */
    public static Context getDefaultContext() {
        Context context = new Context();
        context.setLocale(LocaleContextHolder.getLocale());
        return context;
    }

    /**
     * 获取巴西地区的上下文
     *
     * @return 巴西语言上下文
     */
    public static Context getBrazilContext() {
        Context context = new Context();
        context.setLocale(new Locale("pt", "BR"));
        return context;
    }

    /**
     * 获取墨西哥地区的上下文
     *
     * @return 墨西哥语言上下文
     */
    public static Context getMexicoContext() {
        Context context = new Context();
        context.setLocale(new Locale("es", "MX"));
        return context;
    }

}
