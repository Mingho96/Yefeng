package com.example.administrator.yefeng.page.homepage.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GetBannerInfo;
import com.example.administrator.yefeng.model.bean.GetClassify;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.NetImageHolder;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class HomeHeader extends RecyclerView.ViewHolder {
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.go_loan_list)
    RelativeLayout goLoanList;
    @BindView(R.id.gv_class)
    GridView gvClass;
    @BindView(R.id.marquee)
    MarqueeView marqueeView;


    private List<GetBannerInfo> imgList=new ArrayList<>();
    private BaseAdapter adapter=null;
    private ArrayList<GetClassify> dataList=new ArrayList<>();
    private Context mContext=MyApplication.getContext();
    private List<String> info=new ArrayList<>();
    private int[] indicator=new int[]{R.mipmap.selector_focus,R.mipmap.selector_unfocus};


     public HomeHeader(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        initGridView();
        getImgListRequest();
        loadMarquee();
    }

    private void loadGridDate() {
        dataList.clear();
        dataList.add(new GetClassify(R.mipmap.role,"角色"));
        dataList.add(new GetClassify(R.mipmap.simulation,"模拟"));
        dataList.add(new GetClassify(R.mipmap.action,"动作"));
        dataList.add(new GetClassify(R.mipmap.cads,"卡牌"));
        dataList.add(new GetClassify(R.mipmap.puzzle,"回合"));
        dataList.add(new GetClassify(R.mipmap.tactic,"策略"));
        dataList.add(new GetClassify(R.mipmap.immediately,"即时"));
        dataList.add(new GetClassify(R.mipmap.adventure,"冒险"));
    }

    private void initGridView() {
         loadGridDate();
        if (adapter==null){
            adapter=new GridAdapter(dataList,mContext);
            gvClass.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void loadMarquee() {
        getNoticeRequest();
    }

    private void getNoticeRequest() {
        info.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post()
                        .url(LoanApi.BASE_URL + "noticeList")
                        .build().execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtil.showToast(MyApplication.getContext(), "请求失败");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        JSONObject jsonObject = null;
                        String data = "";
                        try {
                            jsonObject = new JSONObject(s);
                            data = jsonObject.getString("data");
                            Log.i("notice========>",data);
                            JSONArray jsonArray = new JSONArray(data);
                            for (int j = 0; j < jsonArray.length(); j++) {
                                jsonObject = jsonArray.getJSONObject(j);
                                info.add(jsonObject.getString("content"));
                            }
                            Message msg=new Message();
                            if (info.size()>0){
                                msg.what=0x001;
                            }else {
                                msg.what=0x002;
                            }
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }).start();

    }

    @SuppressLint("HandlerLeak")
    private
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0x001:
                   marqueeView.startWithList(info);
                   break;
               case 0x011:
                   List<String> imgs=new ArrayList<>();
                   for (int i=0;i<imgList.size();i++){
                       imgs.add(imgList.get(i).getImage());
                   }
                   banner.setPages(new CBViewHolderCreator() {
                       @Override
                       public Holder createHolder(View itemView) {
                           return new NetImageHolder(itemView,MyApplication.getContext());
                       }

                       @Override
                       public int getLayoutId() {
                           return R.layout.item_banner_image;
                       }
                   },imgs)
                       .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                       .setOnItemClickListener(new OnItemClickListener() {
                           @Override
                           public void onItemClick(int position) {
                               SharedUtilsT.getUser();
                               if(SharedUtilsT.token.equals(""))
                               {
                                   ToastUtil.showToast(mContext,"请先登录");
                               }else
                                {
                                    IntentUtil.toWeb(imgList.get(position).getUrl());
                                }
                           }
                       })
                       .setPageIndicator(indicator)
                       .setPointViewVisible(true)
                       .startTurning(3000);break;
           }
        }
    };


    public void getImgListRequest() {
        imgList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post()
                        .url(LoanApi.BASE_URL + "bannerList")
                        .build().execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtil.showToast(MyApplication.getContext(), "请求失败");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        JSONObject jsonObject = null;
                        String data = "";
                        try {
                            jsonObject = new JSONObject(s);
                            data = jsonObject.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            for (int j = 0; j < jsonArray.length(); j++) {
                                jsonObject = jsonArray.getJSONObject(j);
                                GetBannerInfo bean=new Gson().fromJson(String.valueOf(jsonObject),GetBannerInfo.class);
                                imgList.add(bean);
                            }
                            Message msg=new Message();
                            if (imgList.size()>0){
                                msg.what=0x011;
                            }else {
                                msg.what=0x012;
                            }
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }).start();
    }


    public class BannerViewHolder implements MZViewHolder<String>{
        private RoundedImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner,null);
            mImageView =  view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String res) {
            Glide.with(context).load(res).into(mImageView);
        }

    }


}
