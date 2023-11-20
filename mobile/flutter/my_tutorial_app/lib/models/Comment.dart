class Comment {
  String cContent;
  int cId;
  int mId;
  int uId;
  Comment({
    required this.cContent,
    required this.mId,
    required this.cId,
    required this.uId,
  });

  factory Comment.fromJson(Map<String, dynamic> json) {
    return Comment(
      cContent: json['cComment'],
      cId: json['cId'],
      mId: json['mId'],
      uId: json['uId'],
    );
  }

  Map<String, dynamic> toJson() => {
        'cComment': cContent,
        'cId': cId,
        'mId': mId,
        'uId': uId,
      };
}
