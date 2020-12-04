## Summary

Viral is a single-player turn-based strategy game set inside a social media app. After setting up their account on the fledgling platform, the player will be informed through the app that the CDC has marked their area at risk of a mysterious illness spreading through social media, causing their friends to become hostile. It begins to "infect" the friends on their friends list through the app, slowly causing the people on their network to behave strangely and ultimately turning them hostile toward the player. The player will use clues about the friends from their profiles and posts to determine who has been infected by this illness, and clear them from the friends list as fast as possible. The win condition is to clear all infected friends from the friends list without being brought down to zero friends.

I chose Viral as my project idea because I wanted to focus on something that was heavily dependent on a database to function properly. I have a number of other project ideas that require this, so I decided to use Viral to "dip my toe in" and get some experience with a data-driven project. While maybe not exactly useful, I found it to be interesting. While the idea of a game that takes place in a virtual or digital setting is nothing new (see Pony Island for an example), I have never seen one that takes place inside a fictional social media app. It also combines elements of turn-based strategy and (very light) horror themes, a combination I happen to enjoy but see very little of in the gaming industry.

Viral is very dependent on its database. On creation, a list of all possible actions and demeanors that the user's friends can have are immediately persisted into the database. It will also eventually use Leaflet.js to provide a fake neighborhood map to show the player where all of the outbreaks have occurred near them, and allow the user to take their own profile picture using the device camera.

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

## Wireframe

* [Wireframe](wireframe.md)

## Technical Design TODO

* [Basic User Instructions](user-instructions.md)
* [Build Instructions](build-instructions.md)
* [Copyright and Licenses](copyright-and-licenses.md)
* [Data Model Implementation](data-model-implementation.md)
* [DDL](ddl.md)
* [Entity-Relationship Diagram](entity-relationship.md)
* [Javadocs]()
* [Technical Requirements and Dependencies](requirements.md)

## Current State

Viral is currently building, and successfully inserting Actions, Demeanors, Friends, Games, and Actions Taken into the database upon new game creation.

* It is not able to, on launch, recognize when a game is already started and redirect the user to the main activity.
* It is not able to infect friends on the friends list, which is primarily how the game advances.
* It is not able to display a friend's messages, nor is it able to display posts.
* It is not able to display the Leaflet map, the markers indicating infected locales, or the addresses in the list view underneath the map.
* The user is currently unable to "delete their account" - that is, end the game early, delete that instance of the game, and return to the home screen to start a new game.
* While most of the layouts have been defined, most of the lists and other displays are not utilizing them, if they are indeed displaying at all.
* The user is unable to use the camera to take a picture and have it displayed in the game as their "profile picture."
* There is a known warning that appears on build, stating the ActionTakenDao has some columns that are not used by the ActionTaken entity. This does not prevent the app from running.
* There is a known warning that appears on build, stating that the MainActivity uses or overrides a deprecated API, the AppBarConfiguration.Builder.setDrawerLayout.

* A list of aesthetic/cosmetic (not functional) enhancements that you think would improve your app. This list should be ordered, with those that would give the most improvement (in your opinion) listed first.

## Aesthetic Enhancements

* I would like to provide an option to switch between a light theme and a dark theme as the user pleases.

### Functional Stretch Goals

I have ordered the stretch goals for the Viral project by their level of utility.

* I would like to connect the OpenAI GPT-3 API in order to generate realistic unique display text when required. Unfortunately, it is still in beta for the foreseeable future.
* I would like to connect to an API that can generate realistic fake profile pictures, like the [Generated Photos API](https://generated.photos/api).
* I would like to collect the names and photos for the fake friends list by taking names and photos from the user's contacts, to make it a more personalized experience.
* I would like to utilize the [Google Maps API](https://developers.google.com/maps/documentation/android-sdk/overview) or [LocationIQ](https://locationiq.com/geocoding) to make the user's "current location" their actual home address, and have the infected friends pop up around their actual location within a very narrow radius to their home, making it feel more personal and nearby.
* I would like to connect the [Google AdMob](https://developers.google.com/admob) services to display ads as if they were social media posts.
* I would like to create a multiplayer version of the game, with two possible game modes:
    * The players must work cooperatively to eliminate the infected players from *everyone's* list, though not all friends will be on everyone's lists.
    * The players must work to find the one infected player in the group, who can spread the infection to friends in the friends list and even to other players.