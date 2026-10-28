package dev.frost819.newbv.app.entity.player

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * [VideoAspectRatio] 的单元测试。
 *
 * 验证枚举值、ratio 属性及 [VideoAspectRatio.fromOrdinal] 安全解析。
 */
class VideoAspectRatioTest {
    @Test
    fun `Default has null ratio`() {
        assertThat(VideoAspectRatio.Default.ratio).isNull()
    }

    @Test
    fun `FourToThree has 4f divided by 3f ratio`() {
        assertThat(VideoAspectRatio.FourToThree.ratio).isEqualTo(4f / 3f)
    }

    @Test
    fun `SixteenToNine has 16f divided by 9f ratio`() {
        assertThat(VideoAspectRatio.SixteenToNine.ratio).isEqualTo(16f / 9f)
    }

    @Test
    fun `entries contains five values`() {
        assertThat(VideoAspectRatio.entries).hasSize(5)
    }

    @Test
    fun `fromOrdinal returns correct value for valid ordinals`() {
        assertThat(VideoAspectRatio.fromOrdinal(0)).isEqualTo(VideoAspectRatio.Default)
        assertThat(VideoAspectRatio.fromOrdinal(1)).isEqualTo(VideoAspectRatio.FourToThree)
        assertThat(VideoAspectRatio.fromOrdinal(2)).isEqualTo(VideoAspectRatio.SixteenToNine)
        assertThat(VideoAspectRatio.fromOrdinal(3)).isEqualTo(VideoAspectRatio.Rotate90)
        assertThat(VideoAspectRatio.fromOrdinal(4)).isEqualTo(VideoAspectRatio.Rotate270)
    }

    @Test
    fun `fromOrdinal returns Default for negative ordinal`() {
        assertThat(VideoAspectRatio.fromOrdinal(-1)).isEqualTo(VideoAspectRatio.Default)
    }

    @Test
    fun `fromOrdinal returns Default for out-of-bounds ordinal`() {
        assertThat(VideoAspectRatio.fromOrdinal(99)).isEqualTo(VideoAspectRatio.Default)
    }

    @Test
    fun `FourToThree ratio is approximately 1_333`() {
        val ratio = VideoAspectRatio.FourToThree.ratio!!
        assertThat(ratio).isWithin(0.001f).of(1.3333f)
    }

    @Test
    fun `SixteenToNine ratio is approximately 1_778`() {
        val ratio = VideoAspectRatio.SixteenToNine.ratio!!
        assertThat(ratio).isWithin(0.001f).of(1.7778f)
    }

    @Test
    fun `rotation modes preserve source aspect ratio and expose their angles`() {
        assertThat(VideoAspectRatio.Rotate90.ratio).isNull()
        assertThat(VideoAspectRatio.Rotate90.rotationDegrees).isEqualTo(90f)
        assertThat(VideoAspectRatio.Rotate270.ratio).isNull()
        assertThat(VideoAspectRatio.Rotate270.rotationDegrees).isEqualTo(270f)
    }
}
