# 🧮 BasicCalculator

A clean and functional Android calculator app built with Kotlin that handles everyday arithmetic with a smooth, intuitive interface.

## 📱 Screenshots

_Coming soon_

## ✨ Features

- **Basic Arithmetic** — Addition, subtraction, multiplication, and division
- **Decimal Support** — Full floating point calculations with clean formatting
- **Chained Operations** — Automatically calculates pending operations before applying a new operator
- **Toggle Sign** — Instantly flip between positive and negative numbers
- **Percentage** — Convert any number to its percentage value
- **Division by Zero Handling** — Displays "Error" instead of crashing
- **Smart Formatting** — Strips unnecessary trailing zeros from results (e.g. `2.0` shows as `2`)
- **Leading Zero Prevention** — No accidental multi-zero inputs

## 🛠️ Tech Stack

- **Language**: Kotlin
- **Platform**: Android (minimum SDK 21)
- **UI**: XML layouts
- **Architecture**: Single Activity

## 🚀 Getting Started

### Prerequisites
- Android Studio
- Android device or emulator running Android 5.0+

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/adyashaexe/BasicCalculator.git
   ```
2. Open the project in Android Studio
3. Let Gradle sync
4. Run on your device or emulator

## 📖 How to Use

| Button | Action |
|--------|--------|
| `0–9` | Input digits |
| `.` | Add decimal point |
| `+` `−` `×` `÷` | Arithmetic operators |
| `=` | Calculate result |
| `C` | Clear everything |
| `+/-` | Toggle positive/negative |
| `%` | Convert to percentage |

## 📁 Project Structure

```
app/src/main/
├── java/com/example/basiccalculator/
│   └── MainActivity.kt
└── res/
    └── layout/
        └── activity_main.xml
```

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
