package com.panshi.hujin2.base.common.enmu;

/**
 * @author ZhangZH 2017/12/1 15:53
 * 平台支持的银行账户枚举
 */
public enum BankType {

    NONG_YE_BANK("01030000", "农业银行"),
    JIAO_TONG_BANK("03010000", "交通银行"),
    GONG_SHANG_BANK("01020000", "工商银行"),
    YOU_CHU_BANK("01000000", "邮储银行"),
    PU_FA_BANK("03100000", "浦发银行"),
    GUANG_FA_BANK("03060000", "广发银行"),
    PING_AN_BANK("03070000", "平安银行"),
    ZHAO_SHANG_BANK("03080000", "招商银行"),
    JIAN_SHE_BANK("01050000", "建设银行"),
    GUANG_DA_BANK("03030000", "光大银行"),
    ZHONG_XIN_BANK("03020000", "中信银行"),
    HUA_XIA_BANK("03040000", "华夏银行"),
    HANG_ZHOU_BANK("04233310", "杭州银行"),
    BEI_JING_BANK("04031000", "北京银行"),
    SHANG_HAI_BANK("04012900", "上海银行"),
    GUANG_ZHOU_BANK("64135810", "广州银行"),
    NING_BO_BANK("04083320", "宁波银行"),
    XING_YE_BANK("03090000", "兴业银行"),
    ZHONG_GUO_BANK("01040000", "中国银行");

    private String type;
    private String desc;

    BankType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BankType getBankByType(String type){
        for (BankType bankType : values()) {
            if (bankType.getType().equals(type)) {
                return bankType;
            }
        }
        return null;
    }

    public static BankType getBankByDesc(String desc){
        for (BankType bankType : values()) {
            if (bankType.getDesc().equals(desc)) {
                return bankType;
            }
        }
        return null;
    }
}
