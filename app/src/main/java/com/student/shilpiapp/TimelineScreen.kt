package com.student.shilpiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class WipItem(
    val date: String,
    val caption: String,
    val percentage: Int,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(navController: NavController, sculptureId: String?) {
    val sculpture = DataHelper.getSculptures().find { it.id == sculptureId }

    val sculptureImage = sculpture?.imageResId ?: R.drawable.ganesha

    val wipItems = listOf(
        WipItem("January 5, 2025",  "Raw stone block selected and marked", 10,  sculptureImage),
        WipItem("January 18, 2025", "Basic shape carved out",               35,  sculptureImage),
        WipItem("February 2, 2025", "Fine detailing started",               60,  sculptureImage),
        WipItem("February 20, 2025","Intricate patterns carved",            80,  sculptureImage),
        WipItem("March 10, 2025",   "Final polish and finishing done",      100, sculptureImage)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "WIP: ${sculpture?.name ?: ""}",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(wipItems) { item ->
                TimelineItem(item)
            }
        }
    }
}

@Composable
fun TimelineItem(item: WipItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Timeline dot and line
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFFB5541B), RoundedCornerShape(50))
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Image
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.caption,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Text details
        Column {
            Text(
                text = item.date,
                fontSize = 11.sp,
                color = Color(0xFF888888)
            )
            Text(
                text = item.caption,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF5ECD7),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = "${item.percentage}% complete",
                fontSize = 12.sp,
                color = Color(0xFFC8922A),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}