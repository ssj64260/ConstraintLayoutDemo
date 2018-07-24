package com.android.constraintlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewStub viewstubNameAndPicture;
    private ViewStub viewstubAbilityAndProperty;
    private ViewStub viewstubAttribute;

    private RecyclerView rvAttributeList;

    private List<Attribute> mAttributeList;
    private AttributeListAdapter mAttributeAdapter;

    private Handler mHandler;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void initData() {
        super.initData();
        mHandler = new Handler(Looper.getMainLooper());

        mAttributeList = Attribute.createList();
        mAttributeAdapter = new AttributeListAdapter(this, mAttributeList);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarTitle(R.string.app_name);

        viewstubNameAndPicture = findViewById(R.id.viewstub_name_and_picture);
        viewstubAbilityAndProperty = findViewById(R.id.viewstub_ability_and_property);
        viewstubAttribute = findViewById(R.id.viewstub_attribute);

        loadView();
    }

    private void loadView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                loadNameAndPicture();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                loadAbilityAndProperty();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                loadEthnicValue();
            }
        }).start();
    }

    private void loadNameAndPicture() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                viewstubNameAndPicture.inflate();
            }
        });
    }

    private void loadAbilityAndProperty() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                viewstubAbilityAndProperty.inflate();
            }
        });
    }

    private void loadEthnicValue() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                viewstubAttribute.inflate();

                rvAttributeList = findViewById(R.id.rv_attribute_list);
                rvAttributeList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvAttributeList.setFocusable(false);
                rvAttributeList.setNestedScrollingEnabled(false);
                rvAttributeList.setHasFixedSize(true);
                rvAttributeList.setAdapter(mAttributeAdapter);
            }
        });
    }
}
