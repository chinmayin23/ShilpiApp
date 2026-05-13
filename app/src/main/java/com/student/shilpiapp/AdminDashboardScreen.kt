package com.student.shilpiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(navController: NavController) {
    var sculptures by remember { mutableStateOf(DataHelper.getSculptures()) }
    var isLoading by remember { mutableStateOf(true) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var sculptureToDelete by remember { mutableStateOf<Sculpture?>(null) }
    var statusMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseHelper.getSculpturesFromFirebase { fetchedSculptures ->
            if (fetchedSculptures.isNotEmpty()) {
                sculptures = fetchedSculptures
            }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Admin Dashboard",
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
        ) {
            // Status message
            if (statusMessage.isNotEmpty()) {
                Text(
                    text = statusMessage,
                    fontSize = 13.sp,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A1A1A))
                        .padding(12.dp)
                )
            }

            // Stats bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatCard(
                    title = "Total",
                    value = "${sculptures.size}",
                    color = Color(0xFFC8922A)
                )
                StatCard(
                    title = "Artists",
                    value = "${sculptures.map { it.artistName }.distinct().size}",
                    color = Color(0xFFB5541B)
                )
                StatCard(
                    title = "Styles",
                    value = "${sculptures.map { it.carvingStyle }.distinct().size}",
                    color = Color(0xFF534AB7)
                )
            }

            // Add button
            Button(
                onClick = { navController.navigate("addSculpture") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC8922A)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add New Sculpture",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Loading
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFC8922A))
                }
            } else {
                // Sculptures list
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sculptures) { sculpture ->
                        AdminSculptureItem(
                            sculpture = sculpture,
                            onDelete = {
                                sculptureToDelete = sculpture
                                showDeleteDialog = true
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

    // Delete confirmation dialog
    if (showDeleteDialog && sculptureToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Delete Sculpture?",
                    color = Color(0xFFF5ECD7)
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete ${sculptureToDelete?.name}? This cannot be undone!",
                    color = Color(0xFFCCCCCC)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        sculptureToDelete?.let { sculpture ->
                            FirebaseHelper.deleteSculpture(sculpture.id) { success ->
                                if (success) {
                                    sculptures = sculptures.filter { it.id != sculpture.id }
                                    statusMessage = "${sculpture.name} deleted successfully!"
                                } else {
                                    statusMessage = "Failed to delete. Try again!"
                                }
                            }
                        }
                        showDeleteDialog = false
                        sculptureToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE53935)
                    )
                ) {
                    Text(
                        text = "Delete",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text(
                        text = "Cancel",
                        color = Color(0xFF888888)
                    )
                }
            },
            containerColor = Color(0xFF1A1A1A)
        )
    }
}

@Composable
fun StatCard(title: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color(0xFF888888)
        )
    }
}

@Composable
fun AdminSculptureItem(sculpture: Sculpture, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = sculpture.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5ECD7)
                )
                Text(
                    text = sculpture.id,
                    fontSize = 11.sp,
                    color = Color(0xFF666666)
                )
                Text(
                    text = "${sculpture.material} • ${sculpture.carvingStyle}",
                    fontSize = 12.sp,
                    color = Color(0xFFB5541B)
                )
                Text(
                    text = sculpture.price,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC8922A)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFE53935)
                )
            }
        }
    }
}