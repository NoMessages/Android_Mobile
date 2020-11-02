package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Emp1;
import com.lenovo.smarttraffic.util.PortUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SixFragment extends BaseFragment {


    @BindView(R.id.a6_bt_goumai)
    Button a6BtGoumai;
    @BindView(R.id.a6_edit1)
    EditText a6Edit1;
    @BindView(R.id.a6_edit2)
    EditText a6Edit2;
    @BindView(R.id.textView7)
    TextView textView7;
    Unbinder unbinder;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.activity_a6, null);
        inFo();
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
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

    private void inFo() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String initData = initData(PortUtils.getUrl("dataInterface/UserWorkInfo/getInfo"), "id", "1","","","","");
                try {
                    JSONObject jsonObject = new JSONObject(initData);
                    JSONArray data = jsonObject.optJSONArray("data");
                    String price = data.optJSONObject(0).optString("price");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView7.setText("工厂资金:    "+price);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    private String initData(String url, String key, String values,String k2 ,String v2, String k3 ,String v3) {
        FormBody formBody = new FormBody.Builder().add(key, values).add(k2,v2).add(k3,v3).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @OnClick(R.id.a6_bt_goumai)
    public void onViewClicked() {
        String ed1 = a6Edit1.getText().toString();
        String ed2 = a6Edit2.getText().toString();
        if (ed1.isEmpty() || ed2.isEmpty()){
            Toast.makeText(getActivity(), "输入值", Toast.LENGTH_SHORT).show();
            return;
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String initData = initData(PortUtils.getUrl("dataInterface/UserProductionLine/search"), "position", ed1,"","","","");
                    try {
                        JSONObject jsonObject = new JSONObject(initData);
                        JSONArray data = jsonObject.optJSONArray("data");
                        int id = data.optJSONObject(0).optInt("id");   //学生生产线
                        String initData1 = initData(PortUtils.getUrl("dataInterface/SuppierList/search"), "id", ed2,"","","","");
                        Emp1 emp1 = new Gson().fromJson(initData1, Emp1.class);
                        if (emp1.getData().size() ==0){
//                            Toast.makeText(A6.this, "", Toast.LENGTH_SHORT).show();
                        }
                        int suppierId = emp1.getData().get(0).getSuppierId();
                        int num = emp1.getData().get(0).getNum();
                        String initData2 = initData(PortUtils.getUrl("Interface/index/addUserMaterialStore"), "userLineId", String.valueOf(id), "num", String.valueOf(num), "supplyListId", String.valueOf(suppierId));
                        if (!initData2.isEmpty()){
//                            Toast.makeText(A6.this, "购买成功", Toast.LENGTH_SHORT).show();
                            inFo();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "工厂", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        }

    }

}
