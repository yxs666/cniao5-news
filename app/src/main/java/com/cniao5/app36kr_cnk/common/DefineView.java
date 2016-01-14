package com.cniao5.app36kr_cnk.common;

/**
 * 当前类注释:所有的Activity,Fragment可以实现这个接口，来进行一些公共的操作
 * ProjectName：App36Kr
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public interface DefineView {
	
   public void initView();  //初始化界面元素
   public void initValidata();  //初始化变量
   public void initListener();  //初始化监听器
   public void bindData();       //绑定数据
   
//   public void getPageData();// show page
//   public void getDataOk();  // data ok
//   public void getDataError();// data error
//   public void getDataRefresh();// data refresh
   
}
