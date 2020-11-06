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

import io.github.gusandrianos.foxforreddit.R;
import io.github.gusandrianos.foxforreddit.utilities.TrophyAdapter;
import io.github.gusandrianos.foxforreddit.utilities.InjectorUtils;
import io.github.gusandrianos.foxforreddit.viewmodels.UserViewModel;
import io.github.gusandrianos.foxforreddit.viewmodels.UserViewModelFactory;

import static io.github.gusandrianos.foxforreddit.Constants.ARG_COMMENT_KARMA_NAME;
import static io.github.gusandrianos.foxforreddit.Constants.ARG_POST_KARMA_NAME;
import static io.github.gusandrianos.foxforreddit.Constants.ARG_USERNAME_NAME;

public class AboutUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String username = getArguments().getString(ARG_USERNAME_NAME, "");
        int postKarmaValue = getArguments().getInt(ARG_POST_KARMA_NAME, 0);
        int commentKarmaValue = getArguments().getInt(ARG_COMMENT_KARMA_NAME, 0);

        TextView postKarma = view.findViewById(R.id.txt_post_karma);
        TextView commentKarma = view.findViewById(R.id.txt_comment_karma);
        postKarma.setText(String.valueOf(postKarmaValue));
        commentKarma.setText(String.valueOf(commentKarmaValue));

        UserViewModelFactory factory = InjectorUtils.getInstance().provideUserViewModelFactory();
        UserViewModel viewModel = new ViewModelProvider(this, factory).get(UserViewModel.class);
        viewModel.getTrophies(requireActivity().getApplication(), username).observe(getViewLifecycleOwner(), trophies -> {
            if (trophies != null) {
                RecyclerView trophiesRV = view.findViewById(R.id.recycler_trophies);
                TrophyAdapter adapter = new TrophyAdapter(trophies);
                adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                trophiesRV.setHasFixedSize(true);
                trophiesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
                trophiesRV.setAdapter(adapter);
            } else {
                //TODO: Handle this by showing appropriate error
            }
        });
    }

    public static AboutUserFragment newInstance(String username, int postKarma, int commentKarma) {
        AboutUserFragment fragment = new AboutUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME_NAME, username);
        args.putInt(ARG_POST_KARMA_NAME, postKarma);
        args.putInt(ARG_COMMENT_KARMA_NAME, commentKarma);
        fragment.setArguments(args);
        return fragment;
    }
}