package com.android.constraintlayoutdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView rvAttributeList;

    private List<Attribute> mAttributeList;
    private AttributeListAdapter mAttributeAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mAttributeList = Attribute.createList();
        mAttributeAdapter = new AttributeListAdapter(this, mAttributeList);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarTitle(R.string.app_name);

        rvAttributeList = findViewById(R.id.rv_attribute_list);
        rvAttributeList.setLayoutManager(new LinearLayoutManager(this));
        rvAttributeList.setFocusable(false);
        rvAttributeList.setNestedScrollingEnabled(false);
        rvAttributeList.setHasFixedSize(true);
        rvAttributeList.setAdapter(mAttributeAdapter);
    }
}
