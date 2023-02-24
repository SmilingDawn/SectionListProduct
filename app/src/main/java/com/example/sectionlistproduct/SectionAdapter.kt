package com.example.sectionlistproduct

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SectionAdapter(val items: List<Pair<Int, String>>): RecyclerView.Adapter<SectionAdapter.ViewHolder>() {
    companion object {
        const val TYPE_SECTION = 1
        const val TYPE_ROW = 2
    }

    var headerClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == TYPE_SECTION) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section, parent, false)
            SectionViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            ViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is SectionViewHolder) {
            holder.bind(items[position].second, position, headerClickListener)
        } else {
            holder.bind(items[position].second)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].first
    }

    fun isHeader(postion: Int) = (getItemViewType(position = postion) == TYPE_SECTION)

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtTitle: TextView = itemView.findViewById(R.id.title)

        open fun bind(item: String) {
            txtTitle.text = item
        }
    }

    class SectionViewHolder(view: View) : ViewHolder(view) {
        private val sectionLayout: ViewGroup = itemView.findViewById(R.id.section_layout)

        fun bind(item: String, index: Int? = null, listener: OnClickListener? = null) {
            super.bind(item)
            sectionLayout.tag = index
            sectionLayout.setOnClickListener(listener)
        }
    }
}