package com.example.your_puppy_diary.main.ui.home

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.your_puppy_diary.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.dog_image_item_list.view.*
import timber.log.Timber
import java.io.File
import java.io.FileInputStream

class HomeAdapter(
    val context: Context,
    private val dogImageList: MutableList<String>?
) : RecyclerView.Adapter<HomeAdapter.Holder>() {

    interface OnItemClickListener {
        fun onClickRemoveImageButton(view: View, position: Int)
    }

    private var mListener: OnItemClickListener? = null

    fun onClickItemListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.dog_image_item_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dogImageList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeAdapter.Holder, position: Int) {
        holder.bind(dogImageList, position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        fun bind(dogImageList: MutableList<String>?, position: Int) {
            dogImageList?.let {
                try {
                    Single.just(loadBitmap(directory, it[position]))
                        .subscribeOn(io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                            onSuccess = { Timber.d("呼び出し成功") },
                            onError = { Timber.d("呼び出し失敗") }
                        )
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    itemView.dogImageView.tag = position
                    itemView.dogImageView.visibility = VISIBLE
                    itemView.removeImageButton.visibility = VISIBLE
                }
            } ?: run {
                itemView.dogImageView.visibility = GONE
                itemView.removeImageButton.visibility = GONE
            }

            itemView.removeImageButton.setOnClickListener {
                mListener?.onClickRemoveImageButton(it, adapterPosition)
            }
        }

        private fun loadBitmap(directory: File?, image: String) {
            val file = File(directory, image)
            val inputStream = FileInputStream(file)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            Glide.with(context)
                .load(bitmap)
                .apply(RequestOptions().override(250, 250))
                .into(itemView.dogImageView)
        }
    }
}