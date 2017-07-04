package p2pmomeny.p2pmomeny.activity;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.ui.PieChart;

/**
 * Created by Administrator on 2017-04-12.
 */
public class TouZhiManager extends  BaseActivity {
    PieChart pie;
    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        pie= (PieChart) findViewById(R.id.pie);
        String[] titles = new String[] {"建信中证500","交通银行混合","富国低碳环保","南方策略优化"};
        pie.setTitles(titles);
        int[] colors = new int[]{0xfff5a002,0xfffb5a2f,0xff36bc99,0xfff77a2f};
        pie.setColors(colors);
        pie.setValues(new double[]{1000,500,600,100});
        pie.postInvalidate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.touzhimanager;
    }
}
