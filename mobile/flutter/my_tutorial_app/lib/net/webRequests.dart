 import '../models/Message.dart';
 import 'package:http/http.dart' as http;
 import 'dart:developer' as developer;
import 'dart:convert';
import '../main.dart';
const String backendURL = 'team-margaritavillians.dokku.cse.lehigh.edu';
Future<List<Message>> getWebData() async {
  final response = await http
      .get(Uri.parse(backendURL));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    final List<Message> returnData;
    var res = jsonDecode(response.body);
    print('json decode: $res');
    /*
    final List<dynamic> jsonMessages = jsonDecode(response.body);
    final List<Message> messages = jsonMessages.map((json) {
      return Message(
        json['text'],
        json['likes'],
        false, // 'isLiked is initially set to false becuase the user hasnt liked it yet
      );
    }).toList();
    return messages;
    */
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

/*
//Resources:
//https://docs.flutter.dev/cookbook/networking/fetch-data
//https://www.woolha.com/tutorials/dart-create-http-request-examples
//https://stackoverflow.com/questions/70839460/http-requests-with-dart
// GET Request to retrieve the messages
Future<List<Message>> getMessageData() async {
  developer.log('Making web request...');
  var headers = {"Accept": "application/json"}; // <String,String>{};

  var response = await http.get(Uri.parse(backendURL));

  developer.log('Response status: ${response.statusCode}');
  developer.log('Response headers: ${response.headers}');
  developer.log('Response body: ${response.body}');

  final List<String> returnData;
  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    final List<dynamic> jsonMessages = jsonDecode(response.body);
    final List<Message> messages = jsonMessages.map((json) {
      return Message(
        json['text'],
        json['likes'],
        false, // 'isLiked is initially set to false becuase the user hasnt liked it yet
      );
    }).toList();
    return messages;
  } else {
    throw Exception(
        'Failed to retrieve web data (server returned ${response.statusCode})');
  }
}

//Post a message


Future<void> addMessage(String messageText) async{
  final response = await http.post(
    Uri.parse(backendURL),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode({
      'text': messageText,
      'likes': 0,
      'isLiked': false,
    }),
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
  final response = await http.put(
    Uri.parse(backendURL),
    headers: {'Content-Type': 'application/json'},
    body: jsonEncode({
      'isLiked': message.isLiked,
    }),
  );
  if(response.statusCode !=200){
    throw Exception (
      'Failed to add new message'
    );
  } 
} */