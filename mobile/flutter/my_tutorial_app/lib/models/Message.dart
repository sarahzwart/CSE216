class Message {
  String mMessage;
  String mTitle;
  int mLikes;
  int mId;
  Message({
    required this.mTitle,
    required this.mMessage,
    required this.mLikes,
    required this.mId,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      mTitle: json['mTitle'] ?? 'Default',
      mMessage: json['mMessage'],
      mLikes: json['mLikes'] ?? 0,
      mId: json['mId'],
    );
  }

  Map<String, dynamic> toJson() => {
    'mTitle': mTitle,
    'mMessage': mMessage,
    'mLikes': mLikes,
    "mId": mId,
  };
}
