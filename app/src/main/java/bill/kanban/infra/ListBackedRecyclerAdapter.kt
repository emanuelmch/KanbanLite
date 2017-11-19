package bill.kanban.infra

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class ListBackedRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(val context: Context) : RecyclerView.Adapter<VH>() {

    abstract val layoutResource: Int

    var items = emptyList<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = createViewHolder(context.inflate(layoutResource, parent, false))

    abstract fun createViewHolder(item: View): VH

    override fun onBindViewHolder(holder: VH, position: Int)
            = bindViewHolder(holder, items[position])

    abstract fun bindViewHolder(holder: VH, item: T)

}