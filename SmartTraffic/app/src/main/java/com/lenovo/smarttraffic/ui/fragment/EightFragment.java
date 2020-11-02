package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Fm8.Emp1;
import com.lenovo.smarttraffic.bean.Fm8.Emp2;
import com.lenovo.smarttraffic.bean.Fm8.Emp3;
import com.lenovo.smarttraffic.bean.Fm8.Emp4;
import com.lenovo.smarttraffic.util.PortUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class EightFragment extends BaseFragment {


    @BindView(R.id.a8_listview)
    ListView a8Listview;
    @BindView(R.id.a8_tv_zong)
    TextView a8TvZong;
    private List<Emp> list = new ArrayList<>();
    private MyAdapter myAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override

    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.activity_a8, null);
        initRun(PortUtils.getUrl("dataInterface/UserNormalCarStore/getAll"));
        initRun(PortUtils.getUrl("dataInterface/UserNormalCarStore/getAll"));

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

    private void initRun(String url) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    String initData = initData(url, "", "");
                    Emp1 emp1 = new Gson().fromJson(initData, Emp1.class);
                    for (int i = 0; i < emp1.getData().size(); i++) {
                        int carId = emp1.getData().get(i).getCarId();   //车id
                        int num = emp1.getData().get(i).getNum();       //数量
                        String initData1 = initData(PortUtils.getUrl("dataInterface/CarInfo/search"), "id", String.valueOf(carId));
                        Emp2 emp2 = new Gson().fromJson(initData1, Emp2.class);
                        if (emp2.getData().size() == 0) {
                            continue;
                        }
                        int area = emp2.getData().get(0).getArea();  //占地面积
                        String hege = "";
                        if (url.equals(PortUtils.getUrl("dataInterface/UserNormalCarStore/getAll"))) {
                            hege = "正常";
                        } else {
                            hege = "维修";

                        }
                        String initData2 = initData(PortUtils.getUrl("dataInterface/Car/getInfo"), "id", String.valueOf(carId));
                        Emp3 emp3 = new Gson().fromJson(initData2, Emp3.class);
                        if (emp3.getData().size() == 0) {
                            continue;
                        }
                        String carName = emp3.getData().get(0).getCarName();  //车类型
                        String initData3 = initData(PortUtils.getUrl("dataInterface/ProductionLine/search"), "id", String.valueOf(carId));
                        Emp4 emp4 = new Gson().fromJson(initData3, Emp4.class);
                        if (emp4.getData().size() == 0) {
                            continue;
                        }
                        String productionLineName = emp4.getData().get(0).getProductionLineName();  //生产线
                        Log.i("TAH", "i: " + i + "productionLineName" + productionLineName);
                        Emp emp = new Emp(carId, carName, num, area, productionLineName, hege);
                        list.add(emp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int zangdimianji = 0;
                        for (int i = 0; i < list.size(); i++) {
                            zangdimianji += list.get(i).getNum() * list.get(i).getArea();
                        }
                        myAdapter = new MyAdapter();
                        a8Listview.setAdapter(myAdapter);
                        a8TvZong.setText("成品车辆库存信息：共占地"+zangdimianji+"库存容量 ");
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    private String initData(String url, String key, String values) throws IOException {
        FormBody formBody = new FormBody.Builder().add(key, values).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    class Emp {
        int id;
        String type;
        int num;
        int area;
        String productionLineName;
        String hege;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getProductionLineName() {
            return productionLineName;
        }

        public void setProductionLineName(String productionLineName) {
            this.productionLineName = productionLineName;
        }

        public String getHege() {
            return hege;
        }

        public void setHege(String hege) {
            this.hege = hege;
        }

        public Emp(int id, String type, int num, int area, String productionLineName, String hege) {
            this.id = id;
            this.type = type;
            this.num = num;
            this.area = area;
            this.productionLineName = productionLineName;
            this.hege = hege;
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.a8_adapter, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.a3AdapterTv1.setText("" + list.get(position).getId());
            viewHolder.a3AdapterTv2.setText("" + list.get(position).getType());
            viewHolder.a3AdapterTv3.setText("" + list.get(position).getNum());
            viewHolder.a3AdapterTv4.setText("" + list.get(position).getArea());
            viewHolder.a3AdapterTv5.setText("" + list.get(position).getProductionLineName());
            viewHolder.a3AdapterTv6.setText("" + list.get(position).getHege());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.a3_adapter_tv1)
            TextView a3AdapterTv1;
            @BindView(R.id.a3_adapter_tv2)
            TextView a3AdapterTv2;
            @BindView(R.id.a3_adapter_tv3)
            TextView a3AdapterTv3;
            @BindView(R.id.a3_adapter_tv4)
            TextView a3AdapterTv4;
            @BindView(R.id.a3_adapter_tv5)
            TextView a3AdapterTv5;
            @BindView(R.id.a3_adapter_tv6)
            TextView a3AdapterTv6;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
