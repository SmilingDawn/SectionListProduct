package com.example.sectionlistproduct

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SectionAdapter(val items: List<Pair<Int, String>>): RecyclerView.Adapter<SectionAdapter.ViewHolder>() {
    companion object {
        const val TYPE_SECTION = 1
        const val TYPE_ROW = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            TYPE_SECTION -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section, parent, false)
                return ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position].second)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].first
    }

    fun isHeader(postion: Int) = (getItemViewType(position = postion) == TYPE_SECTION)
    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val sliceList = items.slice(0..position)
        val sectionItem = sliceList.findLast {
            it.first == TYPE_SECTION
        }

        val view = LayoutInflater.from(list.context).inflate(R.layout.item_section, list, false)
        val holder = ViewHolder(view)
        holder.bind(sectionItem?.second ?: "")

        return view
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtTitle: TextView = itemView.findViewById(R.id.title)

        fun bind(item: String) {
            txtTitle.text = item
        }
    }
}