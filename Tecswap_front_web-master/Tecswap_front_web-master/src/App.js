import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddProducto from "./productos/AddProducto";
import EditProducto from './productos/EditProducto';
import ViewProducto from "./productos/ViewProducto";
import MisProductos from './pages/MisProductos'; 
import SobreNosotros from './pages/SobreNosotros';
import OfrecerProducto from './pages/OfrecerProducto';
import Footer from './components/Footer';


function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<Home/>}/>
          <Route exact path="/addproducto" element={<AddProducto/>}/>
          <Route exact path="/editproducto/:id" element={<EditProducto/>}/>
          <Route exact path="/viewproducto/:id" element={<ViewProducto />} />
          <Route exact path="/misproductos" element={<MisProductos />} /> 
          <Route exact path="/sobre" element={<SobreNosotros />} />
          <Route exact path="/ofrecer-producto" element={<OfrecerProducto />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
