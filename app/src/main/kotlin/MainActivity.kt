package jp.takuji31.epoxyexample

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.airbnb.epoxy.Carousel
import com.squareup.picasso.Picasso
import jp.takuji31.epoxyexample.model.Banner
import jp.takuji31.epoxyexample.model.Entry
import jp.takuji31.epoxyexample.model.Work
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    var works: List<Work> = Work.dummyData
    var entries: List<Entry> = Entry.dummyData
    var banners: List<Banner> = Banner.dummyData

    var pagingSnapHelper: PagerSnapHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setItemSpacingDp(8)
        recyclerView.withModels {
            Carousel.setDefaultGlobalSnapHelperFactory(null)
            carousel {
                id("bannerCarousel")

                withModelsFrom(banners) {
                    ItemBannerBindingModel_()
                        .id("banner_${it.id}")
                        .banner(it)
                }

                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }

                onBind { _, view, _ ->
                    if (pagingSnapHelper == null) {
                        pagingSnapHelper = PagerSnapHelper()
                    }
                    pagingSnapHelper?.attachToRecyclerView(view)
                }

                onUnbind { _, _ ->
                    pagingSnapHelper?.attachToRecyclerView(null)
                }
            }

            works.take(10).forEach { work ->
                itemWork {
                    id("work_${work.id}")
                    work(work)
                }
            }

            headerBlogEntry {
                id("blog_header")
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }

            carousel {
                id("entryCaroucel")

                withModelsFrom(entries) { entry ->
                    ItemEntryBindingModel_()
                        .id("entry_${entry.id}")
                        .entry(entry)

                }

                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }

            works.drop(10).forEach { work ->
                itemWork {
                    id("work_${work.id}")
                    work(work)
                }
            }


//            carousel {
//                id("entryCarousel")
//            }
        }
    }
}

@BindingAdapter("imageUri")
fun ImageView.setImageUri(uri: Uri?) {
    if (uri != null) {
        Picasso.get()
            .load(uri)
            .into(this)
    } else {
        setImageDrawable(null)
    }
}