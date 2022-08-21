import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.scss';
import { MatchPage } from './pages/MatchPage';
import { TeamPage } from './pages/TeamPage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/teams/:teamName" element={<TeamPage />} />
          <Route
            path="/teams/:teamName/matches/:year"
            element={<MatchPage />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
