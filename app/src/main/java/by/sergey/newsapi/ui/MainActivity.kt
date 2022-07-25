package by.sergey.newsapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.sergey.newsapi.R
import by.sergey.newsapi.databinding.ActivityMainBinding
import by.sergey.newsapi.models.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = NewsAdapter()
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = adapter

        mainViewModel.isLoading.observe(this){
            if(it)
            {
                binding.rvNews.visibility = View.VISIBLE
                binding.progress.visibility = View.INVISIBLE
            }
            else
            {
                binding.rvNews.visibility = View.INVISIBLE
                binding.progress.visibility = View.VISIBLE
            }
        }

        mainViewModel.response.observe(this){
            when(it)
            {
                is Result.Success -> {
                    adapter.submitList(it.data)
                }
                is Result.Failure -> {
                    Snackbar.make(view, "Something went wrong",Snackbar.LENGTH_SHORT).show()
                }
            }
        }



    }
}