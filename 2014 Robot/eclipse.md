Rather than using the Ant/Netbeans setup that FIRST provides, we're using Gradle and Eclipse.
Gradle is a build system like Ant, but it's more powerful and has more flexible Eclipse support.
	
### Getting Started

Gradle comes with a wrapper script (`gradlew.bat` on Windows and `gradlew` on OS X and Linux) that will download Gradle for you as needed.
Since the FRC SDK is in different places on each person's computer, Gradle needs to be run to generate the Eclipse project files.

Since Eclipse isn't set up at this point, it needs to be done from the command line. Open a terminal (command prompt on Windows) and navigate
to wherever you cloned the Git repository (Google is your friend if you don't have a lot of command-line experience). There, run `./gradlew eclipse`
on OS X or Linux or `gradlew.bat eclipse` on Windows to generate the Eclipse files. Then, you can open the project in Eclipse.

If you prefer, there's a Gradle Eclipse plugin also. Follow the instructions [here](https://github.com/spring-projects/eclipse-integration-gradle/)
to install it. Then, use File->Import and select "Gradle Project". The importer will run Gradle to generate the project files.

### Running Things

A couple of launch configurations are generated to run Gradle tasks from Eclipse.

NOTE: Things aren't quite done yet, so debugging won't work and some launch configurations might be missing. This should be fixed soon.
Also, the code is compiled as Java 1.3 code against the FRC APIs, so only things that would work on the robot will compile. This means no annotations, no Lists, etc.

The "Run in Emulator" and "Run on Robot" launch configurations run the robot code on Nick's emulator or the actual robot.
The "Deploy to Robot" configuration compiles the robot code and sends it to the robot.
The "Debug in Emulator and Debug on Robot" configurations debug the robot code on the emulator or the actual robot.