package com.example.your_puppy_diary.main.ui.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.your_puppy_diary.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_frag.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface HomeFragmentView {
    fun takePicture()
}

class HomeFragment : DaggerFragment(), HomeFragmentView {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        lateinit var currentPhotoPath: String
    }

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addImageButton.setOnClickListener {
            presenter.onClickAddImageButton()
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            saveBitmap(imageBitmap)
        }
    }

    private fun saveBitmap(bitmap: Bitmap): File {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_$timeStamp",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}
