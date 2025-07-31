package my.takealook.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.takealook.theme.TklTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onSignInSuccess: (accessToken: String) -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when(effect) {
                is LoginSideEffect.SignInSuccess -> {
                    onSignInSuccess(effect.accessToken)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            TklTextField(
                text = uiState.username,
                onTextChange = { viewModel.send(LoginUiAction.OnUsernameChanged(it) ) },
                placeHolder = "Username",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TklTextField(
                text = uiState.password,
                onTextChange = { viewModel.send(LoginUiAction.OnPasswordChanged(it) ) },
                placeHolder = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
            )

            if (uiState.errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.error
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.align(Alignment.End).padding(end = 14.dp),
                text = "Forgot password?",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            TklButton(
                onClick = {
                    viewModel.send(
                        action = LoginUiAction
                            .SignIn(
                                username = uiState.username,
                                password = uiState.password
                            )
                    )
                },
                text = "Log In",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun LoginScreenLightPreview() {
    TklTheme {
        LoginScreen()
    }
}

@Composable
@Preview
fun LoginScreenDarkPreview() {
    TklTheme(darkTheme = true) {
        LoginScreen()
    }
}

@Composable
fun TklTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    hasFocus: Boolean = false,
    trailingIcon: @Composable () -> Unit = {}
) {
    val textStyle = MaterialTheme.typography.bodyLarge
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .let {
                if (hasFocus) {
                    it.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(14.dp)
                    )
                } else {
                    it
                }
            }
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 24.dp, vertical = 18.dp),
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeHolder,
                            color = MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                                .copy(alpha = 0.6f),
                            style = textStyle
                        )
                    }
                    innerTextField()
                }
                trailingIcon()
            }

        },
        visualTransformation = visualTransformation,
        textStyle = textStyle.copy(
            color = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        cursorBrush = MaterialTheme.colorScheme.primary.run {
            Brush.horizontalGradient(
                listOf(this, this)
            )
        }
    )
}


@Composable
@Preview
fun TklTextFieldPreview() {
    TklTheme {
        TklTextField(
            text = "",
            onTextChange = {},
            placeHolder = "Email"
        )
    }
}

@Composable
fun TklButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .widthIn(min = 100.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp, horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
@Preview
fun TklButtonPreview() {
    TklTheme {
        TklButton(
            onClick = {},
            text = "Create Account"
        )
    }
}