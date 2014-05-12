## Contents
1. [Getting started](#getting-started)
2. [Conventions](#conventions)
3. [Setting up your GitHub account with IntelliJ](#setting-up-your-github-account-with-intellij)
4. [Creating a main()-method in IntelliJ](#creating-a-main-method-in-intellij)
5. [Creating a new test configuration in IntelliJ](#creating-a-main-method-in-intellij)
6. [Creating a test in IntelliJ](#creating-a-test-in-intellij)
7. [Adding a library to your IntelliJ project](#adding-a-library-to-your-intellij-project)
8. [Adding a test library](#adding-a-test-library)
8. [Known issues](#known-issues)


### Getting started

* Download [Java JDK 8.0](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) for your operating system
* All guides are written for IntelliJ IDEA, go ahead and download [IntelliJ Community Edition ](http://www.jetbrains.com/idea/download/) from [JetBrains](http://www.jetbrains.com).

### Conventions
* When you're about to start programming, download the latest version from the repository and begin working. **You might have an outdated version on your workstation!**
* Always add a commit text which describes WHY you changed the file, i.e "balance is not double instead of float"
* When commiting: Test it before commiting to the repository
* If you change 1 file: Describe why you changed that file and commit it
* If you change X files: Create 1 commit **for each** file and commit, for example:
  * `A.java`: "Changed float A to double A"
  * `B.java`: "Changed float B to double B", etc. 


### Setting up your GitHub account with IntelliJ

**If this is the first time you start IntelliJ:**

You will be greeted with a "Welcome to IntelliJ IDEA" screen. In the Quick Start pane, select `Check out from Version Control`.

**_If you already have tried IntelliJ or opened another project:**

Go to `File`, select `Close Project`. The "Welcome to IntelliJ IDEA" screen should be visible. In the Quick Start pane, select `Check out from Version Control`.

1. Select `GitHub`
2. Use your GitHub login and password
3. You will be asked to create a master password to encrypt your GitHub login and password. This password can be the same as your real GitHub password
4. Select `PA1415.git` (https://github.com/devinant/PA1415.git) and click on `Test` to see if it actually works.
5. Select the `Parent Directory` and `Directory Name` and press `Clone`
6. Select `Yes` on the question `Would you like to create an IDEA project for the sources you have checked out `<folder-you-selected-on-step-5>`"
7. Select "Create project from existing sources".
8. Press `Next` until you get asked which version of Java you'd like to use. Select Java 1.8

### Creating a main()-method in IntelliJ

IntelliJ can differentiate between several `public static void main()`. This is known as `Configurations` Let's assume you have a class called `A` and a class called `B` that both have implemented `public static void main()`. How to only fire the main method of class A? Do the following:

1. Go to `File`, select `Edit Configurations`
2. Press on the `+` button
3. Select `Application`
4. In the pane to the right add following:
  * Name: Testing class A
  * Main Class: A
5. Press on `Apply` and `OK` to confirm
6. Do the same for Class B.
7. You will now have two configurations, one called "Testing class A" and one called "Testing class B", select which configuration you'd like to test and press on the green `Play` button. 


### Creating a new test configuration in IntelliJ

1. Go to `File`, select `Edit Configurations`
2. Press on the `+` button
3. Select `JUnit`
4. In the pane to the right add following:
  * Test kind: Package
  * Package: `name of the package to test`, i.e `tests.users` (this will test package called `user`)
5. Press on `Apply` and `OK` to confirm

### Creating a test in IntelliJ

1. Go to the file, you'd wish to test. For our purpose, let's go to `Account` under package `users`
2. Put your mouse cursor in the class declaration (where it says `public class Account {}`)
3. Press `ALT+Enter` and select `Create Test` and press `Enter`
4. In the "Create Test" dialog, select:
  * Testing Library: JUnit4
  * Class name: AccountTest
    Note: If you test a class called MyClass, then the Class name should be MyClassTest
  * Destination package: tests.users
  * Generate `Set Up/@Before` and `tearDown/@After`
  * Generate test methods for: `deposit`, `withdraw` and `isWithdrawable`
5. Press `OK`, AccountTest should now be generated!

To understand how testing with JUnit works:

* [Read more about testing in IntelliJ](http://www.jetbrains.com/idea/webhelp/testing.html)
* [Unit Testing With JUnit - Tutorial](http://www.vogella.com/tutorials/JUnit/article.html)

#### Adding a library to your IntelliJ project
This example will add the MySQL Connector/J jar to the project

1. Press `Cmd+;` or click on the Project Structure icon
![Step 1](https://photos-4.dropbox.com/t/0/AAC6MFZM9zWg3QK-LL0-IXeL1E1v7erZ4eesFi7qGgQIyg/12/1195895/png/2048x1536/3/1399834800/0/2/1.png/1HEIbGM0m3pTIuXqdLFAGlFzTKVX3o-GfVRzbdHKgfw)
2. In the project settings menu to the left, press `Libraries`
![Step 2](https://photos-5.dropbox.com/t/0/AACTk-WRePITH7ZkXtLqPI93SkX54nybiGDKjgfXO2up7g/12/1195895/png/2048x1536/3/1399834800/0/2/2.png/yguwHP2iKfevbltE_ZVnqdm4iX-HJQTbSMNw3U4-bv8)
3. Click the `+` icon and select `Java`
![Step 3](https://photos-4.dropbox.com/t/0/AAAj-4QadkEAUAp3VXmOw9hrXA_f1s-Z5KZgz7nPNp85bw/12/1195895/png/1024x768/3/1399834800/0/2/3.png/xHUNDVMZD4gQLxkqQgPzUkKe8Uk8K9VKaH2kD4KyRqA)
4. Browse to the folder where `mysql-connector-java-5.1.30-bin.jar` is located
![Step 4](https://photos-5.dropbox.com/t/0/AADkC9ZUR2SYAwnBeDtnoe8BBKftcOmmP-ScWaROvRAmLg/12/1195895/png/1024x768/3/1399834800/0/2/4.png/gYHXuiTPQDd24qgXM0l8s6ix_zQNxl9VpyrQ-BUSjlw)
5. Click on OK
![Step 5](https://photos-1.dropbox.com/t/0/AAAYFnWvPqD5clvB3Ify14ZpGry69KMeBWKs3KBFeqIVIg/12/1195895/png/1024x768/3/1399834800/0/2/5.png/_IDrHHYbrhuvkshYU4FlG-KT-3B5C94AjWCORp7HChI)

#### Adding a test library

This process is similar to the one described above. We will use JUnit 4 for testing.

1. Go to `File`, select `Project Structure`
2. In the pane (left) labeled "Project Settings", select `Libraries`
3. Click the `+` icon and select `Java`
4. Go to the folder where IntelliJ was installed, on a Mac: `/Applications/IntelliJ IDEA 13 CE.app/`
5. There should be a folder called `lib`, select it
6. Find `junit-4.10.jar`
7. Press `Apply` and `OK`

### Known Issues
#### Java: Diamond operator is not supported in -source 1.6 (use -source 7 or higher to enable diamond operator)
The diamond operator `<>` was added to Java 7 and is used to express generics without having to define them twice, for example: `Collection<String> c = new Collection<>()`. Prior to Java 7 you had to write `Collection<String> c = new Collection<String>()`. IntelliJ uses Java 6 per default. To resolve this issue:

1. Go to `File`, select `Project Structure`
2. In the pane (left) labeled "Project Settings", select `Project`
3. In the pane (right) labeled "General Settings for Project `Your Project Name`" under heading `Project language level` select `8.0 - Lambdas, type annotations etc.`
4. Press `Apply` and `OK`
