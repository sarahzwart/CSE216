import React, { useState } from "react";
import axios from 'axios';

type MessageFormProps = {
  onAddMessage: (message: string) => void; 
};

function MessageForm ({ onAddMessage }: MessageFormProps){
  const [formData, setFormData] = useState({message: ''});
  
  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setFormData({
      message: e.target.value,
    });
  };

  const onSubmit = (e : React.FormEvent) => {
    e.preventDefault();
    onAddMessage(formData.message)
    setFormData({message:''})
  };

  return (
    <form onSubmit={onSubmit}>
      <textarea
        placeholder="Add a new message"
        value={formData.message}
        onChange={onChange}
      />
      <div> </div>
      <button type="submit">Add Message</button>
    </form>
  );
};
export default MessageForm;
