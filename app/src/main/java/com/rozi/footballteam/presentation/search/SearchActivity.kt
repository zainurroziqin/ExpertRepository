package com.rozi.footballteam.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rozi.footballteam.databinding.ActivitySearchBinding
import com.rozi.footballteam.presentation.detail.DetailActivity
import com.rozi.footballteam.presentation.main.ListTeamAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    private val viewModel : SearchViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teamAdapter = ListTeamAdapter()
        teamAdapter.onItemClick = {selectedData ->
            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DATA_TEAMS, selectedData)
            startActivity(intent)
        }

        binding.svUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTeams(query.toString()).observe(this@SearchActivity){result ->
                    if(result.isNotEmpty()){
                        binding.tvErrorMessage.visibility = View.GONE
                        binding.rvTeam.visibility = View.VISIBLE
                        teamAdapter.setData(result)
                    }else{
                        binding.tvErrorMessage.visibility = View.VISIBLE
                        binding.rvTeam.visibility = View.GONE
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        with(binding.rvTeam){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = teamAdapter
        }
    }
}