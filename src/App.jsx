import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Homepage from "./pages/Homepage";
import ChatPage from "./pages/ChatPage";
import NotFound from "./pages/NotFound";
import Navbar from "./components/Navbar";
import EmotionTracker from "./pages/EmotionTrackerPage";
import CounselorList from "./pages/CounselorList";
import LibraryPage from "./pages/LibraryPage";
import RegisterPage from "./pages/RegisterPage";

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gradient-to-b from-[#3358A8] to-[#DBF2F4]">
        <Navbar />
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/counselorlist" element={<CounselorList/>}/>
          <Route path="/chat" element={<ChatPage />} />
          <Route path="/library" element={<LibraryPage />} />
          <Route path="*" element={<NotFound />} />
          <Route path="/journal" element={<EmotionTracker />} />
          <Route path="register" element={<RegisterPage />}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
