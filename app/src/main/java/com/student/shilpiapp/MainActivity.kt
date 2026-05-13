package com.student.shilpiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseHelper.seedDatabase()
        setContent {
            ShilpiAppTheme {
                val navController = rememberNavController()
                val startDestination = if (AuthHelper.isLoggedIn()) "gallery" else "login"
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("register") {
                        RegisterScreen(navController)
                    }
                    composable("gallery") {
                        GalleryScreen(navController)
                    }
                    composable("detail/{sculptureId}") { backStackEntry ->
                        val sculptureId = backStackEntry.arguments?.getString("sculptureId")
                        DetailScreen(navController, sculptureId)
                    }
                    composable("timeline/{sculptureId}") { backStackEntry ->
                        val sculptureId = backStackEntry.arguments?.getString("sculptureId")
                        TimelineScreen(navController, sculptureId)
                    }
                    composable("artist/{artistName}") { backStackEntry ->
                        val artistName = backStackEntry.arguments?.getString("artistName")
                        ArtistScreen(navController, artistName)
                    }
                    composable("heritage/{carvingStyle}") { backStackEntry ->
                        val carvingStyle = backStackEntry.arguments?.getString("carvingStyle")
                        HeritageScreen(navController, carvingStyle)
                    }
                    composable("adminLogin") {
                        AdminLoginScreen(navController)
                    }
                    composable("adminDashboard") {
                        AdminDashboardScreen(navController)
                    }
                    composable("addSculpture") {
                        AddSculptureScreen(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ShilpiAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF121212),
            surface = Color(0xFF1A1A1A),
            primary = Color(0xFFC8922A)
        ),
        content = content
    )
}

@Composable
fun GalleryScreen(navController: NavController) {
    var sculptures by remember { mutableStateOf(DataHelper.getSculptures()) }
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseHelper.getSculpturesFromFirebase { fetchedSculptures ->
            if (fetchedSculptures.isNotEmpty()) {
                sculptures = fetchedSculptures
            }
            isLoading = false
        }
    }

    val filteredSculptures = sculptures.filter { sculpture ->
        sculpture.name.contains(searchQuery, ignoreCase = true) ||
                sculpture.material.contains(searchQuery, ignoreCase = true) ||
                sculpture.carvingStyle.contains(searchQuery, ignoreCase = true) ||
                sculpture.artistName.contains(searchQuery, ignoreCase = true) ||
                sculpture.village.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // Top bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A1A1A))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "🏛 Shilpa-Kala",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC8922A)
                    )
                    Text(
                        text = "Digital Gallery of Master Craftsmen",
                        fontSize = 12.sp,
                        color = Color(0xFF888888)
                    )
                }
                // Logout and Admin buttons
                Row {
                    if (AuthHelper.isLoggedIn()) {
                        TextButton(
                            onClick = {
                                AuthHelper.logoutUser()
                                navController.navigate("login") {
                                    popUpTo("gallery") { inclusive = true }
                                }
                            }
                        ) {
                            Text(
                                text = "Logout",
                                fontSize = 12.sp,
                                color = Color(0xFFE53935)
                            )
                        }
                    }
                    TextButton(
                        onClick = { navController.navigate("adminLogin") }
                    ) {
                        Text(
                            text = "Admin",
                            fontSize = 12.sp,
                            color = Color(0xFF666666)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = "Search by name, style, artist...",
                        color = Color(0xFF666666),
                        fontSize = 14.sp
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

            if (searchQuery.isNotEmpty()) {
                Text(
                    text = "${filteredSculptures.size} result(s) found",
                    fontSize = 11.sp,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // Loading indicator
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFFC8922A)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Loading from Firebase...",
                        fontSize = 14.sp,
                        color = Color(0xFF888888)
                    )
                }
            }
        } else if (filteredSculptures.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No sculptures found!",
                    fontSize = 16.sp,
                    color = Color(0xFF666666)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredSculptures) { sculpture ->
                    SculptureCard(sculpture) {
                        navController.navigate("detail/${sculpture.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun SculptureCard(sculpture: Sculpture, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = sculpture.imageResId),
                contentDescription = sculpture.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = sculpture.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5ECD7)
                )
                Text(
                    text = sculpture.material,
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
        }
    }
}