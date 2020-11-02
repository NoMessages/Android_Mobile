package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Fm7_Emp;
import com.lenovo.smarttraffic.util.PortUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*

        查询全部问题仓库 dataInterface/UserQuestion/getAll { "carId": 1,"userProductionLineId": 1770}
        查询车辆信息 dataInterface/CarInfo/getInfo
        查询生产线信息 dataInterface/ProductionLine/getInfo

 */
public class SevenFragment extends BaseFragment {


    @BindView(R.id.fm7_list)
    ListView fm7List;
    Unbinder unbinder;
    private List<Fm7_Emp.DataBean> alist;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.seven_fragment, null);
        init();
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

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int i) {
            return alist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(getActivity(), R.layout.fm7_adapter, null);
            }
            Fm7_Emp.DataBean dataBean = alist.get(i);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.fm7Id.setText(dataBean.getId() + "");
            if (dataBean.getProductionLineName() != null)
                viewHolder.fm7Info.setText(dataBean.getProductionLineName() + "");
            else
                viewHolder.fm7Info.setText("MPV生产车间类型");
            if (dataBean.getGold() != 0)
                viewHolder.fm7FixMoney.setText(dataBean.getGold() + "");
            else
                viewHolder.fm7FixMoney.setText("65");
            if (dataBean.getContent() != null)
                viewHolder.fm7Desc.setText(dataBean.getContent() + "");
            else
                viewHolder.fm7Desc.setText("SUV汽车标准型");
            if (dataBean.getCarType() != null)
                viewHolder.fm7Type.setText(dataBean.getCarType() + "");
            else
                viewHolder.fm7Type.setText("SUV汽车");
            return view;
        }

        class ViewHolder {
            @BindView(R.id.fm7_id)
            TextView fm7Id;
            @BindView(R.id.fm7_type)
            TextView fm7Type;
            @BindView(R.id.fm7_desc)
            TextView fm7Desc;
            @BindView(R.id.fm7_info)
            TextView fm7Info;
            @BindView(R.id.fm7_fix_money)
            TextView fm7FixMoney;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public void getUserQuestionCar() {

        Call call = PortUtils.getCall(PortUtils.getUrl("dataInterface/UserQuestion/getAll"));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                Fm7_Emp fm7_emp = gson.fromJson(s, Fm7_Emp.class);
                for (int i = 0; i < fm7_emp.getData().size(); i++) {
                    Fm7_Emp.DataBean datam = fm7_emp.getData().get(i);
                    if (datam.getUserProductionLineId() > 2000)
                        alist.add(datam);
                }
                //获取相关数据 查询车辆 和生产线

                for (int i = 0; i < alist.size(); i++) {
                    Fm7_Emp.DataBean dataBean = alist.get(i);
                    getCarCash(dataBean.getCarId(), i);//查钱
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyAdapter myAdapter = new MyAdapter();
                        fm7List.setAdapter(myAdapter);
                    }
                });
            }
        });
    }

    private void init() {
        alist = new ArrayList<>();
        getUserQuestionCar();
    }

    public void getCarCash(int carId, int position) {
        String url = PortUtils.getUrl("dataInterface/CarInfo/getInfo");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("id", String.valueOf(carId)).build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    alist.get(position).setGold(new JSONObject(response.body().string()).optJSONArray("data").getJSONObject(0).optInt("repairGold"));
                    getCarInfo(carId, position);//查车描述

                } catch (Exception e) {

                }
            }
        });
    }

    public void getCarInfo(int carId, int position) {
        String url = PortUtils.getUrl("dataInterface/Car/getInfo");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("id", String.valueOf(carId)).build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    alist.get(position).setCarType(new JSONObject(json).optJSONArray("data").getJSONObject(0).optString("carName"));
                    alist.get(position).setContent(new JSONObject(json).optJSONArray("data").getJSONObject(0).optString("content"));
                    Log.i("asdasd", "onResponse: ");

                    getLineInfo(alist.get(position).getUserProductionLineId(), position);
                } catch (Exception e) {

                }
            }
        });
    }

    public void getLineInfo(int userLineInfo, int position) {
        String url = PortUtils.getUrl("dataInterface/UserProductionLine/getInfo");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("id", String.valueOf(userLineInfo)).build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    int ProductionLine = new JSONObject(response.body().string()).optJSONArray("data").getJSONObject(0).optInt("productionLineId");
                    alist.get(position).setProductionLineId(ProductionLine);

                    getLineContent(alist.get(position).getProductionLineId(), position);
                } catch (Exception e) {
                }
            }
        });
    }

    public void getLineContent(int lineId, int position) {
        String url = PortUtils.getUrl("dataInterface/ProductionLine/getInfo");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("id", String.valueOf(lineId)).build();
        Request request = new Request.Builder().post(requestBody).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    String LineName = new JSONObject(json).optJSONArray("data").getJSONObject(0).optString("productionLineName");
                    alist.get(position).setProductionLineName(LineName);
                } catch (Exception e) {
                }
            }
        });
    }
}