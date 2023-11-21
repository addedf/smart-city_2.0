package com.example.smartcity_20.config.kotlin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
// TODO("记不住")
class GenericAdapter<T : ViewBinding>(
    private val size : Int,
    private val create : () -> T,
    private val bind:(T,Int) -> Unit
) : RecyclerView.Adapter<GenericAdapter.VH>() {
    class VH(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = create()
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        bind(holder.binding as T,position)
    }

    override fun getItemCount(): Int = size
}