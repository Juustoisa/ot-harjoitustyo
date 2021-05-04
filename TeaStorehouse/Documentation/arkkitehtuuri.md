The application has layered architecture, the three layers are UI made primarily with javaFX, controller layer which talks between UI and Database, ensuring validations and data formats. And finally the database ensuring data persistence powered by SQLite.


![architecture chart](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/arkkitehtuuri.jpg)



---
### UI

The final UI will consists of 4 different views:
1. Listing teas   **done**
2. Adding teas    **done**
3. Listing notes
4. Adding notes

Views can be shown one at a time, and the active view is assigned to javaFx stage.
![Main menu](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/Assets/Mainmenu.png)

---
### Logic
User interacts only with the UI layer, UI calls on either teaController or noteController.class methods which in-turn interact with the database.class.
Examples of teaController methods: 
- ArrayList<String> getAll() Which returns all teas from the database. 
- Boolean addTea(String[] userInput) which validates userinput and saves valid inputs as a tea to the database.
noteController will get similar methods for listing all notes, listing single tea related notes and adding new notes.

As for SQL relations, Teas can have any number of notes, but all notes must associate only to 1 tea.

#### Example of adding a new tea:
![add a tea diagram](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/SequenceDiagramAddTea.png)

---
