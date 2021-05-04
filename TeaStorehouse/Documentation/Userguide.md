## User guide

### Steps

#### 1.Option 1 Clone & mvn compile
In a location of your choice:
```console
$ git clone git@github.com:Juustoisa/ot-harjoitustyo.git
```
In TeaStorehouse folder:
```
$ mvn compile exec:java -Dexec.mainClass=teastorehouse.Main
```

#### 1.Option 2 Download jar & java -jar


Download a release jar from repository and then in the folder containing jar:

```console
$ java -jar <|jarfilename|>.jar
```

#### 2. Main Menu
After starting the application with either of the 2 options user arrives to main menu:

![Main menu](www.placeholderstringforurl.com)


#### 3. Two functionalities currently work, adding tea and listing added teas.
Clicking "browse teas" lands user in Tea List View, for first time user the list is empty:

![Empty tea list](www.placeholderstringforurl.com)


Clicking "Add a new tea" lands user in Add Tea View:

![Add a new tea](www.placeholderstringforurl.com)

From here the user can input information about the tea, values marked with * are mandatory:
Fields 4-6 can be empty.

 1. Tea name*
 2. Tea type*
 3. Score for tea*    * - needs to be a number.*
 4. Price for tea 	*- needs to be a number.*
 5. Amount of the tea the user has *- needs to be a number.*
 6. How much tea gets consumed at one time of use *- needs to be a number.*
 
 After submitting a valid tea user is greeted with a popup informing them that the tea was successfully added.
 User is offered an option to create a note for the added tea, but this functionality doesn't yet work so choosing either has the same outcome.

![New tea added](www.placeholderstringforurl.com)

Now when returning to main menu and clicking "Browse teas" user can see newly added tea:

![New tea in list](www.placeholderstringforurl.com)
