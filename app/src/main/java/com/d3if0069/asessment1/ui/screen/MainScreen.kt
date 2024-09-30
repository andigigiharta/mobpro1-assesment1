package com.d3if0069.asessment1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d3if0069.asessment1.R
import com.d3if0069.asessment1.navigation.Screen
import com.d3if0069.asessment1.ui.theme.TeoremaPhythagorasTheme

@Composable
fun ScreenContent(modifier: Modifier) {
    var sisiSatu by rememberSaveable { mutableStateOf("") }
    var sisiDua by rememberSaveable { mutableStateOf("") }
    var sisiTiga by rememberSaveable { mutableStateOf("") }
    var selectedRadioButton by remember { mutableStateOf("option1") }
    var hasil by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    var sisiSatuError by rememberSaveable { mutableStateOf(false) }
    var sisiDuaError by rememberSaveable { mutableStateOf(false) }
    var sisiTigaError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro),
            modifier = Modifier.fillMaxWidth()
        )
        Surface(
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = R.drawable.contohsegitiga),
                contentDescription = "Gambar Segitiga",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.sisi_hilang),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedRadioButton == "option1", onClick = {
                        selectedRadioButton = "option1"
                        sisiTiga = ""
                        hasil = 0f
                    },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary))
                Text(
                    text = stringResource(id = R.string.sisiy),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selectedRadioButton == "option1") MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
                RadioButton(
                    selected = selectedRadioButton == "option2",
                    onClick = {
                        selectedRadioButton = "option2"
                        sisiDua = ""
                        hasil = 0f
                    },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = stringResource(id = R.string.sisixz),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selectedRadioButton == "option2") MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
        OutlinedTextField(
            value = sisiSatu,
            isError = sisiSatuError,
            onValueChange = { sisiSatu = it
                hasil = 0f},
            label = { Text(text = stringResource(id = R.string.sisixz)) },
            trailingIcon = { IconPicker(sisiSatuError, "cm")},
            supportingText = { ErrorHint(sisiSatuError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = sisiDua,
            isError = sisiDuaError,
            onValueChange = { sisiDua = it
                hasil = 0f},
            label = { Text(text = stringResource(id = R.string.sisixz), color = if (selectedRadioButton == "option1") Color.Black else Color.Gray) },
            trailingIcon = { IconPicker(sisiDuaError, "cm")},
            supportingText = { ErrorHint(sisiDuaError)},
            singleLine = true,
            enabled = selectedRadioButton == "option1",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = sisiTiga,
            isError = sisiTigaError,
            onValueChange = {
                sisiTiga = it
                hasil = 0f
                            },
            label = { Text(text = stringResource(id = R.string.sisiy), color = if (selectedRadioButton == "option2") Color.Black else Color.Gray) },
            trailingIcon = { IconPicker(sisiTigaError, "cm")},
            supportingText = { ErrorHint(sisiTigaError)},
            singleLine = true,
            enabled = selectedRadioButton == "option2",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (selectedRadioButton == "option1") {
                    sisiSatuError = (sisiSatu == "" || sisiSatu == "0")
                    sisiDuaError = (sisiDua == "" || sisiDua == "0")
                    if (sisiSatuError || sisiDuaError) return@Button
                }
                else{
                    sisiSatuError = (sisiSatu == "" || sisiSatu == "0")
                    sisiTigaError = (sisiTiga == "" || sisiTiga == "0" || sisiTiga < sisiSatu)
                    if (sisiSatuError || sisiTigaError) return@Button
                }

                val sisi1 = sisiSatu.toFloatOrNull() ?: 0f
                val sisi2 = sisiDua.toFloatOrNull() ?: 0f
                val sisi3 = sisiTiga.toFloatOrNull() ?: 0f
                hasil = hitungSisi(sisi1, sisi2, sisi3)
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }

        if (hasil != 0f){
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.hasil, hasil),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

private fun hitungSisi(sisiSatu: Float, sisiDua: Float, sisiTiga: Float): Float {
    return when {
        sisiDua == 0f -> {
            val sisiBSquared = (sisiTiga * sisiTiga) - (sisiSatu * sisiSatu)
            kotlin.math.sqrt(sisiBSquared)
        }
        else -> {
            val sisiTigaSquared = (sisiSatu * sisiSatu) + (sisiDua * sisiDua)
            kotlin.math.sqrt(sisiTigaSquared)
        }
    }
}

@Composable
fun IconPicker (isError: Boolean, unit: String){
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
    else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint (isError: Boolean){
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    TeoremaPhythagorasTheme {
        MainScreen(rememberNavController())
    }
}
