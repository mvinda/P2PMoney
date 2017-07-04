package p2pmomeny.p2pmomeny.common;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-04-06.
 */
public class UIUtils {
    public  static View getXmlView(int  layoutId){
        return  View.inflate(getContent(),layoutId,null);
    }
    public  static  int getColor(int colorId){
        return  getContent().getResources().getColor(colorId);
    }
    public  static Context getContent(){
       return MyAppLication.context;
    }
    public  static Handler gethandler(){
        return  MyAppLication.handler;
    }

    public static int dp2px(int dp) {
        float density = getContent().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }


    public static int px2dp(int px) {
        float density = getContent().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static String[] getStringArr(int arrId) {
        return getContent().getResources().getStringArray(arrId);
    }


    /**
     * 保证runnable对象的run方法是运行在主线程当中
     *
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable) {
        if (isInMainThread()) {
            runnable.run();
        } else {
            gethandler().post(runnable);
        }
    }

    private static boolean isInMainThread() {
        //当前线程的id
        int tid = android.os.Process.myTid();
        if (tid == MyAppLication.mainThreadId) {
            return true;
        }
        return false;
    }


    public static void Toast(String text, boolean isLong) {
        Toast.makeText(getContent(), text, isLong == true ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }


}
