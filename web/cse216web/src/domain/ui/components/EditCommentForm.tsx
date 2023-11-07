import React, {useState} from "react";
import axios from "axios";

interface EditCommentProps {
  onEditComment: (comment: string) => void;
  comment: string;
}

function EditCommentForm({ onEditComment, comment }: EditCommentProps) {
  const [formData, setFormData] = useState({ comment });
  const [isEditing, setIsEditing] = useState(false);

  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setFormData({
      comment: e.target.value,
    });
  };

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (isEditing) {
      onEditComment(formData.comment);
      setIsEditing(false);
    } else {
      setIsEditing(true);
    }
  };

  return (
    <form onSubmit={onSubmit}>
      {isEditing ? (
        <textarea
          placeholder=" "
          value={formData.comment}
          onChange={onChange}
        />
      ) : (
        <div>{formData.comment}</div>
      )}
      <div></div>
      <button type="submit">
        {isEditing ? "Save Comment" : "Edit"}
      </button>
    </form>
  );
}

export default EditCommentForm;
