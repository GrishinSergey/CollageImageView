package com.sagrishin.photogridlayout

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagrishin.collageimageview.inflate
import kotlinx.android.synthetic.main.feed_post_item.view.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.PostViewHolder>() {

    private val posts: MutableList<Pair<Int, List<Bitmap>>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(parent.inflate(R.layout.feed_post_item))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    operator fun plusAssign(post: Pair<Int, List<Bitmap>>) {
        posts += post
        notifyItemInserted(posts.lastIndex)
    }

    operator fun plusAssign(posts: List<Pair<Int, List<Bitmap>>>) {
        this.posts += posts
        notifyItemInserted(posts.lastIndex)
    }

    inner class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(post: Pair<Int, List<Bitmap>>) {
            with(itemView) {
                feed_post.images = post.second
            }
        }
    }

}
