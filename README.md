# JavaClientServer
## General Info
These programs demonstrate the concept of communication between a server and a client using sockets. 
The program was developed for educational purposes, hence it showcases a simple message exchange.

## Getting Started
1. Clone or download the program files from the reposityory.
2. Open the Java development environment (to write this program i used IntelliJ IDEA)
3. Compile and run the `Server` program first.
4. Compile and run the `Client` program after the server is running.

## Program Description
### Client
The `Client` class establishes a connection with the server and exchanges messages using sockets. Here are the main steps performed by the `Client`:

1. Connects to the server using the specified host (`localhost`) and port (`12345`).
2. Receives a "ready" message from the server.
3. Prompts the user to enter the value of `n`.
4. Sends the value of `n` to the server.
5. Receives a "ready for n" message from the server.
6. Sends `n` instances of the `Message` class to the server.
7. Closes the client socket after receiving the "finished" message.

### Message
The `Message` class is a simple serializable class used to represent messages exchanged between the client and server. 
Each `Message` object has two properties: `number` (an integer) and `content` (a string). 
These properties are used to store the number and content of each message.

### Server
The `Server` class listens for client connections on a specified port (`12345`). Here are the main steps performed by the `Server`:

1. Starts the server and waits for client connections.
2. Accepts a client connection and creates a separate thread to handle the client.
3. Sends a "ready" message to the client.
4. Receives the value of `n` from the client.
5. Sends a "ready for n" message to the client.
6. Receives `n` instances of the `Message` class from the client.
7. Prints the received messages.
8. Sends a "finished" message to the client.
9. Closes the client socket after all communication is completed.

## Troubleshooting
- Ensure that the host and port in the `Client` match the server's host and port.
- Make sure the server is running before starting the client.
- Check for any error messages or exceptions printed in the console, which may indicate issues with the program.
