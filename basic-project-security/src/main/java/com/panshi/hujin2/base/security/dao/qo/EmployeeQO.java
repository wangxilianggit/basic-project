package com.panshi.hujin2.base.security.dao.qo;

import com.panshi.hujin2.base.domain.qo.BaseQO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZhangZhiHao 2018/6/28 9:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeQO extends BaseQO {

    /**
     * 描述
     */
    private String description;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 住址
     */
    private String address;

    /**
     * 头像地址
     */
    private String headPortraitUrl;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 状态
     */
    private Integer status;

}
