
import 'package:flutter/material.dart';
import '../models/Message.dart';
const String backendURL = 'team-margaritavillians.dokku.cse.lehigh.edu';

void main() {
  runApp(MyApp());
}

/*
Stateful Widgets can maintain and update their internal state.
They are used when you need to create interactive UI elements that change 
in response to user input or data changes.
The setState method is used to trigger a rebuild of the widget 
when the internal state changes.
*/
class MyApp extends StatefulWidget {
  const MyApp({super.key});
  @override
  _MessageAppState createState() => _MessageAppState();
}

class _MessageAppState extends State<MyApp> {
  final List<Message> messages = [];
  bool userIsLoggedIn = true;
  //URL of Backend

  @override
  Widget build(BuildContext context) {
    return MaterialApp( //Provides basic app structure
      home: Scaffold( //represents the overall structure of the app
        appBar: AppBar(
          title: const Text(
            'Anonymous Posts',
            style: TextStyle(
              fontFamily: 'San Francisco',
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
                      //https://dart.dev/codelabs/async-await
                     //this is always asynchronous because the user can like or unlike a message
                    onLike: () async {
                      final message = messages[index];
                      // Increment the likes for the message
                      setState(() {
                        message.isLiked = !message.isLiked;
                        if (messages[index].isLiked) {
                          messages[index].likes++;
                        } else {
                          messages[index].likes--;
                        }
                      });
                    }
                  );
                },
              ),
            ),
            // Add a new message to the list
            MessageInput(onMessageAdded: (messageText) {
              //Create a new message with likes equal to 0, and isLiked = false
              final newMessage = Message(
                text: messageText, 
                likes: 0, 
                isLiked: false
              ); 
              //Updating the widgets state when adding a new message
              setState(() {
                messages.add(newMessage); //add to the list
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

  const MessageTile({required this.message, required this.onLike});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.all(8.0),
      child: ListTile(
        title: Text(message.text),
        subtitle: Text('Likes: ${message.likes}'),
        trailing: LikeButton(
          isLiked: message.isLiked,
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

  LikeButton({required this.isLiked, required this.onPressed});

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

  MessageInput({required this.onMessageAdded});

  @override
  _MessageInputState createState() => _MessageInputState();
}

class _MessageInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(8.0),
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



