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
  test('Like value should be incremented', () {
    //Create a message with 1 like
    final Message message = Message(
        mMessage: 'Test Message', mLikes: 0, mTitle: ' none', mId: 0, uId: 0);
    //Increment the Likes
    message.mLikes = message.mLikes + 1;
    //Expect that the likes are equal to 1
    expect(message.mLikes, 1);
  });
  test('Like value should be decremented', () {
    //Create a message with 1 like
    final Message message = Message(
        mMessage: 'Test Message', mLikes: 1, mTitle: ' none', mId: 0, uId: 0);
    //Decrement the like count
    message.mLikes = message.mLikes - 1;
    //Expect that the likes have been decremented
    expect(message.mLikes, 0);
  });
}
