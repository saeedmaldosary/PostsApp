package com.saeedmaldosary.postsapp.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.saeedmaldosary.myapplication.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var title = findViewById<TextView>(R.id.postTitle)

        //Get the post title form the first activity
        var getTitle = intent.extras?.get("postTitle").toString()
        title.setText(getTitle)

        //Get the post image from firebase storage
        var storageReference: StorageReference = FirebaseStorage.getInstance()
            .getReference().child("${getTitle}.jpg")

        var localFile: File = File.createTempFile("${getTitle}", "jpg")

        storageReference.getFile(localFile).addOnSuccessListener {
            var bitmap: Bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            findViewById<ImageView>(R.id.postImage).setImageBitmap(bitmap)
        }

        //Set action Bar
        val actionbar = supportActionBar
        actionbar!!

        //Set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    //When the back button clicked
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}