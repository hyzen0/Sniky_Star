package com.joad.sinkystar.Fragment.others;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.joad.sinkystar.R;
import com.joad.sinkystar.databinding.BottomMoreOptionSheetBinding;
import com.joad.sinkystar.databinding.BottomReportSheetBinding;

public class Bottom_sheet_report extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";

    int flagApi = 0;
    BottomReportSheetBinding binding;
    private BottomSheetDialog bottomSheetDialog;
    private int type = 0;
    private Activity activity;
    private int count = 0;
    private Bundle masterBundle;

    public static Bottom_sheet_report newInstance(Activity activity,Bundle bundle) {
        Bottom_sheet_report filter_bottom_sheet = new Bottom_sheet_report();
        filter_bottom_sheet.activity = activity;
        filter_bottom_sheet.masterBundle = bundle;
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
                LayoutInflater.from(getContext()), R.layout.bottom_report_sheet, null, false);
        View contentView = binding.getRoot();
        contentView.setBackgroundColor(Color.TRANSPARENT);
        bottomSheetDialog.setContentView(contentView);

        binding.clearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        binding.Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Bottom_sheet_thanks bottom_sheet_report = Bottom_sheet_thanks.newInstance(activity,masterBundle);
//                bottom_sheet_report.show(getParentFragment().getChildFragmentManager(), "tile");
//                bottomSheetDialog.dismiss();
//
                Bottom_sheet_other bottom_sheet_report = Bottom_sheet_other.newInstance(activity,masterBundle);
                bottom_sheet_report.show(getParentFragment().getChildFragmentManager(), "tile");
                bottomSheetDialog.dismiss();


            }
        });


    }


}
