package com.student.shilpiapp

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseHelper {

    private val db = FirebaseFirestore.getInstance()

    fun seedDatabase() {
        val sculptures = DataHelper.getSculptures()
        sculptures.forEach { sculpture ->
            val data = hashMapOf(
                "id" to sculpture.id,
                "name" to sculpture.name,
                "material" to sculpture.material,
                "price" to sculpture.price,
                "artistName" to sculpture.artistName,
                "village" to sculpture.village,
                "description" to sculpture.description,
                "carvingStyle" to sculpture.carvingStyle
            )
            db.collection("sculptures")
                .document(sculpture.id)
                .set(data)
        }
    }

    fun getSculpturesFromFirebase(onResult: (List<Sculpture>) -> Unit) {
        db.collection("sculptures")
            .get()
            .addOnSuccessListener { result ->
                val list = result.documents.mapNotNull { doc ->
                    try {
                        Sculpture(
                            id = doc.getString("id") ?: "",
                            name = doc.getString("name") ?: "",
                            material = doc.getString("material") ?: "",
                            price = doc.getString("price") ?: "",
                            artistName = doc.getString("artistName") ?: "",
                            village = doc.getString("village") ?: "",
                            imageResId = getImageResId(doc.getString("id") ?: ""),
                            description = doc.getString("description") ?: "",
                            carvingStyle = doc.getString("carvingStyle") ?: ""
                        )
                    } catch (e: Exception) {
                        null
                    }
                }
                onResult(list)
            }
            .addOnFailureListener {
                onResult(DataHelper.getSculptures())
            }
    }

    fun addSculpture(sculpture: Sculpture, onResult: (Boolean) -> Unit) {
        val data = hashMapOf(
            "id" to sculpture.id,
            "name" to sculpture.name,
            "material" to sculpture.material,
            "price" to sculpture.price,
            "artistName" to sculpture.artistName,
            "village" to sculpture.village,
            "description" to sculpture.description,
            "carvingStyle" to sculpture.carvingStyle
        )
        db.collection("sculptures")
            .document(sculpture.id)
            .set(data)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    fun deleteSculpture(sculptureId: String, onResult: (Boolean) -> Unit) {
        db.collection("sculptures")
            .document(sculptureId)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    private fun getImageResId(id: String): Int {
        return when (id) {
            "SKS-2025-0001" -> R.drawable.ganesha
            "SKS-2025-0002" -> R.drawable.nandi
            "SKS-2025-0003" -> R.drawable.shiva
            "SKS-2025-0004" -> R.drawable.lakshmi
            "SKS-2025-0005" -> R.drawable.elephant
            "SKS-2025-0006" -> R.drawable.saraswati
            "SKS-2025-0007" -> R.drawable.durga
            "SKS-2025-0008" -> R.drawable.vishnu
            "SKS-2025-0009" -> R.drawable.garuda
            "SKS-2025-0010" -> R.drawable.hanuman
            else -> R.drawable.placeholder
        }
    }
}