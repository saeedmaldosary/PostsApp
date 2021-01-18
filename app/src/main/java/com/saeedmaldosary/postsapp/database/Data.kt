package com.saeedmaldosary.postsapp.database

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.saeedmaldosary.postsapp.activities.ListActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Data(
    arrayadapter: ArrayAdapter<String>,
    arrayListTitles: ArrayList<String>
) {

    //Get the data from firebase database
    var retreiveData = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {

        }

        override fun onDataChange(snapshot: DataSnapshot) {
            //Loop in the database
            for (i in snapshot.children) {
                var title = i.child("title").getValue()
                //Add the data from firebase database to the array
                arrayListTitles.add(title.toString())
            }
            //Update the array adapter
            arrayadapter.notifyDataSetChanged()

        }


    }
}