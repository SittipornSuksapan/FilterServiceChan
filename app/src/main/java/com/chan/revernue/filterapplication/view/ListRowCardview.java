package com.chan.revernue.filterapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.chan.revernue.filterapplication.R;
import com.chan.revernue.filterapplication.view.state.BundleSavedState;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ListRowCardview extends BaseCustomViewGroup {

    TextView tvName;
    TextView tvDescription;
    TextView tvId;

    public ListRowCardview(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ListRowCardview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ListRowCardview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ListRowCardview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_row_cardview, this);
    }

    private void initInstances() {
        // findViewById here
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvName = (TextView) findViewById(R.id.tvName);
        tvId = (TextView) findViewById(R.id.tvID);

    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }
    public void setNameText(String text) {
        tvName.setText(text);
    }
    public void setDescriptionText(String text) {
        tvDescription.setText(text);
    }
    public void setIdText(String text) {
        tvId.setText(text);
    }

}
