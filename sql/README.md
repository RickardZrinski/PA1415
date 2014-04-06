## Prerequsites
You need to download the Java MySQL ODBC connector from Oracle and include the corresponding .jar file to your project.

[Download Connector/J](https://dev.mysql.com/downloads/connector/j/)


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
    public getId() { return this.id }

    @Override
    public String toString() {
    	return String.format("id: %s, firstname: %s, lastname:%s", this.id, this.firstname, this.lastname);
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

The important thing to remember is that we're dealing with objects. Objects go in, objects go out. For example, to edit "Patrick Stewart":
```java
public class Demo1 {
    public static void main(String[] args) {
        // ...
        int id = 1;

        // Retrieve the person
        Person firstPerson = sql.getId(id);
        
        // Change the last name 
        firstPerson.setLastname("Klepek");
        
        // Update
        sql.update(id, firstPerson); 
    }
}
```

To add several people, do as you normally would:
```java
public class Demo2 {
    public static void main(String[] args) {
        // ...
        Person[] people = new Person[4];
        people[0] = new Person("Brad", "Shoemaker");
        people[1] = new Person("Jeff", "Gerstmann");
        people[2] = new Person("Vinny", "Caravella");
        people[3] = new Person("Ryan, "Davis");
    
        for(Person person: people) 
            sql.insert(person);
    }
}
```

The table should now be populated and look like this:
id | firstnam | lastname
-- | --------- | --------
1  | Patrick   | Klepek
2  | Brad      | Shoemaker
3  | Jeff      | Gerstmann
4  | Vinny     | Caravella
5  | Ryan      | Davis

The dapper also allows you to remove fields from the table. However, we won't go into it here - see the source for more information.

## Known bugs
Some type-fields such as YEAR, SMALLINT and TINYINT do not work. This a known limitation in the java.sql API. Use INT(X) where X is your range instead.
