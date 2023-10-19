import '../net/webRequests.dart';
import 'package:flutter/material.dart';
import '../models/Message.dart';
import 'package:flutter/services.dart';
import 'dart:io';

const String backendURL = 'http://team-margaritavillians.dokku.cse.lehigh.edu';
//ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set 2023fa-tutorial-sml3 CORS_ENABLED=true'
// ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set 2023fa-tutorial-sml3 CORS_ENABLED=false'
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  ByteData data = await rootBundle.load('lib/assets/ca/lets-encrypt-r3.pem');
  SecurityContext.defaultContext.setTrustedCertificatesBytes(data.buffer.asUint8List());
  runApp(const MyApp());
}

/*
Stateful Widgets can maintain and update their internal state.
They are used when you need to create interactive UI elements that change 
in response to user input or data changes.
The setState method is used to trigger a rebuild of the widget 
when the internal state changes.
*/
/*class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  MessageAppState createState() => MessageAppState();
}
*/
/*
class MessageAppState extends State<MyApp> {
  final List<Message> messages = [];
  bool userIsLoggedIn = true;
  List<bool> likeStatus = [];
  //URL of Backend
  @override
  void initState() {
    super.initState();
    retrieveMessages(); // Retrieve messages when the app starts
  }

  void retrieveMessages() async {
     try {
      final fetchedMessages = await getWebData();
      setState(() {
        messages.addAll(fetchedMessages);
        likeStatus = List.filled(messages.length, false); // Initialize likeStatus list based on the number of messages
      });
    } catch (e) {
      ('Error retrieving messages: $e');
    }
  }

  void addNewMessage(String messageText) async {
    try {
      // Add the new message to the messages list
      final newMessage = Message(mTitle: "title", mMessage: messageText, mLikes: 0); // You might need to adjust this based on your Message model
      setState(() {
        messages.insert(0, newMessage); // Add the new message to the beginning of the list
        likeStatus.insert(0, false); // Initialize likeStatus for the new message
      });
      // Send the new message to the server
      await addMessage(messageText);
    } catch (e) {
      ('Error adding a new message: $e');
    }
  }

  void addLike(int index) async {
    final message = messages[index];
    message.mLikes = message.mLikes + 1;
    // Now toggle the like on the server
    await toggleLike(message);
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //Provides basic app structure
      home: Scaffold(
        //represents the overall structure of the app
        appBar: AppBar(
          title: const Text(
            'Anonymous Posts',
            style: TextStyle(
              color: Color.fromARGB(255, 0, 43, 117),
              fontSize: 24.0,
              fontWeight: FontWeight.bold,
            ),
          ),
          backgroundColor: const Color.fromARGB(255, 179, 187, 255),
        ),
        body: Column(
          children: [
            Expanded(
              //Displays the list of messages
              child: ListView.builder(
                itemCount: messages.length,
                itemBuilder: (BuildContext context, int index) {
                  return MessageTile(
                    message: messages[index],
                    isLiked: likeStatus[index],
                    onLike: () {
                      addLike(index);
                    },
                  );
                },
              ),
            ),
            // Add a new message to the list
            MessageInput(onMessageAdded: addNewMessage,),
          ],
        ),
      ),
    );
  }
}

class MessageTile extends StatelessWidget {
  final Message message;
  final bool isLiked;
  final VoidCallback onLike;

  const MessageTile(
      {required this.message, required this.onLike, required this.isLiked, Key? key,}):super(key: key);
  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.all(8.0),
      child: ListTile(
        title: Text(message.mMessage),
        subtitle: Text('Likes: ${message.mLikes}'),
        trailing: LikeButton(
          isLiked: isLiked,
          onPressed: onLike,
        ),
      ),
    );
  }
}

//Make a heart that fills in when liked and unfills if unliked, also keep
//track of the likes from all users
class LikeButton extends StatelessWidget {
  final bool isLiked;
  final VoidCallback onPressed;
  const LikeButton({required this.isLiked, required this.onPressed,Key?key,}):super(key: key);

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: Icon(
        isLiked ? Icons.favorite : Icons.favorite_border,
        color: Colors.pink,
      ),
      onPressed: onPressed,
    );
  }
}

class MessageInput extends StatefulWidget {
  final ValueChanged<String> onMessageAdded;

  const MessageInput({required this.onMessageAdded, Key?key}): super(key: key);

  @override
  MessageInputState createState() => MessageInputState();
}

class MessageInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          Expanded(
            child: TextField(
              controller: _textController,
              decoration: const InputDecoration(
                hintText: 'Enter a message...',
              ),
            ),
          ),
          IconButton(
            icon: const Icon(Icons.send),
            onPressed: () {
              final messageText = _textController.text;
              if (messageText.isNotEmpty) {
                widget.onMessageAdded(messageText);
                _textController.clear();
              }
            },
          ),
        ],
      ),
    );
  }
}
*/
class MyApp extends StatefulWidget {
  const MyApp({super.key});
  @override
  MessageAppState createState() => MessageAppState();
}

class MessageAppState extends State<MyApp> {
  final List<Message> messages = [];
  @override
  void initState() {
    super.initState();
    fetchData(); // Call the function to fetch initial data
  }
  Future<void> fetchData() async {
    try {
      final data = await getWebData();
      setState(() {
        messages.addAll(data);
      });
    } catch (error) {
      // Handle errors if needed
      ('Error fetching data: $error');
    }
  }
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Anonymous Message Board'),
        ),
        body: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: messages.length,
                itemBuilder: (BuildContext context, int index) {
                  return MessageTile(
                    message: messages[index],
                    onLike: () {
                      // Increment the likes for the message
                      setState(() {
                        messages[index].mLikes++;
                      });
                    },
                  );
                },
              ),
            ),
            MessageInput(onMessageAdded: (messageText) {
              // Add a new message to the list
              final newMessage = Message(mTitle: "new message", mMessage: messageText, mLikes: 0);
              setState(() {
                messages.add(newMessage);
              });
            }),
          ],
        ),
      ),
    );
  }
}

class MessageTile extends StatelessWidget {
  final Message message;
  final VoidCallback onLike;

  MessageTile({required this.message, required this.onLike});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(8.0),
      child: ListTile(
        title: Text(message.mMessage),
        subtitle: Text('Likes: ${message.mLikes}'),
        trailing: IconButton(
          icon: const Icon(
            Icons.favorite,
            color: Colors.pink),
          onPressed: onLike,
        ),
      ),
    );
  }
}

class MessageInput extends StatefulWidget {
  final ValueChanged<String> onMessageAdded;
  MessageInput({required this.onMessageAdded});
  @override
  MessageInputState createState() => MessageInputState();
}

class MessageInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          Expanded(
            child: TextField(
              controller: _textController,
              decoration: const InputDecoration(
                hintText: 'Enter a message...',
              ),
            ),
          ),
          IconButton(
            icon: const Icon(Icons.send),
            onPressed: () {
              final messageText = _textController.text;
              if (messageText.isNotEmpty) {
                widget.onMessageAdded(messageText);
                _textController.clear();
              }
            },
          ),
        ],
      ),
    );
  }
}