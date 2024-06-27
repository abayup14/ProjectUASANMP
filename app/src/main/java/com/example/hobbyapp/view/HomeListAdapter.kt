package com.example.hobbyapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.databinding.CardItemBinding
import com.example.hobbyapp.model.News


class HomeListAdapter(val newsList: ArrayList<News>, val adapterOnClick: (News) -> Unit): RecyclerView.Adapter<HomeListAdapter.ListViewHolder>(), NewsDetailClick
//    , TodoCheckedChangeListener
{
    class ListViewHolder(var view: CardItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.view.checkTask.text = todoList[position].title
//
//        holder.binding.checkTask.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (buttonView.isPressed) {
//                adapterOnClick(todoList[position])
//            }
//        }
//
//        holder.binding.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.view.news = newsList[position]
//        holder.view.listener = this
        holder.view.detailListener = this
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

//    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: News) {
//        if (cb.isPressed) {
//            adapterOnClick(obj)
//        }
//    }

    override fun onNewsDetailClick(v: View) {
        val id = v.tag.toString().toInt()
        val action = HomeFragmentDirections.actionDetailFragment(id)

        Navigation.findNavController(v).navigate(action)
    }
}