package com.example.wordplayer

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.InputStream

class GameMode : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var scoreTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var undoButton: Button
    private lateinit var exitButton: Button
    private lateinit var nextWordButton: Button

    private var targetWord = ""
    private var scrambledLetters = mutableListOf<String>()
    private var validWords = mutableListOf<String>()
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gameview)

        gridLayout = findViewById(R.id.wordInputGrid)
        linearLayout = findViewById(R.id.jumbledLettersLayout)
        scoreTextView = findViewById(R.id.scoreText)
        submitButton = findViewById(R.id.submitWordButton)
        undoButton = findViewById(R.id.undobtn)
        exitButton = findViewById(R.id.exitButton)
        nextWordButton = findViewById(R.id.nextWordButton)

        targetWord = fetchTargetWord()
        val dictionary = fetchDictionary()

        scrambledLetters = scrambleWord(targetWord)

        addScrambledLetters()

        setupGridLayout(targetWord.length)

        submitButton.setOnClickListener {
            checkUserInputAndCalculateScore(dictionary)
        }
        undoButton.setOnClickListener {
            gridLayout.removeAllViews()
            linearLayout.removeAllViews()
            scrambledLetters = scrambleWord(targetWord)

            addScrambledLetters()

            setupGridLayout(targetWord.length)
        }
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        nextWordButton.setOnClickListener {
            gridLayout.removeAllViews()
            linearLayout.removeAllViews()
            targetWord = fetchTargetWord()
            scrambledLetters = scrambleWord(targetWord)

            addScrambledLetters()

            setupGridLayout(targetWord.length)
        }
    }

    private fun fetchTargetWord(): String {
        val inputStream: InputStream = resources.openRawResource(R.raw.words)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val words = jsonObject.getJSONArray("words")

        val randomIndex = (0 until words.length()).random()
        return words.getString(randomIndex)
    }

    private fun fetchDictionary(): Set<String> {
        val inputStream: InputStream = resources.openRawResource(R.raw.dictionary)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val dictionaryArray = jsonObject.getJSONArray("dictionary")

        val dictionarySet = mutableSetOf<String>()
        for (i in 0 until dictionaryArray.length()) {
            dictionarySet.add(dictionaryArray.getString(i))
        }
        return dictionarySet
    }

    private fun scrambleWord(word: String): MutableList<String> {
        val scrambledList = word.toList().map { it.toString() }.shuffled().toMutableList()
        return scrambledList
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addScrambledLetters() {
        scrambledLetters.forEach { letter ->
            val textView = TextView(this).apply {
                text = letter
                textSize = 24f
                setPadding(16, 16, 16, 16)
                setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                gravity = Gravity.CENTER

                setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        val dragShadow = View.DragShadowBuilder(v)
                        val clipData = ClipData.newPlainText("letter", (v as TextView).text)
                        v.startDragAndDrop(clipData, dragShadow, v, 0)
                        true
                    } else {
                        false
                    }
                }

            }
            linearLayout.addView(textView)
        }
    }

    private fun setupGridLayout(wordLength: Int) {
        gridLayout.columnCount = wordLength

        gridLayout.removeAllViews()

        for (i in 0 until wordLength) {
            val textView = TextView(this).apply {
                text = "_"
                textSize = 36f
                gravity = Gravity.CENTER
                setPadding(16, 16, 16, 16)

                isFocusable = true
                isFocusableInTouchMode = true

                setOnDragListener { v, event ->
                    when (event.action) {
                        DragEvent.ACTION_DRAG_STARTED -> {
                            true
                        }
                        DragEvent.ACTION_DRAG_ENTERED -> {
                            true
                        }
                        DragEvent.ACTION_DROP -> {
                            val draggedView = event.localState as? TextView
                            if (draggedView != null) {
                                val draggedLetter = draggedView.text.toString()
                                val droppedView = v as TextView

                                if (droppedView.text == "_") {
                                    droppedView.text = draggedLetter
                                }
                                droppedView.invalidate()
                            } else {
                                Log.e("GameActivity", "Dragged view is not a TextView")
                            }
                            true
                        }
                        else -> false
                    }
                }
            }
            gridLayout.addView(textView)
        }
    }

    private fun checkUserInputAndCalculateScore(dictionary: Set<String>) {
        val userInput = StringBuilder()
        for (i in 0 until gridLayout.childCount) {
            val textView = gridLayout.getChildAt(i) as TextView
            val texttouse = textView.text.split("_")
            userInput.append(texttouse[0])
        }

        if (isValidWord(userInput.toString(), dictionary)) {
            validWords.add(userInput.toString())
            score += 10
            Toast.makeText(this, "Valid word!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Invalid word.", Toast.LENGTH_SHORT).show()
        }

        updateScore()
    }

    private fun isValidWord(word: String, dictionary: Set<String>): Boolean {
        return word in dictionary
    }

    private fun updateScore() {
        // Calculate the score based on the number of valid words
        scoreTextView.text = "Score: $score"
    }
}
