# Java Client-Server Chat Application

This is a simple Java-based client-server chat application that allows multiple clients to connect to a server and exchange messages. The application uses `Socket` programming for network communication and `Swing` for the user interface.

## Features

- **Multiple Clients:** The server can handle multiple clients simultaneously.
- **Text-Based Chat:** Clients can send and receive text messages in real-time.
- **Graphical User Interface (GUI):** The application uses Java Swing for the GUI.
- **Threading:** Each client connection is handled in a separate thread for concurrent communication.

## Prerequisites

- **Java Development Kit (JDK):** Ensure that JDK 8 or higher is installed on your system.

## How to Run

### Server

1. Compile the server code:
   ```bash
   javac Main.java
2 . Run the server:
```bash
  java Main
```
The server will start listening for client connections on a specified port.
Client
Compile the client code:
3.Compile Client.java
```bash
javac Client.java

```
Run the client:

```bash
java Client
```

A GUI window will appear allowing you to send and receive messages.
