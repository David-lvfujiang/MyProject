package com.example.firstapplication.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.firstapplication.R;
import com.example.firstapplication.adapter.RcvBaseAdapter;

import java.util.ArrayList;

/**
 * 2019.8.10
 * 程序主界面,recyclerView展示相册所有图片
 * 吕福奖
 */
public class RecyclerViewActivity extends FragmentActivity {
    private RecyclerView recyclerView;
    private RcvBaseAdapter rcvBaseAdapter;
    private static ArrayList<Integer> imgList;
    private int[] imgDatas = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.seven};
    final private int DATAS_LENGHT=50;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        init();//初始化控件
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < DATAS_LENGHT; i++) {
                    imgList.add(imgDatas[i%imgDatas.length]);
                }
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                RcvBaseAdapter.OnItemClickListener listener = new RcvBaseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        index = position;
                       // Toast.makeText(RecyclerViewActivity.this,"这是第"+(position+1)+"张图片",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(RecyclerViewActivity.this,ImgDetailActivity.class);
                        intent.putExtra("position", position);
                        intent.putIntegerArrayListExtra("imgList", (ArrayList<Integer>) imgList);
                        startActivityForResult(intent,0);


                    }

                };
                 rcvBaseAdapter = new RcvBaseAdapter(imgList,listener);//创建适配器
                recyclerView.setAdapter(rcvBaseAdapter);




            }
        }).start();


    }

    /**
     * 接受activity的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0&&resultCode == 200) {
//            imgList.remove(index);//更新数据源
//            rcvBaseAdapter.notifyDataSetChanged();//更新适配器
            rcvBaseAdapter.removeData(index);//删除item
        }

    }

    /**
     * 初始化方法
     */
    public void init()
{
    recyclerView = (RecyclerView)this.findViewById(R.id.rview);
    imgList=new ArrayList<Integer>();

}



}
