from django.urls import path
from . import views

urlpatterns = [
    path('productos/usuario/<int:usuario_id>/', views.get_productos_by_usuario, name='get_productos_by_usuario'),
    # Otras rutas...
]