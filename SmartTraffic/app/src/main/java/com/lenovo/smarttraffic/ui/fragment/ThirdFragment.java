package com.lenovo.smarttraffic.ui.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Fm3_Person;
import com.lenovo.smarttraffic.util.PortUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ThirdFragment extends BaseFragment {

    @BindView(R.id.fm3_name)
    TextView fm3Name;
    @BindView(R.id.fm3_type)
    TextView fm3Type;
    @BindView(R.id.salary)
    TextView salary;
    @BindView(R.id.fm3_list)
    ListView fm3List;
    Unbinder unbinder;
    private List<Fm3_Person.DataBean> alist;
    private MyAdapter myAdapter;
    private boolean isType = false ,isSalary = false;
    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.thr_fragment, null);
        init();
        return view;
    }

    @Override
    protected Object requestData() {
        SystemClock.sleep(1000);/*模拟请求服务器的延时过程*/
        return "";/*加载失败*/
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

    private void init() {
        alist = new ArrayList<>();
        PortUtils.getCall(PortUtils.getUrl("dataInterface/People/getAll")).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                Fm3_Person persons = gson.fromJson(string, Fm3_Person.class);
                for (Fm3_Person.DataBean datum : persons.getData()) {
                    datum.setContent(datum.getStatus() > 2 ? "质检员" : datum.getStatus() > 1 ? "技术人员" : datum.getStatus() > 0 ? "工人" : "工程师");
                    alist.add(datum);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter = new MyAdapter();
                        fm3List.setAdapter(myAdapter);
                    }
                });
            }
        });
    }

    @OnClick({R.id.fm3_type, R.id.salary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fm3_type:
                isType = !isType;
                if (isType){
                    Collections.sort(alist, new Comparator<Fm3_Person.DataBean>() {
                        @Override
                        public int compare(Fm3_Person.DataBean dataBean, Fm3_Person.DataBean t1) {
                            return dataBean.getContent().compareTo(t1.getContent());
                        }
                    });
                    fm3Type.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_menu_down_gray_24dp,0);
                }else{
                    Collections.sort(alist, new Comparator<Fm3_Person.DataBean>() {
                        @Override
                        public int compare(Fm3_Person.DataBean dataBean, Fm3_Person.DataBean t1) {
                            return t1.getContent().compareTo(dataBean.getContent());
                        }
                    });
                    fm3Type.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_menu_up_gray_24dp,0);
                }
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.salary:
                isSalary = !isSalary;
                if (isSalary){
                    Collections.sort(alist, new Comparator<Fm3_Person.DataBean>() {
                        @Override
                        public int compare(Fm3_Person.DataBean dataBean, Fm3_Person.DataBean t1) {
                            return dataBean.getGold() - t1.getGold();
                        }
                    });
                    salary.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_menu_down_gray_24dp,0);
                }else{
                    Collections.sort(alist, new Comparator<Fm3_Person.DataBean>() {
                        @Override
                        public int compare(Fm3_Person.DataBean dataBean, Fm3_Person.DataBean t1) {
                            return t1.getGold() - dataBean.getGold();
                        }
                    });
                    salary.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_menu_up_gray_24dp,0);
                }
                myAdapter.notifyDataSetChanged();
                break;
        }
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
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(getActivity(), R.layout.fm3_adapter, null);
            }
            Fm3_Person.DataBean dataBean = alist.get(position);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.fm3Name.setText(dataBean.getPeopleName());
            viewHolder.fm3Type.setText(dataBean.getContent());
            viewHolder.salary.setText(dataBean.getGold()+"");
            return view;
        }

        class ViewHolder {
            @BindView(R.id.fm3_name)
            TextView fm3Name;
            @BindView(R.id.fm3_type)
            TextView fm3Type;
            @BindView(R.id.salary)
            TextView salary;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
