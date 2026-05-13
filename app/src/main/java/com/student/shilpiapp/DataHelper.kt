package com.student.shilpiapp

object DataHelper {
    fun getSculptures(): List<Sculpture> {
        return listOf(
            Sculpture(
                id = "SKS-2025-0001",
                name = "Ganesha Idol",
                material = "Black Granite",
                price = "₹45,000",
                artistName = "Ramu Shilpi",
                village = "Shivarapatna",
                imageResId = R.drawable.ganesha,
                description = "Hand-carved Ganesha in traditional Hoysala style. This magnificent idol took 4 months to complete.",
                carvingStyle = "Hoysala"
            ),
            Sculpture(
                id = "SKS-2025-0002",
                name = "Nandi Bull",
                material = "Sandstone",
                price = "₹38,000",
                artistName = "Krishna Shilpi",
                village = "Belur",
                imageResId = R.drawable.nandi,
                description = "Majestic Nandi carved from a single sandstone block in the Chalukya tradition.",
                carvingStyle = "Chalukya"
            ),
            Sculpture(
                id = "SKS-2025-0003",
                name = "Dancing Shiva",
                material = "Black Granite",
                price = "₹72,000",
                artistName = "Venkat Shilpi",
                village = "Halebidu",
                imageResId = R.drawable.shiva,
                description = "Nataraja in classical Dravidian style, capturing the cosmic dance in stone.",
                carvingStyle = "Dravidian"
            ),
            Sculpture(
                id = "SKS-2025-0004",
                name = "Lakshmi Statue",
                material = "White Marble",
                price = "₹55,000",
                artistName = "Ramu Shilpi",
                village = "Shivarapatna",
                imageResId = R.drawable.lakshmi,
                description = "Goddess Lakshmi in serene seated pose, finished with natural polish.",
                carvingStyle = "Hoysala"
            ),
            Sculpture(
                id = "SKS-2025-0005",
                name = "Elephant Panel",
                material = "Granite",
                price = "₹90,000",
                artistName = "Suresh Shilpi",
                village = "Mysuru",
                imageResId = R.drawable.elephant,
                description = "Decorative elephant frieze in Hoysala tradition, perfect for temple or home entrance.",
                carvingStyle = "Hoysala"
            ),
            Sculpture(
                id = "SKS-2025-0006",
                name = "Saraswati Idol",
                material = "Sandstone",
                price = "₹48,000",
                artistName = "Krishna Shilpi",
                village = "Belur",
                imageResId = R.drawable.saraswati,
                description = "Goddess of knowledge in classical Belur style with veena.",
                carvingStyle = "Chalukya"
            ),
            Sculpture(
                id = "SKS-2025-0007",
                name = "Durga Idol",
                material = "Red Sandstone",
                price = "₹85,000",
                artistName = "Suresh Shilpi",
                village = "Mysuru",
                imageResId = R.drawable.durga,
                description = "Goddess Durga in powerful stance, a masterpiece of dynamic stone carving.",
                carvingStyle = "Mysore"
            ),
            Sculpture(
                id = "SKS-2025-0008",
                name = "Vishnu Idol",
                material = "Black Granite",
                price = "₹65,000",
                artistName = "Venkat Shilpi",
                village = "Halebidu",
                imageResId = R.drawable.vishnu,
                description = "Standing Vishnu with four arms in classic Halebidu temple style.",
                carvingStyle = "Hoysala"
            ),

            Sculpture(
                id = "SKS-2025-0009",
                name = "Garuda Idol",
                material = "Granite",
                price = "₹62,000",
                artistName = "Ramu Shilpi",
                village = "Shivarapatna",
                imageResId = R.drawable.garuda,
                description = "Divine eagle Garuda in majestic pose in Hoysala tradition.",
                carvingStyle = "Hoysala"
            ),
            Sculpture(
                id = "SKS-2025-0010",
                name = "Hanuman Idol",
                material = "Red Sandstone",
                price = "₹52,000",
                artistName = "Ramu Shilpi",
                village = "Shivarapatna",
                imageResId = R.drawable.hanuman,
                description = "Powerful Hanuman in devotional pose carved in Mysore tradition.",
                carvingStyle = "Mysore"
            )

        )
    }
}