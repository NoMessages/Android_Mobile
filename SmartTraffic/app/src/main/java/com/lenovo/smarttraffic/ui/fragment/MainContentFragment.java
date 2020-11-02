package com.lenovo.smarttraffic.ui.fragment;

import android.view.View;

import com.lenovo.smarttraffic.Constant;
import com.lenovo.smarttraffic.R;



/**
 * @author Amoly
 * @date 2019/4/11.
 * description：主页面
 */
public class MainContentFragment extends BaseFragment {
    private static MainContentFragment instance = null;

    public static MainContentFragment getInstance() {
        if (instance == null) {
            instance = new MainContentFragment();
        }
        return instance;
    }



    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        setListener();
        return view;
    }

    private void setListener() {

    }

    @Override
    protected Object requestData() {
        return Constant.STATE_SUCCEED;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        if (instance != null) {
            instance = null;
        }
        super.onDestroy();
    }


}
