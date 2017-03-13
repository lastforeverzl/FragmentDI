package com.zackyzhang.fragmentdi;

import android.app.Fragment;
import android.content.Context;

import com.zackyzhang.fragmentdi.data.GithubRepo;

import java.util.List;

/**
 * Created by lei on 3/13/17.
 */

public class BaseFragment extends Fragment {

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
