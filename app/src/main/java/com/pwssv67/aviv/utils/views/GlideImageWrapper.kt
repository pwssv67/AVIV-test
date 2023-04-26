package com.pwssv67.aviv.utils.views

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
/**
 * Wrapper for [GlideImage] that shows a [Loader] when in inspection mode.
 *
 * For some reason, this isn't implemented in GlideImage itself for [Composable] placeholder, though mentioned in comments.
 *
 * @see [GlideImage]
* */
fun GlideImageWrapper(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    loading: @Composable (() -> Unit)? = null,
    failure: Placeholder? = null,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it }
) {
    if (LocalInspectionMode.current) {
        loading?.invoke()
    } else {
        val loadingPlaceholder = loading?.let { placeholder(it) }
        GlideImage(
            model,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter,
            loadingPlaceholder,
            failure,
            requestBuilderTransform,
        )
    }
}