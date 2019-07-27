package com.vivy.ui.doctorsearch.doctorslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bailm.vivychallenge.util.ImageLoader
import com.vivy.DOCTOR_URL
import com.vivy.R
import com.vivy.data.model.Doctor
import com.vivy.network.NetworkState
import kotlinx.android.synthetic.main.item_doctors_list.view.*

class DoctorsAdapter : PagedListAdapter<Doctor, RecyclerView.ViewHolder>(diffCallBack) {
    var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_doctors_list -> DoctorViewHolder.create(parent)
            R.layout.item_netwok_loading -> LoadingViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unknown Type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_doctors_list -> { getItem(position)?.let { (holder as DoctorViewHolder).bind(it) } }
            R.layout.item_netwok_loading -> {
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (hasLoadingRow() && position == itemCount - 1) {
            R.layout.item_netwok_loading
        } else {
            R.layout.item_doctors_list
        }
    }


    override fun getItemCount(): Int = super.getItemCount() + if (hasLoadingRow()) 1 else 0

    private fun hasLoadingRow(): Boolean = networkState != null && networkState == NetworkState.LOADING

    class DoctorViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
        var doctorNameTV = view.doctor_name
        var doctorAddressTV = view.address
        var doctorRateTV = view.rating
        var avatarIv = view.avatar

        companion object {
            fun create(parent: ViewGroup): DoctorViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.item_doctors_list, parent, false)
                return DoctorViewHolder(itemView)
            }
        }

        fun bind(doctor: Doctor) {
            doctorNameTV.text = doctor.name
            doctorAddressTV.text = doctor.address
            doctorRateTV.text = doctor.rating.toString()
            if (doctor.photoId != null) {
                val imageUrl: String = DOCTOR_URL +   "api/doctors/${doctor.id}/keys/profilepictures"
                ImageLoader.load(avatarIv, imageUrl, R.drawable.no_doctor_avatar)
            } else {
                ImageLoader.load(avatarIv, ImageLoader.EMPTY_IMAGE, R.drawable.no_doctor_avatar)
            }
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): LoadingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return LoadingViewHolder(layoutInflater.inflate(R.layout.item_netwok_loading, parent, false))
            }
        }
    }

    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor): Boolean = oldItem.id == newItem.id
        }
    }

    fun updateNetworkState(newNetworkState: NetworkState) {
        val oldNetworkState = networkState
        val hadLoadingRow = hasLoadingRow()
        this.networkState = newNetworkState
        val hasLoadingRow = hasLoadingRow()
        if (hadLoadingRow != hasLoadingRow) {
            if (hadLoadingRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasLoadingRow && oldNetworkState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}