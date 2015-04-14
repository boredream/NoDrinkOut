package com.boredream.nodrinkout.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.boredream.nodrinkout.BaseActivity;
import com.boredream.nodrinkout.R;
import com.boredream.nodrinkout.utils.DisplayUtils;
import com.boredream.nodrinkout.view.CropImageView;
import com.boredream.nodrinkout.view.base.CropImage;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * ͼƬ�༭��
 */
public class ImageFilterActivity extends BaseActivity implements OnClickListener {
	private static final int REQUESTCODE_IMAGEFILTER_FACE = 1;
	private static final int REQUESTCODE_IMAGEFILTER_FRAME = 2;
	private static final int REQUESTCODE_IMAGEFILTER_CROP = 3;
	private static final int REQUESTCODE_IMAGEFILTER_EFFECT = 4;
	
	private static final int SHOW_PROGRESS = 0;
	private static final int REMOVE_PROGRESS = 1;
	
	private LinearLayout mImageFilterTopBar;
	private Button mCancel;
	private Button mFinish;
	private ImageButton mBack;
	private ImageButton mForward;
	private CropImageView mDisplay;
	private ProgressBar mProgressBar;
	private CropImage mCropImage;
	
	private LinearLayout mImageFilterBottomBar;
	private Button mCut;
	private Button mEffect;
	private Button mFace;
	private Button mFrame;
	
	private LinearLayout mCropBar;
	private Button mLeft;
	private Button mRight;

	private String mOldPath;// ��ͼƬ��ַ
	private Bitmap mOldBitmap;// ��ͼƬ
	private Bitmap mNewBitmap;// ��ͼƬ
	private boolean mIsOld = true;// �Ƿ���ѡ���˾�ͼƬ
	private boolean mIsSetResult = false;// �Ƿ���Ҫ��������
	
	// 0-�ޱ༭ģʽ 1-�ü� 2-�˾� 3-��ͼ 4-�߿�
	private int currentFilterType = 0;
	
