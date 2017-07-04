package p2pmomeny.p2pmomeny.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import p2pmomeny.p2pmomeny.R;
import p2pmomeny.p2pmomeny.common.UIUtils;
import p2pmomeny.p2pmomeny.ui.randomLayout.StellarMap;

/**
 * Created by Administrator on 2017-04-09.
 */
public class ProductReCommendFragment extends Fragment {
     StellarMap stellarMap;

    private String[] datas = new String[]{"建信中证500指数增强", "交银优势行业混合", "富国低碳环保混合", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "申万量化小盘", "南方策略优化混合", "屌丝下海经商计划", "汇添富价值精选混合", "国投瑞银医疗保健混合", "养猪场扩大经营",
            "华安逆向策略混合", "阿里巴巴洗钱计划", "铁路局回款计划", "易万达科翔混合"
    };
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View xmlView = UIUtils.getXmlView(R.layout.fragement_product_recommend);
        stellarMap= (StellarMap) xmlView.findViewById(R.id.stellarMap);

        initData();

        return xmlView;
    }

    private void initData() {
        random = new Random();
        stellarMap.setAdapter(new MyAdapter());
        //每组出现数据的搭配规则
        stellarMap.setRegularity(7, 9);
        //从哪一组执行规则
        stellarMap.setGroup(0,true);




    }

    private class MyAdapter implements StellarMap.Adapter {

        //一共有多少组
        @Override
        public int getGroupCount() {
            return 2;
        }

        @Override
        public int getCount(int group) {
            return 8;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView tv=new TextView(getActivity());
            tv.setText(datas[position]);
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            int rgb= Color.rgb(r,g,b);
            tv.setTextSize(UIUtils.dp2px(8)+random.nextInt(8));
            tv.setTextColor(rgb);
            return tv;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 1;
        }
    }
}
