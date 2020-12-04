# Build Instructions

## Step One: Clone the GitHub Repository

1. Navigate to the Github repository [here](https://github.com/AugmenTab/viral).
2. In the very top-right corner of the screen, select "Fork."
3. Select the account or organization you would like to fork the repository to.
4. Once selected, GitHub will redirect you to the newly-created Viral repository in your organization or account.
5. Select the green "Code" button, then copy the link to clone the repository.

From here, there are two options: you can clone the repository manually, or you can clone it from IntelliJ IDEA.

### Method One: Clone the Repository Manually

6. In a Git Bash terminal, navigate to the directory you wish to clone the repository into.
7. Once you are in the desired directory, enter the following command, replacing the placeholder with the link: ```git clone [LINK]```

### Method Two: Clone from IntelliJ IDEA or Android Studio

6. Open IntelliJ IDEA.
7. Instead of selecting a recent project from the left, select instead the "Get from Version Control" option.
8. Make sure that the "Repository URL" option on the left is selected.
9. Enter the link copied from the "Code" button earlier into the "URL" field.
10. Click the folder icon in the "Directory" field and navigate to the directory you would like to save the repository to.
11. Select "Clone."

## Step Two: Import the App into IntelliJ IDEA or Android Studio

***This step is unnecessary if you opted to clone the repository using IntelliJ instead of doing so manually.***

1. Once the repository has been cloned into your desired directory, open IntelliJ IDEA.
2. Instead of selecting a recent project from the left, select the "Open or Import" option.
3. Navigate to the directory you would like to save the repository to.
4. Select "OK."

## Step Three: Executing the Build

1. Currently, Viral has no API keys or other requirements to activate an external service. This means that Viral can simply be run from the emulator or hardware device without any additional preparation.