package com.lenovo.smarttraffic.ui.fragment;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.Fm4_Emp;
import com.lenovo.smarttraffic.util.PortUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/*
        1.按照位置查询学生生产线ID  http://192.168.31.244:8085/dataInterface/UserProductionLine/search
        2.按学生生产线ID查询学生员工 http://192.168.31.244:8085/dataInterface/UserPeople/search
        3.按学生员工ID查询学生职位 http://192.168.31.244:8085/dataInterface/People/getInfo
 */
public class FourFragment extends BaseFragment {
    private GridView fm4_list;
    private int positions = 0;
    private MyAdapter myAdapter;
    private List<Fm4_Emp.DataBean> alist;
    private boolean isFir = true;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getActivity(), R.layout.four_fragment, null);
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
                view = View.inflate(getActivity(), R.layout.fm4_adapter, null);
            }
            //判断适配器的list是否为空
            if(alist!=null)
                    if(alist.get(i).getPosition() == 0){
                        if(alist.get(i).getPersonList() != null || alist.get(i).getPersonList().size() != 0)
                            showData("#82AAFF","#FF5B5B",i,new ViewHolder(view));
                    }
                    if(alist.get(i).getPosition() == 1){
                        if(alist.get(i).getPersonList() != null || alist.get(i).getPersonList().size() != 0)
                            showData("#82AAFF","#FF5B5B",i,new ViewHolder(view));
                    }

                    if(alist.get(i).getPosition() == 2){
                        //"#82AAFF"蓝色的
                        if(alist.get(i).getPersonList() != null || alist.get(i).getPersonList().size() != 0)
                        showData("#82AAFF","#FF5B5B",i,new ViewHolder(view));
                    }

                    if(alist.get(i).getPosition() == 3){
                        //"#82AAFF"蓝色的
                        if(alist.get(i).getPersonList() != null || alist.get(i).getPersonList().size() != 0)
                            showData("#82AAFF","#FF5B5B",i,new ViewHolder(view));
                    }



                    /*
                    点击事件
                     */

