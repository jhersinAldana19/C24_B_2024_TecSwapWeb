from django.urls import path,include
from rest_framework.routers import DefaultRouter
from . import views
from .views import usuarioViewSet, rolesViewSet, categoriaViewSet, productoViewSet, ofertaViewSet, favoritoViewSet, transaccionViewSet, historialViewSet, SolicitudIntercambioViewSet, carreraViewSet

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
]