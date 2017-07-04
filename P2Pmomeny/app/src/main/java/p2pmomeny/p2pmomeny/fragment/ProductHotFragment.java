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
import p2pmomeny.p2pmomeny.common.DrawableUtil;
import p2pmomeny.p2pmomeny.common.UIUtils;
import p2pmomeny.p2pmomeny.ui.FlowLayout;

/**
 * Created by Administrator on 2017-04-09.
 */
public class ProductHotFragment extends Fragment {
    FlowLayout flow;

    private String[] datas = new String[]{"新华趋势领航混合", "易万达科翔混合", "华安逆向策略混合", "国投瑞银医疗保健混合(加息2%)",
            "汇添富价值精选混合", "南方策略优化混合", "申万量化小盘", "富国低碳环保混合", "交银优势行业混合", "HelloWorld", "建信中证500指数增强", "中国移动有限公司等你来投资", "腾讯科技有限公司", "万达国际", " 交银优势行业混合"};
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View xmlView = UIUtils.getXmlView(R.layout.fragement_hot_recommend);
        flow= (FlowLayout) xmlView.findViewById(R.id.flow);
        initData();
        return xmlView;
    }

    private void initData() {
        random = new Random();
        for (String data : datas) {
            TextView tv = new TextView(getActivity());

            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);
            tv.setLayoutParams(mp);
            tv.setText(data);
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            tv.setBackground(
                    DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(r, g, b), UIUtils.dp2px(5)),
                            DrawableUtil.getDrawable(Color.WHITE, UIUtils.dp2px(5))));
            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            flow.addView(tv);

        }

    }
}
