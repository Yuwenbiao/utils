package com.example.util.利用Stream处理文件;

import java.util.List;

/**
 * 城市类
 *
 * @author yuwb
 * @date 18-8-20 下午3:14
 */
public class City extends District {

    public City(String name, String areaCode, String zipCode, List<District> districts) {
        super(name, areaCode, zipCode);
        this.districts = districts;
    }

    public City(String name, String areaCode, String zipCode) {
        super(name, areaCode, zipCode);
    }

    public City(List<District> districts) {
        this.districts = districts;
    }

    public City() {
    }

    /**
     * 该城市所管辖行政区列表
     */
    private List<District> districts;

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
