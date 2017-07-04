package p2pmomeny.p2pmomeny.common;

import android.app.Application;
import android.content.Context;
import android.os.*;

/**
 * Created by Administrator on 2017-04-06.
 */
public class MyAppLication extends Application{
    public  static Context context=null;
    public  static Handler handler=null;
    public  static  Thread mainThread=null;
    public  static  int mainThreadId=0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
         handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        CrashHandler.getInstance().init(this);




    }
}
