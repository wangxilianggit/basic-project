package com.panshi.hujin2.base.common.enmu;

import java.util.Objects;

/**
 * create by shenjiankang on 2018/7/2 19:51
 *
 * 应用程序枚举
 */
public enum ApplicationEnmu {

    PAN_GUAN_JIA(1,"盘管家","pt_BR"),//葡萄牙文(巴西)
    WU_YOU_DAI(2,"无忧贷","es_MX"),//西班牙文(墨西哥)
    BR_Eloan(3,"巴西现金贷","pt_BR"),//葡萄牙文(巴西)
    BR_MY_LOAN(4,"MyLoan","pt_BR"),
    BR_FLASH_LOAN(5,"FlashLoan","pt_BR"),
    BR_SIMPLE_LOAN(6,"SimpleLoan","pt_BR"),

    MX_I_LOAN(7,"iLoan","es_MX"),
    MX_MONEYR(8,"MoneyR","es_MX"),
    MX_M_LOAN(9,"mLoan","es_MX"),
    MX_Y_LOAN(10,"yLoan","es_MX"),
    MX_U_LOAN(11,"uLoan","es_MX"),
    MX_HI_LOAN(12,"HiLoan","es_MX"),

    BR_WOWLOAN(13,"WOWLoan","pt_BR"),
    BR_FREELOAN(14,"FreeLoan","pt_BR"),

    VI_CASH_DOG(21,"cashDog","vi"),
    INA_WEB_GAME(22,"webGame","id_ID"),// 印尼/印尼
    INA_CASH_KANGAROO(23,"cashKangaroo","id_ID"),// 印尼/印尼
    INA_CASH_KANGAROO_2(24,"cashKangaroo2","id_ID"),// 印尼/印尼
    INA_CASH_KANGAROO_DEXTER(25,"cashKangarooDexter","id_ID"),// 印尼/印尼

    ;

    private Integer code;
    private String desc;
    private String i18n;

    ApplicationEnmu(Integer code, String desc, String i18n) {
        this.code = code;
        this.desc = desc;
        this.i18n = i18n;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public static ApplicationEnmu getByCode(Integer code) {
        for (ApplicationEnmu t : values()) {
            if (Objects.equals(t.getCode(), code))
                return t;
        }
        return null;
    }

    public static ApplicationEnmu getByDesc(String desc) {
        for (ApplicationEnmu t : values()) {
            if (t.getDesc().equals(desc))
                return t;
        }
        return null;
    }
}
