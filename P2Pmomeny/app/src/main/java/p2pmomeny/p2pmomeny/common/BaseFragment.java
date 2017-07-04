package p2pmomeny.p2pmomeny.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.loopj.android.http.RequestParams;
import p2pmomeny.p2pmomeny.ui.LoadingPage;
/**
 * Created by Administrator on 2017-04-08.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadingPage = new LoadingPage(getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }
            @Override
            protected void OnSuccess(ResultState resultState,View successView) {
                this.successView=successView;
                initTitle(successView);
                initData(resultState.getContent());
            }
            @Override
            protected RequestParams params() {
                return getParams();
            }
            @Override
            protected String url() {
                return getUrl();
            }
        };
        return loadingPage;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }
    private void show() {
        loadingPage.show();
    }
    protected abstract RequestParams getParams();
    protected abstract String getUrl();
    public abstract  int getLayoutId();
    public abstract  void  initData(String data);
    public abstract  void  initTitle(View successView);
}
