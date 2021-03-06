# MassManager



Version 1.0. (28.4.2021)

Developer in charge: Oskari Arpinen, oskari.arpinen@student.lut.fi

Contents:
    1. User interface
    2. AccountManager & Account
    3. FileReadAndWrite
    4. PasswordHasher
    5. BmiCalculator
    6. CarbonEmissionsAPI
    7. Data classes
    8. User data storing

1. User interface

MassManager consists of 3 different views. Login view is the first one to open, where the user has
the option to either login to a existing account or to create a new account. Second view is for
creating a account. User must give the application their name, password which is atleast 12
characters long, weight, height and weekly activity goal (min/week). Once done, new account is
created by pressing the create account button. Application will the take the user back to the login
screen, in which the user must login using their new credentials. After inputting correct credentials
user is let to the third and final view. In this view, the user has the ability to give recent weight
measurements to the software, input activity, check on their recent weights. This information is
logged to txt files, and can be accessed every time at login.


2. AccountManager and Account

AccountManager is responsible for interacting with the Account data structure, which is used to
store vital data to the functions of this application. AccountManager has multiple attributes, including
Accoutn itself, context of the application for reading/writing and a hasmap of users. Account is used
to store everything as the application is being used.


3. FileReadAndWrite

FileReadAndWrite is resposible for writing and reading the files containing data vital to the sofware
It has methods for writing user data files, user weight data files, user activity files and methods
for reading these files too. FileReadAndWrite is user mostly for every read and write, apart from
one read method in Fragment_login file, where users stored hash password is fetched from file


4. PasswordHasher

PasswordHasher has one method, which is used to convert plaintext passwords to SHA-256 hashes. Salting
is yet to be implemented in this patch, so application is vunerable for dictionary attacks at the moment.
This is low concern right now, since the app is only using local resources. Hashing is done using
the MessageDigest class found in java.security package. This application never stores password in
plain text.


5. BmiCalculator

BmiCalculator is simply used to calculate users BMI using given height and weight of the user. The
formula for BMI is Weight in kg divided by height squared. Math library is used to do the calculations


6. CarbonEmissionsAPI

CarbonEmissionsAPI is used as a interface between the application and Ilmastodietti API, which
calculates emissions based on travelled kilometers using public transport. In the application user
is able to see, how much carbon dioxide emissions have been avoided by walking cycling instead of
using public transport. This class sends request to the server in JSON format.


7. Data classes
In addition to already discussed Account data class, there are two more: ActivityPoint and WeightPoint.
These two are similar data structures. Both of them include a timestamp in Epoch format. ActivityPoint
also has activity amount that the user has input at that timestamp. WeighPoint has weight data.
Epoch data is used to track the activity/weight development as a function of time.


8. User data storing

User data is stored in plain text files, which are formatted to function as CSV files. Every account
has three files: account file, weight data file and activity data file. These files are created at
the time that the account is created
