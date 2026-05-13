package com.student.shilpiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistScreen(navController: NavController, artistName: String?) {
    val allSculptures = DataHelper.getSculptures()
    val artistSculptures = allSculptures.filter { it.artistName == artistName }
    val artist = artistSculptures.firstOrNull()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Artist Profile",
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
        ) {
            // Artist header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Artist image circle
                Image(
                    painter = painterResource(
                        id = artistSculptures.firstOrNull()?.imageResId ?: R.drawable.ganesha
                    ),
                    contentDescription = artistName,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = artistName ?: "Unknown Artist",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5ECD7),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "📍 ${artist?.village ?: ""}, Karnataka",
                    fontSize = 14.sp,
                    color = Color(0xFFB5541B),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "25+ Years of Experience",
                    fontSize = 13.sp,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "Style: ${artist?.carvingStyle ?: ""}",
                    fontSize = 12.sp,
                    color = Color(0xFF888888),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Bio section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "About the Artist",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "A master craftsman from the heart of Karnataka, " +
                            "${artistName} has dedicated their life to preserving " +
                            "the ancient art of stone carving. Trained under the " +
                            "Hoysala tradition, their work reflects centuries of " +
                            "artistic heritage passed down through generations.",
                    fontSize = 14.sp,
                    color = Color(0xFFCCCCCC),
                    lineHeight = 22.sp
                )
            }

            // Works section
            Text(
                text = "Works by this Artist",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC8922A),
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(6.dp),
                modifier = Modifier.height(400.dp),
                userScrollEnabled = false
            ) {
                items(artistSculptures) { sculpture ->
                    SculptureCard(sculpture) {
                        navController.navigate("detail/${sculpture.id}")
                    }
                }
            }
        }
    }
}