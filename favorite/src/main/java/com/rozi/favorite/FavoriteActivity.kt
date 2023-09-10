package com.rozi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rozi.core.domain.model.Team
import com.rozi.favorite.databinding.ActivityFavoriteBinding
import com.rozi.favorite.di.favoriteModule
import com.rozi.footballteam.R
import com.rozi.footballteam.presentation.detail.DetailActivity
import com.rozi.footballteam.presentation.main.ListTeamAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = getString(R.string.title_favorite)


        viewModel.favoriteTeam.observe(this) { teams ->
            val teamAdapter = ListTeamAdapter(teams)
            teamAdapter.setOnItemClickCallback(object :
                ListTeamAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: Team) {
                    val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.DATA_TEAMS, data)
                    startActivity(intent)
                }
            })
            with(binding.rvTeam){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = teamAdapter
            }
            binding.tvError.visibility = if(teams.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}