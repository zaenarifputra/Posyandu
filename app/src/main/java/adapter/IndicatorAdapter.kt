package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posdu.databinding.ItemIndicatorSmallBinding
import model.ISOnBoardingModel

class IndicatorAdapter(private val imageSliderModels: List<ISOnBoardingModel>) :
    RecyclerView.Adapter<IndicatorAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemIndicatorBinding = ItemIndicatorSmallBinding.inflate(layoutInflater, parent, false)
        return Holder(itemIndicatorBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (imageSliderModels[position].isActive) {
            holder.viewBinding.viewIndicatorNonactive.visibility = View.GONE
            holder.viewBinding.viewIndicatorActive.visibility = View.VISIBLE
        } else {
            holder.viewBinding.viewIndicatorNonactive.visibility = View.VISIBLE
            holder.viewBinding.viewIndicatorActive.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return imageSliderModels.size
    }

    inner class Holder(var viewBinding: ItemIndicatorSmallBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    )
}