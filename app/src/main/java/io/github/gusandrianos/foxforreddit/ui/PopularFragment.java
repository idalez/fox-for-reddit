package io.github.gusandrianos.foxforreddit.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.gusandrianos.foxforreddit.R;
import io.github.gusandrianos.foxforreddit.data.models.ChildrenItem;
import io.github.gusandrianos.foxforreddit.data.models.Listing;
import io.github.gusandrianos.foxforreddit.data.models.Post;
import io.github.gusandrianos.foxforreddit.data.models.Token;
import io.github.gusandrianos.foxforreddit.utilities.InjectorUtils;
import io.github.gusandrianos.foxforreddit.viewmodels.PostViewModel;
import io.github.gusandrianos.foxforreddit.viewmodels.PostViewModelFactory;



public class PopularFragment extends Fragment {
    // Add RecyclerView member
    private View mView;
    private RecyclerView mPostRecyclerView;
    private List<Post> mPosts;
    private Token mToken;
    private List<Post> dataSet;
    PostRecyclerViewAdapter mPostRecyclerViewAdapter;
    String after="";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mView = inflater.inflate(R.layout.fragment_popular, container, false);
        initializeUI(mView);
        return mView;

    }

    private void initRecycleView(@NotNull View view, List<Post> posts) {

        mPostRecyclerView = view.findViewById(R.id.recyclerview);
        mPostRecyclerViewAdapter = new PostRecyclerViewAdapter(getContext(), posts);
        mPostRecyclerViewAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY ); //keep recyclerview on position
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPostRecyclerView.setAdapter(mPostRecyclerViewAdapter);
    }

    private void initializeUI(View view) {
        mToken = InjectorUtils.getInstance().provideTokenRepository(getActivity().getApplication()).getToken();
        PostViewModelFactory factory = InjectorUtils.getInstance().providePostViewModelFactory();
        PostViewModel viewModel = new ViewModelProvider(this, factory).get(PostViewModel.class);
        viewModel.getPosts(mToken, after).observe(getViewLifecycleOwner(), new Observer<Listing>() {
            @Override
            public void onChanged(Listing listing) {
                dataSet = new ArrayList<>();  //ToDo check if it is correct
                for (ChildrenItem child : listing.getTreeData().getChildren()) {
                    dataSet.add(child.getPost());
                }
                initRecycleView(view, dataSet);
            }
        });
    }
}