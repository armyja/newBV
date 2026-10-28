package dev.frost819.newbv.player

import android.view.LayoutInflater
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import dev.frost819.newbv.player.impl.exo.ExoMediaPlayer

/**
 * B 站视频播放器 Composable。
 *
 * 封装 Media3 [PlayerView]，将 [ExoMediaPlayer] 的 ExoPlayer 实例绑定到 UI。
 *
 * @param modifier Compose 修饰符
 * @param videoPlayer 播放器实例，为 null 时不渲染
 * @param useTextureView 是否使用可参与图层变换的纹理表面，旋转画面时启用
 */
@OptIn(UnstableApi::class)
@Composable
fun BvVideoPlayer(
    modifier: Modifier = Modifier,
    videoPlayer: AbstractVideoPlayer?,
    useTextureView: Boolean = false,
) {
    if (videoPlayer is ExoMediaPlayer) {
        key(useTextureView) {
            AndroidView(
                modifier = modifier.fillMaxSize(),
                factory = { ctx ->
                    (if (useTextureView) {
                        LayoutInflater.from(ctx).inflate(R.layout.bv_video_player_texture, null, false) as PlayerView
                    } else {
                        PlayerView(ctx)
                    }).apply {
                        player = videoPlayer.mPlayer
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        useController = false
                    }
                },
                update = { playerView ->
                    playerView.player = videoPlayer.mPlayer
                },
                onRelease = { playerView ->
                    playerView.player = null
                },
            )
        }
    }
}
