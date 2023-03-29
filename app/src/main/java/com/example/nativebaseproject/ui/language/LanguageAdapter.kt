package com.example.nativebaseproject.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.nativebaseproject.common.util.getDefaultLanguage
import com.example.nativebaseproject.databinding.ItemLanguageBinding

/**
 * Created by ThangNNT on 23/03/2023.
 */
class LanguageAdapter(
    private val languages: List<LanguageModel>,
    selectedLanguage: LanguageModel? = getDefaultLanguage(),
    private val onLanguageChanged: (LanguageModel) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {
    private var selectedPos = 0
    init {
        if (selectedLanguage != null){
            for (i in languages.indices){
                if (languages[i].languageCode == selectedLanguage.languageCode){
                    selectedPos = i
                    break
                }
            }
        }
    }
    class ViewHolder(val binding: ItemLanguageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLanguageBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = languages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val languageModel = languages[position]
        holder.apply {
            binding.languageModel = languageModel
            binding.ivSelected.isVisible = position == selectedPos
            binding.root.setOnClickListener {
                if (position == selectedPos) return@setOnClickListener
                onLanguageChanged.invoke(languageModel)
                val oldPos = selectedPos
                selectedPos = position
                notifyItemChanged(oldPos, PAYLOAD_UPDATE_SELECTED_CHANGED)
                notifyItemChanged(selectedPos, PAYLOAD_UPDATE_SELECTED_CHANGED)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            payloads.forEach {
                if (it == PAYLOAD_UPDATE_SELECTED_CHANGED){
                    holder.binding.ivSelected.isVisible = position == selectedPos
                }
            }
        }
    }

    companion object {
        const val PAYLOAD_UPDATE_SELECTED_CHANGED = "PAYLOAD_UPDATE_SELECTED_CHANGED"
    }
}