package com.example.nativebaseproject.common.action_popup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nativebaseproject.databinding.ItemActionBinding

class PopupAdapter<T: Any>(private val actionItems: List<ActionItem<T>>, private val actionClicked: (actionItem: ActionItem<T>)-> Unit): RecyclerView.Adapter<PopupAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemActionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemActionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val action = actionItems[position]
        holder.binding.apply {
            tvActionText.text = action.actionText
            ivIcon.setImageResource(action.icon)
            root.setOnClickListener {
                actionClicked.invoke(action)
            }
        }
    }

    override fun getItemCount() = actionItems.size
}