	/**
	 * cropͼƬ���ƽ�����
	 */
	Handler cropHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_PROGRESS:
				mProgressBar.setVisibility(View.VISIBLE);
				break;
			case REMOVE_PROGRESS:
				cropHandler.removeMessages(SHOW_PROGRESS);
				mProgressBar.setVisibility(View.INVISIBLE);
				break;
			}
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imagefilter);
		
		initView();
		init();
	}

	private void initView() {
		mImageFilterTopBar = (LinearLayout) findViewById(R.id.ll_imagefilter_topbar);
		mCancel = (Button) findViewById(R.id.imagefilter_cancel);
		mFinish = (Button) findViewById(R.id.imagefilter_finish);
		mBack = (ImageButton) findViewById(R.id.imagefilter_back);
		mForward = (ImageButton) findViewById(R.id.imagefilter_forward);
		mDisplay = (CropImageView) findViewById(R.id.imagefilter_display);
		mProgressBar = (ProgressBar) findViewById(R.id.imagefilter_crop_progressbar);
		
		mImageFilterBottomBar = (LinearLayout) findViewById(R.id.ll_imagefilter_bar);
		mCut = (Button) findViewById(R.id.imagefilter_cut);
		mEffect = (Button) findViewById(R.id.imagefilter_effect);
		mFace = (Button) findViewById(R.id.imagefilter_face);
		mFrame = (Button) findViewById(R.id.imagefilter_frame);
		
		mCropBar = (LinearLayout) findViewById(R.id.ll_imagefilter_crop);
		mLeft = (Button) findViewById(R.id.imagefilter_crop_left);
		mRight = (Button) findViewById(R.id.imagefilter_crop_right);
		
		mCancel.setOnClickListener(this);
		mFinish.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mForward.setOnClickListener(this);
		mCut.setOnClickListener(this);
		mEffect.setOnClickListener(this);
		mFace.setOnClickListener(this);
		mFrame.setOnClickListener(this);
		mLeft.setOnClickListener(this);
		mRight.setOnClickListener(this);
	}

	private void init() {
		// ��ʼ�����水ť��Ϊ������
		mBack.setImageResource(R.drawable.image_action_back_arrow_normal);
		mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
		mBack.setEnabled(false);
		mForward.setEnabled(false);
		// ��ȡ�Ƿ񷵻�����
		mIsSetResult = getIntent().getBooleanExtra("isSetResult", false);
		// ���մ��ݵ�ͼƬ��ַ
		mOldPath = getIntent().getStringExtra("path");
		ImageSize targetImageSize = new ImageSize(DisplayUtils.getScreenWidthPixels(this), 
				DisplayUtils.getScreenHeightPixels(this));
		// ����ͼƬ,ͬ����ȡ
		mOldBitmap = imageLoader.loadImageSync("file://" + mOldPath, targetImageSize);
		mDisplay.setImageBitmap(mOldBitmap);
		mNewBitmap = mOldBitmap;
	}
	
	/**
	 * ��ʼ��cropͼƬ
	 */
	private void resetImageView(Bitmap b) {
		mDisplay.clear();
		mDisplay.setImageBitmap(b);
		mDisplay.setImageBitmapResetBase(b, true);
		mCropImage = new CropImage(this, mDisplay, cropHandler);
		mCropImage.crop(b);
	}
	
	private void intent2MoreFilterActivity(Class<? extends BaseActivity> activity, int requestCode) {
		Intent intent = new Intent(this, activity);
		intent.putExtra("path", mIsOld ? mOldBitmap : mNewBitmap);
		startActivityForResult(intent, requestCode);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imagefilter_cancel:
			if(currentFilterType == 1) {
				mCropImage.cropCancel();
			} else {
				finish();
			}
			break;
		case R.id.imagefilter_finish:
			if(currentFilterType == 1) {
				mNewBitmap = mCropImage.cropAndSave();
				mIsOld = false;
			} else {
				// �ж��Ƿ�Ҫ��������
				if (mIsSetResult) {
					// �����Ƿ�ѡ���ͼƬ����ͼƬ��ַ
					Intent intent = new Intent();
					intent.putExtra("image", mIsOld ? mOldBitmap : mNewBitmap);
					setResult(RESULT_OK, intent);
				} else {
					// �����Ƿ�ѡ���ͼƬ���һ���µ�ͼƬ����ת���ϴ�ͼƬ����
					Intent intent = new Intent(this, AddInfoActivity.class);
					intent.putExtra("image", mIsOld ? mOldBitmap : mNewBitmap);
					startActivity(intent);
				}
				finish();
			}
			
			break;
		case R.id.imagefilter_back:
			// ѡ���ͼƬ
			mIsOld = true;
			mBack.setImageResource(R.drawable.image_action_back_arrow_normal);
			mForward.setImageResource(R.drawable.image_filter_action_forward_arrow);
			mBack.setEnabled(false);
			mForward.setEnabled(true);
			mDisplay.setImageBitmap(mOldBitmap);
			break;
		case R.id.imagefilter_forward:
			// ѡ����ͼƬ
			mIsOld = false;
			mBack.setImageResource(R.drawable.image_filter_action_back_arrow);
			mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
			mBack.setEnabled(true);
			mForward.setEnabled(false);
			mDisplay.setImageBitmap(mNewBitmap);
			break;
		case R.id.imagefilter_cut:
			currentFilterType = 1;
			
			mImageFilterTopBar.setVisibility(View.GONE);
			mImageFilterBottomBar.setVisibility(View.GONE);
			mCropBar.setVisibility(View.VISIBLE);
			resetImageView(mOldBitmap);
			break;
		case R.id.imagefilter_crop_left:
			// ����ת
			mCropImage.startRotate(270f);
			break;
		case R.id.imagefilter_crop_right:
			// ����ת
			mCropImage.startRotate(90f);
			break;
		case R.id.imagefilter_effect:
			// ��ת����Ч����,������ͼƬ��ַ
			intent2MoreFilterActivity(ImageFilterEffectActivity.class,
					REQUESTCODE_IMAGEFILTER_EFFECT);
			break;
		case R.id.imagefilter_face:
			// ��ת���������,������ͼƬ��ַ
			intent2MoreFilterActivity(ImageFilterFaceActivity.class, 
					REQUESTCODE_IMAGEFILTER_FACE);
			break;
		case R.id.imagefilter_frame:
			// ��ת���߿����,������ͼƬ��ַ
			intent2MoreFilterActivity(ImageFilterFrameActivity.class, 
					REQUESTCODE_IMAGEFILTER_FRAME);
			break;
		default:
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			// �����޸ĺ��ͼƬ��ַ,������
			mNewBitmap = data.getParcelableExtra("bitmap");
			mIsOld = false;
			mBack.setImageResource(R.drawable.image_filter_action_back_arrow);
			mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
			mBack.setEnabled(true);
			mForward.setEnabled(false);
			mDisplay.setImageBitmap(mNewBitmap);

		}
	}
}
