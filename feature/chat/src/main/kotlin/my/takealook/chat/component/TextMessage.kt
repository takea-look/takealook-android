package my.takealook.chat.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import my.takealook.theme.TklTheme

@Composable
fun TextMessage(
    authorId: String,
    authorName: String,
    profileImageUrl: String,
    isAuthorRepeated: Boolean,
    onImageClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    isUserMe: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {

    val spaceBetween = if (isAuthorRepeated)
        Modifier else Modifier.padding(top = 8.dp)

    Row(modifier = spaceBetween.then(modifier)) {
        when {
            isUserMe -> {}
            isAuthorRepeated -> {
                Spacer(modifier = Modifier.width(58.dp))
            }
            else -> ChatCircleImage(
                authorId = authorId,
                profileImageUrl = profileImageUrl,
                onClick = onImageClick
            )
        }
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f),
        ) {
            when {
                (isAuthorRepeated || isUserMe) -> Spacer(modifier = Modifier.height(4.dp))
                else -> AuthorName(authorName)
            }

            Column(
                modifier = Modifier
                    .align(if (isUserMe) Alignment.End else Alignment.Start)
                    .widthIn(0.dp, 250.dp)
                ,
                content = content
            )
        }
    }
}

@Composable
private fun AuthorName(
    name: String
) {
    Row {
        BasicText(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )
    }
}

@Composable
private fun ChatCircleImage(
    authorId: String,
    profileImageUrl: String,
    onClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    val borderColor = MaterialTheme.colorScheme.tertiary
    AsyncImage(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .border(1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .clickable(onClick = { onClick(authorId) })
            .then(modifier),
        model = profileImageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview(showBackground = true)
fun TextMessagePreview() {
    TklTheme {
        Column {
            TextMessage(
                authorId = "Lee",
                authorName = "Lee",
                profileImageUrl = "https://img.takealook.my/cat.jpeg",
                isUserMe = true,
                isAuthorRepeated = false,
                onImageClick = {}
            ) {
                ChatBubble(
                    modifier = Modifier
                        .align(if (true) Alignment.End else Alignment.Start)
                        .size(100.dp),
                )
            }

            TextMessage(
                authorId = "Lee",
                authorName = "Lee",
                profileImageUrl = "https://img.takealook.my/cat.jpeg",
                isUserMe = true,
                isAuthorRepeated = true,
                onImageClick = {}
            ) {
                ChatBubble(
                    modifier = Modifier
                        .align(if (false) Alignment.End else Alignment.Start)
                        .size(100.dp),
                )
            }

            TextMessage(
                authorId = "Kim",
                authorName = "Kim",
                profileImageUrl = "https://img.takealook.my/cat.jpeg",
                isUserMe = false,
                isAuthorRepeated = false,
                onImageClick = {}
            ) {
                ChatBubble(
                    modifier = Modifier
                        .align(if (false) Alignment.End else Alignment.Start)
                        .size(100.dp),
                )
            }
        }
    }
}