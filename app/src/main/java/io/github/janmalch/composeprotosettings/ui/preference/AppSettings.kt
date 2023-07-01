package io.github.janmalch.composeprotosettings.ui.preference

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreSettingState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreTransformSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import io.github.janmalch.composeprotosettings.preferences.UserPreferencesSerializer
import io.github.janmalch.composeprotosettings.preferences.copy
import io.github.janmalch.composeprotosettings.ui.theme.ComposeProtoSettingsTheme

@Composable
fun AppSettings() {
    val dataStoreState = rememberProtoDataStoreState(
        // "filename" is optional, but it is recommended to set the protobuf file name so that it wouldn't conflict with other datastore definition.
        filename = "compose_settings_datastore_proto.pb",
        serializer = UserPreferencesSerializer,
    )
    val state = rememberProtoDataStoreTransformSettingState(
        protoDataStoreState = dataStoreState,
        encoder = { saved, newValue -> saved.copy { showCompleted = newValue } },
        decoder = { it.showCompleted }
    )

    SettingsCheckbox(
        state = state,
        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Wifi") },
        title = { Text(text = "Hello") },
        subtitle = { Text(text = "This is a longer text") },
    )
}

@Preview(showBackground = true)
@Composable
fun AppSettingsPreview() {
    ComposeProtoSettingsTheme {
        AppSettings()
    }
}
