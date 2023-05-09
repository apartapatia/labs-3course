# Chat Application

This is a simple chat application built using Java sockets. It consists of two classes: `Client` and `Server`.

## Client

The `Client` class is responsible for sending messages to the server. It takes in the server's hostname and port number as parameters when created.

When the `start` method is called, the client creates a UDP socket and sends a message to the server indicating that a new user has joined the chat. The client then prompts the user to enter a nickname and sends all subsequent messages with the nickname appended to the beginning of the message.

The client listens for user input and sends any messages entered by the user to the server until the user enters the command "/exit" to leave the chat. When the user leaves, the client sends a message to the server indicating that the user has left.

## Server

The `Server` class is responsible for receiving messages from clients and broadcasting them to all connected clients. It takes in a port number as a parameter when created.

When the `start` method is called, the server creates a UDP socket and listens for incoming messages from clients. When a message is received, the server extracts the nickname of the sender from the message and broadcasts the message to all connected clients.

The server also prints out messages indicating when new clients connect and when clients leave the chat.

## Usage

To use the chat application, start the server by running the `Server` class. Then, start one or more clients by running the `Client` class with the hostname and port number of the server as parameters.

Once a client is connected, enter a nickname when prompted and start chatting with other clients connected to the server.

To exit the chat, enter the command "/exit".