package com.example.cse438.cse438_assignment2.Adapter

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class MoveItemListener(val dragAdapter : TrackInPlayListAdapter) : ItemTouchHelper.Callback() {

    interface Listener {
        fun moveRow(fromPosition: Int, toPosition: Int)
        fun clearRow(itemViewHolder: TrackInPlayListAdapterViewHolder)
        fun selectRow(itemViewHolder: TrackInPlayListAdapterViewHolder)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        dragAdapter.moveRow(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is TrackInPlayListAdapterViewHolder) {
                dragAdapter.selectRow(viewHolder)
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (viewHolder is TrackInPlayListAdapterViewHolder) {
            dragAdapter.clearRow(viewHolder)
        }
    }

    override fun isLongPressDragEnabled(): Boolean = false

    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

}