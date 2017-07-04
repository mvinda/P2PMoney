package p2pmomeny.p2pmomeny.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017-04-06.
 */
public class MyScrollView extends ScrollView {
    private View innerView;
    private float y;
    private Rect normal=new Rect();
    private boolean animationFinsh=true;


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount=getChildCount();
        if (childCount>0){
        innerView=getChildAt(0);

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
      if (innerView==null){
          return super.onTouchEvent(ev);
      }else {
          commonTouchEvent(ev);
      }
        return super.onTouchEvent(ev);
    }

    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinsh){
        int action=ev.getAction();
        switch (action){
            case  MotionEvent.ACTION_DOWN:{
                y=  ev.getY();
                break;
            }
            case  MotionEvent.ACTION_MOVE:{
                float preY=y==0? ev.getY():y;
                float nowY=ev.getY();
                int detailY= (int) (preY-nowY);
                y=nowY;
               if (isNeedMove()){
                   //布局改变位置之前，记录一下正确状态下的位置
                     if (normal.isEmpty()){
                         normal.set(innerView.getLeft(),innerView.getTop(),innerView.getRight(),innerView.getBottom());


                     }
                  innerView.layout(innerView.getLeft(),innerView.getTop() - detailY / 2,innerView.getRight(),innerView.getBottom() - detailY / 2);
               }
                break;
            }
            case MotionEvent.ACTION_UP:{
                if (isNeedAnimation()){
                    animation();


                }
                break;
            }
        }
        }
    }

    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top-innerView.getTop() );
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinsh = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {

              innerView.clearAnimation();
                animationFinsh = true;
                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });

        innerView.startAnimation(ta);


    }

    /**
     * 判断是否需要移动
     * @return
     */
    private boolean isNeedAnimation() {
        if (!normal.isEmpty()){
            return true;
        }else {
            return  false;
        }
    }

    private boolean isNeedMove() {
        //getMeasuredHeight  某个控件原始侧量的值 和屏幕无关
        // getHeight  显示在屏幕上的控件的高度
        int offset=innerView.getMeasuredHeight()-getHeight();
        int  scrollY=getScrollY();
        if(scrollY==0||scrollY==offset){
            return true;
        }
        return false;
    }
}
