# s8114877_Assignment2
This Android application demonstrates authentication, API data retrieval, and UI navigation using modern Android architecture components.  

---

##Features

- **Login screen** that authenticates with an API (`https://nit3213api.onrender.com/footscray/auth`)
- **Dashboard screen** that displays entities fetched from the API (`/dashboard/{keypass}`)
- **Detail screen** for viewing selected entity information
- **MVVM architecture** using:
  - `ViewModel`
  - `Hilt` for Dependency Injection
  - `RecyclerView` for displaying API data

---

## Tech Stack

| Component | Description |
|------------|-------------|
| **Language** | Kotlin |
| **Architecture** | Model–View–ViewModel |
| **Dependency Injection** | Hilt |
| **Coroutines & Flows** | For async API calls |
| **Navigation Component** | To handle screen navigation |
| **RecyclerView** | For list display |
| **JUnit & Coroutines Test** | For unit testing |

---

## Requirements

- **Android Studio**: Giraffe (or newer)
- **Gradle Plugin**: 8.1+
- **Compile SDK**: 34
- **Min SDK**: 26
- **Internet connection** (for API calls)

---

## Build & Run Instructions
## Clone the repository
Open your terminal (or Git Bash) and run:
```bash
git clone https://github.com/BaoYeager/s8114877_Assignment2.git

Open in Android Studio

Launch Android Studio

Click File → Open

Select the cloned folder s8114877_Assignment2

Sync Gradle

When prompted, let Android Studio automatically sync Gradle dependencies.

Click Build (or press Ctrl+F9)

Run the app

Select an emulator or connect a physical device

Click Run ▶ or press Shift+F10

The app should launch and display the Login screen.


