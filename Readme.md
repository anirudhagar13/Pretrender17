#Pretrender

# What is it:

	> A java based application to predict trend in entities on an upcoming date by leveraging entities trend in the past.
	> User can either enter a hashtag or someone's twitter ID to analyze their tweets for its past sentiment trend.
	> This trend is then used to plot a pattern which is extrapolated on the future date fed by the user.
	> The slope on the future date is then compared for similar slope patterns in the past, which is given as output prediction.
	> This is based on the concept that trend pattern for entities tend to repeat.
	> UI can either be through java application or by installing apk in the repo.
	> Can goto files folder inside src and tweets and their sentiment.

# How to run:

	> Import Server folder as eclipse General/Existing Projects into workspace
	> Get all JAR files in JARs folder as external libraries
	> Also get 'stanford-corenlp-3.5.2-models.jar' as file is too big to upload on repo
	> Get various developer keys from ur twitter profile - '' and mention them in twitter4j.properties file in src
	> Run client.java inorder to access platform through system
	> Run server.java to interact with system using phone application
	> Pretrender2 folder has phone application that can edited in Android Studio