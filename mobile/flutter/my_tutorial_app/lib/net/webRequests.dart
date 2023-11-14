import 'package:my_tutorial_app/models/Comment.dart';

import '../models/Message.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;
import 'dart:convert';

String backendURL =
    'https://team-margaritavillians.dokku.cse.lehigh.edu/messages';
//https://stackoverflow.com/questions/55331782/flutter-send-json-body-for-http-get-request

Future<List<Message>> getMessageData() async {
  developer.log("Making Web Request");
  http.Response response = await http.get(
    Uri.parse(backendURL),
    headers: {"Content-Type": "application/json"},
  );
  developer.log('HTTP Response Status Code: ${response.statusCode}');
  developer.log('HTTP Response Body: ${response.body}');
  if (response.statusCode == 200) {
    if (response.body.isNotEmpty) {
      List<Message> returnData = [];
      final jsonData = jsonDecode(response.body);
      if (jsonData['mData'] is List) {
        returnData = (jsonData['mData'] as List)
            .map((data) => Message.fromJson(data))
            .toList();
      } else if (jsonData['mData'] is Map) {
        returnData = <Message>[
          Message.fromJson(jsonData['mData'] as Map<String, dynamic>)
        ];
      } else {
        developer.log(
            'ERROR: Unexpected json response type (was not a List or Map).');
      }
      return returnData;
    } else {
      // Handle the case where the server returned an empty response.
      developer.log('Server returned an empty response.');
      return [];
    }
  } else {
    // Handle the case where the server returned a non-200 status code.
    developer
        .log('Server returned a non-200 status code: ${response.statusCode}');
    throw Exception('Did not receive a success status code from the request.');
  }
}

//get list of comments on a message
Future<List<Comment>> getMessageComment(Message message) async {
  developer.log("Making Web Request");
  http.Response response = await http.get(
    Uri.parse(
        "https://team-margaritavillians.dokku.cse.lehigh.edu/comments/:${message.mId}"),
    headers: {"Content-Type": "application/json"},
  );
  developer.log('HTTP Response Status Code: ${response.statusCode}');
  developer.log('HTTP Response Body: ${response.body}');
  if (response.statusCode == 200) {
    if (response.body.isNotEmpty) {
      List<Comment> returnData = [];
      final jsonData = jsonDecode(response.body);
      if (jsonData['cContent'] is List) {
        returnData = (jsonData['cContent'] as List)
            .map((data) => Comment.fromJson(data))
            .toList();
      } else if (jsonData['cContent'] is Map) {
        returnData = <Comment>[
          Comment.fromJson(jsonData['cContent'] as Map<String, dynamic>)
        ];
      } else {
        developer.log(
            'ERROR: Unexpected json response type (was not a List or Map).');
      }
      return returnData;
    } else {
      // Handle the case where the server returned an empty response.
      developer.log('Server returned an empty response.');
      return [];
    }
  } else {
    // Handle the case where the server returned a non-200 status code.
    developer
        .log('Server returned a non-200 status code: ${response.statusCode}');
    throw Exception('Did not receive a success status code from the request.');
  }
}

//Resources:
//https://docs.flutter.dev/cookbook/networking/fetch-data
//https://www.woolha.com/tutorials/dart-create-http-request-examples
//https://stackoverflow.com/questions/70839460/http-requests-with-dart
// GET Request to retrieve the messages

//Post a message

Future<void> addMessage(String message) async {
  final Map<String, dynamic> messageData = {
    'mTitle': 'Title',
    'mMessage': message,
    'uId': 0,
  };
  final response = await http.post(
      Uri.parse("https://team-margaritavillians.dokku.cse.lehigh.edu/messages"),
      headers: {"content-type": "application/json"},
      body: jsonEncode(messageData));
  if (response.statusCode == 200) {
    final responseData = jsonDecode(response.body);
    if (responseData['mStatus'] == 'ok') {
      return;
    } else {
      throw Exception('Failed to add new message');
    }
  } else {
    throw Exception('Failed to add new message');
  }
}

//Put Like
Future<void> addLikes(Message updatedMessage) async {
  // Make a PUT request to update the message on the server
  //if the message of the frontend matches the message of the backend, give the ID
  final List<Message> messageList = await getMessageData();
  /*Message matchingMessage = Message(
    uId: 0,
    mId: 0,
    mTitle: " ",
    mMessage: " ",
    mLikes: 0,
  ); //Initializing a message
  for (var m in messageList) {
    if (m.mMessage == updatedMessage.mMessage) {
      matchingMessage = m;
      break;
    }
  }*/
  final Map<String, dynamic> messageData = {
    'mLikes': updatedMessage.mLikes,
    'uId': 0,
    'isLike': 1,
  };
  final response = await http.put(
    Uri.parse(
        "https://team-margaritavillians.dokku.cse.lehigh.edu/messages/${updatedMessage.mId}"),
    headers: {
      "content-type": "application/json",
      "accept": "application/json",
    },
    body: jsonEncode(messageData), // Convert the updated message to JSON
  );
  if (response.statusCode != 200) {
    throw Exception('Failed to update message like status');
  }
}
