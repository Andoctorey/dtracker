package com.dtracker.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.dtracker.DTrackerApp;
import com.dtracker.ui.base.BaseDialogFragment;


public class ConfirmationDialogFragment extends BaseDialogFragment {

    private static final String CONFIRMATION_DIALOG_FRAGMENT = "CONFIRMATION_DIALOG_FRAGMENT";
    private static final String TITLE_TAG = "TITLE_TAG";
    private static final String TITLE_MESSAGE = "TITLE_MESSAGE";

    private OnConfirmListener listener;

    public interface OnConfirmListener {
        void onClick();
    }

    @Override
    protected void inject() {
        DTrackerApp.getComponent(getActivity()).inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int title = getArguments().getInt(TITLE_TAG);
        if (title != 0) {
            builder.setTitle(getString(title));
        }

        int message = getArguments().getInt(TITLE_MESSAGE);
        if (message != 0) {
            builder.setMessage(getString(message));
        }

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClick();
                dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }

    public void setListener(OnConfirmListener listener) {
        this.listener = listener;
    }

    public static void showDialog(FragmentManager fragmentManager, int title, int message, OnConfirmListener listener) {
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putInt(TITLE_TAG, title);
        args.putInt(TITLE_MESSAGE, message);
        fragment.setArguments(args);
        fragment.setListener(listener);
        fragment.show(fragmentManager, CONFIRMATION_DIALOG_FRAGMENT);
    }
}
