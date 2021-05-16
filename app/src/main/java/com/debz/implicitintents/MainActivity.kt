package com.debz.implicitintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.debz.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPhoto.setOnClickListener(){
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
//  Here we are specifying the type of intent, in this case its ACTION_GET_CONTENT which is use to get in general any media content
//  Now since we want image we need to set the type. The it under the Intent calls the Intent and so we set its type to image
//  The /* tell android that we are looking for all kinds of image file. We can also specify jpeg,png,svg,etc in place of asterisk(*)
                startActivityForResult(it,0)
//  Here we generally use StartActivity(it), but since our activity is returning a result,we need to use another function
//  startActivityForResult(it,0) is it. The zero represents the request code which is used to identify different results from different apps.
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 0) {
            val uri= data?.data
// The path to any kind of content on Android is considered an URI
            binding.ivPhoto.setImageURI(uri)
        }
    }
}