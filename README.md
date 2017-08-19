## JHub 
[![Build Status](https://travis-ci.org/clh161/JHub.svg?branch=master)](https://travis-ci.org/clh161/JHub)
[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/clh161-JHub/Lobby)
[![codecov](https://codecov.io/gh/clh161/JHub/branch/master/graph/badge.svg)](https://codecov.io/gh/clh161/JHub)

This is a showcase app created to fetches facebook’s repositories from Github and displays as a list of information.

# Specs
Programming language: Java 8
Supported platform: Android 5.0+

# Architecture:
MVP - The business logic of an activity is extracted into a class Presenter and the data management is handled by another class Interactor. This architecture increases testability to expand unit test coverage. 

# Dependencies injection
[Dagger](https://github.com/google/dagger) is applied to provide a better dependencies management and also increase testability of the program because singleton class is not mockable while Dagger can replace traditional singleton class by maintaining an instance during the app run time.

# RxAndroid
[RxAndroid](https://github.com/ReactiveX/RxAndroid) is very useful for async task and data manipulation such as http calls because this provides an easy way to manage threading and also has many methods for edit and pass  data from different observables. 
In this project, GitHubServiceImpl.java shows how to do simultaneous calls and receive response in one return. (Ref. getProfileAndRepositories() in GitHubServiceImpl.java).
Please also note that there are three class GitHubService, GitHubServiceImpl and GitHubServiceRetrofit for API calls. The reason why not just using the GitHubServiceRetrofit from the interactor is that Retrofit does not support much operational logic, and therefore it needs to be done in GitHubServiceImpl with RX.

# Unit test
Part of the business logic is covered by unit test.
