package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.util.PortUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SecondFragment extends BaseFragment {


    @BindView(R.id.fm2_img_cold)
    ImageView fm2ImgCold;
    @BindView(R.id.fm2_img_hot)
    ImageView fm2ImgHot;
    @BindView(R.id.fm2_btn_cold)
    Button fm2BtnCold;
    @BindView(R.id.fm2_btn_hot)
    Button fm2BtnHot;
    @BindView(R.id.fm2_btn_close)
    Button fm2BtnClose;
    Unbinder unbinder;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.sec_fragment, null);
        return view;
    }

    @Override
    protected Object requestData() {
        SystemClock.sleep(1000);/*模拟请求服务器的延时过程*/
        return "";/*加载为空*/
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fm2_btn_cold, R.id.fm2_btn_hot, R.id.fm2_btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fm2_btn_cold:
                fm2ImgCold.getDrawable().setAlpha(100);
                fm2ImgHot.getDrawable().setAlpha(60);
                changeStatus("1");
                break;
            case R.id.fm2_btn_hot:
                fm2ImgCold.getDrawable().setAlpha(40);
                fm2ImgHot.getDrawable().setAlpha(200);
                changeStatus("2");
                break;
            case R.id.fm2_btn_close:
                fm2ImgCold.getDrawable().setAlpha(40);
                fm2ImgHot.getDrawable().setAlpha(60);
                changeStatus("0");
                break;
        }
    }
    private void changeStatus(String status){
        String url = PortUtils.getUrl("dataInterface/UserWorkEnvironmental/updateAcOnOff");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("id","1")
                .add("acOnOff",status)
                .build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
