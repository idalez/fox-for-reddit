package io.github.gusandrianos.foxforreddit.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.gusandrianos.foxforreddit.Constants;
import io.github.gusandrianos.foxforreddit.R;
import io.github.gusandrianos.foxforreddit.utilities.TrophyAdapter;
import io.github.gusandrianos.foxforreddit.utilities.InjectorUtils;
import io.github.gusandrianos.foxforreddit.viewmodels.UserViewModel;
import io.github.gusandrianos.foxforreddit.viewmodels.UserViewModelFactory;

public class AboutUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String username = getArguments().getString(Constants.ARG_USERNAME_NAME, "");

        UserViewModelFactory factory = InjectorUtils.getInstance().provideUserViewModelFactory();
        UserViewModel viewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);

        viewModel.getUser(requireActivity().getApplication(), username)
                .observe(getViewLifecycleOwner(), data -> {
                    TextView postKarma = view.findViewById(R.id.txt_post_karma);
                    TextView commentKarma = view.findViewById(R.id.txt_comment_karma);
                    postKarma.setText(String.valueOf(data.getLinkKarma()));
                    commentKarma.setText(String.valueOf(data.getCommentKarma()));
                });

        viewModel.getTrophies(requireActivity().getApplication(), username).observe(getViewLifecycleOwner(), trophies -> {
            if (trophies != null) {
                RecyclerView trophiesRV = view.findViewById(R.id.recycler_trophies);
                TrophyAdapter adapter = new TrophyAdapter(trophies);
                adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                trophiesRV.setHasFixedSize(true);
                trophiesRV.setLayoutManager(new LinearLayoutManager(requireActivity()));
                trophiesRV.setAdapter(adapter);
            }
        });
    }

    public static AboutUserFragment newInstance(String username) {
        AboutUserFragment fragment = new AboutUserFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_USERNAME_NAME, username);
        fragment.setArguments(args);
        return fragment;
    }
}