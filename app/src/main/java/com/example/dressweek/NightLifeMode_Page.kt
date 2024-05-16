package com.example.dressweek
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.dressweek.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File
import java.util.Calendar
import kotlin.math.max
import kotlin.random.Random

class NightLifeMode_Page : Fragment() {

    private lateinit var shirtImageView: ImageView
    private lateinit var pantsImageView: ImageView
    private lateinit var footwearImageView: ImageView
    private lateinit var outerwearImageView: ImageView
    private lateinit var accessoriesImageView: ImageView
    private lateinit var reloadButton: Button
    private lateinit var saveOutfitButton: Button
    private var maxTemp: Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_night_life_mode__page, container, false)
        // Find views
        shirtImageView = view.findViewById(R.id.Shirt)
        pantsImageView = view.findViewById(R.id.Pant)
        footwearImageView = view.findViewById(R.id.Footwear)
        outerwearImageView = view.findViewById(R.id.Outerwear)
        accessoriesImageView = view.findViewById(R.id.Accessories)

        reloadButton = view.findViewById(R.id.reloadButton)
        saveOutfitButton = view.findViewById(R.id.SaveOutfitButton)
        // Load a random images to the current page
        loadRandomShirtImage()
        loadRandomPantImage()
        loadRandomFootWearImage()
        loadRandomOuterwearImage()
        loadRandomAccessoriesImage()

        // The reloads our images although it doesn't work on real time for some reason
        reloadButton.setOnClickListener { loadRandomShirtImage() }
        saveOutfitButton.setOnClickListener { onSaveOutfitClicked() }
        return view
    }

    private fun loadRandomShirtImage() {
        // Construct folder path for shirts
        val folderPath = "${requireActivity().getExternalFilesDir(null)}/DressWeek/Shirt"
        // Create a list to store shirt images
        val shirtImages = mutableListOf<File>()
        // Load shirt images
        val shirtFolder = File(folderPath)
        if (shirtFolder.exists() && shirtFolder.isDirectory) {
            val allShirtImages = shirtFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
            shirtImages.addAll(allShirtImages)
            // Randomly select a shirt image
            if (shirtImages.isNotEmpty()) {
                val randomShirtImage = shirtImages[Random.nextInt(shirtImages.size)]
                // Load the selected shirt image into the ImageView
                shirtImageView.setImageURI(randomShirtImage.toUri())
            }
        }
    }
    private fun loadRandomPantImage() {
        // Construct folder path for pants
        val folderPath = "${requireActivity().getExternalFilesDir(null)}/DressWeek/Pant"
        // Create a list to store pant images
        val pantImages = mutableListOf<File>()
        // Load pant images
        val pantsFolder = File(folderPath)
        if (pantsFolder.exists() && pantsFolder.isDirectory) {
            val allPantsImages = pantsFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
            pantImages.addAll(allPantsImages)
            // Randomly select a pant image
            if (pantImages.isNotEmpty()) {
                val randomPantImage = pantImages[Random.nextInt(pantImages.size)]
                // Load the selected pant image into the ImageView
                pantsImageView.setImageURI(randomPantImage.toUri())
            }
        }
    }
    private fun loadRandomFootWearImage() {
        // Construct folder path for pants
        val folderPath = "${requireActivity().getExternalFilesDir(null)}/DressWeek/Foot Wear"
        // Create a list to store pant images
        val footwearImages = mutableListOf<File>()
        // Load pant images
        val footwearFolder = File(folderPath)
        if (footwearFolder.exists() && footwearFolder.isDirectory) {
            val allFootwearImages = footwearFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
            footwearImages.addAll(allFootwearImages)
            // Randomly select a pant image
            if (footwearImages.isNotEmpty()) {
                val randomFootwearImage = footwearImages[Random.nextInt(footwearImages.size)]
                // Load the selected pant image into the ImageView
                footwearImageView.setImageURI(randomFootwearImage.toUri())
            }
        }
    }
    private fun loadRandomOuterwearImage() {
        // Construct folder path for pants
        val folderPath = "${requireActivity().getExternalFilesDir(null)}/DressWeek/Over Wear"
        // Create a list to store pant images
        val outerwearImages = mutableListOf<File>()
        // Load pant images
        val outerwearFolder = File(folderPath)
        if (outerwearFolder.exists() && outerwearFolder.isDirectory) {
            val allOuterwearImages = outerwearFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
            outerwearImages.addAll(allOuterwearImages)
            // Randomly select a pant image
            if (outerwearImages.isNotEmpty()) {
                val randomOuterwearImage = outerwearImages[Random.nextInt(outerwearImages.size)]
                // Load the selected pant image into the ImageView
                outerwearImageView.setImageURI(randomOuterwearImage.toUri())
            }
        }
    }
    private fun loadRandomAccessoriesImage() {
        // Construct folder path for pants
        val folderPath = "${requireActivity().getExternalFilesDir(null)}/DressWeek/Accessories"
        // Create a list to store pant images
        val accessoriesImages = mutableListOf<File>()
        // Load pant images
        val accessoriesFolder = File(folderPath)
        if (accessoriesFolder.exists() && accessoriesFolder.isDirectory) {
            val allAccessoriesImages = accessoriesFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
            accessoriesImages.addAll(allAccessoriesImages)
            // Randomly select a pant image
            if (accessoriesImages.isNotEmpty()) {
                val randomAccessoriesImage = accessoriesImages[Random.nextInt(accessoriesImages.size)]
                // Load the selected pant image into the ImageView
                accessoriesImageView.setImageURI(randomAccessoriesImage.toUri())
            }
        }
    }

    private fun onSaveOutfitClicked() {
        // Disable save outfit button
        saveOutfitButton.isEnabled = false
        // Start a coroutine to wait until 5 PM
        lifecycleScope.launch {
            delayUntil5PM()
            // Enable save outfit button after 5 PM
            saveOutfitButton.isEnabled = true
        }
    }
