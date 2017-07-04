package p2pmomeny.p2pmomeny.ui;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Administrator on 2017-04-12.
 */
public class AmountUtils {
        public static String moneyFormat(double  numDouble) {
            // 把string类型的货币转换为double类型。
            // 想要转换成指定国家的货币格式
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
            // 把转换后的货币String类型返回
            String numString = format.format(numDouble);
            return numString;
        }
}
