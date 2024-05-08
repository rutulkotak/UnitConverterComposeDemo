package com.rutulkotak.unitconverter.composedemo.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.rutulkotak.unitconverter.composedemo.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {

    // When Activity restarts, remember will be reset
    // If we want our state in a composable to preserve such configuration change, we should use rememberSaveable
    var displayText by rememberSaveable { mutableStateOf("Select the conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) } // To assign the same width as TextField.
    var expended by remember { mutableStateOf(false) }
    val icon = if (expended) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column {

        OutlinedTextField(
            value = displayText,
            onValueChange = { displayText = it },
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = "Conversion Type") },
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                    modifier.clickable {
                        expended = !expended
                    })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false },
            modifier = modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        displayText = conversion.description
                        expended = false
                        convert(conversion)
                    })
            }
        }
    }
}