Section List Project
========================

## Introduction
iOS에서 쉽게 구현할 수 있는 Sticky Section List를 RecyclerView. ItemDecoration을 응용하여 만든 프로젝트.  
[StackOverFkow][1] 를 참고하여 구현.  

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

        recyclerView.addItemDecoration(StickyHeaderItemDecoration(getSectionCallback()))
}
```
이후 Item을 그릴때 마다 해당 아이템이 Header인지 아닌지 호출되며  
Header인 경우 해당 View를 넘겨준다.  
```kotlin
private fun getSectionCallback(): StickyHeaderItemDecoration.SectionCallback {
        return object : StickyHeaderItemDecoration.SectionCallback {
            override fun isHeader(position: Int): Boolean {
                return adapter.isHeader(position)
            }

            override fun getHeaderLayoutView(list: RecyclerView, position: Int): View? {
                return adapter.getHeaderView(list, position)
            }
        }
}
```

### Adapter
Adapter안에서 Item이 Header인지 체크 및 View를 생성해서 return
```kotlin
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
```

[1]: https://stackoverflow.com/questions/32949971/how-can-i-make-sticky-headers-in-recyclerview-without-external-lib
