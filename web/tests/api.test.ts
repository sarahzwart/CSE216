// Unit Tests created with Chat GPT
import axios, { AxiosRequestConfig } from 'axios';
import { AxiosResponse } from 'axios'; // Import AxiosResponse explicitly
import {
  fetchMessages,
  fetchComments,
  fetchUsers,
  addMessage,
  addComment,
  editComment,
  fetchUser,
  fetchUserName,
  editUserInfo,
  addSessionKey,
} from '../src/api/api'; // Adjust the path accordingly

// Mocking axios to avoid actual HTTP requests during testing
jest.mock('axios');

describe('API Functions', () => {
  const sessionKey = 'mockedSessionKey'; // Replace with an actual session key for testing

  test('fetchMessages should fetch messages', async () => {
    const mockedMessages = ['message1', 'message2'];
    (axios.get as jest.MockedFunction<typeof axios.get>).mockResolvedValue({
      data: { mStatus: 'success', mData: mockedMessages },
    } as AxiosResponse<any>);

    const result = await fetchMessages();

    expect(result).toEqual(mockedMessages);
  });

  test('fetchComments should fetch comments', async () => {
    const mockedComments = ['comment1', 'comment2'];
    (axios.get as jest.MockedFunction<typeof axios.get>).mockResolvedValue({
      data: { mStatus: 'success', mData: mockedComments },
    } as AxiosResponse<any>);

    const result = await fetchComments();

    expect(result).toEqual(mockedComments);
  });

  test('fetchUsers should fetch users', async () => {
    const mockedUsers = [{ id: 1, name: 'user1' }, { id: 2, name: 'user2' }];
    (axios.get as jest.MockedFunction<typeof axios.get>).mockResolvedValue({
      data: { mStatus: 'success', mData: mockedUsers },
    } as AxiosResponse<any>);

    const result = await fetchUsers();

    expect(result).toEqual(mockedUsers);
  });

  test('addMessage should add a message', async () => {
    const message = 'New message';
    const mockedResponse = { mData: 123 }; // Adjust based on your API response
    (axios.post as jest.MockedFunction<typeof axios.post>).mockResolvedValue({
      data: mockedResponse,
    } as AxiosResponse<any>);

    const result = await addMessage(message);

    expect(result).toEqual(mockedResponse.mData);
  });

  test('addComment should add a comment', async () => {
    const comment = 'New comment';
    const mId = 456; // Replace with a valid message ID
    const mockedResponse = { mData: 456 }; // Adjust based on your API response
    (axios.post as jest.MockedFunction<typeof axios.post>).mockResolvedValue({
      data: mockedResponse,
    } as AxiosResponse<any>);

    const result = await addComment(comment, mId);

    expect(result).toEqual(mockedResponse.mData);
  });

  test('editComment should edit a comment', async () => {
    const cId = 789; // Replace with a valid comment ID
    const cContent = 'Edited comment';
    await editComment(cId, cContent);

    // Add appropriate assertions based on the behavior of your editComment function
  });

  test('fetchUser should fetch user data', async () => {
    const uId = 123; // Replace with a valid user ID
    const mockedUserData = { uName: 'John Doe', uSO: 'Male' }; // Adjust based on your API response
    (axios.get as jest.MockedFunction<typeof axios.get>).mockResolvedValue({
      data: { mData: mockedUserData },
    } as AxiosResponse<any>);

    const result = await fetchUser(uId);

    expect(result).toEqual(mockedUserData);
  });

  test('fetchUserName should fetch user name', async () => {
    const uId = 123; // Replace with a valid user ID
    const mockedUserName = 'John Doe'; // Adjust based on your API response
    (axios.get as jest.MockedFunction<typeof axios.get>).mockResolvedValue({
      data: { mData: { uName: mockedUserName } },
    } as AxiosResponse<any>);

    const result = await fetchUserName(uId);

    expect(result).toEqual(mockedUserName);
  });


  test('addSessionKey should add a session key', async () => {
    const userJWT = 'mockedUserJWT';
    const mockedResponse = { sessionKey: 'newSessionKey', newUId: 123 };
    (axios.post as jest.MockedFunction<typeof axios.post>).mockResolvedValue({
      data: mockedResponse,
    } as AxiosResponse<any>);

    const result = await addSessionKey(userJWT);

    expect(result).toEqual(mockedResponse);
  });
});