package com.student.shilpiapp

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, sculptureId: String?) {
    val sculpture = DataHelper.getSculptures().find { it.id == sculptureId }
    val context = LocalContext.current
    var aiDescription by remember { mutableStateOf("Tap the button below to generate an AI description") }
    var isGenerating by remember { mutableStateOf(false) }

    if (sculpture == null) return

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = sculpture.name,
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
            // Sculpture image
            var scale by remember { mutableStateOf(1f) }
            val transformState = rememberTransformableState { zoomChange, _, _ ->
                scale = (scale * zoomChange).coerceIn(1f, 4f)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .transformable(state = transformState)
            ) {
                Image(
                    painter = painterResource(id = sculpture.imageResId),
                    contentDescription = sculpture.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale),
                    contentScale = ContentScale.Crop
                )
            }

            // Details section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1A1A1A))
                    .padding(16.dp)
            ) {
                Text(
                    text = sculpture.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5ECD7)
                )
                Text(
                    text = sculpture.price,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC8922A),
                    modifier = Modifier.padding(top = 6.dp)
                )
                Text(
                    text = "Product ID: ${sculpture.id}",
                    fontSize = 12.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // More details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "By ${sculpture.artistName}, ${sculpture.village}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB5541B)
                )
                Text(
                    text = "Material: ${sculpture.material}",
                    fontSize = 13.sp,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.padding(top = 6.dp)
                )
                Text(
                    text = "Style: ${sculpture.carvingStyle}",
                    fontSize = 13.sp,
                    color = Color(0xFFAAAAAA),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = sculpture.description,
                    fontSize = 14.sp,
                    color = Color(0xFFCCCCCC),
                    modifier = Modifier.padding(top = 12.dp),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // WhatsApp button
                Button(
                    onClick = {
                        val message = "Hi, I am interested in:\n" +
                                "Product ID: ${sculpture.id}\n" +
                                "Sculpture: ${sculpture.name}\n" +
                                "Artist: ${sculpture.artistName}, ${sculpture.village}\n" +
                                "Price: ${sculpture.price}\n\n" +
                                "Please share more details."
                        val url = "https://wa.me/919035695419?text=${Uri.encode(message)}"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF25D366)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Enquire via WhatsApp",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

// Timeline button
                Button(
                    onClick = {
                        navController.navigate("timeline/${sculpture.id}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB5541B)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "View Work in Progress",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

// Artist button
                Button(
                    onClick = {
                        navController.navigate("artist/${sculpture.artistName}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1565C0)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "View Artist Profile",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

// Heritage button
                Button(
                    onClick = {
                        navController.navigate("heritage/${sculpture.carvingStyle}")
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
                        text = "View Heritage Story",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

// AI button
                Button(
                    onClick = {
                        isGenerating = true
                        aiDescription = "Please wait..."
                        Handler(Looper.getMainLooper()).postDelayed({
                            aiDescription = when (sculpture.id) {
                                "SKS-2025-0001" -> "This magnificent Ganesha idol, masterfully carved from Black Granite, exemplifies the pinnacle of Hoysala craftsmanship. The intricate detailing of the trunk, crown, and ornaments reflects centuries of artistic tradition passed down through generations of Shivarapatna craftsmen."
                                "SKS-2025-0002" -> "This majestic Nandi Bull, hewn from a single block of Sandstone, embodies the serene power of the sacred vehicle of Lord Shiva. Crafted in the ancient Chalukya tradition of Belur, the sculpture showcases extraordinary attention to anatomical detail."
                                "SKS-2025-0003" -> "This breathtaking Nataraja in Black Granite captures the cosmic dance of Lord Shiva in the classical Dravidian tradition. Carved by a master craftsman from Halebidu, every detail demonstrates the highest level of sculptural achievement."
                                "SKS-2025-0004" -> "This serene Lakshmi statue in pristine White Marble radiates divine grace and prosperity in the finest Hoysala tradition. The craftsman has captured the goddess's benevolent expression and ornate jewelry with extraordinary precision."
                                "SKS-2025-0005" -> "This grand Elephant Panel in solid Granite represents the quintessential Hoysala decorative tradition. Each elephant is rendered with lifelike detail — from wrinkled skin texture to gentle eyes — telling stories of royal processions of ancient Karnataka."
                                "SKS-2025-0006" -> "This elegant Saraswati idol in Sandstone embodies the divine grace of the goddess of knowledge in the classical Chalukya style. The veena, flowing garments, and serene expression are masterfully captured in warm sandstone."
                                "SKS-2025-0007" -> "This powerful Durga idol in Red Sandstone captures the fierce yet compassionate nature of the goddess in the Mysore tradition. The dynamic composition and intricate detailing of weapons and garments amplify the sculpture's divine energy."
                                "SKS-2025-0008" -> "This stately Vishnu idol in Black Granite exemplifies the classical Hoysala tradition. The four-armed deity is rendered with extraordinary attention to symbolic attributes — conch, discus, mace, and lotus — each carved with meticulous precision."
                                "SKS-2025-0009" -> "This majestic Garuda idol, masterfully carved from solid Granite, captures the divine eagle vehicle of Lord Vishnu in the classical Hoysala tradition. The powerful wings, fierce expression, and ornate crown are rendered with extraordinary precision by the master craftsmen of Shivarapatna. A symbol of strength, loyalty, and divine protection, this Garuda idol is a centerpiece for any temple or collector."
                                "SKS-2025-0010" -> "This powerful Hanuman idol carved from Red Sandstone embodies the strength and devotion of the mighty monkey god in the classical Mysore tradition. The dynamic pose, flowing tail, and devotional expression are masterfully captured by the skilled hands of Ramu Shilpi from Shivarapatna. A symbol of courage and unwavering faith, this idol brings divine protection and blessings to any home or temple."
                                else -> "This exquisite sculpture represents the finest traditions of Karnataka's stone carving heritage, reflecting deep artistic knowledge and spiritual devotion."
                            }
                            isGenerating = false
                        }, 2000)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF534AB7)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (isGenerating) "Generating..." else "✨ Generate AI Description",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // AI description text
                Text(
                    text = aiDescription,
                    fontSize = 14.sp,
                    color = Color(0xFFF5ECD7),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A1A1A))
                        .padding(12.dp),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}