package com.example.posdu

import adapter.IndicatorAdapter
import adapter.OnboardingPagerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.posdu.databinding.ActivityMainBinding
import model.ISOnBoardingModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var indicatorAdapter: IndicatorAdapter
    private lateinit var onboardingPagerAdapter: OnboardingPagerAdapter
    private val imageSliderModels: MutableList<ISOnBoardingModel> = ArrayList()
    private val titleBanner = arrayOf(
        R.string.text_onboarding1_title,
        R.string.text_onboarding2_title,
        R.string.text_onboarding3_title,
    )
    private val descriptionBanner = arrayOf(
        R.string.text_onboarding1_desc,
        R.string.text_onboarding2_desc,
        R.string.text_onboarding3_desc
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageSliderModels.add(
            ISOnBoardingModel(
                ContextCompat.getDrawable(this, R.drawable.img_onboarding1), true, ""
            )
        )
        imageSliderModels.add(
            ISOnBoardingModel(
                ContextCompat.getDrawable(this, R.drawable.img_onboarding2), false, ""
            )
        )
        imageSliderModels.add(
            ISOnBoardingModel(
                ContextCompat.getDrawable(this, R.drawable.img_onboarding3), false, ""
            )
        )
        initView()
        initListener()
        setDescription(0)
    }

    private fun initView() {
        onboardingPagerAdapter = OnboardingPagerAdapter(imageSliderModels, this)
        indicatorAdapter = IndicatorAdapter(imageSliderModels)
        binding.vpImageSlider.adapter = onboardingPagerAdapter
        binding.rvPoint.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        binding.rvPoint.adapter = indicatorAdapter
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener {
            binding.vpImageSlider.currentItem = binding.vpImageSlider.currentItem + 1
        }

        binding.tvSignIn.setOnClickListener {
            goToCheckUser(true)
        }

        binding.vpImageSlider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                deactivatePoint()
                if (position < imageSliderModels.size) {
                    val imageSliderModel = imageSliderModels[position]
                    imageSliderModel.isActive = true
                    imageSliderModels[position] = imageSliderModel
                }
                if (position == imageSliderModels.size - 1) {
                    binding.btnNext.text = getString(R.string.text_btn_create_account)
                    binding.btnNext.setOnClickListener { goToCheckUser(false) }
                } else {
                    binding.btnNext.text = getString(R.string.text_btn_next)
                    binding.btnNext.setOnClickListener {
                        binding.vpImageSlider.currentItem = binding.vpImageSlider.currentItem + 1
                    }
                }

                setDescription(position)
                repeat(3) { indicatorAdapter.notifyItemChanged(position + (it - 1)) }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setDescription(position: Int) {
        binding.tvTitleOnBoarding.text = getString(titleBanner[position])
        binding.tvDescOnBoarding.text = getString(descriptionBanner[position])
    }

    private fun deactivatePoint() {
        imageSliderModels.forEachIndexed { index, imageSliderModel ->
            imageSliderModel.isActive = false
            imageSliderModels[index] = imageSliderModel
        }
    }

    private fun goToCheckUser(isLogin: Boolean) {
        if (isLogin) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}