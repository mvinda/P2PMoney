package p2pmomeny.p2pmomeny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.common.UIUtils;
import p2pmomeny.p2pmomeny.ui.PieChart;

/**
 * Created by Administrator on 2017-04-12.
 */
public class IncomeFragment extends Fragment {
      PieChart pieChart;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View xmlView = UIUtils.getXmlView(R.layout.fragemnt_income);

        pieChart= (PieChart) xmlView.findViewById(R.id.pieChart);
        String[] titles = new String[] {"建信中证500","交通银行混合","富国低碳环保","南方策略优化混合"};
        pieChart.setTitles(titles);
        int[] colors = new int[]{0xfff5a002,0xfffb5a2f,0xff36bc99,0xff77bc99};
        pieChart.setColors(colors);
        pieChart.setValues(new double[]{50,100,200,50});
        pieChart.postInvalidate();
        return xmlView;
    }
}
