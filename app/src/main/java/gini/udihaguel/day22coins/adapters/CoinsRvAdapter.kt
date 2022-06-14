package gini.udihaguel.day22coins.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import gini.udihaguel.day22coins.R
import kotlin.math.roundToInt


class CoinsRvAdapter(private val coinsMap:Map<String,Double>, private val usd:Double, private val ils:Double) : RecyclerView.Adapter<CoinsRvAdapter.ViewHolder>(){

    companion object{
        const val LARGER_THAN_USD:Int = 0
        const val SMALLER_THAN_USD:Int = 1
    }
    private val coinsList = coinsMap.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = if (getItemViewType(viewType) == LARGER_THAN_USD) {
            LayoutInflater.from(parent.context).inflate(R.layout.coin_item_150, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.coin_item_100, parent, false)
        }
       return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coinsList[position]
        val valueInIls = (((1/coin.second)*ils)*1000).roundToInt() / 1000.0

        holder.tvCurrency.text = coin.first
        holder.tvValueInIls.text = valueInIls.toString()

        holder.clItem.setOnClickListener {
            flipAnimation(it, holder.tvValueInIls)
        }
    }

    private fun flipAnimation(view: View, tvValueInIls: TextView) {
        val anim1 = ObjectAnimator.ofFloat(view,
            "scaleX",
            1f, 0f)

        val anim2 = ObjectAnimator.ofFloat(view,
            "scaleX",
            0f, 1f)

        anim1.interpolator = DecelerateInterpolator()
        anim2.interpolator = AccelerateInterpolator()

        anim1.addListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (tvValueInIls.visibility == View.VISIBLE)
                    tvValueInIls.visibility = View.INVISIBLE
                else
                    tvValueInIls.visibility = View.VISIBLE
                anim2.start()
            }
        })
        anim1.start()
    }

    override fun getItemCount(): Int = coinsList.size

    override fun getItemViewType(position: Int): Int {
        val currentCoin:Double = coinsList[position].second

        return if (usd < currentCoin)
            SMALLER_THAN_USD
        else
            LARGER_THAN_USD

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvCurrency: TextView = itemView.findViewById(R.id.tv_currency)
        val tvValueInIls: TextView = itemView.findViewById(R.id.tv_value_in_ils)
        val clItem:ConstraintLayout = itemView.findViewById(R.id.cl_item)
    }


}