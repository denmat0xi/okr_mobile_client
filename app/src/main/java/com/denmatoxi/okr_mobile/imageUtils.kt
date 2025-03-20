package com.denmatoxi.okr_mobile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageUtils(private val activity: Activity) {

    private var imagePickerCallback: ((Uri?) -> Unit)? = null

    @SuppressLint("IntentReset")
    fun openImagePicker(callback: (Uri?) -> Unit) {
        imagePickerCallback = callback

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity.startActivityForResult(intent, IMAGE_PICKER_REQUEST_CODE)
    }

    suspend fun getBitmapFromUri(uri: Uri): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val imageLoader = ImageLoader(activity)
                val request = ImageRequest.Builder(activity)
                    .data(uri)
                    .build()

                val drawable = imageLoader.execute(request).drawable
                drawable?.toBitmap()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    companion object {
        const val IMAGE_PICKER_REQUEST_CODE = 1001
    }
}


/* Пример использования класса. В данном случае ставлю его в ImageView
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPickImage = findViewById<Button>(R.id.buttonPickImage)
        buttonPickImage.setOnClickListener {
            imageUtils.openImagePicker { uri ->
                if (uri != null) {
                    GlobalScope.launch {
                        val bitmap = imageUtils.getBitmapFromUri(uri)
                        runOnUiThread {
                            val imageView = findViewById<ImageView>(R.id.imageView)
                            imageView.setImageBitmap(bitmap)
                        }
                    }
                }
            }
        }
    }*/