package com.example.bhopetheexplorer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePicker extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int year=c.YEAR;
        int month=c.MONTH;
        int day=c.DAY_OF_MONTH;
        return new DatePickerDialog(getActivity(),listener,year,month,day);
    }
    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener=listener;
    }

}
