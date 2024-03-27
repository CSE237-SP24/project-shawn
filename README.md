# Base-Project Overview

This document provides a detailed overview of the progression and current status of the Base-Project, highlighting the features developed, user stories addressed, and future plans for the project.

### Iteration 1: Foundational Banking Functions

#### Developments:

- **Initial Functionality:** The team focused on establishing core banking operations such as depositing and withdrawing funds, alongside creating a user interface for navigating these options.
- **Deposit and Withdraw Methods:** The deposit functionality was pre-defined, and Larry contributed the withdraw method and its unit test, ensuring these essential features were operational for a functional bank account experience.
- **User Interface Enhancements:** Jiabei enhanced the user interface, enabling users to easily access different banking operations (deposit, withdraw, and balance inquiry). This included handling invalid inputs and allowing continuous interaction until the user exits the application.
- **Preliminary Multi-User Transactions:** Dijkstra developed a framework for multi-user transactions, though it remains non-functional due to the absence of integrated bank accounts within the system.
- **Bank System and Data Persistence:** Shawn introduced a BankSystem capable of storing user data in files. This system supports account creation, login, and logout functionalities, though these features have yet to be incorporated into the main user interface. Shawn also ensured quality by reviewing pull requests and created a utility script (`script.sh`) for the project.

### Completed User Stories:

- **As a bank customer, I want to deposit money into my account so that I can securely store my earnings and have them available for future use.** This story was completed by implementing a feature allowing users to easily deposit money into their accounts through the application.

- **As a bank customer, I want to withdraw money from my account so that I can have access to my funds whenever I need them.** We've fulfilled this need by enabling users to withdraw money, ensuring they have flexible access to their funds.

- **As a potential user, I want a user-friendly interface for conducting banking operations so that I can perform my banking activities efficiently and without confusion.** A significant focus was placed on enhancing the user interface to make banking operations as intuitive and straightforward as possible.

### Upcoming User Stories:

- **As a bank customer, I want to be able to perform transactions with other users so that I can easily transfer funds to family, friends, or for services.** Implementing this feature will facilitate user-to-user transactions, enhancing the application's functionality and user satisfaction.

- **As a new user, I want to be able to create an account and easily log in or out so that I can access banking services securely and conveniently.** The development of a comprehensive user account system will address this need by providing mechanisms for account creation and management, including secure login and logout functionalities.

- **As a bank customer, I want to be able to apply for and manage a credit card through the app so that I can make payments and manage my finances more effectively.** Introducing a credit card system will enable users to apply for a credit card, manage their card details, and monitor transactions directly through the banking application, offering greater financial flexibility and control.

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
