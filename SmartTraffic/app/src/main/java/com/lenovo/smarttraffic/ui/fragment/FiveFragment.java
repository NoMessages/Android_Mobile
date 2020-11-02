package com.lenovo.smarttraffic.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Supply;
import com.lenovo.smarttraffic.util.PortUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FiveFragment extends BaseFragment {



    private Toolbar toolbar;
    private ImageView imgRed;
    private ImageView imgBlack;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<Supply.DataBean> data;
    private boolean red_isup, black_up;


    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.activity_sm5, null);
        initView(view);
        loading();
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

    private void initView(View view ) {

        imgRed = view.findViewById(R.id.img_red);
        imgBlack = view.findViewById(R.id.img_black);
        listView = view.findViewById(R.id.listView);
        imgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!red_isup) {
                    imgRed.setImageResource(R.drawable.red_up);
                    Collections.sort(data, new Comparator<Supply.DataBean>() {
                        @Override
                        public int compare(Supply.DataBean o1, Supply.DataBean o2) {
                            return o1.getPrice() - o2.getPrice();
                        }
                    });
                    red_isup = true;
                } else {
                    imgRed.setImageResource(R.drawable.red_down);
                    Collections.sort(data, new Comparator<Supply.DataBean>() {
                        @Override
                        public int compare(Supply.DataBean o1, Supply.DataBean o2) {
                            return o2.getPrice() - o1.getPrice();
                        }
                    });
                    red_isup = false;
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        imgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!black_up) {
                    imgBlack.setImageResource(R.drawable.black_up);
                    Collections.sort(data, new Comparator<Supply.DataBean>() {
                        @Override
                        public int compare(Supply.DataBean o1, Supply.DataBean o2) {
                            return o1.getNum() - o2.getNum();
                        }
                    });
                    black_up = true;
                } else {
                    imgBlack.setImageResource(R.drawable.black_down);
                    Collections.sort(data, new Comparator<Supply.DataBean>() {
                        @Override
                        public int compare(Supply.DataBean o1, Supply.DataBean o2) {
                            return o2.getNum() - o1.getNum();
                        }
                    });
                    black_up = false;
                }
                myAdapter.notifyDataSetChanged();
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
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
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.activity_sm5_listview, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_shop.setText(data.get(position).getMaterialName());
            viewHolder.tv_num.setText(data.get(position).getNum() + "");
            viewHolder.tv_gonghuo.setText(data.get(position).getContent());
            viewHolder.tv_price.setText(data.get(position).getPrice() + "");
            return convertView;
        }

        class ViewHolder {
            public View rootView;
            public TextView tv_shop;
            public TextView tv_price;
            public TextView tv_num;
            public TextView tv_gonghuo;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_shop = (TextView) rootView.findViewById(R.id.tv_shop);
                this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
                this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
                this.tv_gonghuo = (TextView) rootView.findViewById(R.id.tv_gonghuo);
            }

        }
    }


    private void loading() {
        String url = PortUtils.getUrl("Interface/index/getMaterial");
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("error", call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Supply supply = new Gson().fromJson(response.body().string(), Supply.class);
                data = supply.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter = new MyAdapter();
                        listView.setAdapter(myAdapter);
                    }
                });
            }
        });
    }
}
