export class User{
    mName: string;
    mEmail: string;
    mSexualIdentity: string;
    mGenderOrientation: string;
    mNote: string;
    constructor(name: string , email: string, sexualIdentity: string, genderOrientation: string, note: string){
        this.mName = name;
        this.mEmail = email;
        this.mSexualIdentity = sexualIdentity;
        this.mGenderOrientation = genderOrientation;
        this.mNote = note;
    }
}