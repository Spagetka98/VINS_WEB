import React from "react";
import { Routes, Route } from "react-router-dom";

import LandingPage from "./pages/landingPage";
import DashboardPage from "./pages/dashboardPage";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/dashboard/:token" element={<DashboardPage />} />
      <Route path="*" element={<LandingPage />} />
    </Routes>
  );
};

export default App;
