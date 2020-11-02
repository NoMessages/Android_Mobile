package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.util.PortUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FirstFragment extends BaseFragment {

    private ImageView fm1_img;
    private Button fm1_btn_open , fm1_btn_close;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.fir_fragment, null);
        init(view);
        return view;
    }

    @Override
    protected Object requestData() {
        SystemClock.sleep(1000);/*模拟请求服务器的延时过程*/
        return "";/*加载成功*/
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 类似于 Activity的 onNewIntent()
     */
    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);

        Toast.makeText(_mActivity, args.getString("from"), Toast.LENGTH_SHORT).show();
    }


    private void init(View view){
        fm1_img = view.findViewById(R.id.fm1_img);
        fm1_btn_open = view.findViewById(R.id.fm1_btn_open);
        fm1_btn_close = view.findViewById(R.id.fm1_btn_close);


        fm1_btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开灯
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    String url =  PortUtils.getUrl("dataInterface/UserWorkEnvironmental/updateLightSwitch");
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("id","1")
                                .add("lightSwitch","1")
                                .build();
                        Request request = new Request.Builder().url(url).post(requestBody).build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        fm1_img.setImageResource(R.drawable.gc_light);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });

        fm1_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开灯
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = PortUtils.getUrl("dataInterface/UserWorkEnvironmental/updateLightSwitch");
                        OkHttpClient client = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("id","1")
                                .add("lightSwitch","0")
                                .build();
                        Request request = new Request.Builder().url(url).post(requestBody).build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        fm1_img.setImageResource(R.drawable.gc_dark);

                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
