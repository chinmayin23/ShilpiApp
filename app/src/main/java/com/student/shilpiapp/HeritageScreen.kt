package com.student.shilpiapp

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeritageScreen(navController: NavController, carvingStyle: String?) {

    data class HeritageData(
        val title: String,
        val period: String,
        val region: String,
        val imageResId: Int,
        val body: String,
        val fact1: String,
        val fact2: String,
        val fact3: String
    )

    val heritage = when (carvingStyle) {
        "Hoysala" -> HeritageData(
            title = "Hoysala Carving Tradition",
            period = "Period: 10th to 14th Century",
            region = "Region: Hassan, Mysuru, Karnataka",
            imageResId = R.drawable.elephant,
            body = "The Hoysala Empire produced some of the most intricate stone " +
                    "carvings ever created in India. Known for their star-shaped temple " +
                    "platforms and incredibly detailed sculptures, Hoysala craftsmen used " +
                    "chloritic schist — a soft stone that hardens over time — to create " +
                    "works of unparalleled beauty.\n\n" +
                    "The carvings feature rows of elephants, horses, scrolling foliage, " +
                    "epic scenes, mythological figures, and friezes of geese. Every inch " +
                    "of the temple walls was covered in sculpture, making Hoysala temples " +
                    "a UNESCO World Heritage Site.\n\n" +
                    "Today, master craftsmen in villages like Shivarapatna and Belur " +
                    "continue this tradition, carving idols and panels using techniques " +
                    "passed down through generations.",
            fact1 = "⚡ Hoysala temples have over 40,000 individual sculptures",
            fact2 = "⚡ Craftsmen used soft stone that hardens after carving",
            fact3 = "⚡ UNESCO World Heritage Site since 2023"
        )
        "Chalukya" -> HeritageData(
            title = "Chalukya Carving Tradition",
            period = "Period: 6th to 12th Century",
            region = "Region: Badami, Pattadakal, Karnataka",
            imageResId = R.drawable.nandi,
            body = "The Chalukya dynasty created magnificent rock-cut cave temples " +
                    "and structural temples that showcase extraordinary stone carving " +
                    "skills. Their style bridges the gap between North Indian Nagara " +
                    "and South Indian Dravidian architectural traditions.\n\n" +
                    "Famous for the Badami cave temples and Pattadakal monuments, " +
                    "Chalukya carving features deeply cut sculptures with expressive " +
                    "figures of gods, goddesses, and mythological scenes.\n\n" +
                    "The artisans of Belur and Halebidu trace their roots to this " +
                    "ancient tradition, keeping the craft alive through their workshops.",
            fact1 = "⚡ Pattadakal is a UNESCO World Heritage Site",
            fact2 = "⚡ Combines both Nagara and Dravidian styles",
            fact3 = "⚡ Rock-cut caves date back to 6th century AD"
        )
        "Dravidian" -> HeritageData(
            title = "Dravidian Carving Tradition",
            period = "Period: 7th Century to Present",
            region = "Region: Tamil Nadu, Karnataka, Andhra Pradesh",
            imageResId = R.drawable.shiva,
            body = "The Dravidian style is one of the oldest and most refined stone " +
                    "carving traditions in India. Characterized by towering gopurams " +
                    "covered in thousands of colorful sculptures, the Dravidian tradition " +
                    "represents the pinnacle of South Indian art.\n\n" +
                    "The famous Nataraja — dancing Shiva — is perhaps the most iconic " +
                    "sculpture from this tradition. Carved in stone and cast in bronze, " +
                    "it represents the cosmic dance of creation and destruction.\n\n" +
                    "Master craftsmen in Halebidu continue this tradition, creating " +
                    "Nataraja idols that are exported across the world.",
            fact1 = "⚡ Nataraja is recognized worldwide as Indian art",
            fact2 = "⚡ Gopuram towers can have up to 1000 sculptures",
            fact3 = "⚡ Tradition is over 1300 years old"
        )
        "Vijayanagara" -> HeritageData(
            title = "Vijayanagara Carving Tradition",
            period = "Period: 14th to 17th Century",
            region = "Region: Hampi, Bellary, Karnataka",
            imageResId = R.drawable.ganesha,
            body = "The Vijayanagara Empire, with its magnificent capital at Hampi, " +
                    "produced some of the most awe-inspiring stone carvings in Indian history. " +
                    "The empire's master craftsmen carved entire hillsides into temples, " +
                    "creating a landscape of unparalleled artistic beauty.\n\n" +
                    "Vijayanagara style is characterized by tall, slender pillars with " +
                    "rearing horses and yali (mythical lion-horse creatures), intricate " +
                    "ceiling panels, and massive monolithic sculptures. The famous " +
                    "Sasivekalu Ganesha and Kadalekalu Ganesha at Hampi are monolithic " +
                    "carvings standing over 4 meters tall.\n\n" +
                    "Today, craftsmen inspired by this tradition continue to create " +
                    "monumental sculptures that carry the spirit of Vijayanagara into " +
                    "the modern world.",
            fact1 = "⚡ Hampi is a UNESCO World Heritage Site since 1986",
            fact2 = "⚡ Vijayanagara had over 1000 temples in its capital alone",
            fact3 = "⚡ Monolithic Ganesha at Hampi stands 4.5 meters tall"
        )
        else -> HeritageData(
            title = "Mysore Carving Tradition",
            period = "Period: 14th Century to Present",
            region = "Region: Mysuru, Karnataka",
            imageResId = R.drawable.durga,
            body = "The Mysore school of art and sculpture developed under the " +
                    "patronage of the Wadiyar dynasty. Known for its refined elegance " +
                    "and attention to detail, Mysore style carvings are characterized " +
                    "by graceful figures, intricate jewelry details, and serene " +
                    "facial expressions.\n\n" +
                    "Mysore craftsmen are particularly renowned for their sandalwood " +
                    "carvings and ivory work, though stone carving remains the backbone " +
                    "of the tradition.\n\n" +
                    "The Mysore palace itself is adorned with hundreds of stone carvings " +
                    "created by master craftsmen whose descendants continue the tradition " +
                    "today in villages around Mysuru.",
            fact1 = "⚡ Mysore palace has over 500 stone carvings",
            fact2 = "⚡ Wadiyar dynasty patronized the arts for 600 years",
            fact3 = "⚡ Sandalwood carving is a GI tagged craft"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Heritage Story",
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
            // Hero image
            Image(
                painter = painterResource(id = heritage.imageResId),
                contentDescription = heritage.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            // Title section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A))
                    .padding(16.dp)
            ) {
                Text(
                    text = heritage.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5ECD7)
                )
                Text(
                    text = heritage.period,
                    fontSize = 13.sp,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(top = 6.dp)
                )
                Text(
                    text = heritage.region,
                    fontSize = 13.sp,
                    color = Color(0xFFB5541B),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Body section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "The Story",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = heritage.body,
                    fontSize = 14.sp,
                    color = Color(0xFFCCCCCC),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Did You Know?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                listOf(heritage.fact1, heritage.fact2, heritage.fact3).forEach { fact ->
                    Text(
                        text = fact,
                        fontSize = 13.sp,
                        color = Color(0xFFF5ECD7),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                            .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}