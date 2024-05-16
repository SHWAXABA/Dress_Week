package com.example.dressweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class Achievements_Page : Fragment() {

    private lateinit var goalLayout: LinearLayout
    private lateinit var textViewProgress: TextView
    private var goalCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Creates the layout for our fragment
        val rootView = inflater.inflate(R.layout.fragment_achievements__page, container, false)
        goalLayout = rootView.findViewById(R.id.goalLayout)
        textViewProgress = rootView.findViewById(R.id.textViewProgress)
        val ClickBut = rootView.findViewById<Button>(R.id.clickSetGoal)
        ClickBut.setOnClickListener{
            onClickSetGoal()
        }
        return rootView
    }
//Allows us to set the goal
    fun onClickSetGoal() {
        val editTextGoal = requireView().findViewById<EditText>(R.id.editTextGoal)
        val goalValue = editTextGoal.text.toString().toIntOrNull() ?: return

        addGoal(goalValue)
        updateProgress()
        editTextGoal.text.clear()
    }

    private fun addGoal(goalValue: Int) {
        val textViewGoal = TextView(requireContext())
        textViewGoal.text = "Goal ${++goalCount}: $goalValue"
        goalLayout.addView(textViewGoal)
    }

    private fun updateProgress() {
        val totalGoals = goalLayout.childCount
        var achievedGoals = 0
        for (i in 0 until totalGoals) {
            val goalView = goalLayout.getChildAt(i) as? TextView ?: continue
            val goalText = goalView.text.toString()
            val goalValue = goalText.substringAfter(":").trim().toIntOrNull() ?: continue
            if (goalValue > 0) achievedGoals++
        }
        textViewProgress.text = "Progress: $achievedGoals/$totalGoals"
    }
}
