package com.example.dressweek

import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Bitmap
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CaptureMode
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class Shirt_Page : AppCompatActivity() {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }
    private var clicked = false
    private lateinit var imageCapture: ImageCapture

    private var isReadPermissionGranted = false
    private var isCameraPermissionGranted = false
    private var isStoragePermissionGranted = false
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var recyclerView: RecyclerView
    private lateinit var shirtAdapter: ShirtAdapter
    private val imageItems = mutableListOf<Shirt>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shirt_page)
        window.statusBarColor = ContextCompat.getColor(this, R.color.greyBlue)
        var addbut = findViewById<FloatingActionButton>(R.id.add_btn)
        var cambut = findViewById<FloatingActionButton>(R.id.camera_btn)
        var galbut = findViewById<FloatingActionButton>(R.id.gallery_btn)

        var cameraFacing = CameraSelector.LENS_FACING_BACK

        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permissions ->
            isCameraPermissionGranted = permissions[Manifest.permission.CAMERA]?: isCameraPermissionGranted
            isStoragePermissionGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE]?: isStoragePermissionGranted
            isReadPermissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE]?: isReadPermissionGranted
        }
        requestPermission()
        addbut.setOnClickListener(){
            onAddButtonClicked()
        }
        cambut.setOnClickListener(){
            takePicture()

        }
        galbut.setOnClickListener(){
            openGallery()
        }

        //For RecyclerView
        // Initialize RecyclerView and Adapter
        startRecyclerView()
        updateRecyclerView()

    }
    private fun updateRecyclerView() {
        val folderPath = "${getExternalFilesDir(null)}/DressWeek/Shirt"
        val shirtFolder = File(folderPath)
        if (shirtFolder.exists() && shirtFolder.isDirectory) {
            val shirtImages = shirtFolder.listFiles { file ->
                file.extension.equals("jpg", ignoreCase = true) || file.extension.equals("png", ignoreCase = true)
            }?.toList() ?: emptyList()
// Sort the shirtImages list alphabetically by file name

            imageItems.clear()
            for (shirtImage in shirtImages) {
                val imageName = shirtImage.nameWithoutExtension
                imageItems.add(Shirt(shirtImage, imageName))
            }
            shirtAdapter.notifyDataSetChanged()
        }
    }

    private fun startRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        shirtAdapter = ShirtAdapter(imageItems)
        recyclerView.adapter = shirtAdapter
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            //add image to additem menu
            if (imageBitmap != null) {
                // Start AddItem activity and pass the image bitmap
                val intent = Intent(this, AddItem::class.java)
                intent.putExtra("imageBitmap", imageBitmap)
                startActivity(intent)
                updateRecyclerView()
            }
        } else {
            Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show()
        }
    }


    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureLauncher.launch(intent)
    }
    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        openGalleryLauncher.launch(intent)
    }
    private val openGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                // Start AddItem activity and pass the image URI
                val intent = Intent(this, AddItem::class.java)
                intent.putExtra("imageUri", imageUri)

                startActivity(intent)
                updateRecyclerView()
            }
        } else {
            Toast.makeText(this, "Failed to pick image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setClickable(clicked: Boolean) {
        var cambut = findViewById<FloatingActionButton>(R.id.camera_btn)
        var galbut = findViewById<FloatingActionButton>(R.id.gallery_btn)
        if(!clicked){
            cambut.isClickable = true
            galbut.isClickable = true
        }else{
            cambut.isClickable = false
            galbut.isClickable = false
        }
    }

    private fun setAnimation(clicked:Boolean) {
        var addbut = findViewById<FloatingActionButton>(R.id.add_btn)
        var cambut = findViewById<FloatingActionButton>(R.id.camera_btn)
        var galbut = findViewById<FloatingActionButton>(R.id.gallery_btn)
        if(!clicked){
            addbut.startAnimation(rotateOpen)
            cambut.startAnimation(fromBottom)
            galbut.startAnimation(fromBottom)
        }else{
            addbut.startAnimation(rotateClose)
            cambut.startAnimation(toBottom)
            galbut.startAnimation(toBottom)
        }
    }

    private fun setVisibility(clicked:Boolean) {
        var cambut = findViewById<FloatingActionButton>(R.id.camera_btn)
        var galbut = findViewById<FloatingActionButton>(R.id.gallery_btn)
        if(!clicked){
            cambut.visibility = View.VISIBLE
            galbut.visibility = View.VISIBLE
        }else{
            cambut.visibility = View.INVISIBLE
            galbut.visibility = View.INVISIBLE
        }
    }
    //Grants permissions
    private fun requestPermission(){
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            this,Manifest.permission.CAMERA
        )== PackageManager.PERMISSION_GRANTED

        isStoragePermissionGranted = ContextCompat.checkSelfPermission(
            this,Manifest.permission.WRITE_EXTERNAL_STORAGE
        )== PackageManager.PERMISSION_GRANTED

        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            this,Manifest.permission.READ_EXTERNAL_STORAGE
        )== PackageManager.PERMISSION_GRANTED

        val permissionRequest:MutableList<String> = ArrayList()

        if(!isCameraPermissionGranted){
            permissionRequest.add(Manifest.permission.CAMERA)
        }
        if(!isReadPermissionGranted){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(!isStoragePermissionGranted){
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(permissionRequest.isNotEmpty()){
            requestPermissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }

}



