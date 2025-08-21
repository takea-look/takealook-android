package my.takealook.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import my.takealook.theme.TklTheme

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
) {
    val commonModifier = modifier
        .widthIn(max = 300.dp)
        .clip(RoundedCornerShape(12.dp))

    val isInPreview = LocalInspectionMode.current
    if (isInPreview) {
        Box(modifier = commonModifier.background(Color.LightGray))
        return
    }


    AsyncImage(
        modifier = modifier
            .widthIn(max = 300.dp)
            .clip(RoundedCornerShape(12.dp)),
        model = imageUrl,
        contentDescription = ""
    )
}

@Composable
@Preview(showBackground = true)
fun ChatBubblePreview() {
    TklTheme {
        ChatBubble(modifier = Modifier.size(100.dp))
    }
}