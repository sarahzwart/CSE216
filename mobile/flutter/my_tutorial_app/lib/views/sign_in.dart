import 'package:flutter/material.dart';
import 'package:my_tutorial_app/models/User.dart';
import '../api/google_signin_api.dart';
import 'package:google_sign_in/google_sign_in.dart';

class sign_in extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        width: 300,
        height: 500,
        decoration: BoxDecoration(
            color: Color.fromARGB(255, 103, 150, 112),
            borderRadius: BorderRadius.circular(10)),
        child: SignInButton(),
      ),
    );
  }
}

class SignInButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return TextButton(
      style: TextButton.styleFrom(
        textStyle: const TextStyle(fontSize: 20),
      ),
      onPressed: signIn,
      child: const Text('Google Sign In'),
    );
  }
}

Future<String?> signIn() async {
  final GoogleSignInAccount? account = await GoogleSignInApi.login();
  final GoogleSignInAuthentication auth = await account!.authentication;
  final String? idToken = auth.idToken;
  debugPrint('TOKEN = $idToken');
  return idToken;
}
