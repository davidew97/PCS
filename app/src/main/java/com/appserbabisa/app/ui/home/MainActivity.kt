package com.appserbabisa.app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.appserbabisa.app.R
import com.appserbabisa.app.databinding.ActivityAuthBinding
import com.appserbabisa.app.databinding.ActivityMainBinding
import com.appserbabisa.app.ui.auth.AppserbaAuth
import com.appserbabisa.app.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.BtmLogout.setOnClickListener {
            AppserbaAuth.logout(this){
                startActivity(Intent(this,AuthActivity::class.java))
                finish()
            }
        }
    }
}