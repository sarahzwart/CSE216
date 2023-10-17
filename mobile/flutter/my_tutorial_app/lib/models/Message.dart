import 'package:uuid/uuid.dart';
class Message {
  /// The string representation of message
  final String text; 
  /// The int representation of the like
  int likes;
  bool isLiked;
  var id;

  Message({
    required this.text,
    required this.likes,
    required this.isLiked,
    required this.id,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      text: json['text'],
      likes: json['likes'],
      isLiked: json['isLiked'],
      id: json['id'],
    );
  }

  Map<String, dynamic> toJson() => {
    'text': text,
    'likes': likes,
    'isLiked': isLiked,
    'id': id,
  };

  
}
