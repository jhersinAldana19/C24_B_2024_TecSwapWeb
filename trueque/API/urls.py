from django.urls import path,include
from rest_framework.routers import DefaultRouter
from . import views
from .views import usuarioViewSet, rolesViewSet, categoriaViewSet, productoViewSet, ofertaViewSet, favoritoViewSet, transaccionViewSet, historialViewSet, SolicitudIntercambioViewSet, carreraViewSet, delete_usuario

router = DefaultRouter()
router.register(r'usuarios', usuarioViewSet)
router.register(r'roles', rolesViewSet)
router.register(r'categorias', categoriaViewSet)
router.register(r'productos', productoViewSet)
router.register(r'ofertas', ofertaViewSet)
router.register(r'favoritos', favoritoViewSet)
router.register(r'transacciones', transaccionViewSet)
router.register(r'historiales', historialViewSet)
router.register(r'solicitudes', SolicitudIntercambioViewSet)
router.register(r'carrera', carreraViewSet)

urlpatterns = [
    path('productos/usuario/<int:usuario_id>/', views.get_productos_by_usuario, name='get_productos_by_usuario'),
    path('', include(router.urls)),
    path('auth/login/', views.login_view, name='login'),  # Ruta para login
    path('auth/register/', views.register_view, name='register'),  # Ruta para registro
    path('create_categoria/', views.create_categoria, name='create_categoria'),
    path('create_carrera/', views.create_carrera, name='create_carrera'),
    path('usuarios/delete/<int:pk>/', delete_usuario, name='delete_usuario'),
]