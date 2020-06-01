# iTunes Search Demo
**iTunes Search Demo** is a simple application that fetches data from the iTunes Search API with the following parameters:
- term = star
- country =  au
- media = movie

(iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)

This app implements MVVM architecture using Dagger2, RxJava, Retrofit, and Glide.
I enabled caching with Retrofit to reduce bandwidth consumption. Since the data being fetched from the iTunes Search API doesn’t really get updated very frequently, I think we can take advantage of caching here. I set it in a way that when the device has internet connection, it can use the cached data for 90 seconds before discarding it and doing another request. When there is no internet connection, it can use the cached data for 7 days before discarding it and doing another request.

## Persistence
I used SharedPreferences for the persistence functions. Since the data to be saved is simple enough to be stored in a key-value pair, I didn’t feel the need to use database or other persistence options that may be too complex for the task.
- A date when the user previously visited, shown in the list header - e.g., if the user’s last visit is yesterday, when he opens it today, yesterday’s date will be shown on the list header.
- Save the last screen that the user was on. When the app restores, it should present the user with that screen. - When the user visits a movie’s detailed view, the app will store the movie’s details in JSON string format in the SharedPreferences.

## Architecture
I used MVVM architecture because I like how it decouples the different components of the application from each other, making it easier to make changes without breaking something in the process. The UI components are kept separate from the business logic. The business logic is also decoupled from other operations. It is also much easier to read, such that when I come back to it several months later, I’ll still be able to understand what I did and how I did it. I also noticed that when I started using MVVM, I don’t encounter much of the bugs I make when I was still a beginner. MVVM helps make my code cleaner and a bit more resilient to bugs caused by spaghetti code.
