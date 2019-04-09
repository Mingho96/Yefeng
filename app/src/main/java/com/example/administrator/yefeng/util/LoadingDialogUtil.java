package com.example.administrator.yefeng.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.administrator.yefeng.R;


public  class LoadingDialogUtil {
    @SuppressLint("StaticFieldLeak")
    private static ImageView imageView;
    private static Dialog dialog;
    private static   long startTime;
    private static long duration;

    public static void initDialog(Context context,long d){
       duration=d;
       dialog=new Dialog(context,R.style.Dialog_NoBG);
       View dialogView=LayoutInflater.from(context).inflate(R.layout.loading_dialog,null);
       dialog.setContentView(dialogView);
       Window dialogWindow=dialog.getWindow();
       dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
       imageView=dialogView.findViewById(R.id.img_load);
       final RotateAnimation rotate  = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
       LinearInterpolator lin = new LinearInterpolator();
       rotate.setInterpolator(lin);
       rotate.setDuration(1000);
       rotate.setFillAfter(true);
       rotate.setStartOffset(10);
       rotate.setRepeatCount(-1);
       if (rotate!=null){
           imageView.startAnimation(rotate);
       }else {
           imageView.setAnimation(rotate);
           imageView.startAnimation(rotate);
       }
       dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
           @Override
           public void onDismiss(DialogInterface dialog) {
               imageView.clearAnimation();
           }
       });
       dialog.show();
       startTime= System.currentTimeMillis();
       new Thread(new Runnable() {
           @Override
           public void run() {
               while(true){
                   Message message=new Message();
                   try {
                       Thread.sleep(100);
                       if (System.currentTimeMillis()-startTime>=duration){
                           message.what=0x100001;
                           handler.sendMessage(message);
                           break;
                       }
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }).start();
    }

    private static void closeDialog(){
        try{
            dialog.dismiss();

        }catch (NullPointerException e){
            Log.i("error","对话框没初始化");
        }catch (Exception e){
            e.printStackTrace();
        }
   }

   @SuppressLint("HandlerLeak")
   private static Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case 0x100001:closeDialog();break;
           }
       }
   };

}
