package com.sagrishin.photogridlayout

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sagrishin.collageview.OnCollageClickListener
import kotlinx.android.synthetic.main.feed_post_item.view.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.PostViewHolder>() {

    private val posts: MutableList<FeedData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(parent.inflate(R.layout.feed_post_item))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    operator fun plusAssign(post: FeedData) {
        posts += post
        notifyItemInserted(posts.lastIndex)
    }

    operator fun plusAssign(posts: List<FeedData>) {
        this.posts += posts
        notifyItemInserted(posts.lastIndex)
    }

    inner class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(post: FeedData) {
            with(itemView) {
                feed_post.itemPreviewLoader = GlideItemPreviewLoaderImpl.Builder(context).build()
                feed_post.itemCornerRadius = 4F.dpToPx(context).toInt()
                feed_post.clickListener = OnCollageClickListener { position ->
                    Toast.makeText(context, "clicked position is $position", Toast.LENGTH_SHORT).show()
                }
                feed_post.setItemDatas(post.images)

                feed_post.showCollage()
            }
        }
    }

}
