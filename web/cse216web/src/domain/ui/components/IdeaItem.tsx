import React, { Component } from 'react';
import {Message} from '../../entitites/Message';

class IdeaItem extends Component {
    constructor(props: Message) {
        super(props);
        this.state = {
            idea: null,
        };
        this.getMessageData = this.getMessageData.bind(this);
    }
    getMessageData(mId){
        axios.get('https://team-margaritavillians.dokku.cse.lehigh.edu/messages/{id}')
    }

    render() {
        const { idea } = this.state;
        return (
            <div>
                <h2>{idea.mTitle}</h2>
                <p>{idea.mMessage}</p>
                <p>Likes: {idea.mLikes}</p>
                <p>User ID: {idea.uId}</p>
            </div>
        );
    }
}
export default IdeaItem;