package com.example.dressweek

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Clothing_Page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragbut = inflater.inflate(R.layout.fragment_clothing__page, container, false)
        //All our button links
        val shirtbut = fragbut.findViewById<Button>(R.id.shirtbutton)
        val pantsbut = fragbut.findViewById<Button>(R.id.pantbutton)
        val outwearbut = fragbut.findViewById<Button>(R.id.outerwearbutton)
        val footwearbut = fragbut.findViewById<Button>(R.id.footwearbutton)
        val accessoriesbut = fragbut.findViewById<Button>(R.id.accessoriesbutton)
        val addfab = fragbut.findViewById<FloatingActionButton>(R.id.addButton)

        //Button listeners
        shirtbut.setOnClickListener {
            // Create an intent to start the Shirt_Page activity
            val intent = Intent(requireContext(), Shirt_Page::class.java)
            startActivity(intent)
        }
        pantsbut.setOnClickListener {
            // Create an intent to start the Pants_Page activity
            val intent = Intent(requireContext(), Pants_Page::class.java)
            startActivity(intent)
        }
        outwearbut.setOnClickListener {
            // Create an intent to start the OuterWear_Page activity
            val intent = Intent(requireContext(), OuterWear_Page::class.java)
            startActivity(intent)
        }
        footwearbut.setOnClickListener {
            // Create an intent to start the FootWear_Page activity
            val intent = Intent(requireContext(), FootWear_Page::class.java)
            startActivity(intent)
        }
        accessoriesbut.setOnClickListener {
            // Create an intent to start the Accessories_Page activity
            val intent = Intent(requireContext(), Accessories_Page::class.java)
            startActivity(intent)
        }
        //Thie FAB for our categories button
        addfab.setOnClickListener{
            showAddButtonDialog()

        }
        return fragbut
    }
    //This makes a dialogue box so that we can name the new button
    private fun showAddButtonDialog() {
        val editText = EditText(requireContext())
        editText.hint = "Enter Button Name"
        //This creates the dialogue box
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Button")
            .setView(editText)
            .setPositiveButton("Add") { dialog, _ ->
                val buttonName = editText.text.toString().trim()
                if (buttonName.isNotEmpty()) {
                    addButton(buttonName)
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    //This method allows to add another category. This took some time if I'm being honest - Khulani
    private fun addButton(buttonName: String) {
        val layout = requireView().findViewById<LinearLayout>(R.id.layoutButtons)

        val newButton = Button(requireContext())
        newButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newButton.text = buttonName
        newButton.setBackgroundResource(R.drawable.button_background) // Set background programmatically
        newButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white)) // Set text color
        newButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // Set text size in SP (adjust 18f as needed)
        newButton.setOnClickListener {
            // Handle click event for the dynamically created button
            when (buttonName) {
                "Shirts" -> {
                    val intent = Intent(requireContext(), Shirt_Page::class.java)
                    startActivity(intent)
                }
                "Pants" -> {
                    val intent = Intent(requireContext(), Pants_Page::class.java)
                    startActivity(intent)
                }
                "Outerwear" -> {
                    val intent = Intent(requireContext(), OuterWear_Page::class.java)
                    startActivity(intent)
                }
                "Footwear" -> {
                    val intent = Intent(requireContext(), FootWear_Page::class.java)
                    startActivity(intent)
                }
                "Accessories" -> {
                    val intent = Intent(requireContext(), Accessories_Page::class.java)
                    startActivity(intent)
                }
                else -> {
                    //This is our error handler
                    Toast.makeText(requireContext(), "Unknown button: $buttonName", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //This will then make our new button have the same theme as out other buttons
        newButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.greyBlue) // Set background tint
        layout.addView(newButton)
    }



}
