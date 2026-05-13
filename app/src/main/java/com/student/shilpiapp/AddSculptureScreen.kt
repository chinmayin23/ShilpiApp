package com.student.shilpiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSculptureScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var carvingStyle by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add New Sculpture",
                        color = Color(0xFFF5ECD7)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFC8922A)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Sculpture Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC8922A),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Name field
            AdminTextField(
                value = name,
                onValueChange = { name = it },
                label = "Sculpture Name"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Material field
            AdminTextField(
                value = material,
                onValueChange = { material = it },
                label = "Material (e.g. Black Granite)"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Price field
            AdminTextField(
                value = price,
                onValueChange = { price = it },
                label = "Price (e.g. ₹45,000)"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Artist Name field
            AdminTextField(
                value = artistName,
                onValueChange = { artistName = it },
                label = "Artist Name"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Village field
            AdminTextField(
                value = village,
                onValueChange = { village = it },
                label = "Village (e.g. Shivarapatna)"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Carving Style field
            AdminTextField(
                value = carvingStyle,
                onValueChange = { carvingStyle = it },
                label = "Carving Style (e.g. Hoysala)"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description field
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text(
                        text = "Description",
                        color = Color(0xFF888888)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC8922A),
                    unfocusedBorderColor = Color(0xFF444444),
                    focusedTextColor = Color(0xFFF5ECD7),
                    unfocusedTextColor = Color(0xFFF5ECD7),
                    cursorColor = Color(0xFFC8922A)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Status message
            if (statusMessage.isNotEmpty()) {
                Text(
                    text = statusMessage,
                    fontSize = 13.sp,
                    color = if (isSuccess) Color(0xFF4CAF50) else Color(0xFFE53935),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Save button
            Button(
                onClick = {
                    when {
                        name.isEmpty() -> statusMessage = "Please enter sculpture name!"
                        material.isEmpty() -> statusMessage = "Please enter material!"
                        price.isEmpty() -> statusMessage = "Please enter price!"
                        artistName.isEmpty() -> statusMessage = "Please enter artist name!"
                        village.isEmpty() -> statusMessage = "Please enter village!"
                        carvingStyle.isEmpty() -> statusMessage = "Please enter carving style!"
                        description.isEmpty() -> statusMessage = "Please enter description!"
                        else -> {
                            isSaving = true
                            statusMessage = ""
                            isSuccess = false

                            val newId = "SKS-2025-${System.currentTimeMillis()}"
                            val newSculpture = Sculpture(
                                id = newId,
                                name = name,
                                material = material,
                                price = price,
                                artistName = artistName,
                                village = village,
                                imageResId = R.drawable.placeholder,
                                description = description,
                                carvingStyle = carvingStyle
                            )

                            FirebaseHelper.addSculpture(newSculpture) { success ->
                                isSaving = false
                                if (success) {
                                    isSuccess = true
                                    statusMessage = "✅ Sculpture added successfully to Firebase!"
                                    name = ""
                                    material = ""
                                    price = ""
                                    artistName = ""
                                    village = ""
                                    description = ""
                                    carvingStyle = ""
                                } else {
                                    isSuccess = false
                                    statusMessage = "❌ Failed to save. Check internet connection!"
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSaving) Color(0xFF444444) else Color(0xFFC8922A)
                ),
                shape = RoundedCornerShape(8.dp),
                enabled = !isSaving
            ) {
                Text(
                    text = if (isSaving) "Saving to Firebase..." else "Save Sculpture",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun AdminTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color(0xFF888888)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFC8922A),
            unfocusedBorderColor = Color(0xFF444444),
            focusedTextColor = Color(0xFFF5ECD7),
            unfocusedTextColor = Color(0xFFF5ECD7),
            cursorColor = Color(0xFFC8922A)
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}