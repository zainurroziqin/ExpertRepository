package com.rozi.footballteam.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rozi.core.data.Resource
import com.rozi.core.domain.model.Team
import com.rozi.footballteam.R
import com.rozi.footballteam.databinding.ActivityMainBinding
import com.rozi.footballteam.presentation.detail.DetailActivity
import com.rozi.footballteam.presentation.search.SearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.liga_inggris)

        mainViewModel.teams.observe(this) { teams ->
            if (teams != null) {
                when (teams) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val teamAdapter = ListTeamAdapter(teams.data ?: emptyList())
                        teamAdapter.setOnItemClickCallback(object :
                            ListTeamAdapter.OnItemClickCallBack {
                            override fun onItemClicked(data: Team) {
                                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.DATA_TEAMS, data)
                                startActivity(intent)
                            }
                        })
                        with(binding.rvTeam){
                            layoutManager = LinearLayoutManager(context)
                            setHasFixedSize(true)
                            adapter = teamAdapter
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            teams.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_fav -> {
                startActivity(Intent(this@MainActivity, Class.forName("com.rozi.favorite.FavoriteActivity")))
            }
            R.id.action_search -> {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}