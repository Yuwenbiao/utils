package com.example.util.利用Stream处理文件;

/**
 * 地区类
 *
 * @author yuwb
 * @date 18-8-20 下午3:12
 */
public class District {
    /**
     * 名称
     */
    private String name;
    /**
     * 区号
     */
    private String areaCode;
    /**
     * 邮编
     */
    private String zipCode;

    public District(String name, String areaCode, String zipCode) {
        this.name = name;
        this.areaCode = areaCode;
        this.zipCode = zipCode;
    }

    public District() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
