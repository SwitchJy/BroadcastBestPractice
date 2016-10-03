package com.example.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(final Context context, Intent intent) {
		AlertDialog.Builder dialogBuiledr=new AlertDialog.Builder(context);
		dialogBuiledr.setTitle("Warning");
		dialogBuiledr.setMessage("You are forced to be offline.Please try to login again.");
		//对话框不可取消
		dialogBuiledr.setCancelable(false);
		//点击按钮，销毁掉所有活动,并重新启动登录界面
		dialogBuiledr.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityCollector.finishAll();//销毁所有活动
				Intent intent=new Intent(context,LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);//重新启动LoginActivity
				
			}
		});
		//需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
		AlertDialog alertDialog=dialogBuiledr.create();
		
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
		
	}

}
