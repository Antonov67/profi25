package com.example.profi25.Presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {

    private String message;
    private String title;

    public AlertDialogFragment(String message, String title) {
        this.message = message;
        this.title = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder.setTitle(title).setMessage(message)
                .setPositiveButton("OK", null)
                .create();
    }
}
