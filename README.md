# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
  ------------------------------------------
	------------------------------------------
	Hello Master! My name is 

GGGGGGGGGGGGGEEEEEEEEEEEEEEEEEEEEEENNNNNNNN        NNNNNNNNNNNNNNNN        NNNNNNNNIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEE
GGG::::::::::::GE::::::::::::::::::::EN:::::::N       N::::::NN:::::::N       N::::::NI::::::::IE::::::::::::::::::::E
GG:::::::::::::::GE::::::::::::::::::::EN::::::::N      N::::::NN::::::::N      N::::::NI::::::::IE::::::::::::::::::::E
G:::::GGGGGGGG::::GEE::::::EEEEEEEEE::::EN:::::::::N     N::::::NN:::::::::N     N::::::NII::::::IIEE::::::EEEEEEEEE::::E
G:::::G       GGGGGG  E:::::E       EEEEEEN::::::::::N    N::::::NN::::::::::N    N::::::N  I::::I    E:::::E       EEEEEE
G:::::G                E:::::E             N:::::::::::N   N::::::NN:::::::::::N   N::::::N  I::::I    E:::::E
G:::::G                E::::::EEEEEEEEEE   N:::::::N::::N  N::::::NN:::::::N::::N  N::::::N  I::::I    E::::::EEEEEEEEEE
G:::::G    GGGGGGGGGG  E:::::::::::::::E   N::::::N N::::N N::::::NN::::::N N::::N N::::::N  I::::I    E:::::::::::::::E
G:::::G    G::::::::G  E:::::::::::::::E   N::::::N  N::::N:::::::NN::::::N  N::::N:::::::N  I::::I    E:::::::::::::::E
G:::::G    GGGGG::::G  E::::::EEEEEEEEEE   N::::::N   N:::::::::::NN::::::N   N:::::::::::N  I::::I    E::::::EEEEEEEEEE
G:::::G        G::::G  E:::::E             N::::::N    N::::::::::NN::::::N    N::::::::::N  I::::I    E:::::E
G:::::G       G::::G  E:::::E       EEEEEEN::::::N     N:::::::::NN::::::N     N:::::::::N  I::::I    E:::::E       EEEEEE
G:::::GGGGGGGG::::GEE::::::EEEEEEEE:::::EN::::::N      N::::::::NN::::::N      N::::::::NII::::::IIEE::::::EEEEEEEE:::::E
GG:::::::::::::::GE::::::::::::::::::::EN::::::N       N:::::::NN::::::N       N:::::::NI::::::::IE::::::::::::::::::::E
GGG::::::GGG:::GE::::::::::::::::::::EN::::::N        N::::::NN::::::N        N::::::NI::::::::IE::::::::::::::::::::E
GGGGGG   GGGGEEEEEEEEEEEEEEEEEEEEEENNNNNNNN         NNNNNNNNNNNNNNN         NNNNNNNIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEE  

Here are the things that GENNIE can do for you : 
To show all the tasks in the list, please type 'list' or 'l'
To add task under TODO category, please type 'todo [description]' or 't [description]'
To add task under EVENT category, please type 'event [description] /on d/M/yyyy H:m' or 'e [description]' /on d/M/yyyy H:m
To add task under DEADLINE category, please type 'deadline [description] /by d/M/yyyy H:m' or 'd [description]' /by d/M/yyyy H:m
To MARK task, please type 'mark [index]' or 'm [index]'
To UNMARK task, please type 'unmark [index]' or 'u [index]'
To DELETE task, please type 'delete [index]' or 'del [index]'
To FIND task, please type 'find [description]' or 'f [description]'
To clear the list, please type 'delete all' or 'del all'
	Enter command: 

