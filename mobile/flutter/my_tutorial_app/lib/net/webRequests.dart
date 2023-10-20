import '../models/Message.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;
import 'dart:convert';

//https://stackoverflow.com/questions/55331782/flutter-send-json-body-for-http-get-request
Future<List<Message>> getWebData() async {
  developer.log("Making Web Request");
  http.Response response = await http.get(
    Uri.parse('https://team-margaritavillians.dokku.cse.lehigh.edu/messages'),
    headers: {"Content-Type": "application/json"},
  );
  developer.log('HTTP Response Status Code: ${response.statusCode}');
  developer.log('HTTP Response Body: ${response.body}');
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    if (response.body.isNotEmpty) {
      List<Message> returnData = [];
      var res = json.decode(response.body);
      developer.log('JSON decode: $res');
      if (res is List) {
        returnData = res.map((x) => Message.fromJson(x)).toList();
      } else if (res is Map) {
        returnData = <Message>[Message.fromJson(res as Map<String, dynamic>)];
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
  final Map<String, String> messageData = {
    'mTitle': 'Title',
    'mMessage': message,
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
  Future<List<Message>> messageList = getWebData();
  
  final Map<String, String> messageData = {
    'mLikes': ,
    'mId': ,
  };
  final response = await http.put(
    Uri.parse("https://team-margaritavillians.dokku.cse.lehigh.edu/messages/:${id}"),
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
