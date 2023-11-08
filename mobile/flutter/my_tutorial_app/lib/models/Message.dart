class Message {
  String mMessage;
  String mTitle;
  int mLikes;
  int mId;
  int uId;
  Message({
    required this.mTitle,
    required this.mMessage,
    required this.mLikes,
    required this.mId,
    required this.uId,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      mTitle: json['mTitle'] ?? 'Too lazy to title it?',
      mMessage: json['mMessage'],
      mLikes: json['mLikes'] ?? 0,
      mId: json['mId'],
      uId: json['uId'],
    );
  }

  Map<String, dynamic> toJson() => {
        'mTitle': mTitle,
        'mMessage': mMessage,
        'mLikes': mLikes,
        "mId": mId,
        "uId": uId,
      };
}
