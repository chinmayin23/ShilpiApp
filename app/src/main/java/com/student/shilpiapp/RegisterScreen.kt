package com.student.shilpiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun RegisterScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .verticalScroll(rememberScrollState())
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
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC8922A)
        )

        Text(
            text = "Join Shilpa-Kala Showcase",
            fontSize = 14.sp,
            color = Color(0xFF888888),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Full Name field
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = {
                Text(
                    text = "Full Name",
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

        Spacer(modifier = Modifier.height(12.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email Address",
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

        Spacer(modifier = Modifier.height(12.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Password (min 6 characters)",
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

        Spacer(modifier = Modifier.height(12.dp))

        // Confirm Password field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = {
                Text(
                    text = "Confirm Password",
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
                color = if (errorMessage.startsWith("✅"))
                    Color(0xFF4CAF50) else Color(0xFFE53935),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register button
        Button(
            onClick = {
                when {
                    fullName.isEmpty() -> errorMessage = "Please enter your full name!"
                    email.isEmpty() -> errorMessage = "Please enter email!"
                    password.isEmpty() -> errorMessage = "Please enter password!"
                    password.length < 6 -> errorMessage = "Password must be at least 6 characters!"
                    password != confirmPassword -> errorMessage = "Passwords do not match!"
                    else -> {
                        isLoading = true
                        errorMessage = ""
                        AuthHelper.registerUser(
                            email = email,
                            password = password,
                            onSuccess = {
                                isLoading = false
                                navController.navigate("gallery") {
                                    popUpTo("register") { inclusive = true }
                                }
                            },
                            onFailure = { error ->
                                isLoading = false
                                errorMessage = error
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isLoading)
                    Color(0xFF444444) else Color(0xFFC8922A)
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = "Create Account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login link
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text(
                    text = "Login",
                    fontSize = 14.sp,
                    color = Color(0xFFC8922A),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}