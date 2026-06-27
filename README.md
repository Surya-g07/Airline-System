# ✈️ Airline Reservation System (Java Console Application)

## 📌 Project Overview
A Java console-based Airline Reservation System developed using Core Java, JDBC, and MySQL. The application allows users to manage airline reservations through a simple menu-driven interface.

## ✨ Features
- View available flights
- Book flight tickets
- Cancel booked tickets
- View all reservations

## 🗄️ Database Setup
1. Create a MySQL database named `airline`.
2. Create the following tables:
   - `flights`
   - `reservations`
3. Update the database credentials in:
   ```
   src/db/DBConnection.java
   ```
   if required.

## 📦 MySQL JDBC Driver
Place the MySQL Connector JAR file inside the `lib` folder.

```
lib/
└── mysql-connector-j-<version>.jar
```

## ▶️ Compile the Project

```bash
javac -cp "lib/*;src" src\db\DBConnection.java src\dao\AirlineDAO.java src\model\Flight.java src\model\Reservation.java src\service\AirlineService.java src\AirlineReservationSystem.java
```

## ▶️ Run the Project

```bash
java -cp "lib/*;src" AirlineReservationSystem
```

## 🛠️ Technologies Used
- Core Java
- JDBC
- MySQL
- VS Code