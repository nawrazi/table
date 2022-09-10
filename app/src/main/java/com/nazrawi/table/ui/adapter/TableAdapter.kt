package com.nazrawi.table.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazrawi.table.databinding.TeamListItemBinding
import com.nazrawi.table.data.remote.model.Standing

class TableAdapter(private val context: Context) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    private var table: List<Standing>? = null

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
        holder.teamName.text = table!![pos].team.name
        holder.rank.text = table!![pos].rank.toString()
        holder.points.text = table!![pos].points.toString()
        holder.goalDiff.text = table!![pos].goalsDiff.toString()
        holder.matchesPlayed.text = table!![pos].all.played.toString()
        Glide.with(context).load(table!![pos].team.logo).into(holder.logoImage)
    }

    override fun getItemCount(): Int = table?.size ?: 0

    fun setTable(newTable: List<Standing>) {
        table = newTable
        notifyDataSetChanged()
    }

}