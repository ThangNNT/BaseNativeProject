package com.example.nativebaseproject.common.action_popup

import androidx.annotation.DrawableRes

data class ActionItem<T: Any>(
    @DrawableRes val icon: Int,
    val actionText: String, val action: (actionModel: T) -> Unit
)