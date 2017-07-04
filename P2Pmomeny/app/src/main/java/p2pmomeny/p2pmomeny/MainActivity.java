package p2pmomeny.p2pmomeny;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import p2pmomeny.p2pmomeny.common.AppManager;
import p2pmomeny.p2pmomeny.fragment.HomeFragment;
import p2pmomeny.p2pmomeny.fragment.MeFragment;
import p2pmomeny.p2pmomeny.fragment.MoreFragment;
import p2pmomeny.p2pmomeny.fragment.TouziFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    FrameLayout frameLayout;
    LinearLayout  ll_home,ll_touzi,ll_more,ll_me;
    ImageView iv_home,iv_me,iv_touzi,iv_more;
    TextView tv_touzi,tv_home,tv_me,tv_more;
    HomeFragment homeFragment;
    TouziFragment touziFragment;
    MoreFragment moreFragment;
    MeFragment meFragment;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        AppManager.getInstance().addActivity(this);
        initUI();
        initData();
    }
    private void initData() {
        setSelect(0);
    }
    private void initUI() {
        frameLayout= (FrameLayout) findViewById(R.id.fl_main);
        ll_home= (LinearLayout) findViewById(R.id.ll_home);
        ll_home.setOnClickListener(this);
        ll_touzi= (LinearLayout) findViewById(R.id.ll_touzi);
        ll_touzi.setOnClickListener(this);
        ll_more= (LinearLayout) findViewById(R.id.ll_more);
        ll_more.setOnClickListener(this);
        ll_me= (LinearLayout) findViewById(R.id.ll_me);
        ll_me.setOnClickListener(this);


        iv_home= (ImageView) findViewById(R.id.iv_home);
        iv_me= (ImageView) findViewById(R.id.iv_me);
        iv_touzi= (ImageView) findViewById(R.id.iv_touzi);
        iv_more= (ImageView) findViewById(R.id.iv_more);
        tv_touzi= (TextView) findViewById(R.id.tv_touzi);
        tv_home= (TextView) findViewById(R.id.tv_home);
        tv_me= (TextView) findViewById(R.id.tv_me);
        tv_more= (TextView) findViewById(R.id.tv_more);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
           case  R.id.ll_home:{
               setSelect(0);

                break;
            }

            case  R.id.ll_touzi:{
                setSelect(1);
                break;
            }

            case  R.id.ll_me:{
                setSelect(2);
                break;
            }

            case  R.id.ll_more:{
                setSelect(3);
                break;
            }
        }

    }

    public void setSelect(int i) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft= supportFragmentManager.beginTransaction();
        hideFragment(ft);
        resetTab();

        switch (i){
            case  0:{
                if (homeFragment==null){
                     homeFragment = new HomeFragment();
                    ft.add(R.id.fl_main,homeFragment);
                }
                ft.show(homeFragment);
                  iv_home.setImageResource(R.drawable.home_press);
                tv_home.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            }
            case  1:{
                if (touziFragment==null){
                    touziFragment = new TouziFragment();
                    ft.add(R.id.fl_main,touziFragment);
                }
                ft.show(touziFragment);
                iv_touzi.setImageResource(R.drawable.touzi);
                tv_touzi.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            }
            case  2:{
                if (meFragment==null){

                    meFragment = new MeFragment();
                    ft.add(R.id.fl_main,meFragment);
                }
                ft.show(meFragment);
                iv_me.setImageResource(R.drawable.me_presss);
                tv_me.setTextColor(getResources().getColor(R.color.home_back_selected));

                break;
            }
            case  3:{
                if (moreFragment==null){
                    moreFragment = new MoreFragment();
                    ft.add(R.id.fl_main,moreFragment);
                }
                ft.show(moreFragment);
                iv_more.setImageResource(R.drawable.more_press);
                tv_more.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            }
        }
        ft.commit();
    }

    public  void resetTab(){
        iv_home.setImageResource(R.drawable.home_nomal);
        iv_touzi.setImageResource(R.drawable.touzi_nomal);
        iv_more.setImageResource(R.drawable.more_nomal);
        iv_me.setImageResource(R.drawable.menomal);

        tv_home.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tv_touzi.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tv_more.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tv_me.setTextColor(getResources().getColor(R.color.home_back_unselected));




    }

    private void hideFragment(FragmentTransaction ft) {
     if (homeFragment!=null){
            ft.hide(homeFragment);

        }
        if (touziFragment!=null){
            ft.hide(touziFragment);

        }
        if (meFragment!=null){
            ft.hide(meFragment);

        }

        if (moreFragment!=null){
            ft.hide(moreFragment);

        }

    }


}
