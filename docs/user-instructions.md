# Basic User Instructions

1. Upon launching the app, the user is prompted with a new game screen. This is designed to look like a login screen for a social media app, with the button to start a new game even being labeled "Create Account." To begin play, the user must enter a username into the text field, then click the "Create Account" button. The user is not able to begin a new game while the text field is empty.
2. Once the game begins, the user is directed to the main game section. Using the navigation drawer, the user can navigate to the other screens. These are:
    * **Feed:** This is where all posts made by the user's friends will be displayed. Currently, nothing displays here.
    * **Friends List:** This displays a list of all of the user's friends, and provides the user the option to either navigate to that friend's "page" (a filtered feed page that only shows posts by that friend) or to delete the friend from their friends list entirely. This page currently displays the friends in the friend list, but not the options to filter or delete the friend.
    * **Messages:** This page is split vertically, with the left side of the page displaying a list of all friends the user has sent messages to and received messages from. The right side of the page displays the messages associated with the friend selected on the left. This page currently displays all of the friends on the left side, despite having no messages at game creation, and displays no messages.
    * **Local News:** This page displays a map, mocked up using Leaflet.js, with markers indicating the locations of all friends at maximum infection level. Currently, nothing displays here.
    * **Delete Account**: This option will end the current game prematurely, and return the user to the new game screen. This is currently not functional.

This concludes the instructions for the current implemented functionality.