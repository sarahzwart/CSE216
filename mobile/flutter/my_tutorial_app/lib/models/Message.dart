class Message {
  String mMessage;
  String mTitle;
  int mLikes;
  int? mId;
  String? mContent; 
  Message({
    required this.mTitle,
    required this.mMessage,
    required this.mLikes,
    required this.mId,
  });

  factory Message.fromJson(Map<String, dynamic> json) {
    final mMessage = json['mMessage'] ?? json['mContent'] ?? '';
    return Message(
      mTitle: json['mTitle'] ?? 'Default',
      mMessage: mMessage ,
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

class Message2{
  String mContent;
  int mId;
  String mTitle;
  int mLikes;  
  Message2({
    required this.mTitle,
    required this.mId,
    required this.mContent,
    required this.mLikes,
  });

  factory Message2.fromJson(Map<String, dynamic> json) {
    return Message2(
      mTitle: json['mTitle'],
      mId: json['mId'],
      mContent: json['mContent'], 
      mLikes: json['mLikes']
    );
  }

  Map<String, dynamic> toJson() => {
    'mTitle': mTitle,
    'mContent': mContent,
    'mId': mId,
    'mLikes': mLikes,
  };
}