//This helps to lock our save feature until 5pm
    private suspend fun delayUntil5PM() {
        // Get current time in milliseconds
        val currentTimeMillis = System.currentTimeMillis()
        // Calculate milliseconds until 5 PM
        val millisUntil5PM = calculateMillisUntil5PM(currentTimeMillis)
        // Delay until 5 PM
        delay(millisUntil5PM)
    }
//This locks the images once the user selects the check box
    private fun calculateMillisUntil5PM(currentTimeMillis: Long): Long {
        // Get current time as Calendar instance
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTimeMillis
        // Set time to 5 PM
        calendar.set(Calendar.HOUR_OF_DAY, 17)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        // Calculate difference in milliseconds between current time and 5 PM
        val millisUntil5PM = calendar.timeInMillis - currentTimeMillis
        // Return millisUntil5PM, ensuring it's positive
        return max(0, millisUntil5PM)
    }
    //This is the weather feature but it will be implemented properly at a later date
    fun onWeatherDataReceived(maxTemp: Double) {
        this.maxTemp = maxTemp
        // Load a random shirt image
        loadRandomShirtImage()
    }

    private fun determineClothingType(maxTemp: Double): String {
        // Determine clothing type based on max temperature
        return when {
            maxTemp > 25 -> "Short Sleeve" // If it's too hot (> 25°C), select shirts with short sleeves
            maxTemp < 15 -> "Longsleeve" // If it's too cold (< 15°C), select shirts with long sleeves
            else -> "" // For moderate temperature, no specific clothing selection needed
        }
    }

    private fun filterShirtImagesByType(allShirtImages: List<File>, clothingType: String): List<File> {
        // Filter shirt images based on clothing type
        return allShirtImages.filter { shirtImage ->
            // Check if the shirt image metadata matches the clothing type
            val metadataJson = getMetadataJson(shirtImage)
            val metadataClothingType = metadataJson?.getString("clothingType")
            metadataClothingType == clothingType
        }
    }

    private fun getMetadataJson(shirtImage: File): JSONObject? {
        // Load metadata JSON associated with the shirt image
        val metadataFilePath = "${shirtImage.parent}/metadata/${shirtImage.nameWithoutExtension}.json"
        val metadataFile = File(metadataFilePath)
        return if (metadataFile.exists()) {
            try {
                val metadataJsonString = metadataFile.readText()
                JSONObject(metadataJsonString)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

}
