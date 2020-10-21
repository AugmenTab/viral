## Summary

The goal of this personal Android project will be to develop an as-of-yet-unnamed mobile game. It will be a turn-based strategy game set inside a social media app. 

After setting up their account on the fledgling platform, control of the player's account is partially seized by some unidentified outside entity (Something) that drives the game. It taunts them, then begins to "infect" their friends list through the app, slowly causing the people on their network to behave strangely and ultimately turning them hostile toward the player. It even seemingly infects the phone itself, taking control of outside features at times in an attempt to impede the player's progress.

The player will use clues about the friends from their profiles and posts to determine who has been "infected" by this Something and clear them from the friends list as fast as possible. The win condition is to clear all infected friends from the friends list without being brought down to zero friends.

## Intended users

* For people who enjoy games with turn-based strategy elements, resource management, and subtle horror themes.

    > As a fan of turn-based strategy games and horror games, I want more games that combine the two so I can have my favorite game elements in the same product.

* For people who want entertainment experiences that are never exactly the same.

    > As an avid consumer of entertainment media, I want a game that plays out differently every time so that I can keep coming back to it for a different experience.

## Functionality

1. By installing and launching the app, the player is brought to a "sign-up" page, as if it were a social media app.
2. They create an account, which has them enter a number of parameters required for the game, including taking a profile picture with the device's camera and determining the difficulty of the game. The game will generate a "friend's list" using random values in the databases.
3. Once the account is created, the player is given a skippable tutorial that shows them how to navigate the various pages, create or delete posts and comments, and change the display theme of the app.
4. After the tutorial, the player is given a number of "free turns" based on their difficulty selection to explore the app and get a feel for the layout and options available to them. A turn can contain a number of specific actions, but a handful of key actions bring a turn to an end:
    * messaging someone through the app's messenger
    * commenting on a post or profile
    * deleting someone from the friend's list
    * "passing" their turn
    * the Something can make an extra turn every x minutes the player spends in the same turn, based on difficulty (0 for lowest, increasing as difficulty increases)
5. Once the free turns have passed, the player is confronted by the Something, who takes their first turn, and play begins. The Something's turn consists of performing one or more of the following actions, depending on difficulty:
    * adding a number of infections to friends on the player's friends list
        * as infection stages increase, this can cause infections to chain to multiple friends with a single "move"
    * sending messages or making posts in the app on the player's behalf
    * using the Android device services to interfere with the player's progress or alter the game in some way, such as taking pictures with the camera to create posts as the player
    * using outside services to do the same, such as accessing Google Maps to determine the player's location
6. The player and the Something trade turns until the game ends. Any action taken against the Something does not end a turn, even if it is on the above list, but it can be used by the Something to create further obstacles for the player later on.
    * The player can end the game early by choosing to delete their account. This will kick them back to the "sign-up" page, where they can create a new account and start again.
    * **Nothing** created or stored by the game using the device services is saved past the end of a game - it wipes all pictures taken immediately once the game ends. If the player wishes to save these for whatever reason, an option will be available in the game menu, but it is not done by default.

## Persistent data

The game will be data-driven, with all possible text displayed during the game listed in SQLite databases that will be stored on the device.

* all names, comments, messages, social media posts, and other interaction text sorted by disposition of the NPC
* all events, actions, and turn options for the game to make against the player, along with percent chance of each action being selected
* art assets like photographs for posts, the "social media" logo, and icons
* all information created for a single playthrough
    * a map of the actions taken, created files from outside and device services, pictures for friends list profile pictures

## Device/external services

**Device Services:**
* camera

**External Services:**
* [Leaflet.js](https://leafletjs.com/reference-1.7.1.html)
    * The app will take advantage of Leaflet's ability to place markers on a user-imported map image to track the location of infected players after they reach a critical infection point. This will be displayed as a pinned link on the user's feed, presented in the story as a list of addresses where police have responded to reports of unexplained aggressive behavior. This will be accomplished by storing points on the map as values in the database, then assigning a random fake address to the user at the start of a new game as their "current location" and to all members of their friends list. As friends become infected at the maximum infection level, their fake address will be plotted on the map. The user can then check that address against profiles to determine who is infected.
        * This app could still be usable without this external service. Instead of having a map, the player would have to rely on other clues to help them determine which of their friends have been infected - the map simply informs them of final-stage infection, which will not be the ideal time to delete them anyway.

## Wireframe

* [Codebreaker Wireframe](wireframe.md)

## Technical Design

* [Entity-Relationship Diagram](entity-relationship.md)

## Stretch goals/possible enhancements 

* I would like to connect the [Google AdMob](https://developers.google.com/admob) services to display ads as if they were social media posts.
* I would like to connect the OpenAI GPT-3 API in order to generate realistic unique display text when required. Unfortunately, it is still in beta for the foreseeable future.
* I would like to make use of more specialized text-to-speech services that can offer emotion and celebrity voices, like [CeleProc](https://www.cereproc.com/en/products/voices).
* I would like to take control of the device's microphone or camera, in order to make recordings of ambient sound or video to play back to the player.
* I would like to connect to an API that can generate realistic fake profile pictures, like the [Generated Photos API](https://generated.photos/api).
* I would like to collect the names for the fake friends list by taking names from the user's contacts, to make it a more personalized experience.
* I would like to utilize the [Google Maps API](https://developers.google.com/maps/documentation/android-sdk/overview) or [LocationIQ](https://locationiq.com/geocoding) to make the user's "current location" their actual home address, and have the infected friends pop up around their actual location within a very narrow radius to their home, making it feel more personal and nearby.
