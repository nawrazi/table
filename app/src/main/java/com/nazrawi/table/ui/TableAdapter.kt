package com.nazrawi.table.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazrawi.table.databinding.TeamListItemBinding
import com.nazrawi.table.domain.model.Team

class TableAdapter(private val context: Context) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    private var table: List<Team> = listOf()

    class ViewHolder(binding: TeamListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val teamName = binding.teamNameText
        val rank = binding.teamRankText
        val points = binding.pointsText
        val goalDiff = binding.goalDiffText
        val matchesPlayed = binding.matchesPlayedText
        val logoImage = binding.teamLogoImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val team = table[pos]
        holder.teamName.text = team.name
        holder.rank.text = team.rank.toString()
        holder.points.text = team.points.toString()
        holder.goalDiff.text = team.goalsDiff.toString()
        holder.matchesPlayed.text = team.played.toString()
        Glide.with(context).load(team.logo).into(holder.logoImage)
    }

    override fun getItemCount(): Int = table.size

    fun setTable(newTable: List<Team>) {
        table = newTable.sortedBy { it.rank }
        notifyItemRangeChanged(0, table.size)
    }

}