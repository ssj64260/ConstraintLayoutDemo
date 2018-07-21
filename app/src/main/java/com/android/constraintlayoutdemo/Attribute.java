package com.android.constraintlayoutdemo;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private static final String[] names = {
            "HP", "攻击", "防御", "特攻", "特防", "速度"
    };

    private static final int[] values = {
            78, 84, 78, 109, 85, 100
    };

    private static final int[] resourceIds = {
            R.drawable.shape_bg_hp,
            R.drawable.shape_bg_attack,
            R.drawable.shape_bg_defense,
            R.drawable.shape_bg_s_attack,
            R.drawable.shape_bg_s_defense,
            R.drawable.shape_bg_speed
    };

    private String name;
    private int value;
    private int resourceId;

    private boolean hadShowAnimation = false;

    public static List<Attribute> createList() {
        final List<Attribute> attributeList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            final Attribute attribute = new Attribute();
            attribute.setName(names[i]);
            attribute.setValue(values[i]);
            attribute.setResourceId(resourceIds[i]);
            attributeList.add(attribute);
        }
        return attributeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public boolean getHadShowAnimation() {
        return hadShowAnimation;
    }

    public void setHadShowAnimation(boolean hadShowAnimation) {
        this.hadShowAnimation = hadShowAnimation;
    }
}
