package com.panshi.hujin2.base.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageFactory {

    private static MessageSource messageSource;

    /**
     * 获取国际化信息
     *
     * @param key 国际化key
     * @return
     */
    public static String getMsg(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * 获取国际化信息，带参数
     *
     * @param key
     * @param arg
     * @return
     */
    public static String getMsg(String key, String... arg) {
        Locale locale = LocaleContextHolder.getLocale();
        Object[] args = new Object[arg.length];
        for (int i = 0; i < arg.length; i++) {
            args[i] = arg[i];
        }
        return messageSource.getMessage(key, args, locale);
    }

    /**
     * 获取国际化信息
     *
     * @param key 国际化key
     * @return
     */
    public static String getMsg(String key ,Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * 获取国际化信息，带参数
     *
     * @param key
     * @param arg
     * @return
     */
    public static String getMsg(String key, Locale locale ,String... arg) {
        Object[] args = new Object[arg.length];
        for (int i = 0; i < arg.length; i++) {
            args[i] = arg[i];
        }
        return messageSource.getMessage(key, args, locale);
    }

    @Autowired(required = true)
    public void setMessageSource(MessageSource messageSource) {
        MessageFactory.messageSource = messageSource;
    }

}
