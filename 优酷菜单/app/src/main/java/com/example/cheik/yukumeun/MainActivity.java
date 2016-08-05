package com.example.cheik.yukumeun;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rl_level1;
    RelativeLayout rl_level2;
    RelativeLayout rl_level3;

    boolean isLevel3Display = true;
    boolean isLevel2Display = true;
    boolean isLevel1Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initViews();
    }

    private void initViews(){
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_menu).setOnClickListener(this);

        rl_level1 = (RelativeLayout)findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout)findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout)findViewById(R.id.rl_level3);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // keyCode 事件码
        System.out.println("onKeyDown: " + keyCode);
        if(keyCode == KeyEvent.KEYCODE_MENU){

            if(AnimationUtils.runingAnimationCount > 0){
                // 当前有动画正在执行, 取消当前事件
                return true;
            }

//			如果按下的是菜单按钮
            if(isLevel1Display){
                long delay = 0;
                // 隐藏三级菜单
                if(isLevel3Display){
                    AnimationUtils.rotateOutAnimation(rl_level3, 0);
                    isLevel3Display = false;
                    delay += 200;
                }

                // 隐藏二级菜单
                if(isLevel2Display){
                    AnimationUtils.rotateOutAnimation(rl_level2, delay);
                    isLevel2Display = false;
                    delay += 200;
                }

                // 隐藏一级菜单
                AnimationUtils.rotateOutAnimation(rl_level1, delay);

            }else {
                // 顺次转进来
                AnimationUtils.rotateInAnimation(rl_level1, 0);
                AnimationUtils.rotateInAnimation(rl_level2, 200);
                AnimationUtils.rotateInAnimation(rl_level3, 400);

                isLevel3Display = true;
                isLevel2Display = true;
            }
            isLevel1Display = !isLevel1Display;

            return true;// 消费了当前事件
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {
        if(AnimationUtils.runingAnimationCount > 0){
            // 当前有动画正在执行, 取消当前事件
            return;
        }
        switch (view.getId()){

            case R.id.ib_home:{
                //第二个已经显示 转出去
                if(isLevel2Display) {
                    long delay = 0;
                    if (isLevel3Display) {
                        AnimationUtils.rotateOutAnimation(rl_level3, 0);
                        isLevel3Display = false;
                        delay += 200;
                    }
                    AnimationUtils.rotateOutAnimation(rl_level2, delay);
                }else{
                    //第二个已经转出去  显示
                    AnimationUtils.rotateInAnimation(rl_level2,0);
                }

                isLevel2Display = !isLevel2Display;
            }
            break;
            case R.id.ib_menu:{
                //第三个已经显示  转出去
                if(isLevel3Display){
                    AnimationUtils.rotateOutAnimation(rl_level3,0);

                }else {
                    //第三个已经转出去 显示
                    AnimationUtils.rotateInAnimation(rl_level3,0);
                }
                isLevel3Display = !isLevel3Display;
            }
            break;
            default:
                break;
        }
    }
}
