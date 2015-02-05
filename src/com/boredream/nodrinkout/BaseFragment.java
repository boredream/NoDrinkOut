package com.boredream.nodrinkout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BaseFragment extends Fragment {

	protected MainActivity activity;
	private String tag = getClass().getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		Log.i("DDD", tag + "... onAttach");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i("DDD", tag + "... onCreate");
		super.onCreate(savedInstanceState);
		activity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("DDD", tag + "... onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i("DDD", tag + "... onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		Log.i("DDD", tag + "... onStart");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i("DDD", tag + "... onResume");
		super.onResume();
	}

	// //////////////////////

	@Override
	public void onPause() {
		Log.i("DDD", tag + "... onPause");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i("DDD", tag + "... onStop");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		Log.i("DDD", tag + "... onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.i("DDD", tag + "... onDestroy");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.i("DDD", tag + "... onDetach");
		super.onDetach();
	}

}
