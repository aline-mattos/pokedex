package com.line.pokedex.binding

import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

object ViewBinding {

  @JvmStatic
  @BindingAdapter("glideImage")
  fun bindLoadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
  }

  @JvmStatic
  @BindingAdapter("onBackPressed")
  fun bindOnBackPressed(view: View, onBackPress: Boolean) {
    val context = view.context
    if (onBackPress && context is OnBackPressedDispatcherOwner) {
      view.setOnClickListener {
        context.onBackPressedDispatcher.onBackPressed()
      }
    }
  }
}
