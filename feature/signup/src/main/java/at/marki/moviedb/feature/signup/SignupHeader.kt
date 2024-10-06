package at.marki.moviedb.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

private const val HEADER_SIZE = 230
private const val HEADER_IMAGE_SIZE = 200
private const val HEADER_ICON_SIZE = 116

@Composable
fun SignupHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(HEADER_SIZE.dp),
    ) {
        HeaderImage()
        HeaderIcon(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun HeaderImage(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.login_header),
        contentDescription = "Logo",
        modifier = modifier
            .fillMaxWidth()
            .height(HEADER_IMAGE_SIZE.dp),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
fun HeaderIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(HEADER_ICON_SIZE.dp)
            .shadow(8.dp, CircleShape)
            .background(
                color = Color(0xFFF3F3F3),
                shape = CircleShape,
            )
            .border(4.dp, Color.White, CircleShape),
    ) {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Center)
        )
    }
}