import json
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import producto, usuario

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
