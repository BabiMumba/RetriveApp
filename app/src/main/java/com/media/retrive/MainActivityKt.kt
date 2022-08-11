package com.media.retrive

import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import com.media.retrive.R
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.media.retrive.FriendsResponse
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.google.firebase.firestore.FirebaseFirestoreException
import android.widget.TextView
import com.google.firebase.firestore.Query
import de.hdodenhof.circleimageview.CircleImageView

class MainActivityKt : AppCompatActivity() {
    @BindView(R.id.progress_bar)
    var progressBar: ProgressBar? = null

    @BindView(R.id.friend_list)
    var friendList: RecyclerView? = null
    private var db: FirebaseFirestore? = null
    private var adapter: FirestoreRecyclerAdapter<*, *>? = null
    var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        init()
        getFriendList()
    }

    private fun init() {
        linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        friendList!!.layoutManager = linearLayoutManager
        db = FirebaseFirestore.getInstance()
    }

    private fun getFriendList() {
        val query: Query = db!!.collection("friends")
        val response = FirestoreRecyclerOptions.Builder<FriendsResponse>()
            .setQuery(query, FriendsResponse::class.java)
            .build()
        adapter = object : FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>(response) {
            public override fun onBindViewHolder(
                holder: FriendsHolder,
                position: Int,
                model: FriendsResponse
            ) {
                progressBar!!.visibility = View.GONE
                holder.textName!!.text = model.name
                holder.textTitle!!.text = model.title
                holder.textCompany!!.text = model.company
                Glide.with(applicationContext)
                    .load(model.image)
                    .placeholder(R.drawable.idea)
                    .error(R.drawable.man)
                    .into(holder.imageView!!)
                holder.itemView.setOnClickListener { v: View? ->
                    Snackbar.make(
                        friendList!!,
                        model.name + ", " + model.title + " at " + model.company,
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }
            }

            override fun onCreateViewHolder(group: ViewGroup, i: Int): FriendsHolder {
                val view = LayoutInflater.from(group.context)
                    .inflate(R.layout.list_item, group, false)
                return FriendsHolder(view)
            }

            override fun onError(e: FirebaseFirestoreException) {
                Log.e("error", e.message!!)
            }
        }
        (adapter as FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>).notifyDataSetChanged()
        friendList!!.adapter = adapter
    }

    inner class FriendsHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        @BindView(R.id.name)
        var textName: TextView? = null

        @BindView(R.id.image)
        var imageView: CircleImageView? = null

        @BindView(R.id.title)
        var textTitle: TextView? = null

        @BindView(R.id.company)
        var textCompany: TextView? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }

    public override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    public override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
}