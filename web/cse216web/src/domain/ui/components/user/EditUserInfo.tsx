import React, {useState} from "react";

interface EditProfileProps {
  onEditInfo: (info: string) => void;
  info: string;
}

function EditProfileForm({ onEditInfo, info }: EditProfileProps) {
  const [formData, setFormData] = useState({ info });
  const [isEditing, setIsEditing] = useState(false);

  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setFormData({
      info: e.target.value,
    });
  };

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (isEditing) {
      onEditInfo(formData.info);
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
          value={formData.info}
          onChange={onChange}
        />
      ) : (
        <div>{formData.info}</div>
      )}
      <div></div>
      <button type="submit">
        {isEditing ? "Save Comment" : "Edit"}
      </button>
    </form>
  );
}

export default EditProfileForm;
