# TPO
Second year Java EE course in [Polish Japanese Polish-Japanese Academy of Information Technology](https://www.pja.edu.pl)

## NIO
Non blocking FileChannel
Program using Java NIO. Program included FileChannel, ByteBuffer and RandomAccessFile

### WriterFile : Runnable
* Adds two random ints to the file
* Sets the first int to 1 (readers sigal that the file is ready to read)
* Waits for the reader to process information

### ReaderFile : Runnable
* Read the file 
* Adds random ints and outputs the result to the console
* Sets the first int to 2 (ready to write)

## NIO_Server_Client

Custom server using Java NIO. Handles multiple clients. Reads from them and output/send the respond to the client.

### NIO/NET used:

* ByteBuffer
* ServerSocetChannel
* ServerSocket
* Selector

### Server 

* Waits for clients
* Reads the clients message when ready
* Distinguishes client' want: 
  * Echo clients message (Log to server conslole)
  * Adds integer and sends the response to the client 

### Client 
* Writes to server 
* Supported commands: 
  * add _numbers to add together_
    * waits for the server response and outputs it as LOG to the console
  * echo _message_
  * close




