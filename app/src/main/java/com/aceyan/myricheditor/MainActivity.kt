package com.aceyan.myricheditor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.postDelayed
import com.aceyan.richeditor.RichEditor
import com.aceyan.richeditor.Utils
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var launcher: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            it?.let {
                contentResolver.openInputStream(it)?.use {
                    val file = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        "image_${Utils.currentTime}.png"
                    )
                    file.createNewFile()
                    it.copyTo(FileOutputStream(file))
                    findViewById<RichEditor>(R.id.richEditor).insertImage(
                        RichEditor.LOCAL_FILE_HEADER + file.absolutePath,
                        file.name,
                        300
                    )
                }
            }
        }

        findViewById<RichEditor>(R.id.richEditor).apply {
            settings.allowFileAccess = true
            setEditorFontColor(Color.RED)
            requestFocus()
            postDelayed(3000L) {
                //launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                setTextBackgroundColor(Color.BLUE)
            }
        }
    }
}