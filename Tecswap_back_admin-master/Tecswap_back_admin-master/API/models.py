from django.db import models
from django.core.exceptions import ValidationError
# Create your models here.

class usuario(models.Model):
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=100)
    telefono = models.CharField(max_length=100)
    correo = models.CharField(max_length=100)
    contraseña = models.CharField(max_length=100)
    carrera = models.CharField(max_length=100)
    imagen = models.ImageField(upload_to='imagenes/',verbose_name='Imagen',null=True)
    def __str__(self):
        return f"{self.nombre} - {self.apellido}"
    
    def delete(self, using=None, keep_parents=False):
        self.imagen.delete(self.imagen.name)
        super().delete()
    
class roles(models.Model):
    usuarios = models.ForeignKey(usuario, on_delete=models.CASCADE)
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
        return f"{self.tipo} - {self.usuarios}"
    
    
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
    imagen = models.ImageField(upload_to='imagenes/',verbose_name='Imagen',null=True)
    ESTADO_CHOICES = [
        ('pendiente', 'Pendiente'),
        ('reservado', 'Reservado'),
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