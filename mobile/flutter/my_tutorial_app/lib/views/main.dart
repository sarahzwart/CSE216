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
   final Function(String) onMessageAdded; 
  MessageInput({required this.onMessageAdded});
  @override
  MessageInputState createState() => MessageInputState();
}

class MessageInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();
  void _sendMessage() async{
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