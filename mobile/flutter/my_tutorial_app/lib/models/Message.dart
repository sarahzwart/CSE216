import 'package:uuid/uuid.dart';
class Message {
  /// The string representation of message
  final String text; 
  /// The int representation of the like
  int likes;
  bool isLiked;


  Message({
    required this.text,
    required this.likes,
    required this.isLiked,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      text: json['text'],
      likes: json['likes'],
      isLiked: json['isLiked'],
    );
  }

  Map<String, dynamic> toJson() => {
    'text': text,
    'likes': likes,
    'isLiked': isLiked,
  };

  
}
