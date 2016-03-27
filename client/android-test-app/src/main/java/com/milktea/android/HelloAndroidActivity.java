package com.milktea.android;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.milktea.common.dto.CommonResponse;
import com.milktea.common.dto.UserContext;
import com.milktea.common.dto.maketransaction.TransactionDetailRequest;
import com.milktea.common.dto.registeruser.RegisterUserRequest;
import com.milktea.common.dto.registeruser.RegisterUserResponse;
import com.milktea.service.MilkteaClientService;
import com.milktea.service.MilkteaClientServiceImpl;
import com.smt.android.R;

public class HelloAndroidActivity extends Activity {

	private static final String URL = "http://1.milktea.duapp.com";

	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WebServiceTask task = new WebServiceTask();
		task.execute();
		try {
			CommonResponse response = task.get();
			Log.i("milktea", response.getStatus());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.smt.android.R.menu.main, menu);
		return true;
	}

	private class WebServiceTask extends
			AsyncTask<Void, Void, CommonResponse> {

		@Override
		protected CommonResponse doInBackground(Void... arg0) {
			MilkteaClientService consumer = new MilkteaClientServiceImpl(URL);
			RegisterUserRequest request = new RegisterUserRequest();
			request.setUserContext(createValidNewShopUserContext3());
			request.setNumberOfCupsToRedeem(10);
			consumer.registerShop(request);
			TransactionDetailRequest makeTransactionRequest = new TransactionDetailRequest();
			makeTransactionRequest.setUserContext(createValidNewShopUserContext3());
			makeTransactionRequest.setCustomerId("13003109241");
			makeTransactionRequest.setNumberOfCups(3);
			return consumer.makeTransaction(makeTransactionRequest);
		}
		
		private UserContext createValidNewShopUserContext3() {
	    	UserContext userContext = new UserContext();
			userContext.setUsername("0423747419");
			userContext.setPassword("password");
			return userContext;
		}

	}

}
