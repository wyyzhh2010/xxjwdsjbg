package cjh.smile.animation.util;



import android.app.Activity;

import com.xxjwd.sjbg.R;
/**
 * ����Ч����
 * 
 * @author Administrator
 * 
 */
public class ActivityAnimator {
	/**
	 * ˮƽ����Ч��
	 * 
	 * @param a
	 */
	public void flipHorizontalAnimation(Activity a) {
		a.overridePendingTransition(R.anim.flip_horizontal_in,
				R.anim.flip_horizontal_out);
	}

	public void flipHorizontalBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.flip_horizontal_in,
				R.anim.flip_horizontal_out);
	}

	/**
	 * ��ֱ����
	 * 
	 * @param a
	 */
	public void flipVerticalAnimation(Activity a) {
		a.overridePendingTransition(R.anim.flip_vertical_in,
				R.anim.flip_vertical_out);
	}

	public void flipVerticalBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.flip_vertical_in,
				R.anim.flip_vertical_out);
	}

	/**
	 * ���䶯��
	 * 
	 * @param a
	 */
	public void fadeAnimation(Activity a) {
		a.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	public void fadeBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	/**
	 * ���Ͻ��˳�����
	 * 
	 * @param a
	 */
	public void disappearTopLeftAnimation(Activity a) {
		a.overridePendingTransition(R.anim.disappear_top_left_in,
				R.anim.disappear_top_left_out);
	}

	public void disappearTopLeftBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.appear_top_left_in,
				R.anim.appear_top_left_out);
	}

	/**
	 * ���½ǽ��붯��
	 * 
	 * @param a
	 */
	public void appearBottomRightAnimation(Activity a) {
		a.overridePendingTransition(R.anim.appear_bottom_right_in,
				R.anim.appear_bottom_right_out);
	}

	/**
	 * ���½��˳�����
	 * 
	 * @param a
	 */
	public void appearBottomRightBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.disappear_bottom_right_in,
				R.anim.disappear_bottom_right_out);
	}

	/**
	 * ���Ŷ�������
	 * 
	 * @param a
	 */
	public void unzoomAnimation(Activity a) {
		a.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
	}

	/**
	 * ���Ŷ����˳�
	 * 
	 * @param a
	 */
	public void unzoomBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
	}

	/**
	 * ���Ч��-����
	 * 
	 * @param a
	 */
	public void stackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.open_next, R.anim.close_main);
	}

	/**
	 * ���Ч��-�˳�
	 * 
	 * @param a
	 */
	public void stackBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}

	/**
	 * ˮƽƽ��Ч��-�ҽ�����
	 * 
	 * @param a
	 */
	public void slideLeftRightAnimation(Activity a) {
		a.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}

	/**
	 * ˮƽƽ��Ч��-����ҳ�
	 * 
	 * @param a
	 */
	public void slideLeftRightBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
	}

	/**
	 * ��ֱƽ��Ч��1-
	 * 
	 * @param a
	 */
	public void slideTopBottomAnimation(Activity a) {
		a.overridePendingTransition(R.anim.slide_top_in,
				R.anim.slide_bottom_out);
	}

	/**
	 * ��ֱƽ��Ч��1-
	 * 
	 * @param a
	 */
	public void slideTopBottomBackAnimation(Activity a) {
		a.overridePendingTransition(R.anim.slide_bottom_in,
				R.anim.slide_top_out);
	}

}
