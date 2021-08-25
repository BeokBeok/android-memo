package camp.nextstep.edu.memo.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.memo.domain.entity.Memo
import java.util.UUID

class MainAdapter(
    val onUpdate: (uuid: UUID) -> Unit,
    val onDelete: (uuid: UUID) -> Unit
) : ListAdapter<Memo, MainViewHolder>(object : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean =
        oldItem.uuid == newItem.uuid

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent = parent).apply {
            itemView.setOnClickListener {
                onUpdate(getItem(adapterPosition).uuid)
            }
            itemView.setOnLongClickListener {
                onDelete(getItem(adapterPosition).uuid)
                true
            }
        }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
