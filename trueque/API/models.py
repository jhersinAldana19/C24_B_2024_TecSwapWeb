from django.db import models
from django.conf import settings
from django.core.exceptions import ValidationError
# Create your models here.

class carrera(models.Model):
    carrera=models.CharField(max_length=150)
    def __str__(self):
        return f"{self.carrera}"
class roles(models.Model):
    ESTADO_CHOICES = [
        ('usuario', 'Usuario'),
        ('administrador', 'Administrar'),
    ]

    tipo = models.CharField(
        max_length=100,
        choices=ESTADO_CHOICES,
        default='usuario',  # Valor predeterminado
    )
    
    def __str__(self):
        return f"{self.tipo}"

class usuario(models.Model):
    name = models.CharField(max_length=100)
    lastname = models.CharField(max_length=100)
    phone = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    carrera = models.ForeignKey(carrera, on_delete=models.CASCADE)
    roles = models.ForeignKey(roles, on_delete=models.CASCADE)
    imagen = models.ImageField(upload_to='imagenes/',verbose_name='Imagen',null=True)
    def __str__(self):
        return f"{self.name} - {self.lastname}"
    
    def delete(self, using=None, keep_parents=False):
        self.imagen.delete(self.imagen.name)
        super().delete()

class categoria(models.Model):
    tipo = models.CharField(max_length=100)
    def __str__(self):
        return f"{self.tipo}"
    

def validate_positive_number(value):
    if value <= 0:
        raise ValidationError("El valor debe ser un número positivo.")
    
class producto(models.Model):
    titulo = models.CharField(max_length=100)
    descripcion = models.CharField(max_length=100)
    cantidad = models.PositiveIntegerField(validators=[validate_positive_number])
    categoria = models.ForeignKey(categoria, on_delete=models.CASCADE)
    usuarios = models.ForeignKey(usuario, on_delete=models.CASCADE)
    imagen = models.ImageField(upload_to='imagenes/', verbose_name='Imagen', null=True)
    ESTADO_CHOICES = [
        ('pendiente', 'Pendiente'),
        ('cancelado', 'Cancelado'),
    ]

    estado = models.CharField(
        max_length=20,
        choices=ESTADO_CHOICES,
        default='pendiente',  # Valor predeterminado
    )
    
    def __str__(self):
        return f"{self.titulo} - {self.estado}"
    
    def delete(self, using=None, keep_parents=False):
        self.imagen.delete(self.imagen.name)
        super().delete()


class oferta(models.Model):
    usuarios = models.ForeignKey(usuario, on_delete=models.CASCADE)
    producto = models.ForeignKey(producto, on_delete=models.CASCADE)
    fecha = models.DateTimeField(auto_now_add=True)
    def __str__(self):
        return f"{self.usuarios} - {self.producto}"
    
class favorito(models.Model):
    producto = models.ForeignKey(producto, on_delete=models.CASCADE)
    categoria = models.ForeignKey(categoria, on_delete=models.CASCADE)
    usuarios = models.ForeignKey(usuario, on_delete=models.CASCADE)
    def __str__(self):
        return f"{self.usuarios} - {self.producto} - {self.categoria}"
    
class transaccion(models.Model):
    fecha_hora = models.DateTimeField(auto_now_add=True)
    detalles = models.CharField(max_length=100)
    usuarios = models.ForeignKey(usuario, on_delete=models.CASCADE)
    producto = models.ForeignKey(producto, on_delete=models.CASCADE)
    def __str__(self):
        return f"{self.usuarios} - {self.detalles} - {self.fecha_hora}"

class historial(models.Model):
    fecha_compra = models.DateTimeField(auto_now_add=True)
    transaccion = models.ForeignKey(transaccion, on_delete=models.CASCADE)
    def __str__(self):
        return f"{self.transaccion} - {self.fecha_compra}"

class SolicitudIntercambio(models.Model):
    producto_solicitado = models.ForeignKey('producto', related_name='producto_solicitado', on_delete=models.CASCADE)
    producto_ofrecido = models.ForeignKey('producto', related_name='producto_ofrecido', on_delete=models.CASCADE)
    solicitante = models.ForeignKey('usuario', related_name='solicitante', on_delete=models.CASCADE)
    propietario = models.ForeignKey('usuario', related_name='propietario', on_delete=models.CASCADE)
    aceptada = models.BooleanField(default=False)
    respondida = models.BooleanField(default=False)
    fecha = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"Solicitud de {self.solicitante} a {self.propietario} por {self.producto_solicitado} con {self.producto_ofrecido}"