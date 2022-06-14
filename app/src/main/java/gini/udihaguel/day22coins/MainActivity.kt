package gini.udihaguel.day22coins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gini.udihaguel.day22coins.network.ApiClient
import gini.udihaguel.day22coins.network.SharedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}