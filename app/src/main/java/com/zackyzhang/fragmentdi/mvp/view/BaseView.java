package com.zackyzhang.fragmentdi.mvp.view;

import android.content.Context;

/**
 * Created by lei on 3/13/17.
 */

public interface BaseView {

    Context context();

    void showMessage(String message);

}
