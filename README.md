# RecipeMe - Explore and Discover Recipes

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Overview

RecipeMe is an Android app developed in Kotlin and Jetpack Compose. It leverages theMealdb API to provide a delightful experience for exploring and discovering various recipes. The app follows the MVVM architecture, utilizes the repository design pattern, and incorporates Jetpack Navigation for seamless navigation within the app.

## Features

- **Ingredient Categories:** Browse through a list of main ingredient categories, such as chicken, desserts, lamb, etc.
  
- **Recipe Details:** Within each category, access detailed information and instructions for a wide variety of recipes.

- **YouTube Videos and Source Website:** View related YouTube videos for each recipe directly from the recipe page. Additionally, users can visit the source website from which the recipe was taken for more details.

- **Adherence to Material Design 3 Principles:** The app adheres to the principles of Material Design 3, Google's latest iteration of the Material Design system. Material Design 3 introduces new components, patterns, and guidelines for creating modern and visually appealing user interfaces.

## Tech Stack

- **Kotlin:** The primary programming language used for development.
  
- **Jetpack Compose:** A modern UI toolkit for building native Android UIs.

- **Retrofit:** A type-safe HTTP client for making API calls.

- **Coil:** An image loading library for Android.

- **MVVM Architecture:** Ensures a clear separation of concerns and maintainability.

- **Repository Design Pattern:** Manages the data access layer, providing a clean API to the rest of the application.

- **Jetpack Navigation:** Enables navigation between different screens in a structured way.

- **Kotlin Coroutines:** Efficiently handles asynchronous tasks, such as API calls, without blocking the main thread.

- **Jetpack Navigation:** Utilizes NavHost, NavController, and backstacks for seamless navigation between different screens.

- **Repository Design Pattern:** Manages the data access layer, providing a clean API to the rest of the application.

- **State Hoisting Pattern:** The project follows the state hoisting pattern, recommended by Google. This pattern makes the project scalable as the composables in the project become stateless, making future extensions to the project much smoother.

## Screenshots

### Grid of all categories of recipes
[![Recipe-Me-Categories-Page.png](https://i.postimg.cc/MpM6TbNt/Recipe-Me-Categories-Page.png)](https://postimg.cc/rDTXZW8t)

### List of recipes in a category
[![Recipe-Me-Recipes-List.png](https://i.postimg.cc/GmWcsT1R/Recipe-Me-Recipes-List.png)](https://postimg.cc/JsQwfnnY)

### Recipe page
[![Recipe-Me-Recipe-Page.png](https://i.postimg.cc/9FNXCN8r/Recipe-Me-Recipe-Page.png)](https://postimg.cc/jLPYhvrb)


## Installation

To run the app locally, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/recipe-me.git
    cd recipe-me
    ```

2. Build and run the app using Android Studio or your preferred IDE.

3. Make sure to replace `your-username` with your actual GitHub username.

## Usage

Once the app is installed, open RecipeMe on your Android device or emulator. Explore the various ingredient categories and discover new recipes. Enjoy a user-friendly interface designed with Jetpack Compose, and benefit from the responsiveness brought by Kotlin coroutines for seamless asynchronous operations!

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow our [contribution guidelines](CONTRIBUTING.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Special thanks to [theMealdb](https://www.themealdb.com/) for providing the API used in this project.
