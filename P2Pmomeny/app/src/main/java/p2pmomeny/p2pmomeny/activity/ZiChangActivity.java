package p2pmomeny.p2pmomeny.activity;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.ui.PieChart;

/**
 * Created by Administrator on 2017-04-12.
 */
public class ZiChangActivity extends BaseActivity {
    PieChart pieChart;
    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        pieChart= (PieChart) findViewById(R.id.pie2);
        String[] titles = new String[] {"现金存款","基金投资","余额宝金额"};
        pieChart.setTitles(titles);
        int[] colors = new int[]{0xfff5a002,0xfffb5a2f,0xff36bc99};
        pieChart.setColors(colors);
        pieChart.setValues(new double[]{30,500,1000});
        pieChart.postInvalidate();

    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_zhichang;

    }
}
