package p2pmomeny.p2pmomeny.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by Administrator on 2017-04-06.
 */
public class CrashHandler  implements  Thread.UncaughtExceptionHandler {
    private Context mcontext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private String TAG="CrashHandler";
    private   static  CrashHandler crashHandler=null;
    private  CrashHandler(){
    }
    public   static  CrashHandler getInstance(){
        if (crashHandler==null){
            crashHandler=new CrashHandler();
        }
        return  crashHandler;
    }
    public  void init(Context context){
        //将CrashHandler作为系统作为处理
        this.mcontext=context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    //记录日记信息 将提示信息汉化 错误信息反馈到后台
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
    }

    private void handleException(Throwable ex) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                Looper.prepare();
                Toast.makeText(UIUtils.getContent()
                        , "抱歉，系统出现未知异常，即将退回", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collectionException(ex);

        try {
            Thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (InterruptedException e) {
        }
    }

    private void collectionException(Throwable ex) {
        final   String deviceInfo= Build.DEVICE;
        final  String errorInfo=ex.getMessage();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.i(TAG, "run: " + deviceInfo);
                Log.i(TAG, "run: "+errorInfo);
            }
        }.start();
    }
}
