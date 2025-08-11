# Rick and Morty Compose

An Android application developed in Kotlin with Jetpack Compose to use the Rest API of [Rick and Morty API](https://rickandmortyapi.com/) which will display information about the characters and locations in the series: [Rick and Morty](https://www.filmaffinity.com/es/film551351.html).

The application aims to follow the principles of clean architecture, following the **MVVM architecture**, but **using the new Jetpack Compose view system**

# Screenshots

Here is the app design. It is a simple app that, when launched, displays a list of characters with a customised menu at the bottom where we find a **"Characters"** option, a **"Locations"** option and another **“More”** option, which will display a menu to easily switch between **"Light Mode / Dark Mode"**, view **"Favourite characters"**, or access the **"GitHub repository"**.

<img src="https://raw.githubusercontent.com/jeluchu/rick-and-morty-compose/refs/heads/develop/assets/Screenshot_20250811_182022.png" width="250"/> <img src="https://raw.githubusercontent.com/jeluchu/rick-and-morty-compose/refs/heads/develop/assets/Screenshot_20250811_182151.png" width="250"/> 

<img src="https://raw.githubusercontent.com/jeluchu/rick-and-morty-compose/refs/heads/develop/assets/Screenshot_20250811_182035.png" width="250"/> <img src="https://raw.githubusercontent.com/jeluchu/rick-and-morty-compose/refs/heads/develop/assets/Screenshot_20250811_182050.png" width="250"/>

<img src="https://raw.githubusercontent.com/jeluchu/rick-and-morty-compose/refs/heads/develop/assets/Screenshot_20250811_182102.png"/> 

# Video recording
Here is the animation from the menu below. Although I have never seen this series, I have seen that there is a pickle, and I wanted the menu to look like that (as far as I can tell, it is called Pickle Rick).

https://github.com/jeluchu/rick-and-morty-compose/raw/refs/heads/develop/assets/Screen_recording_20250811_182119.webm


## Prerequisites

The new version of Android Studio (the latest as of August 2025, which is **Android Studio Narwhal Feature Drop | 2025.1.2**) has been used to develop this application, so I recommend using that version when opening the project to avoid problems with Gradle.

## Architecture
Although MVI (Model-View-Intent) architecture is currently booming and becoming increasingly popular, I am passionate about MVVM architecture, as it was one of the architectures I studied when I started out and the one I have used the most so far. That is why I wanted to continue using this architecture in this project.

This architecture, MVVM (Model-View-ViewModel), is widely used and helps to more easily separate the interface from the development of the business logic, as well as from the rest of the layers.

## Package Structure
```
rickandmorty/      # Root package
|
├─ core/           # Basic and generic features of the app
│  ├─ database/    # Local data elements
│  ├─ di/          # Dependency injection
│  ├─ exception/   # Generic error elements
│  ├─ extensions/  # Useful functions to use as extensions
│  ├─ interactor/  # General element of the Use Case
│  ├─ navigation/  # Routes and navigation elements between screens
│  ├─ network/     # Elements related to Ktor
│  ├─ ui/          # Common components, styles, etc
│  ├─ utils/       # General utilities as preferences
|
├─ features/       # App features
├──── characters/  # Character Section
├───── models/     # UI models
├───── repository/ # Repository
├───── state/      # State of UI
├───── usecase/    # Use Case
├───── view/       # View (Screens or components)
├───── viewmodel/  # ViewModel
├──── details/     # Character Details Section
├──── favorites/   # Favorites Section
├──── home/        # Main Section
├──── locations/   # Locations Section
├─ MainActivity    # Main Activity for all app
├─ RickAndMortyApp # Application class<
```
# Tech Stack

I have tried to use as few libraries as possible. One of the third-party libraries I have used is **Turbine**, to be able to test the Flows, and I have also used **Coil** to load the images, as I believe it is currently the best option for this task, since it is developed in Kotlin and uses coroutines.

I have defined all dependencies in the new file called [Version Catalogue](https://docs.gradle.org/current/userguide/platforms.html), the file is libs.versions.toml. From here, you can more easily manage dependency versions and even plugins, as well as group them into bundles.

- [Koin](https://insert-koin.io/) - Dependency Injection library
- [Jetpack](https://developer.android.com/jetpack)
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    -   [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.

    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.

    - [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) -Component that allows easier implementation of navigation from composables.
- [Ktor Client](https://ktor.io/) - For API Rest requests
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously
- [Material Design 3](https://m3.material.io/) - Build awesome beautiful UIs
- [Junit](https://junit.org/junit4/) - JUnit is a simple framework
- [CashApp Turbine](https://github.com/cashapp/turbine) - A small testing library for kotlinx.coroutines Flow
- [Mockito](https://site.mockito.org/) - For Unit tests
- [Room Database](https://developer.android.com/jetpack/androidx/releases/room) - Local Database

# Off-line mode
If you have opened the app for the first time and data about the characters/locations on the first few pages has been loaded, you're in luck, because even if you don't have a connection, you will still be able to view that data without the internet. However, new pages will obviously not load, as there will be no access to the Rest API.

I have used the Room database library for Cache and Favourites, so we will have two databases that will help us manage both things.

# GitHub Actions

A GitHub Action is included to generate a debug APK in the application so that it can be tested. The app has two flavours (debug and release), but in order to avoid generating signed APKs, only the development version is generated.

# Future improvements

Many improvements could be made to this app, such as including a character/location **search engine**, a button to **change the style of the items in the list**, or even **adding a custom splash screen**.