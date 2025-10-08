package my.takealook.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TklAttachButton(
    onItemsAttached: (List<Uri>) -> Unit,
    modifier: Modifier = Modifier,
    input: String = "image/*",
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uri ->
        onItemsAttached(uri)
    }

    Column(
        modifier = modifier.clickable {
            onClick()
            launcher.launch(input)
        }
    ) {
        content()
    }
}

@Composable
@Preview
fun TklAttachButtonPreview() {
    TklAttachButton(
        onItemsAttached = {},
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}