package jp.takuji31.epoxyexample.model

import android.net.Uri
import androidx.core.net.toUri

data class Work(val id: String, val title: String, val imageUri: Uri) {
    companion object {
        val dummyData: List<Work> =
            (1..20).map { Work(it.toString(), "作品$it", "https://placehold.jp/150x200.png".toUri()) }
    }
}

data class Entry(val id: String, val title: String, val imageUri: Uri) {
    companion object {
        val dummyData: List<Entry> =
            (1..20).map { Entry(it.toString(), "記事$it", "https://placehold.jp/100x100.png".toUri()) }
    }
}

data class Banner(val id: String, val alt: String, val imageUri: Uri) {
    companion object {
        val dummyData: List<Banner> =
            (1..20).map { Banner(it.toString(), "バナー$it", "https://placehold.jp/320x180.png".toUri()) }
    }
}