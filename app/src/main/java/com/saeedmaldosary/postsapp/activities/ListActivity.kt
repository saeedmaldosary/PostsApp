package com.saeedmaldosary.postsapp.activities

import kotlinx.android.synthetic.main.activity_list.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.saeedmaldosary.postsapp.database.Data
import com.saeedmaldosary.postsapp.R
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        var listView = findViewById<ListView>(R.id.listview)
        var arrayListTitles = ArrayList<String>()

        var arrayadapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, arrayListTitles
        )

        //Perform the refresh app function
        refreshApp(arrayListTitles)

        //Assign the array adapter to the list view
        listView.adapter = arrayadapter

        //Initialize database constructor
        var getData =
            Data(
                arrayadapter,
                arrayListTitles
            )
        database.addValueEventListener(getData.retreiveData)

        //Get the post clicked
        listView.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(this, DetailsActivity::class.java).apply {
                //Store post to move in to the next activity
                putExtra("postTitle", arrayListTitles.get(position))
            }
            //Start details activity
            startActivity(intent)
        }

    }

    //Refreshing the list function
    private fun refreshApp(arrayListTitles: ArrayList<String>) {
        swipeRefresh.setOnRefreshListener {
            if(arrayListTitles.size==0) {
                Toast.makeText(applicationContext, "There is an error, Check your internet", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this, "Refreshing the posts", Toast.LENGTH_LONG).show()
            swipeRefresh.isRefreshing = false
        }
    }

    //Get info icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.posts_app_menu, menu)
        return true
    }

    //When the info icon clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoItem -> {
                Toast.makeText(applicationContext, "Developed by Saeed for STC", Toast.LENGTH_LONG)
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Handel back button
    override fun onBackPressed() {
        var intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}