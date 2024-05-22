package com.line.pokedex.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.line.pokedex.ui.main.MainViewModel
import com.line.pokedex.utils.RecyclerViewPaginator

object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
  }

  @JvmStatic
  @BindingAdapter("pagination")
  fun pagination(view: RecyclerView, viewModel: MainViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isLoading },
      loadMore = { viewModel.fetchNext() },
      onLast = { false },
    ).run {
      threshold = 8
    }
  }
}
