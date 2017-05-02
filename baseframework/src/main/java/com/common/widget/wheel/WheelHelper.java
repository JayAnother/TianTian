package com.common.widget.wheel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.R;
import com.common.widget.wheel.adapters.WheelPickerAdapter;
import com.common.widget.wheel.model.WheelConfigModel;

import static com.common.R.id.ctvCancel;
import static com.common.R.id.ctvSet;

public class WheelHelper {
    private WheelConfigModel leftWheelPickerEntity;
    private WheelConfigModel rightWheelPickEntity;
    private WheelConfigModel middleWheelPickEntity;
    private int leftIndex;
    private int rightIndex;
    private int middleIndex;
    private Dialog mCustomDialog;
    public WheelPickerAdapter rightAdapter,middleAdapter,leftAdapter;

    public WheelHelper(Context context, WheelConfigModel wheelPickerEntity) {
        leftWheelPickerEntity = wheelPickerEntity;
        leftIndex = leftWheelPickerEntity.getCurrentIndex();
        initWheel(context);
    }

    public WheelHelper(Context context, WheelConfigModel wheelPickerEntity, WheelConfigModel rightWheelPickEntity) {
        this.leftWheelPickerEntity = wheelPickerEntity;
        this.rightWheelPickEntity = rightWheelPickEntity;
        leftIndex = leftWheelPickerEntity.getCurrentIndex();
        rightIndex = rightWheelPickEntity.getCurrentIndex();
        initWheel(context);
    }

    public WheelHelper(Context context,  WheelConfigModel leftWheelPickerEntity,WheelConfigModel middleWheelPickEntity, WheelConfigModel rightWheelPickEntity) {
        this.leftWheelPickerEntity = leftWheelPickerEntity;
        this.rightWheelPickEntity = rightWheelPickEntity;
        this.middleWheelPickEntity = middleWheelPickEntity;
        leftIndex = leftWheelPickerEntity.getCurrentIndex();
        middleIndex = middleWheelPickEntity.getCurrentIndex();
        rightIndex = rightWheelPickEntity.getCurrentIndex();
        initThreeWheel(context);
    }

    public void initThreeWheel(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_wheelpicker_three, null);
        RelativeLayout rlContainer = (RelativeLayout) dialogView.findViewById(R.id.rlContainer);
        TextView tvCancel = (TextView) dialogView.findViewById(ctvCancel);
        TextView tvSet = (TextView) dialogView.findViewById(ctvSet);

        mCustomDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        Window dialogWindow = mCustomDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mCustomDialog.setContentView(dialogView);

        WheelView wvLeft = (WheelView) dialogView.findViewById(R.id.wvLeft);
        WheelView wvMiddle = (WheelView) dialogView.findViewById(R.id.wvMiddle);
        WheelView wvRight = (WheelView) dialogView.findViewById(R.id.wvRight);

        //left
        leftAdapter = new WheelPickerAdapter(context, leftWheelPickerEntity.getData());
        wvLeft.setViewAdapter(leftAdapter);
        wvLeft.setCurrentItem(leftIndex);
        wvLeft.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                leftIndex = wheel.getCurrentItem();
                leftWheelPickerEntity.setCurrentIndex(leftIndex);
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().scroll(leftIndex);
                }
            }
        });
        //middle
        middleAdapter = new WheelPickerAdapter(context, middleWheelPickEntity.getData());
        wvMiddle.setViewAdapter(middleAdapter);
        wvMiddle.setCurrentItem(middleIndex);
        wvMiddle.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                middleIndex = wheel.getCurrentItem();
                middleWheelPickEntity.setCurrentIndex(middleIndex);
                if (middleWheelPickEntity.getWheelDoneCallback() != null) {
                    middleWheelPickEntity.getWheelDoneCallback().scroll(middleIndex);
                }
            }
        });
        //right

        rightAdapter = new WheelPickerAdapter(context, rightWheelPickEntity.getData());
        wvRight.setViewAdapter(rightAdapter);
        wvRight.setCurrentItem(rightIndex);
        wvRight.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                rightIndex = wheel.getCurrentItem();
                rightWheelPickEntity.setCurrentIndex(rightIndex);
                if (rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().scroll(rightIndex);
                }
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().cancel();
                }
                if (middleWheelPickEntity != null && middleWheelPickEntity.getWheelDoneCallback() != null) {
                    middleWheelPickEntity.getWheelDoneCallback().cancel();
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().cancel();
                }
                mCustomDialog.dismiss();
            }
        });
        mCustomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().cancel();
                }
                if (middleWheelPickEntity.getWheelDoneCallback() != null) {
                    middleWheelPickEntity.getWheelDoneCallback().cancel();
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().cancel();
                }
            }
        });
        tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(leftWheelPickerEntity.getData().size()<=leftIndex||
                        middleWheelPickEntity.getData().size()<=middleIndex||
                        rightWheelPickEntity.getData().size()<=rightIndex){
                    return;
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().done(rightIndex);
                }

                mCustomDialog.dismiss();
            }
        });

        rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialog.dismiss();
            }
        });
    }


    private void initWheel(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_wheelpicker_two, null);
        RelativeLayout rlContainer = (RelativeLayout) dialogView.findViewById(R.id.rlContainer);
        WheelView wvLeft = (WheelView) dialogView.findViewById(R.id.wvLeft);
        TextView tvCancel = (TextView) dialogView.findViewById(ctvCancel);
        TextView tvSet = (TextView) dialogView.findViewById(ctvSet);
        WheelView wvRight = (WheelView) dialogView.findViewById(R.id.wvRight);
        mCustomDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        Window dialogWindow = mCustomDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mCustomDialog.setContentView(dialogView);
        leftAdapter = new WheelPickerAdapter(context, leftWheelPickerEntity.getData());
        wvLeft.setViewAdapter(leftAdapter);
        wvLeft.setCurrentItem(leftIndex);
        wvLeft.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                leftIndex = wheel.getCurrentItem();
                leftWheelPickerEntity.setCurrentIndex(leftIndex);
            }
        });
        if (rightWheelPickEntity != null) {
            rightAdapter = new WheelPickerAdapter(context, rightWheelPickEntity.getData());
            wvRight.setViewAdapter(rightAdapter);
            wvRight.setCurrentItem(rightIndex);
            wvRight.addChangingListener(new OnWheelChangedListener() {
                @Override
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    rightIndex = wheel.getCurrentItem();
                    rightWheelPickEntity.setCurrentIndex(rightIndex);
                }
            });
        } else {
            wvRight.setVisibility(View.GONE);
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().cancel();
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().cancel();
                }
                mCustomDialog.dismiss();
            }
        });
        mCustomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().cancel();
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().cancel();
                }
            }
        });
        tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leftWheelPickerEntity.getWheelDoneCallback() != null) {
                    leftWheelPickerEntity.getWheelDoneCallback().done(leftIndex);
                }
                if (rightWheelPickEntity != null && rightWheelPickEntity.getWheelDoneCallback() != null) {
                    rightWheelPickEntity.getWheelDoneCallback().done(rightIndex);
                }

                mCustomDialog.dismiss();
            }
        });

        rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialog.dismiss();
            }
        });
    }

    public void showWheel() {
        mCustomDialog.show();
    }

    public void dimssWheel() {
        if (mCustomDialog != null) {
            mCustomDialog.dismiss();
        }
    }


    public WheelPickerAdapter getRightAdapter(){
        return rightAdapter;
    }
    public WheelPickerAdapter getLeftAdapter(){
        return leftAdapter;
    }
    public WheelPickerAdapter getMiddleAdapter(){
        return middleAdapter;
    }
}
