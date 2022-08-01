package by.sergey.newsapi.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import by.sergey.newsapi.databinding.ActivityMainBinding
import by.sergey.newsapi.models.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


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


        binding.searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    mainViewModel.getNews(title = query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        mainViewModel.isLoading.observe(this){
            if(it)
            {
                binding.rvNews.visibility = View.INVISIBLE
                binding.progress.visibility = View.VISIBLE
            }
            else
            {
                binding.rvNews.visibility = View.VISIBLE
                binding.progress.visibility = View.INVISIBLE
            }
        }

        mainViewModel.response.observe(this){
            when(it)
            {
                is Result.Success -> {
                    adapter.submitList(it.data.articles!!)
                }
                is Result.Failure -> {
                    Snackbar.make(view, "Something went wrong",Snackbar.LENGTH_SHORT).show()
                }
            }
        }


    }
}