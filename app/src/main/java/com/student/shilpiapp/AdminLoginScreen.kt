package com.student.shilpiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AdminLoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Admin credentials
    val ADMIN_USERNAME = "admin"
    val ADMIN_PASSWORD = "shilpi2025"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🏛",
            fontSize = 60.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Admin Panel",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC8922A)
        )

        Text(
            text = "Shilpa-Kala Showcase",
            fontSize = 14.sp,
            color = Color(0xFF888888),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(
                    text = "Username",
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

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Password",
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
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Error message
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                fontSize = 13.sp,
                color = Color(0xFFE53935),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login button
        Button(
            onClick = {
                when {
                    username.isEmpty() || password.isEmpty() -> {
                        errorMessage = "Please enter username and password!"
                    }
                    username == ADMIN_USERNAME && password == ADMIN_PASSWORD -> {
                        errorMessage = ""
                        navController.navigate("adminDashboard")
                    }
                    else -> {
                        errorMessage = "Invalid username or password!"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC8922A)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back button
        TextButton(
            onClick = { navController.popBackStack() }
        ) {
            Text(
                text = "← Back to Gallery",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
        }
    }
}