package at.marki.moviedb.core.designsystems.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import at.marki.moviedb.core.designsystems.ThemePreviews
import at.marki.moviedb.core.designsystems.theme2.AppTheme

/**
 * A text field for submitting a password.
 *
 * @param value The current value of the text field.
 * @param label The label of the text field.
 * @param onTextChanged The callback that is called when the text field value changes.
 * @param onDoneClicked The callback that is called when the done button is clicked on the keyboard. If null, the done button is not shown.
 */
@Composable
fun PasswordTextField(
    value: String,
    label: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onDoneClicked: (() -> Unit)? = null,
) {
    val hasDoneAction = onDoneClicked != null
    var valueText by remember(value) { mutableStateOf(value) }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = valueText,
        onValueChange = { newValue ->
            valueText = newValue
            onTextChanged(valueText)
        },
        label = {
            Text(
                text = label,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = when (hasDoneAction) {
                true -> ImeAction.Done
                false -> ImeAction.None
            },
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDoneClicked?.invoke() },
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {

            val icon = when (passwordVisible) {
                true -> Icons.Default.Visibility
                else -> Icons.Default.VisibilityOff
            }

            IconButton(
                onClick = { passwordVisible = !passwordVisible },
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedTextColor = LocalContentColor.current,
            unfocusedTextColor = LocalContentColor.current,
            cursorColor = LocalContentColor.current,
            unfocusedLabelColor = LocalContentColor.current,
            errorTextColor = LocalContentColor.current,
        ),
    )
}

@ThemePreviews
@Composable
private fun PasswordTextFieldPreview() {
    AppTheme {
        Surface {
            PasswordTextField(
                value = "password",
                label = "Password",
                onTextChanged = { },
            )
        }
    }
}