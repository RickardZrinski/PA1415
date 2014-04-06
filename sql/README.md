## Prerequisites
You need to download the Java MySQL ODBC connector from Oracle and include the corresponding .jar file to your project.

**Download:** [Connector/J](https://dev.mysql.com/downloads/connector/j/)


## Dapper
The Dapper is a way for us to abstract away the database interaction and use native objects to create, read, update and delete content from a database. This process is known as CRUD. The Dapper also interacts with the database and assigns values to our object - ultimately acting as a object mapper.
Let's get into it:

## The Person Object

Assume you have the following Person object:
```java
public class Person {
    private String firstname;
    private String lastname;
    
    // Java reflection will handle IDs (no setter required)
    private int id;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public setFirstname(String firstname) { this.firstname = firstname; }
    public setLastname(String lastname) { this.lastname = lastname; }
    public getFirstname() { return this.firstname; }
    public getLastname() { return this.lastname; }
    public getId() { return this.id; }

    @Override
    public String toString() {
    	return String.format("Id: %s, Firstname: %s, Lastname:%s", this.id, this.firstname, this.lastname);
    }
}
```

Since we're using the Person object, we need to have "Person" table in our database. If we would like, for some reason, to add a Animal object to our database, then we need a Animal table.
The corresponding SQL structure for our Person object would be:
```SQL
CREATE TABLE IF NOT EXISTS `Person` (
  `id` int(4) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```



### Inserting a Person
In your main class, create a new instance of the Dapper. Prepare your Person object and watch the magic happen!
```java
public class Demo {
    public Demo() {
        // New instance of Dapper
        Dapper<Person> sql = new Dapper(Person.class);

        // New person
        Person person = new Person("Patrick", "Stewart");
        
        // Insert "Patrick Stewart" into the database
        sql.insert(person);

        // To view the last person in the database, do the following
        int lastIdOfPerson = sql.getLastInsertId();

        // Just print the name of the person.
        System.out.println( sql.getId(lastIdOfPerson) );

        // You can also do:
        // Person personFromDatabase = sql.getId(lastIdOfPerson);
        // System.out.println( personFromDatabase );
    }
}
```
Our table now looks like this:

id | firstname | lastname
---|-----------|---------
1 | Patrick | Stewart



### Updating a Person

The important thing to remember is that we're dealing with objects. Objects go in, objects go out. For example, to edit "Patrick Stewart":

```java
Dapper<Person> sql = new Dapper(Person.class);
        
int id = 1;

// Retrieve the person
Person person = sql.getId(id);

// Change the last name 
person.setLastname("Klepek");

// Update
sql.update(id, person); 

// This also works:
sql.update(person.getId(), person);
```


### Inserting multiple person objects

To add several people, do as you normally would:
```java
Dapper<Person> sql = new Dapper(Person.class);
        
Person[] people = new Person[]{
    new Person("Brad", "Shoemaker"),
    new Person("Jeff", "Gerstmann"),
    new Person("Vinny", "Caravella"),
    new Person("Ryan", "Davis")
};

// Iterate through each Person object and insert it into the 
// database
for(Person person: people) 
    sql.insert(person);
```

The table should now be populated and look like this:

id | firstname | lastname
---|-----------|---------
1 | Patrick | Klepek
2 | Brad | Shoemaker
3 | Jeff | Gerstmann
4 | Vinny | Caravella
5 | Ryan | Davis



### Deleting a Person
Thought experiment: How would we remove `Ryan Davis` from the table? The answer is *not* to use `sql.getId(sql.getLastInsertId())`. When we use `getLastInsertId()`, the Dapper returns the last id of the current session. If the person using the program quits out and starts the program, the `getLastInsertId()` method would return `null`. With that warning, let's take a look at how you can remove data from the table:
```java
Dapper<Person> sql = new Dapper(Person.class);

// Delete Ryan Davis, his id is 5.
sql.delete(5);
```



### Retrieving a list of Person objects.
The Dapper provides a way for you to retrieve several people using variations on the `getList()` method.

#### To retrive all person objects
```java
ArrayList<Person> all = sql.getList();

for(Person person: all) 
    System.out.println(person);
```

Output in console:
```
Id: 1, Firstname: Patrick, Lastname: Klepek
Id: 2, Firstname: Brad, Lastname: Shoemaker
Id: 3, Firstname: Jeff, Lastname: Gerstmann
Id: 4, Firstname: Vinny, Lastname: Caravella
```

#### To retrive 2 person objects
```java
ArrayList<Person> two = sql.getList(2);

for(Person person: all) 
    System.out.println(person);
```

Output in console:
```
Id: 1, Firstname: Patrick, Lastname: Klepek
Id: 2, Firstname: Brad, Lastname: Shoemaker
```

#### To retrive all person objects, sorted by lastname first:
```java
ArrayList<Person> allByLastname = sql.getList("lastname", Sort.ASC); 

for(Person person: all) 
    System.out.println(person);
```

Output in console:
```
Id: 4, Firstname: Vinny, Lastname: Caravella
Id: 3, Firstname: Jeff, Lastname: Gerstmann
Id: 1, Firstname: Patrick, Lastname: Klepek
Id: 2, Firstname: Brad, Lastname: Shoemaker
```

As briefly mentioned before, there are several variations of `getList()` method. Make sure you read the source to fully understand them.


### Counting people and truncating tables
Sometimes you need to know how many people there are in a database. For this you need to use the `count()` method. Also, while developing your app you might need to remove all entries from your table. How would one go about to do that? 

Again, a tought experiment: what would happen if we would get a list of all people, use their IDs and run the `sql.delete(int)` method on each person?

You see, using `getList()` is a costly operation. If we would have 10 000 people in our database we would have to (1) retrive the dataset, (2) map the dataset to our ArrayList and finally (3) for each Person object, call the delete operation. This would result in 10 001 calls to our database - just to truncate it! The solution to this problem is to use `truncate()`! Please note that you **should not use** `drop()` as that method removes the table **and** all of its contents!

```java
// Count people
System.out(String.format("There are %s people in our database!", sql.count());

// Truncate the table
sql.truncate();
System.out("Table truncated!");

// Count again.
System.out(String.format("Now there are %s people in our database. It's lonely here ...", sql.count());
```

Output in your console would be:
```
There are 4 people in our database!
Table truncated!
Now there are 0 people in our database. It's lonely here ...
```


# Debugging
```java
Dapper.EXECUTE_STATEMENTS = false; // Default is: true
Dapper.PRINT_STATEMENTS = true;    // Default is: false
```

Change the boolean static properties `EXECUTE_STATEMENTS` and `PRINT_STATEMENTS` to debug the database operations. Setting `EXECUTE_STATEMENTS` to `false` will not execute any operations at all (update, delete, insert, and so on ...). Setting `PRINT_STATEMENTS` to `true` will print all the statements out to the console. For example, having both set to `true` will execute all statements and print them to the console:

```java
sql.insert(new Person("Topper", "Harley"));
```

Output:
```
INSERT INTO Person (firstname, lastname) VALUES('Topper', 'Harley');
<inserted into database>
```


# Known bugs
Some type-fields such as `YEAR`, `SMALLINT` and `TINYINT` do not work. This a known limitation in the java.sql API. Use `INT(...)` instead.
