package p2pmomeny.p2pmomeny.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import p2pmomeny.p2pmomeny.R;

/**
 * Created by Administrator on 2017-04-12.
 */
public class TiXianActivity extends BaseActivity {

    EditText ed_money;
    Button chongzhi;
    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ed_money= (EditText) findViewById(R.id.ed_money);
        chongzhi= (Button) findViewById(R.id.chongzhi);
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String money = ed_money.getText().toString();
                intent.putExtra("money",money);
                setResult(4,intent);
                Toast.makeText(getApplicationContext(), "提现成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixian;
    }
}
