from rest_framework import serializers
from .models import usuario, roles, categoria, producto, oferta, favorito, transaccion, historial, SolicitudIntercambio, carrera
        
class carreraSerializer(serializers.ModelSerializer):
    class Meta:
        model = carrera
        fields = '__all__'        

class usuarioSerializer(serializers.ModelSerializer):
    carrera = serializers.StringRelatedField()
    class Meta:
        model = usuario
        fields = '__all__'

class rolesSerializer(serializers.ModelSerializer):
    class Meta:
        model = roles
        fields = '__all__'
        
class categoriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = categoria
        fields = '__all__'

class productoSerializer(serializers.ModelSerializer):
    usuarios = serializers.StringRelatedField()
    class Meta:
        model = producto
        fields = '__all__'

class ofertaSerializer(serializers.ModelSerializer):
    class Meta:
        model = oferta
        fields = '__all__'

class favoritoSerializer(serializers.ModelSerializer):
    class Meta:
        model = favorito
        fields = '__all__'

class transaccionSerializer(serializers.ModelSerializer):
    class Meta:
        model = transaccion
        fields = '__all__'
        
class historialSerializer(serializers.ModelSerializer):
    class Meta:
        model = historial
        fields = '__all__'
        
class SolicitudIntercambioSerializer(serializers.ModelSerializer):
    solicitante = serializers.StringRelatedField()
    propietario = serializers.StringRelatedField()
    producto_solicitado = serializers.StringRelatedField()
    producto_ofrecido = serializers.StringRelatedField()

    class Meta:
        model = SolicitudIntercambio
        fields = '__all__'