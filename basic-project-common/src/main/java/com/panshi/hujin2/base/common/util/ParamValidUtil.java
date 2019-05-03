package com.panshi.hujin2.base.common.util;

import com.panshi.hujin2.base.common.HotolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by xueyongfeng on 2017/6/20.
 */

public class ParamValidUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamValidUtil.class);


    public static void validValue(Integer param) {
        if (param == null || param < 1) {
            LOGGER.error("*SERVICE_ERROR* param is {}", param);
            throw new HotolException(ResultCodes.DATA_ERROR);
        }
    }

    public static void validValue(Long param) {
        if (param == null || param < 1) {
            LOGGER.error("*SERVICE_ERROR* param is {}", param);
            throw new HotolException(ResultCodes.DATA_ERROR);
        }
    }

    public static void validParamValue(Integer param) {
        if (param == null || param < 1) {
            LOGGER.error("*SERVICE_ERROR* param is {}", param);
            throw new HotolException(ResultCodes.PARAMETER_INVALID);
        }
    }

    public static void validParamValue(String param) {
        if (StringUtils.isEmpty(param)) {
            LOGGER.error("*SERVICE_ERROR* param is {}", param);
            throw new HotolException(ResultCodes.PARAMETER_INVALID);
        }
    }

    public static void validValue(String param) {
        if (StringUtils.isEmpty(param)) {
            LOGGER.error("*SERVICE_ERROR* param is {}", param);
            throw new HotolException(ResultCodes.DATA_ERROR);
        }
    }

    public static void validStatus(Integer status) {
        if (status == null || status < 0 || status > 1) {
            LOGGER.error("*SERVICE_ERROR* status is {} out of defined", status);
            throw new HotolException(ResultCodes.DATA_ERROR);
        }
    }

    public static void validStatusParam(Integer status) {
        if (status == null || status < 0 || status > 1) {
            LOGGER.error("*SERVICE_ERROR* param status is {} out of defined", status);
            throw new HotolException(ResultCodes.PARAMETER_INVALID);
        }
    }

    public static void validObjectNull(Object o) {
        if (o == null) {
            LOGGER.error("*SERVICE_ERROR* param is null");
            throw new HotolException(ResultCodes.DATA_ERROR);
        }
    }


    public static boolean validListNullAndSize0(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
