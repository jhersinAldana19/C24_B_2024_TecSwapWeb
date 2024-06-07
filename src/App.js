import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import AddProducto from './pages/AddProducto';
import EditProducto from './pages/EditProducto';
import ViewProducto from './pages/ViewProducto';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import ProtectedRoute from './components/ProtectedRoute';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addproducto" element={<AddProducto />} />
          <Route exact path="/editproducto/:id" element={<ProtectedRoute><EditProducto /></ProtectedRoute>} />
          <Route exact path="/viewproducto/:id" element={<ProtectedRoute><ViewProducto /></ProtectedRoute>} />
          <Route exact path="/login" element={<Login />} />
          <Route exact path="/register" element={<Register />} />
          <Route exact path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
