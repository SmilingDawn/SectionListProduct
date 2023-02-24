package com.example.sectionlistproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        recyclerView.addItemDecoration(HeaderItemDecoration(recyclerView, false, isHeader = { index ->
            val adapter = recyclerView.adapter as? SectionAdapter
            adapter?.isHeader(index) == true
        }))
        adapter.headerClickListener = View.OnClickListener {
            Log.e("TAG","Header click tag : ${it.tag}")
        }
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
}