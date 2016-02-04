package com.dtracker.ui.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inject();
        ButterKnife.inject(this, view);
    }

    protected abstract void inject();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected Bundle safeGetArguments() {
        if (getArguments() != null) {
            return getArguments();
        }

        return new Bundle();
    }
}
