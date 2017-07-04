package p2pmomeny.p2pmomeny.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.common.BaseFragment;


/**
 * Created by Administrator on 2017-04-06.
 */
public class TouziFragment  extends BaseFragment{
    String[] arr={"基金列表","推荐基金","本月收益"};

    private List<Fragment> fragmentList=new ArrayList<>();

    private TabPageIndicator tab_indictor;
    private  ViewPager pager;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.touzi_fragemnt;
    }

    @Override
    public void initData(String data) {

        initFragment();
        pager.setAdapter(new MyAdapter(getFragmentManager()));
        tab_indictor.setViewPager(pager);

    }

    private void initFragment() {
        ProductListFragment productListFragment = new ProductListFragment();
   //     ProductHotFragment productHotFragment = new ProductHotFragment();
        IncomeFragment incomeFragment = new IncomeFragment();

        ProductReCommendFragment productReCommendFragment = new ProductReCommendFragment();
        fragmentList.add(productListFragment);
        fragmentList.add(productReCommendFragment);
        fragmentList.add(incomeFragment);

    }

    @Override
    public void initTitle(View successView) {
        ImageView iv_back = (ImageView) successView.findViewById(R.id.iv_back);
        ImageView iv_setting = (ImageView) successView.findViewById(R.id.iv_setting);
        iv_back.setVisibility(View.INVISIBLE);
        iv_setting.setVisibility(View.INVISIBLE);
        tab_indictor = (TabPageIndicator) successView.findViewById(R.id.tab_indictor);
        pager = (ViewPager) successView.findViewById(R.id.pager);
        TextView tv_title = (TextView) successView.findViewById(R.id.tv_title);
        tv_title.setText("投资超市");

    }


    private class MyAdapter extends FragmentPagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];

        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return fragmentList==null ? 0:fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


    }
}
