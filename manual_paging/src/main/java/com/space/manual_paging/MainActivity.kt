package com.space.manual_paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.manaul_paging.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}