package com.MyLibrary.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver {
	private Context mCtx;
	private Bundle bundle;
	private SmsMessage[] msgs;
	private SmsListener listen;

	private String str;
	private String phoneNo;
	
	public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	public static final String SMS_MIME_TYPE = "vnd.android-dir/mms-sms";
	
	public SmsBroadcastReceiver(Context ctx, SmsListener listener){
		mCtx = ctx;
		listen = listener;
	}

	public class SmsBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context,Intent intent) {
			if (intent.getAction().equals(SMS_RECEIVED)) {
				new SmsTask().execute(intent);
			}
		}
	}
	
	public class SmsTask extends AsyncTask<Intent, Void, Void>{

		@Override
		protected Void doInBackground(Intent... intent) {
				bundle = intent[0].getExtras();
				msgs = null;
				str = "";
				phoneNo = null;

				if (bundle != null) {
					Object[] pdus = (Object[]) bundle.get("pdus");
					msgs = new SmsMessage[pdus.length];

					for (int i = 0; i < msgs.length; i++) {
						msgs[i] = SmsMessage
								.createFromPdu((byte[]) pdus[i]);
						str += msgs[i].getMessageBody().toString();
						phoneNo = msgs[i].getDisplayOriginatingAddress();
					}
					
					listen.SmsReceived(str, phoneNo);
				}
		return null;
	}
  }
	
	public abstract class SmsListener{
		public abstract void SmsReceived(String message, String phoneNo);
	}
}
