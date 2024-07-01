from rest_framework import viewsets
from .models import usuario, roles, categoria, producto, oferta, favorito, transaccion, historial, SolicitudIntercambio, carrera
from .serializer import usuarioSerializer, rolesSerializer, categoriaSerializer, productoSerializer, ofertaSerializer, favoritoSerializer, transaccionSerializer, historialSerializer, SolicitudIntercambioSerializer, carreraSerializer
import json
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import IsAuthenticated
from django.contrib.auth import authenticate, login
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.contrib.auth.hashers import check_password
from rest_framework.parsers import MultiPartParser, FormParser
from django.contrib.auth.hashers import make_password, check_password
from django.views.decorators.http import require_http_methods

@csrf_exempt
# Create your views here.

@api_view(['DELETE'])
def delete_usuario(request, pk):
    try:
        user = usuario.objects.get(pk=pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
    except usuario.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)
    except Exception as e:
        return Response(status=status.HTTP_500_INTERNAL_SERVER_ERROR)

@api_view(['POST'])
def create_categoria(request):
    serializer = categoriaSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=201)
    return Response(serializer.errors, status=400)

@api_view(['POST'])
def create_carrera(request):
    serializer = carreraSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=201)
    return Response(serializer.errors, status=400)

@api_view(['POST'])
def login_view(request):
    data = request.data
    email = data.get('email')
    password = data.get('password')
    try:
        user = usuario.objects.get(email=email)
        if check_password(password, user.password):
            user_data = usuarioSerializer(user).data
            user_data['isAdmin'] = user.roles.tipo == 'administrador'
            return JsonResponse(user_data)
        else:
            return JsonResponse({'error': 'Invalid credentials'}, status=400)
    except usuario.DoesNotExist:
        return JsonResponse({'error': 'Invalid credentials'}, status=400)

@api_view(['POST'])
def register_view(request):
    data = request.data
    file = request.FILES.get('imagen')  # Obtiene la imagen del archivo subido
    data['password'] = make_password(data['password'])  # Encripta la contraseña

    # Crea una instancia de usuario sin guardar en la base de datos aún
    user = usuario(
        name=data.get('name'),
        lastname=data.get('lastname'),
        phone=data.get('phone'),
        email=data.get('email'),
        password=data.get('password'),
        carrera_id=data.get('carrera'),
        imagen=file  # Asigna la imagen subida
    )

    # Asigna el rol de usuario por defecto
    try:
        role = roles.objects.get(tipo='administrador')
    except roles.DoesNotExist:
        return JsonResponse({'error': 'El rol especificado no existe.'}, status=400)
    
    user.roles = role

    # Guarda el usuario en la base de datos
    try:
        user.save()
        return JsonResponse(usuarioSerializer(user).data, status=201)
    except Exception as e:
        return JsonResponse({'error': str(e)}, status=400)
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
    
class carreraViewSet(viewsets.ModelViewSet):
    queryset = carrera.objects.all()
    serializer_class = carreraSerializer