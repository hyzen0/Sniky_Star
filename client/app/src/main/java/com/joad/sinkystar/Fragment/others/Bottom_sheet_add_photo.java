package com.joad.sinkystar.Fragment.others;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.BottomAddPhotoBinding;

public class Bottom_sheet_add_photo extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";

    int flagApi = 0;
    BottomAddPhotoBinding binding;
    private BottomSheetDialog bottomSheetDialog;
    private TextView mTxtFilter;
    private TextView mFilterClear;
    private RelativeLayout mFltrCategory;
    private RelativeLayout mCategoryFtrSub;
    private RelativeLayout mSubCategoryFltrSub;
    private ProgressBar mCircularProgress;
    private RecyclerView mViewRecycler;
    private RelativeLayout mRl;
    private View m1Line;
    private TextView mNumberCat;
    private TextView mNumberSubcat;
    private TextView mNumberSubSubCat;
    private int type = 0;
    private Activity activity;
    private int count = 0;
    private Fragment fragment;

    public static Bottom_sheet_add_photo newInstance(Activity activity, Fragment fragment2) {
        Bottom_sheet_add_photo filter_bottom_sheet = new Bottom_sheet_add_photo();
        filter_bottom_sheet.activity = activity;
        filter_bottom_sheet.fragment = fragment2;
        return filter_bottom_sheet;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        bottomSheetDialog = (BottomSheetDialog) dialog;
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.bottom_add_photo, null, false);
        View contentView = binding.getRoot();
        contentView.setBackgroundColor(Color.TRANSPARENT);
        bottomSheetDialog.setContentView(contentView);

        binding.clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        binding.cameraAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                try {
                    ImagePicker.Companion.with(fragment)
                            .crop() //Crop image(Optional), Check Customization for more option
                            .compress(100)
                            .cameraOnly()//Final image size will be less than 1 MB(Optional)
                            //Final image resolution will be less than 1080 x 1080(Optional)
                            .start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.galleryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

                try {
                    ImagePicker.Companion.with(fragment)
                            .crop() //Crop image(Optional), Check Customization for more option
                            .compress(100)
                            .galleryOnly()//Final image size will be less than 1 MB(Optional)
                            //Final image resolution will be less than 1080 x 1080(Optional)
                            .start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        initView(contentView);

    }


}
