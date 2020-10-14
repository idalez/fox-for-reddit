package io.github.gusandrianos.foxforreddit.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;


import java.util.Objects;

import io.github.gusandrianos.foxforreddit.R;
import io.github.gusandrianos.foxforreddit.data.models.Token;
import io.github.gusandrianos.foxforreddit.data.models.gencomments.ChildrenItem;

import io.github.gusandrianos.foxforreddit.data.models.gene.SinglePost;
import io.github.gusandrianos.foxforreddit.utilities.InjectorUtils;
import io.github.gusandrianos.foxforreddit.viewmodels.PostViewModel;
import io.github.gusandrianos.foxforreddit.viewmodels.PostViewModelFactory;
import kotlin.Unit;

public class SinglePostFragment extends Fragment {
    private RecyclerView mCommentsRecyclerView;
    private View mView;

    private GroupAdapter<GroupieViewHolder> groupAdapter = new GroupAdapter<>();
    private GridLayoutManager groupLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        initRecyclerView();
        Token mToken = InjectorUtils.getInstance().provideTokenRepository(getActivity().getApplication()).getToken();
        PostViewModelFactory factory = InjectorUtils.getInstance().providePostViewModelFactory();
        PostViewModel viewModel = new ViewModelProvider(this, factory).get(PostViewModel.class);
        viewModel.getPostComments("r/AskReddit", "jab3wa", "redditors_who_work_at_remote_places_like_forest", mToken)
                .observe(getViewLifecycleOwner(), new Observer<SinglePost>() {
                    @Override
                    public void onChanged(SinglePost singlePost) {

                        for (Object comment : singlePost.getComments().getData().getChildren()) {

                            if(((LinkedTreeMap)comment).get("kind").equals("t1")){
                                ChildrenItem object = new Gson().fromJson(new Gson().toJson(((LinkedTreeMap<ChildrenItem, Object>) comment)), ChildrenItem.class);
                                groupAdapter.add(new ExpandableCommentGroup(object, object.getData().getDepth()));
                            }else{

                            }

//                            try {
//                                ChildrenItem item = (ChildrenItem) comment;
//
//                                Log.d("SinglesPost", "---------------------------------------------------");
//                                if (item.getClass() == ChildrenItem.class) {
//
//                                    groupAdapter.add(new ExpandableCommentGroup((ChildrenItem) comment, ((ChildrenItem) comment).getData().getDepth()));
//                                    Log.d("SinglesPost", "safsdfsdfsdfsdf");
//                                    Log.d("SinglesPost", "---------------------------------------------------");
//                                } else {
//
//                                    Log.i("SinglesPost", comment.toString());
//                                }
//                            } catch (Exception e) {
//                                Log.d("ASD", Objects.requireNonNull(e.getMessage()));
//                            }
                        }
                    }
                });

    }

    private void initRecyclerView() {
        mCommentsRecyclerView = mView.findViewById(R.id.recyclerview);
        groupLayoutManager = new GridLayoutManager(getActivity(), groupAdapter.getSpanCount());
        groupLayoutManager.setSpanSizeLookup(groupAdapter.getSpanSizeLookup());


        mCommentsRecyclerView.setLayoutManager(groupLayoutManager);
        mCommentsRecyclerView.setAdapter(groupAdapter);
    }

    //  private void initRecycleView() {
    //     mPostRecyclerView.setAdapter(mPostRecyclerViewAdapter.withLoadStateFooter(postLoadStateAdapter));
    // }


}
