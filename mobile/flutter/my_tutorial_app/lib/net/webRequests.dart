 import '../models/NumberWordPair.dart';
 import 'package:http/http.dart' as http;
 import 'dart:developer' as developer;
import 'dart:convert';
Future<List<NumberWordPair>> fetchNumberWordPairs() async {
  final response = await http
      .get(Uri.parse('http://www.cse.lehigh.edu/~spear/5k.json'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response, then parse the JSON.
    final List<NumberWordPair> returnData;
    var res = jsonDecode(response.body);
    print('json decode: $res');

    if( res is List ){
      returnData = (res as List<dynamic>).map( (x) => NumberWordPair.fromJson(x) ).toList();
    }else if( res is Map ){
      returnData = <NumberWordPair>[NumberWordPair.fromJson(res as Map<String,dynamic>)];
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