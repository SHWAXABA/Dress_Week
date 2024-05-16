package com.example.dressweek

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream

class AddItem : AppCompatActivity() {

    //Our item values for out DropDown Menus
    val item = arrayOf("Casual", "Formal")
    val item2 = arrayOf("Short Sleeve", "Longsleeve")
    val item3 = arrayOf("Shirt", "Pant","Foot Wear","Over Wear","Accessories")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item)
        //Changes the window color to our theme
        window.statusBarColor = ContextCompat.getColor(this, R.color.greyBlue)
        //Seems like useless junk. Might delete later
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Dropdown menu values
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autocompletetext)
        val autoCompleteTextView2 = findViewById<AutoCompleteTextView>(R.id.autocompletetext2)
        val autoCompleteTextView3 = findViewById<AutoCompleteTextView>(R.id.autocompletetext3)

        //The adapters that will populate the values of the Dropdown
        val adapterItem = ArrayAdapter<String>(this,R.layout.dropdownlist,item3)
        autoCompleteTextView.setAdapter(adapterItem)

        val adapterItem2 = ArrayAdapter<String>(this,R.layout.dropdownlist,item2)
        autoCompleteTextView2.setAdapter(adapterItem2)

        val adapterItem3 = ArrayAdapter<String>(this,R.layout.dropdownlist,item)
        autoCompleteTextView3.setAdapter(adapterItem3)

        //Populates the image from the previous page to the AddItem view
        val imageView = findViewById<ImageView>(R.id.clothingImage)

        // Retrieve image data from intent extras
        val imageBitmap = intent.getParcelableExtra<Bitmap>("imageBitmap")
        val imageUri = intent.getParcelableExtra<Uri>("imageUri")

        // Set image bitmap or URI to ImageView
        if (imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap)
        } else if (imageUri != null) {
            imageView.setImageURI(imageUri)
        }

        val saveItem = findViewById<Button>(R.id.saveButton)
        saveItem.setOnClickListener(){
// Retrieve selected clothing type and sleeve type and style from the dropdowns
            val clothingType = autoCompleteTextView.text.toString()
            val sleeveType = autoCompleteTextView2.text.toString()
            val clothingStyle = autoCompleteTextView3.text.toString()
            // This will construct the folder path
            val folderPath = "${getExternalFilesDir(null)}/DressWeek/$clothingType"

            // Create the folder if it doesn't exist
            val folder = File(folderPath)
            if (!folder.exists()) {
                folder.mkdirs()
            }

            // Count the number of existing files in the folder
            val existingFiles = folder.listFiles()?.filter { it.isFile } ?: emptyList()
            val nextNumber = existingFiles.size + 1

            // Save the image with the incremented number in the file name
            val imageFile = File(folder, "$clothingType $nextNumber.jpg")
            // Save the image to imageFile
            imageBitmap?.let { it1 -> saveImageToFile(imageFile, it1) }
            imageUri?.let { it1 -> saveGalImageToFile(imageFile, it1) }
            // Save metadata to a JSON file
            saveMetadata(folder, nextNumber, clothingType, sleeveType, clothingStyle)
            this.onBackPressed()
        }

    }
    //This allows the app to save the image into our folder
    //We used compression to save space so that it won't destroy the users memory
    fun saveImageToFile(file: File, bitmap: Bitmap) {
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
    }
    fun saveGalImageToFile(file: File, uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
    }
    //This adds metaData to each image using a JSON so that we can then load each image by our parameters
    private fun saveMetadata(folder: File, imageNumber: Int, clothingType: String, sleeveType: String, clothingStyle: String) {
        val metadataFile = File(folder, "metadata.json")
        val metadata = mapOf(
            "imageNumber" to imageNumber,
            "clothingType" to clothingType,
            "sleeveType" to sleeveType,
            "clothingStyle" to clothingStyle
        )
        metadataFile.appendText(metadata.toString())
    }
}