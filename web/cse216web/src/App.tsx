import UserProfilePage from './pages/UserProfile';
import LogInPage from './pages/LogInPage';
import IdeaListPage from './pages/IdeaListPage';
import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route path="list" element={<IdeaListPage/>} />
      <Route path="about" element={<LogInPage/>} />
      <Route path="profile" element={<UserProfilePage/>} />
    </Route>
  )
)
function App() {
  return (
    <>
      <RouterProvider router={router}/>
    </>
  );
}

export default App;
