# Tea Storehouse

A tea organizing app in the making.

![enter image description here](https://image.flaticon.com/icons/png/512/112/112430.png)

**As of 20.04:**
Project has been changed to use GUI, took longer than expected, ability to add teas to database has been added. Checkstyle has been implemented. Pretty slow progress for now.

**As of 13.04:**
Project has been started, there's some basic skeletons in place, mut not much functionality yet. By choosing to add a new tea to the database a placeholder will be saved and all the saved placeholders can be listed. The ui is text-based for now.

## Documentation

[Requirement specification](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/Vaatimusmaarittely.md)

[Record of working hours](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/Tuntikirjanpito.md)

[Architecture](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/arkkitehtuuri.md)

## User guide

### Steps

#### 1. Clone

In a location of your choice:

```console

$ git clone git@github.com:Juustoisa/ot-harjoitustyo.git

```

#### 2. To Run

In TeaStorehouse folder:

```console

$ mvn compile exec:java -Dexec.mainClass=teastorehouse.Main

```

#### 3. To generate jar and run it

In TeaStorehouse folder:

```console

$ mvn package

```

To run jar

```console

$ cd target/ && java -jar TeaStorehouse.jar

```

#### 4. Tests

To test enter

```console

$ mvn test

```

In TeaStorehouse folder

---

For test coverage report enter:

```console

$ mvn jacoco:report

```

In TeaStorehouse folder

---

For checkstyle report enter:

```console

$ mvn jxr:jxr checkstyle:checkstyle

```

In TeaStorehouse folder
