import '../models/Message.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;
import 'dart:convert';
import 'package:uuid/uuid.dart';

Future<List<Message>> getWebData() async {
  developer.log("Making Web Request");
  var url = Uri.parse("http://localhost:4567");
  var headers = {"Accept": "application/json"};
  var response = await http.get(url, headers: headers);
  developer.log('HTTP Response Status Code: ${response.statusCode}');
  developer.log('HTTP Response Body: ${response.body}');
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    List<Message> returnData = [];
    var res = jsonDecode(response.body);
    developer.log('JSON decode: $res');
    if (res is List) {
      returnData = res.map((x) => Message.fromJson(x)).toList();
    } else if (res is Map) {
      returnData = <Message>[Message.fromJson(res as Map<String, dynamic>)];
    } else {
      developer
          .log('ERROR: Unexpected json response type (was not a List or Map).');
      returnData = List.empty();
    }
    return returnData;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Did not receive success status code from request.');
  }
}

//Resources:
//https://docs.flutter.dev/cookbook/networking/fetch-data
//https://www.woolha.com/tutorials/dart-create-http-request-examples
//https://stackoverflow.com/questions/70839460/http-requests-with-dart
// GET Request to retrieve the messages

//Post a message

Future<void> addMessage(String messageText) async {
  final newMessage = Message(
    mTitle: '  ',
    mMessage: messageText,
    mLikes: 0,
  );
  final response = await http.post(
    Uri.parse('http://localhost:4567'),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode(newMessage.toJson()),
  );
  //Error handling
  if (response.statusCode != 200) {
    throw Exception('Failed to add new message');
  }
}

//Put Like
Future<void> toggleLike(Message message) async {
  final updatedMessage = Message(
    mTitle: message.mTitle,
    mMessage: message.mMessage, // Copy text from the original message
    mLikes: message.mLikes, // Copy likes from the original message // Toggle the isLiked state
  );

  final response = await http.put(
    Uri.parse('http://localhost:4567'),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode(updatedMessage.toJson()), // Convert updatedMessage to JSON
  );
  if (response.statusCode != 200) {
    throw Exception('Failed to update message like status');
  }
}
