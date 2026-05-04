import React, { useState } from 'react';
import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import CoursePage from './pages/CoursePage';
import SettingsPage from './pages/SettingsPage';
import ComparePage from './pages/ComparePage';
import './App.css';

export default function App() {
  const [page, setPage] = useState('main');
  const [user, setUser] = useState(null);
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [compareSet, setCompareSet] = useState([]);

  const navigate = (p, data = null) => {
    if (p === 'course') setSelectedCourse(data);
    setPage(p);
  };

  const logout = () => {
    setUser(null);
    setPage('main');
  };

  const props = { navigate, user, setUser, logout, selectedCourse, setSelectedCourse, compareSet, setCompareSet };

  return (
    <div className="app">
      {page === 'main' && <MainPage {...props} />}
      {page === 'login' && <LoginPage {...props} />}
      {page === 'register' && <RegisterPage {...props} />}
      {page === 'course' && <CoursePage {...props} />}
      {page === 'settings' && <SettingsPage {...props} />}
      {page === 'compare' && <ComparePage {...props} />}
    </div>
  );
}
