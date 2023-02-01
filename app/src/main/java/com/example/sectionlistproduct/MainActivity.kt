package com.example.sectionlistproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sectionlistproduct.SectionAdapter.Companion.TYPE_ROW
import com.example.sectionlistproduct.SectionAdapter.Companion.TYPE_SECTION

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SectionAdapter
    private val items: ArrayList<Pair<Int, String>> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFakeData()
        initViews()
    }

    private fun initViews() {
        adapter = SectionAdapter(items)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
    }

    private fun initFakeData() {
        for (i in 0..100) {
            if (i == 0) {
                // Section
                items.add(Pair(TYPE_SECTION, i.toString()))
            } else {
                if ((i % 10) == 0) {
                    // Section
                    items.add(Pair(TYPE_SECTION, i.toString()))
                } else {
                    // Row
                    items.add(Pair(TYPE_ROW, i.toString()))
                }
            }
        }
    }

    private fun getSectionCallback(): StickyHeaderItemDecoration.SectionCallback {
        return object : StickyHeaderItemDecoration.SectionCallback {
            override fun isHeader(position: Int): Boolean {
                return adapter.isHeader(position) ?: false
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderView(list, position)
            }
        }
    }
}