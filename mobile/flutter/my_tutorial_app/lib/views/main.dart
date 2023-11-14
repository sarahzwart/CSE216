import 'package:my_tutorial_app/views/comment.dart';

import '../net/webRequests.dart';
import 'package:flutter/material.dart';
import '../models/Comment.dart';
import '../models/Message.dart';
import 'package:flutter/services.dart';
import 'dart:io';
import 'package:google_fonts/google_fonts.dart';

//deleting duplicate files: find . -type f -name '* [0-9]' -exec rm {} +, find . -type f -name '* [0-9].*' -exec rm {} +
const String backendURL = 'http://team-margaritavillians.dokku.cse.lehigh.edu';

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
  final List<Comment> comments = [];
  @override
  void initState() {
    super.initState();
    fetchData(); // Call the function to fetch initial data
  }

  Future<void> fetchData() async {
    try {
      final mData = await getMessageData();
      //final cData = await getComData();
      setState(() {
        messages.addAll(mData);
        //comments.addAll(cData);
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
                color: const Color.fromARGB(
                    255, 219, 245, 253), // Set the text color
              ),
            ),
            backgroundColor: const Color.fromARGB(255, 103, 150, 112)),
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
                      //defining what is in each message tile
                      message: messages[mId],
                      mComments: [
                        Comment(cContent: 'test1', mId: mId, cId: 0, uId: 0),
                        Comment(cContent: 'test2', mId: mId, cId: 0, uId: 0),
                        Comment(cContent: 'test3', mId: mId, cId: 0, uId: 0)
                      ],
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
                  uId: 0,
                  //needs to pass the id of the current user, dependent on Oauth
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
  final List<Comment> mComments;
  final Function(Message) onLike;

  MessageTile(
      {required this.message, required this.mComments, required this.onLike});

  @override
  Widget build(BuildContext context) {
    return Card(
        margin: const EdgeInsets.all(8.0),
        child: Column(children: [
          ListTile(
              leading: IconButton(
                icon: const Icon(Icons.person),
                onPressed: () {},
              ),
              title: Text(message.mMessage),
              subtitle: Text('Likes: ${message.mLikes}'),
              trailing: Wrap(spacing: 10, children: <Widget>[
                IconButton(
                    icon: const Icon(Icons.sports_bar_rounded,
                        color: Color.fromARGB(255, 255, 196, 0), size: 30.0),
                    onPressed: () {
                      onLike(message);
                    }),
                IconButton(
                  icon: const Icon(
                    Icons.chat_bubble_outline_rounded,
                  ),
                  onPressed: () {},
                )
              ])),
          ListView.builder(
              shrinkWrap: true,
              itemCount: 3,
              itemBuilder: (BuildContext context, cId) {
                return Card(
                  color: Color.fromARGB(255, 103, 150, 112),
                  margin: const EdgeInsets.all(10),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(2),
                  ),
                  child: CommentTile(
                    comment: mComments[cId],
                  ),
                );
              }),
        ]));
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

/*

Lets see if we can figure this new page bs



class commentPage extends StatelessWidget {
  final List<Comment> comments = [];
  Future<void> editComment(Comment comment) async {
    // alt to add editing comments
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
                color: const Color.fromARGB(
                    255, 219, 245, 253), // Set the text color
              ),
            ),
            backgroundColor: const Color.fromARGB(255, 103, 150, 112)),
        body: Column(
          //allows for scrolling
          children: [
            Expanded(
              child: ListView.builder(
                shrinkWrap: true, // Important to prevent renderFlex error
                itemCount: comments.length,
                itemBuilder: (BuildContext context, mId) {
                  return Card(
                    color: const Color.fromARGB(255, 103, 150, 112),
                    margin: const EdgeInsets.all(8),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: CommentTile(
                      comment: comments[mId],
                      onEditComment: (comment) {},
                    ),
                  );
                },
              ),
            ),
            Container(
              alignment: Alignment.bottomCenter,
              child: CommentInput(onCommentAdded: (cContent) {
                // Add a new message to the list
                final newComment = Comment(
                  cContent: cContent,
                  cId: 0,
                  mId: 0,
                  uId: 0,
                  //needs to pass the id of the current user, dependent on Oauth
                );
                setState(() {
                  comments.add(newComment);
                });
              }),
            ),
          ],
        ),
      ),
    );
  }
}*/

class CommentTile extends StatelessWidget {
  final Comment comment;

  CommentTile({required this.comment});

  @override
  Widget build(BuildContext context) {
    return Card(
        margin: const EdgeInsets.all(8.0),
        child: ListTile(
            //leading: add icon widget with user image
            title: Text(comment.cContent),
            tileColor: Color.fromARGB(200, 125, 193, 137),
            trailing: IconButton(
              icon: const Icon(
                Icons.edit_outlined,
              ),
              onPressed: () {
                //view comments as well as add comment button
              },
            )));
  }
}

class CommentInput extends StatefulWidget {
  final Function(String) onCommentAdded;
  CommentInput({required this.onCommentAdded});
  @override
  CommentInputState createState() => CommentInputState();
}

class CommentInputState extends State<MessageInput> {
  final TextEditingController _textController = TextEditingController();
  void _sendMessage() async {
    final cContent = _textController.text;
    if (cContent.isNotEmpty) {
      //addComment(cContent); // Call the function to add a new comment
      widget.onMessageAdded(cContent);
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
