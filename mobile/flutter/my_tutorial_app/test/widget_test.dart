// This is a basic Flutter widget test.
//
// To perform an interaction with a widget in your test, use the WidgetTester
// utility in the flutter_test package. For example, you can send tap and scroll
// gestures. You can also use WidgetTester to find child widgets in the widget
// tree, read text, and verify that the values of widget properties are correct.

import 'package:flutter_test/flutter_test.dart';
import 'package:my_tutorial_app/models/Message.dart';
import 'package:uuid/uuid.dart';

void main() {
  test('Like value should be incremented', (){
    //Create a message with 1 like
    final Message message = Message(text: 'Test Message', likes: 0, isLiked: false, id: const Uuid());
    //Increment the Likes
    message.likes = message.likes + 1;
    //Expect that the likes are equal to 1
    expect(message.likes, 1);
  });
  test('Like value should be decremented', () {
    //Create a message with 1 like
    final Message message = Message(text: 'Test Message', likes: 1, isLiked: true, id: const Uuid());
    //Decrement the like count
    message.likes = message.likes - 1;
    //Expect that the likes have been decremented
    expect(message.likes, 0);
  });

  test('Toggle like status and adjust likes', () {
    //Create message with 0 likes
    final Message message = Message(text: 'Test Message', likes: 0, isLiked: false, id: const Uuid());
    //Change like status to true
    message.isLiked = true;
    message.likes = message.likes + 1;
    //Expect isLiked has changed to true
    expect(message.isLiked, true);
    //Expect likes have been incremented by 1
    expect(message.likes, 1);
    //Toggle the like status again, which should decrement likes
    message.isLiked = false;
     message.likes = message.likes - 1;
    //Expect isLiked has changed to false
    expect(message.isLiked, false);
    //Expect likes have been decremented
    expect(message.likes, 0);
  });
}
