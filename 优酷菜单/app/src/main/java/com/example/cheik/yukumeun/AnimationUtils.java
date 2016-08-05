package com.example.cheik.yukumeun;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Cheik on 16/8/5.
 */
public class AnimationUtils {
    //正在运行的动画个数
    public static int runingAnimationCount = 0;

    /*
     * 转出动画
     */
    public static void rotateOutAnimation(RelativeLayout layout, long delay){

        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++){
            //如果隐藏 找到所有子控件 禁用
            layout.getChildAt(i).setEnabled(false);
        }

        RotateAnimation ra = new RotateAnimation(
                0f, -180f, // 开始, 结束的角度, 逆时针
                Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
                Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
        ra.setDuration(500);//设置动画时间
        ra.setFillAfter(true);//设置动画结束后停在动画结束的位置
        ra.setStartOffset(delay);
        ra.setAnimationListener(new MyAnimationListener()); // 添加监听
        layout.startAnimation(ra);
    }

    /*
        转入动画
     */
    public  static void rotateInAnimation(RelativeLayout layout,long delay){
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++){
            //如果显示 找到所有子控件 可用
            layout.getChildAt(i).setEnabled(true);
        }
        RotateAnimation ra = new RotateAnimation(
                -180f,0f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,1.0f);

        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(delay);
        ra.setAnimationListener(new MyAnimationListener()); // 添加监听
        layout.startAnimation(ra);

    }


    private static class MyAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            runingAnimationCount ++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runingAnimationCount -- ;
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
}
