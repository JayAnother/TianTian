package com.common.widget.wheel;

import com.common.widget.wheel.model.WheelPickerEntity;

/**
 * Created by jay on 2017/4/20.
 */

public abstract class WheelPickerCallback {
    private final WheelPickerEntity oldValue = new WheelPickerEntity();
    private final WheelPickerEntity newValue = new WheelPickerEntity();

    public WheelPickerCallback() {
    }

    public WheelPickerEntity getOldValue() {
        return oldValue;
    }

    public WheelPickerEntity getNewValue() {
        return newValue;
    }

    public abstract void onCancel();
    public abstract void onScrolling(WheelPickerEntity oldValue, WheelPickerEntity newValue);
    public abstract void onDone(WheelPickerEntity oldValue, WheelPickerEntity newValue);
}
