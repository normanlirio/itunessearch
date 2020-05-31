package com.ralphlirio.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.material.imageview.ShapeableImageView
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.models.Track

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private var trackList: ArrayList<Track> = ArrayList()
    private var requestManager: RequestManager? = null
    private var clickListener: OnTrackClickListener? = null

    interface OnTrackClickListener {
        fun onTrackClick(track: Track)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_tracks, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return trackList.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.tvTrackName!!.text = trackList[position].trackName
        holder.tvGenre!!.text = trackList[position].primaryGenreName
        holder.tvPrice!!.text = trackList[position].currency.plus(" " + trackList[position].trackPrice)

        requestManager!!
            .load(trackList[position].artworkUrl100)
            .into(holder.ivTrackCover!!)

    }

    fun setTrack(track : List<Track>) {
        this.trackList.clear()

        this.trackList.addAll(track)
        notifyDataSetChanged()
    }

    fun setRequestManager(requestManager: RequestManager) {
        this.requestManager = requestManager
    }

    fun setClickListener( clickListener: OnTrackClickListener) {
        this.clickListener = clickListener
    }

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvTrackName: TextView? = null
        var tvGenre: TextView? = null
        var tvPrice: TextView? = null
        var ivTrackCover: ShapeableImageView? = null

        init {
            tvTrackName = itemView.findViewById(R.id.textView_trackName)
            tvGenre = itemView.findViewById(R.id.textView_genre)
            tvPrice = itemView.findViewById(R.id.textView_price)
            ivTrackCover = itemView.findViewById(R.id.imageView_trackCover)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickListener!!.onTrackClick(trackList[adapterPosition])
        }
    }
}