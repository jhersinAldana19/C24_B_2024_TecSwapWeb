import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import AddProducto from './productos/AddProducto';
import EditProducto from './productos/EditProducto';
import ViewProducto from './productos/ViewProducto';
import SobreNosotros from './pages/SobreNosotros';
import MisProductos from './pages/MisProductos'; 


function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Navbar />
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route exact path="/addproducto" element={<AddProducto />} />
                    <Route exact path="/editproducto/:id" element={<EditProducto />} />
                    <Route exact path="/viewproducto/:id" element={<ViewProducto />} />
                    <Route exact path="/sobrenosotros" element={<SobreNosotros />} />
                    <Route exact path="/misproductos" element={<MisProductos />} /> 
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
