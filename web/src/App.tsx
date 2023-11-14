import UserProfilePage from "./pages/UserProfile";
import LogInPage from "./pages/LogInPage";
import IdeaListPage from "./pages/IdeaListPage";
import UserProfile from "./domain/ui/components/user/UserProfile";
import CurrentUserProfile from "./domain/ui/components/user/CurrentUserProfile";

import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
} from "react-router-dom";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route path="list" element={<IdeaListPage />} />
      <Route path="login" element={<LogInPage />} />
      <Route path="profile" element={<UserProfilePage />} />
      <Route path="profile/:userId" element={<UserProfile />} />
      <Route path="currentuserprofile" element={<CurrentUserProfile />} />
    </Route>
  )
);

function App() {
  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
