package com.litelogistics.app.litelogistics.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    /*
    * ButterKnife enabled viewholder
    * */
    public BaseViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
