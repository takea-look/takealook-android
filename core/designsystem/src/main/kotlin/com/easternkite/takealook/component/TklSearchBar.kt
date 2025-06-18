package com.easternkite.takealook.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.easternkite.takealook.R
import com.easternkite.takealook.theme.TklTheme

@Composable
fun TklSearchBar(
    query: TextFieldState,
    modifier: Modifier = Modifier,
    onKeyboardAction: () -> Unit = {},
) {
    val textStyle = MaterialTheme
        .typography
        .bodyLarge
        .copy(color = MaterialTheme.colorScheme.primary)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(32.dp).padding(end = 8.dp),
            model = R.drawable.ic_search_outlined,
            contentDescription = "Search icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        BasicTextField(
            state = query,
            lineLimits = TextFieldLineLimits.SingleLine,
            onKeyboardAction = { onKeyboardAction() },
            keyboardOptions = KeyboardOptions.Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        )
    }
}

@Composable
@Preview
fun TklSearchBarLightPreview() {
    val state = rememberTextFieldState(stringResource(R.string.tkl_search_placeHolder))
    TklTheme {
        TklSearchBar(modifier = Modifier.width(300.dp), query = state)
    }
}

@Composable
@Preview
fun TklSearchBarDarkPreview() {
    val state = rememberTextFieldState(stringResource(R.string.tkl_search_placeHolder))
    TklTheme(darkTheme = true) {
        TklSearchBar(modifier = Modifier.width(300.dp), query = state)
    }
}