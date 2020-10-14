package io.github.gusandrianos.foxforreddit.ui

import android.view.LayoutInflater
import android.view.View
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import io.github.gusandrianos.foxforreddit.R
import io.github.gusandrianos.foxforreddit.data.models.gencomments.ChildrenItem
import kotlinx.android.synthetic.main.single_post_expandable_comment.view.*

class ExpandableCommentGroup constructor(private val mComment: ChildrenItem, private val depth: Int = 0) : ExpandableGroup(ExpandableCommentItem(mComment, depth)) {

    init {

        for (comment in mComment.data.replies.replyData.children) {
            if ((comment as LinkedTreeMap<*, *>)["kind"] == "t1" && !comment.isEmpty() ) {
                val child = Gson().fromJson(Gson().toJson(comment as LinkedTreeMap<ChildrenItem?, Any?>), ChildrenItem::class.java)
                if(child is ChildrenItem)
                    add(ExpandableCommentGroup(child, depth.plus(1)))

            }
        }
    }

}

open class ExpandableCommentItem constructor(private val mComment: ChildrenItem, private val depth: Int) : Item<GroupieViewHolder>(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }

    override fun getLayout(): Int {
        return R.layout.single_post_expandable_comment
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tv_user.text = mComment.data.author
        viewHolder.itemView.body.text = mComment.data.body
        viewHolder.itemView.apply {
            setOnClickListener {
                expandableGroup.onToggleExpanded()
                true
            }
        }

        fun addDepthViews(viewHolder: GroupieViewHolder) {
            viewHolder.itemView.separatorContainer.removeAllViews()
            viewHolder.itemView.separatorContainer.visibility =
                    if (depth > 0) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            for (i in 1..depth) {
                val v: View = LayoutInflater.from(viewHolder.itemView.context)
                        .inflate(R.layout.separator_view, viewHolder.itemView.separatorContainer, false)
                viewHolder.itemView.separatorContainer.addView((v))
            }
        }

    }


}
