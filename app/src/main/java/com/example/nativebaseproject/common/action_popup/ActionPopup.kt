package com.example.nativebaseproject.common.action_popup

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ListPopupWindow
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nativebaseproject.R
import com.example.nativebaseproject.common.util.getScreenHeight
import com.example.nativebaseproject.common.util.toPixel
import com.example.nativebaseproject.databinding.PopupActionBinding

@Suppress("unused")
object ActionPopup {
    /**
     * Usage example:
        val actions = listOf<ActionItem<Any>>(ActionItem(icon = R.drawable.ic_home_black_24dp, actionText = "Hello", action = {
        Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
        }),
        ActionItem(icon = R.drawable.ic_dashboard_black_24dp, actionText = "Haha", action = {
        Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
        }))
        ActionPopup.show(requireContext(), it, Any(), actions)
     */
     fun <T: Any>
            show(context: Context, anchorView: View, model: T, actions: List<ActionItem<T>>){
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PopupActionBinding.inflate(inflater, null, false)

        val popupWidth = context.resources.getDimensionPixelSize(R.dimen._200dp)
        val popupWindow = PopupWindow(
            binding.root,
            popupWidth,
            ListPopupWindow.WRAP_CONTENT,
            true
        )
        popupWindow.elevation = 2F.toPixel(context)
        binding.rvAction.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PopupAdapter(actions){ actionItem ->
                actionItem.action.invoke(model)
                popupWindow. dismiss()
            }
        }

        //show popup menu
        val values = IntArray(2)
        anchorView.getLocationInWindow(values)
        val positionOfIcon = values[1]
        val height = getScreenHeight() * 2 / 3
        if (positionOfIcon > height) {
            // when parent view in the bottom of the screen show popup up
            val offsetY = context.resources.getDimensionPixelSize(R.dimen._200dp)
            popupWindow.showAsDropDown(anchorView, 0, -offsetY, Gravity.END)
        } else {
            // when parent view in the bottom of the screen show popup down
            popupWindow.showAsDropDown(
                anchorView,
                0,
                8F.toPixel(context).toInt(),
                Gravity.END
            )
        }
    }
}