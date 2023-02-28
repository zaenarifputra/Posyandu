package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.posdu.R
import model.ISOnBoardingModel

class OnboardingPagerAdapter(
    private val imagesList: List<ISOnBoardingModel>,
    private val context: Context
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_image_slider_on_boarding, container, false)
        val imageView = view.findViewById<ImageView>(R.id.iv_banner)

        imageView.apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageDrawable(imagesList[position].imageSource) }
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return imagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}