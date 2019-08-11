package com.example.firstapplication.MyFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.firstapplication.R;

/**
 * Created by Administrator on 2019/8/8.
 */
public class ViewImgDetailFragment extends Fragment {
    private ImageView imageView;
    private View view;
    private int datas;
    /**
     * 静态方法，用来创建fragment并为其设置参数
     * @param datas
     * @return
     */
    public static ViewImgDetailFragment createFragment(int datas) {
        ViewImgDetailFragment f = new ViewImgDetailFragment();//创建对象
        Bundle bundle = new Bundle();//创建Bundle对象
        bundle.putInt("datas",datas);
        f.setArguments(bundle);//装入数据源
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //解析Fragment布局文件
        view =inflater.inflate(R.layout.recycler_img_layout, null);
        //获取listView控件
        imageView=(ImageView)view.findViewById(R.id.img);
        return  view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = this.getArguments();
        datas = b.getInt("datas");
        Log.d("aas",datas+"");
        imageView.setImageResource(datas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w("onDestroyView","调用onDestroyView");
    }
}
