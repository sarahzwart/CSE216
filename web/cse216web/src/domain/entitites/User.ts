export class User{
    uName: string;
    uEmail: string;
    uSO: string;
    uGO: string;
    uNote: string;
    uId: number;
    constructor(name: string , email: string, sexualIdentity: string, genderOrientation: string, note: string, userId: number){
        this.uName = name;
        this.uEmail = email;
        this.uSO = sexualIdentity;
        this.uGO = genderOrientation;
        this.uNote = note;
        this.uId= userId;
    }
}