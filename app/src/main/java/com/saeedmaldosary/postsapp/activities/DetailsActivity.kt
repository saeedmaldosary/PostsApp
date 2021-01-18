package com.saeedmaldosary.postsapp.activities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.saeedmaldosary.postsapp.R
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
            //Change the size of images
            var resized:Bitmap = Bitmap.createScaledBitmap(bitmap, 800, 500, true);
            findViewById<ImageView>(R.id.postImage).setImageBitmap(resized)
        }

        //Set action Bar
        val actionbar = supportActionBar
        actionbar!!

        //Set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    //Handel back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}