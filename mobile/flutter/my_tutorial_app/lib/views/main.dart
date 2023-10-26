import '../net/webRequests.dart';
import 'package:flutter/material.dart';
import '../models/Message.dart';
import 'package:flutter/services.dart';
import 'dart:io';
import 'package:google_fonts/google_fonts.dart';

//deleting duplicate files: find . -type f -name '* [0-9]' -exec rm {} +, find . -type f -name '* [0-9].*' -exec rm {} +
const String backendURL = 'http://team-margaritavillians.dokku.cse.lehigh.edu';
//ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set 2023fa-tutorial-sml3 CORS_ENABLED=true'
// ssh -i ~/.ssh/id_ed25519 -t dokku@dokku.cse.lehigh.edu 'config:set 2023fa-tutorial-sml3 CORS_ENABLED=false'
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  ByteData data = await rootBundle.load('lib/assets/ca/lets-encrypt-r3.pem');
  SecurityContext.defaultContext
      .setTrustedCertificatesBytes(data.buffer.asUint8List());
  runApp(const MyApp());
}

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

  Future<void> toggleLike(Message message) async {
    // Increment the likes for the message
    setState(() {
      message.mLikes++;
    });
    try {
      await addLikes(message);
    } catch (error) {
      ('Error updating likes: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text(
            'Margarita Villians',
            style: GoogleFonts.amaticSc(
              fontWeight: FontWeight.w700,
              fontSize: 40.0, // Set the font size 
              color: const Color.fromARGB(255, 219, 245, 253), // Set the text color
            ),
          ),
          backgroundColor: const Color.fromARGB(255, 103, 150, 112)
        ),
        body: Column(
          //allows for scrolling
            children: [
              Expanded(
                child: ListView.builder(
                  shrinkWrap: true, // Important to prevent renderFlex error
                  itemCount: messages.length,
                  itemBuilder: (BuildContext context, mId) {
                    return Card(
                      color: const Color.fromARGB(255, 103, 150, 112),
                      margin: const EdgeInsets.all(8),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: MessageTile(
                        message: messages[mId],
                        onLike: (message) {
                          setState(() {
                            message.mLikes++;
                          });
                          addLikes(message);
                        },
                      ),
                    );
                  },
                ),
              ),
              Container(
                alignment: Alignment.bottomCenter,
                child: MessageInput(onMessageAdded: (messageText) {
                // Add a new message to the list
                  final newMessage = Message(
                    mTitle: "new message",
                    mMessage: messageText,
                    mLikes: 0,
                    mId: 0,
                  );
                  setState(() {
                    messages.add(newMessage);
                  });
                }),
              ),
            ],
          ),
        ),
    );
  }
}

class MessageTile extends StatelessWidget {
  final Message message;
  final Function(Message) onLike;

  MessageTile({required this.message, required this.onLike});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.all(8.0),
      child: ListTile(
        title: Text(message.mMessage),
        subtitle: Text('Likes: ${message.mLikes}'),
        trailing: IconButton(
            icon: const Icon(Icons.sports_bar_rounded , color: Color.fromARGB(255, 255, 196, 0), size: 30.0),
            onPressed: () {
              onLike(message);
            }),
      ),
    );
  }
}

class MessageInput extends StatefulWidget {
  final Function(String) onMessageAdded;
  MessageInput({required this.onMessageAdded});
  @override
  MessageInputState createState() => MessageInputState();
}

class MessageInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();
  void _sendMessage() async {
    final messageText = _textController.text;
    if (messageText.isNotEmpty) {
      addMessage(messageText); // Call the function to add a new message
      widget.onMessageAdded(messageText);
      _textController.clear();
    }
  }

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
            onPressed: _sendMessage,
          ),
        ],
      ),
    );
  }
}
