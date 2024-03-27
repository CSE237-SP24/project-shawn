# Base-Project Overview

This document provides a detailed overview of the progression and current status of the Base-Project, highlighting the features developed, user stories addressed, and future plans for the project.

### Iteration 1: Foundational Banking Functions

#### Developments:

-  **Initial Functionality:** The team focused on establishing core banking operations such as depositing and withdrawing funds, alongside creating a user interface for navigating these options.
-  **Deposit and Withdraw Methods:** The deposit functionality was pre-defined, and Larry contributed the withdraw method and its unit test, ensuring these essential features were operational for a functional bank account experience.
-  **User Interface Enhancements:** Jiabei enhanced the user interface, enabling users to easily access different banking operations (deposit, withdraw, and balance inquiry). This included handling invalid inputs and allowing continuous interaction until the user exits the application.
-  **Preliminary Multi-User Transactions:** Dijkstra developed a framework for multi-user transactions, though it remains non-functional due to the absence of integrated bank accounts within the system.
-  **Bank System and Data Persistence:** Shawn introduced a BankSystem capable of storing user data in files. This system supports account creation, login, and logout functionalities, though these features have yet to be incorporated into the main user interface. Shawn also ensured quality by reviewing pull requests and created a utility script (`script.sh`) for the project.

#### Completed User Stories:

1. Users can deposit money into their account.
2. Users can withdraw money from their account.
3. A user-friendly interface for conducting banking operations.

#### Upcoming Goals:

-  Implement transactions between users.
-  Develop a comprehensive user account system, enabling account creation, and login/logout functionalities.
-  Introduce a credit card system to the banking application.

#### Currently Non-Operational Features:

1. Bank account system.
2. Transaction system between users.

#### How to Run the Code:

For manual compilation and execution:

```shell
cd src
javac bankapp/*.java
java bankapp.Menu
```

Alternatively, utilize the provided script for ease of use:

```shell
./script.sh
```

