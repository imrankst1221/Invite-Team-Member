# Invite-Team-Member
##### Programming test for the position of Android Lead Engineer at SPLYZA, INC.

## Overview:
Invite-Team-Member is an Android Studio project. The project compiled version is `SDK 33`.  Android Studio version `chipmunk` is recomended with Gradle version `7.3.3`.

## Project architecture: 
The project was written in Kotlin and XML language based on the Modern Android Development concept with MVVM architecture. 

## Project library: 
- Androidx material design 
- Hilt
- LiveData 
- Coroutines 
- Retrofit2
- Okhttp3
- Gson
- Zxing
- Espresso

## Features:
- Run the app on Debug mode to get mock data
- Project has total 4 mock data and 1 invite link mock data to represent the different scenarios
- The project has an okhttp3 interceptor fake response which always returns positive responses
- Every time when user clicks the Invite link will open with different mock data
- The project has Espresso UI testing 


## Project skeleton (Kotlin): 
- **base**: Base Activity and Fragment
- **data**: Contain all API calls, models, and repository
- **di**: Hilt modules 
- **ui**: 
   - **adapter**: Contains all the list adapters
	- **viewmodels**: Contains all the ViewModel
	- **views**: Activity and fragment classes 
- **utilities**: All the helper components and extensions 

## Project skeleton (UI): 
- **assets**: Contains all the Mock data
- **res**: 
  - **layout**: Contains all the UI views
  - **navigation**: Contains fragment navigation graph

## Project skeleton (AndroidTest): 
- **ui**: 
  - **view/fragments**: Contain all the fragments UI test classes 
  - **viewmodels**: Contains all the ViewModel test classes

