package com.denmatoxi.okr_mobile

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationExtensionRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationRequest
import com.denmatoxi.okr_mobile.enums.Status
import java.io.ByteArrayOutputStream
import java.util.UUID

class CreateExtensionDialog(context: Context, private val onSubmit: (CreateApplicationExtensionRequest) -> Unit) {
    private val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_extension, null)
    private val toDateInput: EditText = dialogView.findViewById(R.id.et_create_extension_date_to)
    private val descriptionInput: EditText = dialogView.findViewById(R.id.et_create_extension_description)
    private val selectImageButton: Button = dialogView.findViewById(R.id.btn_create_extension_image)
    private val context: Context = context

    private var imageBase64: String = ""

    init {
        selectImageButton.setOnClickListener { pickImageFromGallery(context) }
    }

    fun show() {
        AlertDialog.Builder(context)
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                if (validateInputs()) {
                    onSubmit(CreateApplicationExtensionRequest(
                        extensionToDate = toDateInput.text.toString(),
                        description = descriptionInput.text.toString(),
                        image = imageBase64)
                    )
                }
            }
            .setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun validateInputs(): Boolean {
        return when {
            toDateInput.text.isBlank() -> {
                toDateInput.error = "Введите срок продления"
                false
            }
            descriptionInput.text.isBlank() -> {
                descriptionInput.error = "Введите описание"
                false
            }
            imageBase64.isBlank() -> {
                Toast.makeText(dialogView.context, "Выберите изображение", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun pickImageFromGallery(context: Context) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        (context as? Activity)?.startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    fun handleImageResult(data: Intent?) {
        val uri = data?.data ?: return
        val bitmap = MediaStore.Images.Media.getBitmap(dialogView.context.contentResolver, uri)
        imageBase64 = encodeImageToBase64(bitmap)
    }

    private fun encodeImageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }

    companion object {
        const val IMAGE_PICK_CODE = 1001
    }
}
