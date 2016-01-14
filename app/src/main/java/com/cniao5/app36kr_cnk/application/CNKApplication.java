package com.cniao5.app36kr_cnk.application;

import android.app.Application;

/**
 * 当前类注释:全局Application类,作为全局数据的配置以及相关参数数据初始化工作
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class CNKApplication  extends Application{
    private static CNKApplication instance=null;
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
    }
    public static CNKApplication getInstance(){
        return instance;
    }
}