//            if(positions == i){
//                ViewHolder viewHolder = new ViewHolder(view);
//                viewHolder.fm4Eng.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        viewHolder.fm4EngName.setText("qweqweqweqwe"+i);
//                        viewHolder.fm4EngHp.setText(50*i+"");
//                    }
//                });
//            }
            return view;
        }

        class ViewHolder {
            @BindView(R.id.fm4_tecno_name)
            TextView fm4TecnoName;
            @BindView(R.id.fm4_tecno_hp)
            TextView fm4TecnoHp;
            @BindView(R.id.fm4_tecno)
            LinearLayout fm4Tecno;
            @BindView(R.id.fm4_metr_name)
            TextView fm4MetrName;
            @BindView(R.id.fm4_metr_hp)
            TextView fm4MetrHp;
            @BindView(R.id.fm4_metr)
            LinearLayout fm4Metr;
            @BindView(R.id.fm4_tec_name)
            TextView fm4TecName;
            @BindView(R.id.fm4_tec_hp)
            TextView fm4TecHp;
            @BindView(R.id.fm4_tec)
            LinearLayout fm4Tec;
            @BindView(R.id.fm4_eng_name)
            TextView fm4EngName;
            @BindView(R.id.fm4_eng_hp)
            TextView fm4EngHp;
            @BindView(R.id.fm4_eng)
            LinearLayout fm4Eng;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private void init(View view){
        fm4_list = view.findViewById(R.id.fm4_list);
        alist = new ArrayList<>();

        Call call = PortUtils.getCall(PortUtils.getUrl("dataInterface/UserProductionLine/getAll"));
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("asd", "onFailure: ");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Fm4_Emp fm4_emp = new Gson().fromJson(json, Fm4_Emp.class);
                for (Fm4_Emp.DataBean datum : fm4_emp.getData()) {
                    alist.add(datum);
                }//4条线 每条线有一个
                //查询所有生产线
                Collections.sort(alist, new Comparator<Fm4_Emp.DataBean>() {
                    @Override
                    public int compare(Fm4_Emp.DataBean dataBean, Fm4_Emp.DataBean t1) {
                        return dataBean.getPosition() - t1.getPosition();
                    }
                });
                //按照生产线ID查询员工
                for (int i = 0; i < alist.size(); i++) {
                    getPerson(String.valueOf(alist.get(i).getId()),alist.get(i).getPosition());
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getPerson(String usrLine,int position){
        String url = PortUtils.getUrl("dataInterface/UserPeople/search");
        RequestBody requestBody = new FormBody.Builder().add("userProductionLineId",usrLine).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //查询某条线里面的员工
                String json = response.body().string();
                List<Fm4_Emp.DataBean.Person> personLists = new ArrayList<>();
                if(json != null){
                    //过滤掉其中没有职位的
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray data = jsonObject.optJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject1 = data.getJSONObject(i);
                            String s = jsonObject1.optString("workPostId");
                            if("".equals(s)){
                                continue;
                            }
                            personLists.add(new Fm4_Emp.DataBean.Person(jsonObject1.optInt("peopleId")));
                        }
                        alist.get(position).setPersonList(personLists); //过滤出来每条线的有业游名
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    alist.get(position).setPersonList(null);
                }
                //根据人员ID查姓名体力
                for (int i = 0; i < alist.get(position).getPersonList().size(); i++) {
                    getPersonInfo(alist.get(position).getPersonList().get(i).getPeopleId(),position,i);
                }

            }
        });
    }

    public void getPersonInfo(int peopleId, int position,int i){

        String url = PortUtils.getUrl("dataInterface/People/getInfo");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("id",String.valueOf(peopleId))
                .build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject data = jsonObject.optJSONArray("data").getJSONObject(0);
                    String peopleName = data.optString("peopleName");
                    int hp = data.optInt("hp");
                    int status = data.optInt("status");
                    alist.get(position).getPersonList()
                            .get(i)
                            .setPeopleName(peopleName);//名字

                    alist.get(position).getPersonList()
                            .get(i)
                            .setHp(hp);//体力

                    alist.get(position).getPersonList()
                            .get(i)
                            .setStatus(status);//状态

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myAdapter = new MyAdapter();
                            fm4_list.setAdapter(myAdapter);
                            fm4_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                                    positions = index;
                                    myAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showData(String succ_color,String white_color, int position, MyAdapter.ViewHolder viewHolder){

//      MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);
        viewHolder.fm4Eng.setBackgroundColor(Color.parseColor(white_color));
        viewHolder.fm4Tec.setBackgroundColor(Color.parseColor(white_color));
        viewHolder.fm4Metr.setBackgroundColor(Color.parseColor(white_color));
        viewHolder.fm4Tecno.setBackgroundColor(Color.parseColor(white_color));

        for (int i1 = 0; i1 < alist.get(position).getPersonList().size(); i1++) {
            Fm4_Emp.DataBean.Person person = alist.get(position).getPersonList().get(i1);
            switch (person.getStatus()) {
                case 0:
                    viewHolder.fm4EngName.setText(person.getPeopleName());
                    viewHolder.fm4EngHp.setText("体力：" + person.getHp());
                    viewHolder.fm4Eng.setBackgroundColor(Color.parseColor(succ_color));
                    break;
                case 1:
                    viewHolder.fm4TecName.setText(person.getPeopleName());
                    viewHolder.fm4TecHp.setText("体力：" + person.getHp());
                    viewHolder.fm4Tec.setBackgroundColor(Color.parseColor(succ_color));
                    break;
                case 2:
                    viewHolder.fm4MetrName.setText(person.getPeopleName());
                    viewHolder.fm4MetrHp.setText("体力：" + person.getHp());
                    viewHolder.fm4Metr.setBackgroundColor(Color.parseColor(succ_color));
                    break;
                case 3:
                    viewHolder.fm4TecnoName.setText(person.getPeopleName());
                    viewHolder.fm4TecnoHp.setText("体力：" + person.getHp());
                    viewHolder.fm4Tecno.setBackgroundColor(Color.parseColor(succ_color));
                    break;
            }
        }

    }

}