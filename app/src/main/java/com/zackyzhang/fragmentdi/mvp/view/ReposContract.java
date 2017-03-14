package com.zackyzhang.fragmentdi.mvp.view;

import com.zackyzhang.fragmentdi.data.GithubRepo;
import com.zackyzhang.fragmentdi.mvp.view.BaseView;

import java.util.List;

/**
 * Created by lei on 3/13/17.
 */

public interface ReposContract {

    interface View extends BaseView {
        void renderRepoList(List<GithubRepo> repos);
    }

    interface presenter {
        void setView(View view);
    }

}
