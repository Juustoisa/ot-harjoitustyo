# Tea Storehouse

A tea organizing app in the making.

![enter image description here](https://image.flaticon.com/icons/png/512/112/112430.png)

**As of 13.04:**
Project has been started, there's some basic skeletons in place, mut not much functionality yet. By choosing to add a new tea to the database a placeholder will be saved and all the saved placeholders can be listed. The ui is text-based for now.

## Documentation

[Requirement specification](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/Vaatimusmaarittely.md)

[Record of working hours](https://github.com/Juustoisa/ot-harjoitustyo/blob/master/TeaStorehouse/Documentation/Tuntikirjanpito.md)

_...records will get translated in the near future... maybe..._

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

$ mvn compile exec:java -Dexec.mainClass=TeaStorehouse.Main

```

#### 3. Tests

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
