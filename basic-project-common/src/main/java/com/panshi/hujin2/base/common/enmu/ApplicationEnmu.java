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

    VI_CASH_DOG(21,"CashDog","vi"),
    INA_WEB_GAME(22,"webGame","id_ID"),// 印尼
    INA_CASH_KANGAROO(23,"CashKangaroo","id_ID"),// 印尼-cashKangaroo
    INA_CASH_KANGAROO_2(24,"CashKangaroo","id_ID"),// 印尼-cashKangaroo2
    INA_CASH_KANGAROO_DEXTER(25,"CashKangaroo","id_ID"),// 印尼 -cashKangarooDexter

    VI_CASH_DOG_NEVERSOLDOUT(26,"CashDog","vi"),    // 越南-cashDogNeverSoldout

    VI_CASH_DOG_GOODDAY(27,"CashDog","vi"),    // 越南-cashDogGoodDay
    VI_CASH_CAT(28,"CashCat","vi"),    // 越南
    VI_IN_CASHCAT(29,"CashCat","vi"),    // 越南
    INA_ATTRACTIVE_KANGAROOCASH(30,"CashKangaroo","id_ID"),    // 印尼 com.attractive.kangaroocash
    INA_KAS_KANGAROO(31,"CashKangaroo","id_ID"),    // 印尼 com.KasKangaroo.in
    VI_CASH_CAT_32(32,"CashCat","vi"),

    INA_KAS_INS(33,"CashKangaroo","id_ID"),    // 印尼 com.Kas.ins
    INA_KASROO(34,"CashKangaroo","id_ID"),    // 印尼 com.Kas.ins



    // 印尼马甲包
    NEW_INA_CASH_KLICK(200,"CashKlick","id_ID"),    // 印尼 com.cashklick.wqas
    NEW_INA_CASH_MAS(201,"MasCash","id_ID"),    // 印尼 com.mascash.unndt
    NEW_INA_CASH_KLICK_TASK(204,"CashKlick","id_ID"),    // 印尼 com.task.wqas
    NEW_INA_CASH_MAS_TEXT(203,"MasCash","id_ID"),    // 印尼  com.text.unndt

    NEW_INA_CASH_MASTEXT_UNNDT(205,"MasCash","id_ID"),    // 印尼  com.mastext.unndt

    NEW_INA_CASH_TASK_WQAS(206,"CashKlick","id_ID"),    // 印尼  com.cashtask.wqas


    SUPER_SMS(101,"SUPER_SMSSUPER_SMS","id_ID"),

    // 印尼马甲包
    INA_CASH_YINNI_IN(500,"cashKangaroo","id_ID"),    // 印尼 com.yinni.in


    INA_CASH_IN_ROO (501,"DompetEmas","id_ID"),    //印尼 com.in.roo

    //INA_CASH_IN_ROO (501,"cashKangaroo","id_ID"),    //印尼 com.in.roo

    // 印尼马甲包
    INA_CASH_DF(502,"cashKangaroo","id_ID"),    // 印尼 com.app.df.in
    INA_CASH_DCM_MIAO(503,"DompetEmas","id_ID"),    // 印尼 com.app.dcm.miao
    INA_APPAPK_CASH_DCM_MIAO(505,"DompetEmas","id_ID"),    // 印尼 com.appapk.dcm.miao
    INA_XUAN_CASH_DCM_MIAO(509,"DompetEmas","id_ID"),    // 印尼 com.xuan.dcm.miao
    INA_XM_DM(510,"dompetku","id_ID"),    // 印尼 com.xm.dm
    INA_XM_DOMPETNMAN(511,"dompetku","id_ID"),    // 印尼 com.xuanmiao.dompetaman

    // 越南马甲包
    VI_CASH_ANDROID_CASHCAT(600,"MasCash","id_ID"),    // 印尼  com.android.cashcat
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
