package com.example.your_puppy_diary.main.ui.home

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_puppy_diary.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_frag.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface HomeFragmentView {
    fun takePicture()
    fun removeImage(dogImageFile: String?)
}

class HomeFragment : DaggerFragment(), HomeFragmentView {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    @Inject
    lateinit var presenter: HomePresenter

    private var storageDir: File? = null
    private var dogImageList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        addImageButton.setOnClickListener {
            presenter.onClickAddImageButton()
        }
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager).also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun removeImage(dogImageFile: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.remove_image_confrim_title)
            .setMessage(R.string.remove_image_confirm_message)
            .setPositiveButton(R.string.ok) { _, _ ->
                val file = File(storageDir, dogImageFile)
                file.delete().run {
                    initAdapter()
                }
            }.setNegativeButton(R.string.cancel) { _, _ -> }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            saveBitmap(bitmap)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveBitmap(bitmap: Bitmap) {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        val file = File(storageDir, "${timeStamp}.jpg")
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        initAdapter()
    }

    private fun initAdapter() {
        loadStorageFileList()
        recyclerHome.layoutManager = LinearLayoutManager(requireContext())
        recyclerHome.setHasFixedSize(true)
        recyclerHome.isNestedScrollingEnabled = false

        val adapter = HomeAdapter(requireContext(), dogImageList)
        adapter.onClickItemListener(object : HomeAdapter.OnItemClickListener {
            override fun onClickRemoveImageButton(view: View, position: Int) {
                view.setOnClickListener {
                    presenter.onClickRemoveImageButton(dogImageList[position])
                }
            }
        })
        recyclerHome.adapter = adapter
        recyclerHome.adapter?.notifyDataSetChanged()
    }

    private fun loadStorageFileList() {
        dogImageList = mutableListOf()
        val directory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val filePath = directory?.listFiles()
        if (!filePath.isNullOrEmpty()) {
            for (element in filePath) {
                dogImageList.add(element.name)
            }
        }
    }
}
