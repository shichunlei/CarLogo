package com.chingtech.carlogo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.chingtech.carlogo.R;
import com.chingtech.library.base.activity.BaseActivity;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * CarLogo
 * Package com.chingtech.carlogo.view
 * Description:
 * Created by 师春雷
 * Created at 18/4/13 下午12:53
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash)
    LinearLayout layout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        initAnim();
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    private void initAnim() {
        // 透明度动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(layout, "alpha", 0.1f, 1.0f);
        animator.setDuration(5000);//动画时间5秒
        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                openActivity(MainActivity.class, true);
            }
        });
    }
}
