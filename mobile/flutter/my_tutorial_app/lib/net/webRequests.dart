import '../models/Message.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;
import 'dart:convert';

Future<List<Message>> getWebData() async {
  developer.log("Making Web Request");
  http.Response response = await http
      .get(
        Uri.parse('https://team-margaritavillians.dokku.cse.lehigh.edu/messages'), 
        headers: {"Content-Type": "application/json"},
        );
  developer.log('HTTP Response Status Code: ${response.statusCode}');
  developer.log('HTTP Response Body: ${response.body}');
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    if (response.body.isNotEmpty) {
      List<Message> returnData = [];
      var res = jsonDecode(response.body);
      developer.log('JSON decode: $res');
      if (res is List) {
        returnData = res.map((x) => Message.fromJson(x)).toList();
      } else if (res is Map) {
        returnData = <Message>[Message.fromJson(res as Map<String, dynamic>)];
      } else {
        developer.log('ERROR: Unexpected json response type (was not a List or Map).');
      }
      return returnData;
    } else {
      // Handle the case where the server returned an empty response.
      developer.log('Server returned an empty response.');
      return [];
    }
  } else {
    // Handle the case where the server returned a non-200 status code.
    developer.log('Server returned a non-200 status code: ${response.statusCode}');
    throw Exception('Did not receive a success status code from the request.');
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
    mTitle: 'Title',
    mMessage: messageText,
    mLikes: 0,
  );
  final jsonBody = jsonEncode(newMessage.toJson());
  final response = await http.post(
    Uri.parse("https://team-margaritavillians.dokku.cse.lehigh.edu/messages"),
    headers: {'Content-Type': 'application/json'},
    body: jsonBody,
  );
  //Error handling
  if (response.statusCode == 200) {
    // Parse the response JSON
    final responseData = json.decode(response.body);

    // Handle the response data according to the actual server structure
    // You can access individual fields in responseData, e.g., responseData['status']
    
    // Example: Check if the response contains a 'status' field
    if (responseData.containsKey('status')) {
      final status = responseData['status'];
      if (status == 'ok') {
        // The request was successful
      } else {
        // Handle the error based on the 'status' and any other fields in responseData
        final message = responseData['message'];
        throw Exception('Request failed: $message');
      }
    } else {
      // Handle the response if it doesn't conform to the expected structure
      throw Exception('Unexpected response format');
    }
  } else {
    // If the request failed, you can throw an exception or return an error response.
    throw Exception('Failed to add new message');
  }
}

//Put Like
Future<void> toggleLike(Message message) async {
  final updatedMessage = Message(
    mTitle: message.mTitle,
    mMessage: message.mMessage, // Copy text from the original message
    mLikes: message
        .mLikes, // Copy likes from the original message // Toggle the isLiked state
  );

  final response = await http.put(
    Uri.parse("https://team-margaritavillians.dokku.cse.lehigh.edu/messages"),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode(updatedMessage.toJson()), // Convert updatedMessage to JSON
  );
  if (response.statusCode != 200) {
    throw Exception('Failed to update message like status');
  }
}
