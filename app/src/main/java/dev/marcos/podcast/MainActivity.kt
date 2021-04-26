package dev.marcos.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.imageview.ShapeableImageView
import dev.marcos.podcast.data.Home
import dev.marcos.podcast.utils.HorizontalMarginItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomSheet: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        viewPager = findViewById(R.id.pager)
        bottomSheet = findViewById(R.id.bottomSheet)


        APIService.podcastService.get().enqueue(object : Callback<Home?> {
            override fun onResponse(call: Call<Home?>, response: Response<Home?>) {
                response.body()?.let { home: Home ->
//                    thread {
                    viewPager.adapter = CustomAdapterViewPager(baseContext, home.featured)

                    viewPager.offscreenPageLimit = 1
                    viewPager.currentItem = Int.MAX_VALUE / 2

                    recyclerView.adapter = CustomAdapterHorizontal(baseContext, home.regions)
//                    }
                }
            }

            override fun onFailure(call: Call<Home?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
            page.alpha = 0.5f + (1 - abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            baseContext,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)

        BottomSheetBehavior.from(bottomSheet).addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    val fragment = supportFragmentManager.findFragmentById(R.id.fragment_player)
                    val playerFragment = fragment as? PlayerFragment
                    playerFragment?.animate(slideOffset)
                }
            }
        )

    }
}