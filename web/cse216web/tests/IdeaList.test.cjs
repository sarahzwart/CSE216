import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import axios from "axios";
import IdeaList from "../src/domain/ui/components/IdeaList"; // Assuming you have a separate component that wraps the IdeaList component

jest.mock("axios");

describe("IdeaList Component", () => {
  it("should handleAddMessage correctly", async () => {
    const mockAddMessage = jest.fn();
    (axios.post as jest.Mock).mockResolvedValue({ data: { mMessage: 2 } });
    
    const messageInput = screen.getByPlaceholderText("Enter your message");
    const submitButton = screen.getByText("Submit");

    fireEvent.change(messageInput, { target: { value: "New Message" } });
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(mockAddMessage).toHaveBeenCalledWith("New Message");
    });
  });

  it("should handleAddComment correctly", async () => {
    const mockAddComment = jest.fn();
    (axios.post as jest.Mock).mockResolvedValue({ data: { mData: 2 } });

    const commentInput = screen.getByPlaceholderText("Enter your comment");
    const submitButton = screen.getByText("Submit Comment");

    fireEvent.change(commentInput, { target: { value: "New Comment" } });
    fireEvent.click(submitButton);

    await waitFor(() => {
      expect(mockAddComment).toHaveBeenCalledWith(1, "New Comment", 1);
    });
  });

  it("should handleEditComment correctly", async () => {
    const mockEditComment = jest.fn();
    (axios.put as jest.Mock).mockResolvedValue({ data: {} });

    const editButton = screen.getByText("Edit");
    const editCommentInput = screen.getByPlaceholderText("Edit Comment");

    fireEvent.click(editButton);
    fireEvent.change(editCommentInput, { target: { value: "Edited Comment" } });

    await waitFor(() => {
      expect(mockEditComment).toHaveBeenCalledWith(1, "Edited Comment");
    });
  });

  // Add more tests for other functions like messageComments and displayUsername
});