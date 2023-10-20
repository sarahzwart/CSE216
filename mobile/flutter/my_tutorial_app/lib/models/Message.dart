class Message {
  String mMessage;
  String mTitle;
  int? mId;
  int mLikes;

  Message({
    required this.mTitle,
    required this.mMessage,
    required this.mLikes,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      mTitle: json['mTitle'],
      mMessage: json['mMessage'],
      mLikes: json['mLikes']
    );
  }

  Map<String, dynamic> toJson() => {
    'mTitle': mTitle,
    'mMessage': mMessage,
    'mLikes': mLikes
  };
}
