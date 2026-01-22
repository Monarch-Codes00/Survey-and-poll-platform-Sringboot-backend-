import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import PollList from './components/PollList';
import AdminPanel from './components/AdminPanel';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <header>
          <h1>Poll System</h1>
          <nav className="navigation">
            <Link to="/">Vote in Polls</Link>
            <Link to="/admin">Admin Panel</Link>
          </nav>
        </header>
        <main>
          <Routes>
            <Route path="/" element={<PollList />} />
            <Route path="/admin" element={<AdminPanel />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
