package dev.frost819.newbv.player

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import androidx.media3.ui.PlayerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/** 验证旋转播放布局使用可由图层变换的视频纹理表面。 */
@RunWith(AndroidJUnit4::class)
class BvVideoPlayerTextureLayoutTest {
    @Test
    fun rotation_layout_uses_TextureView() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val playerView =
            LayoutInflater
                .from(context)
                .inflate(R.layout.bv_video_player_texture, null, false) as PlayerView

        assertThat(playerView.videoSurfaceView).isInstanceOf(TextureView::class.java)
    }
}
