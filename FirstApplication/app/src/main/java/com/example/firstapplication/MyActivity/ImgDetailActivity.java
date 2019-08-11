package com.example.firstapplication.MyActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firstapplication.R;
import com.example.firstapplication.MyFragment.ViewImgDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片详细展示Activity
 * 2019.8.10
 * 吕福奖
 */
public class ImgDetailActivity extends FragmentActivity {
    private ArrayList<Integer> imgList;//图片数据源
    private int position;//点击的位置
    private ArrayList<ViewImgDetailFragment> viewImgDetailFragment;//fragment数据源
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private AlertDialog dialog;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_detail);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        Intent intent = getIntent();
        init(intent);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        viewImgDetailFragment = new ArrayList<ViewImgDetailFragment>();
        for (int i = 0; i < imgList.size(); i++) {
            viewImgDetailFragment.add(ViewImgDetailFragment.createFragment(imgList.get(i)));
        }
        myAdapter = new MyAdapter(fragmentManager, viewImgDetailFragment);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(position);//设置默认加载的页面


    }

    /**
     * 删除图片方法
     * @param view
     */
    public void deletePagerView(View view) {

        position = viewPager.getCurrentItem();//获取当前页的码数
        //Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(ImgDetailActivity.this);
        normalDialog.setMessage("所选文件将会删除。是否删除？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewImgDetailFragment.remove(position);//更新数据源
                        myAdapter.notifyDataSetChanged();//通知适配器UI更新
                        viewPager.setAdapter(myAdapter);//viewpager重新加载
                        viewPager.setCurrentItem(position - 1);//删除后viewpager默认显示前一张
                        setResult(200);//向上一层activity发送成功码
                        //finish();相当于返回键
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        normalDialog.show();
    }

    /**
     * 初始化方法
     * @param intent
     */
    public void init(Intent intent)
    {

        position = intent.getIntExtra("position", 0);
        imgList = intent.getIntegerArrayListExtra("imgList");
    }

    /**
     * 定义FragmentPager适配器类
     */
    class MyAdapter extends FragmentStatePagerAdapter {
        private List<ViewImgDetailFragment> fragments;//fragment管理员
        private FragmentManager fm;//fragment数组

        /**
         * 构造函数
         *
         * @param fm           fragment管理员对象
         * @param fragmentList fragment数组
         */
        public MyAdapter(FragmentManager fm, List<ViewImgDetailFragment> fragmentList) {
            super(fm);
            this.fragments = fragmentList;
            this.fm = fm;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }


}
