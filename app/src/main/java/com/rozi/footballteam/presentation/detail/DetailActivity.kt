package com.rozi.footballteam.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rozi.core.domain.model.Team
import com.rozi.footballteam.R
import com.rozi.footballteam.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val viewModel : DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataTeam = intent.getParcelableExtra<Team>(DATA_TEAMS)
        showDetail(dataTeam)
        clickSosialMedia(dataTeam)
    }

    private fun clickSosialMedia(dataTeam: Team?) {
        dataTeam?.let {
            binding.ibInstagram.setOnClickListener {
                openLinkInBrowser(dataTeam.instagram)
            }
            binding.ibTwitter.setOnClickListener {
                openLinkInBrowser(dataTeam.twitter)
            }
            binding.ibYoutube.setOnClickListener {
                openLinkInBrowser(dataTeam.youtube)
            }
            binding.ibWebsite.setOnClickListener {
                openLinkInBrowser(dataTeam.website)
            }
        }
    }

    private fun showDetail(dataTeam: Team?) {
        dataTeam?.let {
            Glide.with(this@DetailActivity)
                .load(dataTeam.teamFanart1)
                .into(binding.imgItemPhoto)

            binding.tvItemName.text = dataTeam.team
            binding.tvItemDescription.text = dataTeam.descriptionEN
            binding.tvStadium.text = dataTeam.stadium
            var statusFavorite = dataTeam.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteTeam(dataTeam, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    private fun openLinkInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val link = "https://$url"
        intent.data = Uri.parse(link)
        startActivity(intent)
    }

    companion object {
        const val DATA_TEAMS = "data_teams"
    }
}