## Summary TODO

Viral is a single-player turn-based strategy game set inside a social media app. After setting up their account on the fledgling platform, the player will be informed through the app that the CDC has marked their area at risk of a mysterious illness spreading through social media, causing their friends to become hostile. It begins to "infect" the friends on their friends list through the app, slowly causing the people on their network to behave strangely and ultimately turning them hostile toward the player. The player will use clues about the friends from their profiles and posts to determine who has been infected by this illness, and clear them from the friends list as fast as possible. The win condition is to clear all infected friends from the friends list without being brought down to zero friends.

[1 or more paragraphs summarizing your aims or motivations for selecting the given topic for your app. That is, why did you choose to develop this particular app, and why is this (at least potentially) a useful or interesting app?]

[1 or more paragraphs summarizing the key functional elements of the app.]

## Intended Users

* For people who enjoy games with turn-based strategy elements, resource management, and subtle horror themes.

    > As a fan of turn-based strategy games and horror games, I want more games that combine the two so I can have my favorite game elements in the same product.

* For people who want entertainment experiences that are never exactly the same.

    > As an avid consumer of entertainment media, I want a game that plays out differently every time so that I can keep coming back to it for a different experience.

## Functionality

1. By installing and launching the app, the player is brought to a "sign-up" page, as if it were a social media app.
2. They "create an account," which has them enter a number of parameters required for the game, including taking a profile picture with the device's camera and determining the difficulty of the game. The game will generate a "friends list" using random values in the databases.
3. Once the account is created, the player is given a skippable tutorial that shows them how to navigate the various pages and create or delete posts and comments.
4. After the tutorial, the player is given a number of "free turns" to explore the app and get a feel for the layout and options available to them. A turn can contain a number of specific actions, but a handful of key actions bring a turn to an end:
    * messaging someone through the app's messenger
    * commenting on a post or profile
    * deleting someone from the friend's list
    * the illness can make an extra turn every x minutes the player spends in the same turn
5. Once the free turns have passed, the "illness" takes its first turn, and play begins. The illness's turn consists of performing one or more of the following actions, depending on difficulty:
    * adding a number of infections to friends on the player's friends list
        * as infection stages increase, this can cause infections to chain to multiple friends with a single "move"
    * sending a number of messages or making a number of posts and comments in the app on the behalf of NPCs
6. The player and the illness trade turns until the game ends.
    * The player can end the game early by choosing to delete their account. This will kick them back to the "sign-up" page, where they can "create a new account" and start again.
    * **Nothing** created or stored by the game using the device services is saved past the end of a game - it wipes all pictures taken immediately once the game ends. If the player wishes to save these for whatever reason, an option will be available in the game menu, but it is not done by default.

## Persistent Data

The game will be data-driven, with all possible text displayed during the game listed in SQLite databases that will be stored on the device.

* all names, comments, messages, social media posts, and other interaction text sorted by disposition of the NPC
* all events, actions, and turn options for the game to make against the player
* art assets like photographs for posts, the "social media" logo, and icons
* all information created for a single playthrough
    * a map of the actions taken, created files from device services, pictures for friends list profile pictures

## Device/External Services

**Device Services:**
* This app will use the [Camera](https://developer.android.com/guide/topics/media/camera#:~:text=%20Camera%20API%20%201%20Saving%20media%20files.,fields%20that%20require%20permission.%20LENS_POSE_REFERENCE%20LENS_INFO_HYPERFOCAL_DISTANCE...%20More%20) to take a profile picture for the user at the start of each new game.

**External Services:**
* [Leaflet.js](https://leafletjs.com/reference-1.7.1.html)
    * The app will take advantage of Leaflet's ability to place markers on a user-imported map image to track the location of infected players after they reach a critical infection point. This will be displayed as a pinned link on the user's feed, presented in the story as a list of addresses where police have responded to reports of unexplained aggressive behavior. This will be accomplished by storing points on the map as values in the database, then assigning a random fake address to the user at the start of a new game as their "current location" and to all members of their friends list. As friends become infected at the maximum infection level, their fake address will be plotted on the map. The user can then check that address against profiles to determine who is infected.
        * This app could still be usable without this external service. Instead of having a map, the player would have to rely on other clues to help them determine which of their friends have been infected - the map simply informs them of final-stage infection, which will not be the ideal time to delete them anyway.

## Wireframe TODO

* [Wireframe](wireframe.md)

## Technical Design TODO

* [Basic User Instructions](user-instructions.md)
* [Build Instructions](build-instructions.md)
* [Copyright and Licenses]()
* [Data Model Implementation](data-model-implementation.md)
* [DDL](ddl.md)
* [Entity-Relationship Diagram](entity-relationship.md)
* [Javadocs]()
* [Technical Requirements and Dependencies](requirements.md) TODO

## Current State TODO

In grading a prototype, understanding what it does and doesn’t (yet) do is important preparation for evaluating it in operation. Please provide the following in separate paragraphs & lists:

* A description of the current state of completion/readiness of your app. This should include a “hit list” of deficiencies: any unimplemented/incomplete elements, and known bugs, that would have to be implemented or corrected for a usable prototype (i.e. one that could be given to a skilled user for testing and feedback), ordered with the most urgent items first.

* A list of aesthetic/cosmetic (not functional) enhancements that you think would improve your app. This list should be ordered, with those that would give the most improvement (in your opinion) listed first.

* A list of functional stretch goals. These should be sorted either with those that would add the most utility at the top, or with those that would be the simplest to implement at the top.

## Stretch Goals/Possible Enhancements 

* I would like to connect the [Google AdMob](https://developers.google.com/admob) services to display ads as if they were social media posts.
* I would like to connect the OpenAI GPT-3 API in order to generate realistic unique display text when required. Unfortunately, it is still in beta for the foreseeable future.
* I would like to connect to an API that can generate realistic fake profile pictures, like the [Generated Photos API](https://generated.photos/api).
* I would like to collect the names and photos for the fake friends list by taking names and photos from the user's contacts, to make it a more personalized experience.
* I would like to utilize the [Google Maps API](https://developers.google.com/maps/documentation/android-sdk/overview) or [LocationIQ](https://locationiq.com/geocoding) to make the user's "current location" their actual home address, and have the infected friends pop up around their actual location within a very narrow radius to their home, making it feel more personal and nearby.