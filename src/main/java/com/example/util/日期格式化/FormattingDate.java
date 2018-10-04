package com.example.util.日期格式化;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化
 *
 * @author yuwb@corp.21cn.com
 * @date 2018/10/4 10:46
 */
public class FormattingDate {
    private SimpleDateFormat sdf = null;

    /**
     * 2012-03-05
     */
    public String dateFormat() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd");
        return this.sdf.format(new Date());
    }

    /**
     * 2012-03-05 05:27:15:390
     */
    public String dateFormat1() {
        this.sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        return this.sdf.format(new Date());
    }

    /**
     * 2012年03月05日
     */
    public String dateNewFormat() {
        this.sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return this.sdf.format(new Date());
    }

    /**
     * 2012年03月05日05时26分19秒437毫秒
     */
    public String dateNewFormat1() {
        this.sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒SSS毫秒");
        return this.sdf.format(new Date());
    }
}
