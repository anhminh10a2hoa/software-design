# Weather Application Project

## Introduction

Weather applications have become essential in our daily lives, aiding in planning schedules, making informed decisions, and staying cautious about weather conditions. This project focuses on creating a user-friendly weather application with enhanced functionality by integrating APIs into a Graphical User Interface (GUI) for delivering accurate and up-to-date weather data.

## Scope and Objectives

- Utilize object-oriented design for the Weather system.
- Implement a GUI using JavaFX and follow the Model-View-Controller (MVC) design pattern.
- Integrate external APIs, such as OpenWeatherInfo, for accurate weather information.
- Ensure a high-quality GUI adaptable to desktop screens based on design principles.

## Overall Architecture

The application follows the MVC architectural pattern, promoting maintainability and separation of concerns.

## GUI Implementation

### Packages

Divided into four parts: "airpollution," "forecast," "weather," and "function," each handling specific screens and functionalities.

### Selection Screen

Three options presented on the Selection screen, easily switchable using a Toggle button.

### First Screen

- Utilizes OpenWeather API for up-to-date weather data.
- Components: View (JavaFX UI), Controller (handles user input and API communication), Model (represents weather data).

### Second Screen

- Provides forecast data based on user preferences.
- Components: View (JavaFX UI with options), Controller (handles user input and API communication), Model (represents forecast data).

### Third Screen

- Displays air pollution forecast data.
- Components: View (JavaFX UI), Controller (handles user input and API communication), Model (represents air pollution data).

## API Integration and Usage

- Separate classes implement data providers for interaction with OpenWeather APIs.
- Demonstrates usage of various APIs for fetching current weather, hourly and daily forecasts, geocoding, and air pollution data.

## Design Patterns

- Utilizes the singleton pattern and adheres to SOLID principles for code organization and maintenance.
- MVC model ensures a clear separation of concerns for ease of handling data.

## Self-Evaluation

- Acknowledges successful implementation as a group, with recognition of potential optimizations.
- Identifies the use of GitHub Copilot for coding implementation to save time.

## Use of AI

- GitHub Copilot used for coding implementation, focusing on maintaining original ideas and functionalities.