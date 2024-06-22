from rest_framework import viewsets
from .models import usuario, roles, categoria, producto, oferta, favorito, transaccion, historial, SolicitudIntercambio
from .serializer import usuarioSerializer, rolesSerializer, categoriaSerializer, productoSerializer, ofertaSerializer, favoritoSerializer, transaccionSerializer, historialSerializer, SolicitudIntercambioSerializer
import json
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import IsAuthenticated

@csrf_exempt
# Create your views here.

def create_producto(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        usuario_id = data.get('usuarios_id')
        usuario = usuario.objects.get(id=usuario_id)
        producto = producto.objects.create(
            titulo=data['titulo'],
            descripcion=data['descripcion'],
            cantidad=data['cantidad'],
            categoria_id=data['categoria_id'],
            usuarios=usuario,
            estado=data['estado'],
            imagen=data.get('imagen')
        )
        return JsonResponse({'message': 'Producto creado exitosamente', 'producto_id': producto.id})
    else:
        return JsonResponse({'error': 'Método no permitido'}, status=405)
    from django.shortcuts import render

@csrf_exempt
def get_productos_by_usuario(request, usuario_id):
    if request.method == 'GET':
        try:
            usuario = usuario.objects.get(id=usuario_id)
            productos = producto.objects.filter(usuarios=usuario)
            productos_list = [
                {
                    'id': prod.id,
                    'titulo': prod.titulo,
                    'descripcion': prod.descripcion,
                    'cantidad': prod.cantidad,
                    'categoria': prod.categoria.tipo,
                    'estado': prod.estado,
                    'imagen': prod.imagen.url if prod.imagen else None
                }
                for prod in productos
            ]
            return JsonResponse({'productos': productos_list})
        except usuario.DoesNotExist:
            return JsonResponse({'error': 'Usuario no encontrado'}, status=404)
    else:
        return JsonResponse({'error': 'Método no permitido'}, status=405)

class usuarioViewSet(viewsets.ModelViewSet):
    queryset = usuario.objects.all()
    serializer_class = usuarioSerializer

class rolesViewSet(viewsets.ModelViewSet):
    queryset = roles.objects.all()
    serializer_class = rolesSerializer

class categoriaViewSet(viewsets.ModelViewSet):
    queryset = categoria.objects.all()
    serializer_class = categoriaSerializer

class productoViewSet(viewsets.ModelViewSet):
    queryset = producto.objects.all()
    serializer_class = productoSerializer

class ofertaViewSet(viewsets.ModelViewSet):
    queryset = oferta.objects.all()
    serializer_class = ofertaSerializer

class favoritoViewSet(viewsets.ModelViewSet):
    queryset = favorito.objects.all()
    serializer_class = favoritoSerializer

class transaccionViewSet(viewsets.ModelViewSet):
    queryset = transaccion.objects.all()
    serializer_class = transaccionSerializer

class historialViewSet(viewsets.ModelViewSet):
    queryset = historial.objects.all()
    serializer_class = historialSerializer

class SolicitudIntercambioViewSet(viewsets.ModelViewSet):
    queryset = SolicitudIntercambio.objects.all()
    serializer_class = SolicitudIntercambioSerializer