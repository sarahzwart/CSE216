import React, { useState } from "react";

interface CommentFormProps {
  onAddComment: (comment: string) => void;
}

function CommentForm({ onAddComment }: CommentFormProps) {
  const [formData, setFormData] = useState({comment: ''});
  
  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setFormData({
      comment: e.target.value,
    });
  };

  const onSubmit = (e : React.FormEvent) => {
    e.preventDefault();
    onAddComment(formData.comment)
    setFormData({comment:''})
  };

  return (
    <form onSubmit={onSubmit}>
      <textarea
        placeholder="Add a new comment"
        value={formData.comment}
        onChange={onChange}
      />
      <div></div>
      <button type="submit">Add Comment</button>
    </form>
  );
};

export default CommentForm;
