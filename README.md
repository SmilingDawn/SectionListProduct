Section List Project
========================

## Introduction
iOS에서 쉽게 구현할 수 있는 Sticky Section List를 RecyclerView. ItemDecoration을 응용하여 만든 프로젝트.  
[Github][1] 를 참고하여 구현.  

## Descrption
RecyclerView를 초기화하면서 CustomItemDecoration을 추가.  

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = StickyHeaderAdapter(getEvents())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(HeaderItemDecoration(recyclerView, false, isHeader = { index ->
            val adapter = recyclerView.adapter as? SectionAdapter
            adapter?.isHeader(index) == true
        }))
}
```
### Adapter
Adapter안에서 Item이 Header인지 체크 및 View를 생성해서 return
```kotlin
fun isHeader(postion: Int) = (getItemViewType(position = postion) == TYPE_SECTION)
```

[1]: https://gist.github.com/filipkowicz/1a769001fae407b8813ab4387c42fcbd
