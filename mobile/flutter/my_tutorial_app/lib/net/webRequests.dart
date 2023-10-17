import '../models/Message.dart';
import 'package:http/http.dart' as http;
import 'dart:developer' as developer;
import 'dart:convert';
import 'package:uuid/uuid.dart';

Future<List<Message>> getWebData() async {
  developer.log("Making Web Request");
  final response = await http
      .get(Uri.parse("team-margaritavillians.dokku.cse.lehigh.edu"));
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    List<Message> returnData = [];
    var res = jsonDecode(response.body);
    print('JSON decode: $res');
    if( res is List ){
      returnData = res.map( (x) => Message.fromJson(x) ).toList();
    }else if( res is Map ){
      returnData = <Message>[Message.fromJson(res as Map<String,dynamic>)];
    }else{
      developer.log('ERROR: Unexpected json response type (was not a List or Map).');
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


Future<void> addMessage(String messageText) async{
  final newMessage = Message(
    text: messageText,
    likes: 0,
    isLiked: false,
    id: const Uuid(),
  );

  final response = await http.post(
    Uri.parse('team-margaritavillians.dokku.cse.lehigh.edu'),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode(newMessage.toJson()),
  );
  //Error handling
  if(response.statusCode !=200){
    throw Exception (
      'Failed to add new message'
    );
  } 
}

//Put Like
Future<void> toggleLike(Message message) async {
  final updatedMessage = Message(
    text: message.text, // Copy text from the original message
    likes: message.likes + (message.isLiked ? -1 : 1), // Copy likes from the original message
    isLiked: !message.isLiked, // Toggle the isLiked state
    id: message.id,
  );
  final response = await http.put(
    Uri.parse('team-margaritavillians.dokku.cse.lehigh.edu'),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode(updatedMessage.toJson()), // Convert updatedMessage to JSON
  );
  if(response.statusCode != 200){
    throw Exception (
      'Failed to update message like status'
    );
  } 
} 