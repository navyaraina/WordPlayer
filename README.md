# WordPlayer 🎲🔤

WordPlayer is an Android word game application designed to challenge your vocabulary skills! Unscramble jumbled letters to form valid words and check them against a dictionary. Compete for the highest score while improving your language skills.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Code Structure](#code-structure)
- [Technologies Used](#technologies-used)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)

## Features
- 🔠 **Scrambled Letters**: Form valid words from a set of scrambled letters.
- 📖 **Dictionary Integration**: Validate your words against an extensive dictionary.
- 🏆 **Score Tracking**: Earn points for every correct word.
- 🔄 **Undo and Next Word**: Refresh the game to challenge yourself with new words or retry the current word.
- 🚪 **Exit Anytime**: Return to the main menu seamlessly.

## Getting Started
To get a local copy up and running, follow these steps.

### Prerequisites
- Android Studio Arctic Fox or later
- Minimum SDK: 21 (Android 5.0)
- Kotlin 1.4+

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/navyaraina/wordplayer.git
   cd wordplayer
   ```
2. **Open in Android Studio**:
    - Open Android Studio, click on "Open an existing project," and select the cloned repository.
3. **Build the project**:
    - Sync Gradle and run the app on an emulator or physical device.

### Usage
- Launch the app and start a new game.
- Unscramble the jumbled letters to form valid words.
- Submit your word to check its validity and score points.
- Click **Undo** to reshuffle the current word or **Next** to try a new word.
- Track your score at the top of the screen.

### Code Structure
#### Project Files
- **MainActivity.kt**: Handles navigation to the game mode.
- **GameMode.kt**: Contains the core game logic, including scrambling words, validating input, and updating scores.
- **gameview.xml**: Layout for the game screen, including scrambled letters, user input, and buttons.

#### JSON Structure (words.json and dictionary.json)
- **words.json**:
  ```json
  {
    "words": [
      "example",
      "android",
      "kotlin",
      "puzzle",
      "game"
    ]
  }
  ```
- **dictionary.json**:
 ```json
  {
    "dictionary": [
      "example",
      "puzzle",
      "game",
      "kotlin",
      "android"
    ]
  }
  ```

### Navigation  
WordPlayer uses a simple yet effective navigation structure to guide users through the game flow, from the main menu to the gameplay and results screen.

### Technologies Used  
- **Kotlin**: Primary language for Android development.  
- **Android Jetpack**: Leveraged for lifecycle management and navigation.  
- **JSON**: Utilized for storing words and dictionary data.  
- **XML**: Layout design for the user interface, ensuring a clean and responsive design.

### Future Enhancements  
- 🔍 **Hint System**: Provide optional hints for challenging words.  
- 🌐 **Multilingual Support**: Add support for various languages to increase accessibility.  
- 🏆 **Achievements & Leaderboards**: Integrate Firebase to track high scores and achievements globally.  
- 🎨 **Custom Themes**: Allow users to personalize the app with different themes and colors.

### Contributing  
Contributions are welcome! Follow these steps to contribute:  
1. **Fork the repository**.  
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`).  
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`).  
4. **Push to the branch** (`git push origin feature/AmazingFeature`).  
5. **Open a pull request**.

Your contributions will help improve WordPlayer and make it even more engaging! 🌟  
