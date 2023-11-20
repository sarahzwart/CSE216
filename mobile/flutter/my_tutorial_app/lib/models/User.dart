class User {
  int uId;
  String uName;
  String uEmail;
  String uGI;
  String uSO;
  String uNote;
  User({
    required this.uId,
    required this.uName,
    required this.uEmail,
    required this.uGI,
    required this.uSO,
    required this.uNote,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      uId: json['uId'],
      uName: json['uName'],
      uEmail: json['uEmail'],
      uGI: json['uGI'],
      uSO: json['uSO'],
      uNote: json['uNote'],
    );
  }

  Map<String, dynamic> toJson() => {
        'uId': uId,
        'uName': uName,
        'uEmail': uEmail,
        'uGI': uGI,
        'uSO': uSO,
        'uNote': uName,
      };
}
