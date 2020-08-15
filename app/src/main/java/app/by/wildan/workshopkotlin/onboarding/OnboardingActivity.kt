package app.by.wildan.workshopkotlin.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.auth.RegisterActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupOnboarding()

        buttonSkip.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun setupOnboarding() {
        val onboardingData = listOf<Onboarding>(
            Onboarding(
                R.drawable.img_onboarding_1,
                R.string.title_onboarding_1,
                R.string.subtitle_onboarding_1
            ),
            Onboarding(
                R.drawable.img_onboarding_2,
                R.string.title_onboarding_2,
                R.string.subtitle_onboarding_2
            ),
            Onboarding(
                R.drawable.img_onboarding_3,
                R.string.title_onboarding_3,
                R.string.subtitle_onboarding_3
            )
        )
        onboardingAdapter = OnboardingAdapter(onboardingData)
        onboardingPager.adapter = onboardingAdapter

        TabLayoutMediator(indicatorView, onboardingPager) { tab, position ->
            //some Implementation, not used for now.
        }.attach()
    }
}