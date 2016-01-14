package com.cniao5.app36kr_cnk.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 当前类注释:OKHttpManager_Test 工具类封装
 * ProjectName：OkhttpTest
 * Author:<a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，
 * 打造极致课程，是菜鸟窝不变的承诺
 */
public class OkhttpManager {
    private static final String FILE_PREFIX="CNIAO5_";
    //OKhttp对象实例
    private OkHttpClient client;
    private static OkhttpManager okhttpManager;
    private Handler handler;

    private static OkhttpManager getInstance(){
        if(okhttpManager==null){
            okhttpManager=new OkhttpManager();
        }
        return  okhttpManager;
    }
    private OkhttpManager(){
        client=new OkHttpClient();
        handler=new Handler(Looper.getMainLooper());
    }
    /**
     * 同步Get请求方法
     * @param url
     * @return
     * @throws IOException
     */
    private Response p_getSync(String url)throws  IOException{
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 进行发送同步GET请求，并且返回String类型数据
     * @param url
     * @return
     * @throws IOException
     */
    private String p_getSyncAsString(String url)throws  IOException{
        Response response=p_getSync(url);
        return response.body().string();
    }

    /**
     * 进行GET异步请求
     * @param url
     * @param callBack
     */
    private void p_getAsync(String url, final DataCallBack callBack){
        final Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverFailure(request, e, callBack);
            }
            /**
             * 异步返回数据
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    deliverSuccess(response.body().string(), callBack);
                }catch (IOException e){
                    deliverFailure(request,e,callBack);
                }
            }
        });
    }
    /**
     *
     * @param url
     * @param params
     * @param callBack
     */
    private void p_postAsyncParams(String url,Map<String,String> params,final DataCallBack callBack){
        RequestBody requestBody=null;
        if(params==null){
            params=new HashMap<String,String>();
        }
            FormEncodingBuilder builder=new FormEncodingBuilder();
           for (Map.Entry<String,String> entry:params.entrySet()) {
                  String key=entry.getKey().toString();
                  String value=null;
                  if(entry.getValue()==null){
                      value="";
                  }else {
                      value=entry.getValue();
                  }
               builder.add(key,value);
           }
           requestBody=builder.build();
           final Request request=new Request.Builder().url(url).post(requestBody).build();
           client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    deliverSuccess(response.body().string(), callBack);
                }catch (IOException e){
                    deliverFailure(request,e,callBack);
                }
            }
        });
    }
    /**
     * 进行异步下载文件
     * @param url  文件的地址
     * @param destDir  文件存储的绝对路径
     * @param callBack
     */
    private void p_downloadAsync(final String url, final String destDir,final DataCallBack callBack){
        final Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                  deliverFailure(request,e,callBack);
            }
            @Override
            public void onResponse(Response response) throws IOException {
                   //开始进行写入文件
                InputStream inputStream=null;
                FileOutputStream fileOutputStream=null;
                try {
                    inputStream=response.body().byteStream();
                    byte[] buffer=new byte[2048];
                    int len=0;
                    File file= new File(destDir,getFileName(url));
                    fileOutputStream=new FileOutputStream(file);
                    while((len=inputStream.read(buffer))!=-1){
                        fileOutputStream.write(buffer,0,len);
                    }
                    fileOutputStream.flush();
                    deliverSuccess(file.getAbsolutePath(),callBack);
                }catch (IOException e)
                {
                     deliverFailure(request,e,callBack);
                }finally {
                    if(fileOutputStream!=null){
                        fileOutputStream.close();
                    }
                    if(inputStream!=null){
                        inputStream.close();;
                    }
                }
            }
        });
    }

    /**
     * 根据文件URL地址获取文件的路径文件名
     * @param pUrl
     * @return
     */
    private String getFileName(String pUrl){
        int separatorIndex = pUrl.lastIndexOf("/");
        String path=(separatorIndex < 0) ? pUrl : pUrl.substring(separatorIndex + 1, pUrl.length());
        return FILE_PREFIX+path;
    }
    //*************************数据请求成功或者失败分发方法**********************
    /**
     * 进行分发请求失败的数据情况
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverFailure(final Request request,final IOException e, final DataCallBack callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 请求分发请求成功的数据情况
     * @param result
     * @param callBack
     */
    private void deliverSuccess(final String result, final DataCallBack callBack){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestSuccess(result);
                }
            }
        });
    }

    //*************对外公布的方法********************

    /**
     * 根据请求地址，进行发起GET同步请求,并且返回Response信息
     * @param url
     * @return
     * @throws IOException
     */
    public static Response getSync(String url) throws IOException{
        return  getInstance().p_getSync(url);
    }

    /**
     * 根据请求地址，进行发起GET同步请求,并且返回String信息
     * @param url
     * @return
     * @throws IOException
     */
    public static String getSyncAsString(String url)throws  IOException{
        return getInstance().p_getSyncAsString(url);
    }

    /**
     * 进行GET异步请求数据
     * @param url
     * @param callBack
     */
    public static void getAsync(String url,DataCallBack callBack){
         getInstance().p_getAsync(url, callBack);
    }

    /**
     * 进行POST异步请求数据
     * @param url
     * @param params  需要POST的数据
     * @param callBack
     */
    public static void postAsyncParams(String url,Map<String,String> params,DataCallBack callBack){
           getInstance().p_postAsyncParams(url, params, callBack);
    }

    /**
     * 进行异步下载文件
     * @param url  文件地址
     * @param destDir  存入本地的路径
     * @param callBack 下载成功回调
     */
    public static void downloadAsync(String url,String destDir,DataCallBack callBack){
            getInstance().p_downloadAsync(url,destDir,callBack);
    }

    //*************数据回调接口************************
    public interface DataCallBack{
        /**
         * 请求失败
         * @param request
         */
        void requestFailure(Request request, Exception e);

        /**
         * 请求成功
         * @param result
         */
        void requestSuccess(String result);
    }
}
