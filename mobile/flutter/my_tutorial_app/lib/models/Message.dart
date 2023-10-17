import 'package:uuid/uuid.dart';
class Message {
  /// The string representation of message
  final String text; 
  /// The int representation of the like
  int likes;
  bool isLiked;
  var uuid = Uuid();

  Message({
    required this.text,
    required this.likes,
    required this.isLiked,
    required this.uuid,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      text: json['text'],
      likes: json['likes'],
      isLiked: json['isLiked'],
      uuid: json['uuid'],
    );
  }

  Map<String, dynamic> toJson() => {
    'text': text,
    'likes': likes,
    'isLiked': isLiked,
  };

  
}
