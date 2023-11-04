import React, { useState } from "react";

interface MessageFormProps {
  onAddMessage: (message: string) => void;
}

const MessageForm: React.FC<MessageFormProps> = ({ onAddMessage }) => {
  const [newMessage, setNewMessage] = useState<string>("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onAddMessage(newMessage);
    setNewMessage("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <textarea
        placeholder="Add a new message"
        value={newMessage}
        onChange={(e) => setNewMessage(e.target.value)}
      />
      <button type="submit">Add Message</button>
    </form>
  );
};
export default MessageForm;
