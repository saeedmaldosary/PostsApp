package com.saeedmaldosary.postsapp.utilities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.saeedmaldosary.postsapp.R
import com.saeedmaldosary.postsapp.activities.ListActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<ImageView>(R.id.splashImage).alpha = 0f
        //Image shown in the splash screen
        findViewById<ImageView>(R.id.splashImage).animate().setDuration(1500).alpha(1f)
            .withEndAction {
                //Start the List activity
                val listActivityIntent = Intent(this, ListActivity::class.java)
                startActivity(listActivityIntent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

    }

}